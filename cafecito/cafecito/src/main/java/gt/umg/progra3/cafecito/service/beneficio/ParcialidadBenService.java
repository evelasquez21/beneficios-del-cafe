package gt.umg.progra3.cafecito.service.beneficio;

import gt.umg.progra3.cafecito.DTO.beneficio.ParcialidadBenDTO;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.beneficio.Cuenta;
import gt.umg.progra3.cafecito.model.beneficio.ParcialidadBen;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.beneficio.CuentaRepository;
import gt.umg.progra3.cafecito.repository.beneficio.ParcialidadBenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;

@Service
public class ParcialidadBenService {

    @Autowired
    private ParcialidadBenRepository parcialidadBenRepo;

    @Autowired
    private CuentaRepository cuentaRepo;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepo;

    @Autowired
    private BitacoraBenService bitacoraBenService;

    public String aceptarParcialidad(ParcialidadBenDTO parcialidad){
        ParcialidadBen parcialidadBen = new ParcialidadBen();

        if (parcialidadBenRepo.existsById(parcialidad.id())){
            throw new RuntimeException("ParcialidadBen ya fue aceptada");
        }

        Cuenta cuenta = cuentaRepo.findByIdPesajeAgricultor(parcialidad.idPesajeMaestro())
                        .orElseThrow(() -> new RuntimeException("No existe ID de pesaje maestro"));
        parcialidadBen.setId(parcialidad.id());
        parcialidadBen.setCuenta(cuenta);
        parcialidadBen.setPeso(parcialidad.peso());
        parcialidadBen.setTransporte(parcialidad.transportePlaca());
        parcialidadBen.setIdTransportista(parcialidad.idTransportista());
        parcialidadBen.setFechaRecepcion(LocalDateTime.now());

        // Búsquedda de unidad de medida
        UnidadMedida unidadMedida = unidadMedidaRepo.findById(parcialidad.unidadMedida())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));
        parcialidadBen.setUnidadMedida(unidadMedida);

        parcialidadBenRepo.save(parcialidadBen);

        // Construcción de DTO para Agricultor
        ParcialidadBenDTO parcialiidadAg = new ParcialidadBenDTO(
                parcialidad.id(),
                parcialidadBen.getCuenta().getId(),
                parcialidad.peso(),
                parcialidad.transportePlaca(),
                parcialidad.idTransportista(),
                parcialidad.idPesajeMaestro(),
                parcialidad.unidadMedida(),
                parcialidad.fechaEntrega()
        );

        String urlAgricultor = "http://localhost:8080/agricultor/parcialidades/assign";

        bitacoraBenService.registrarAccion("Aceptó integridad de parcialidad con ID: " + parcialidad.id());

        // aperturar la cuenta
        cuentaService.validarEstadoCuenta(parcialidadBen.getCuenta().getId());

        try {
            HttpServletRequest requestOriginal = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = requestOriginal.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (token != null) {
                headers.set("Authorization", token);
            }

            HttpEntity<?> requestEntity = new HttpEntity<>(parcialiidadAg, headers);

            restTemplate.exchange(urlAgricultor, HttpMethod.PUT, requestEntity, Void.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "Parcialidad aceptada";
    }
}
