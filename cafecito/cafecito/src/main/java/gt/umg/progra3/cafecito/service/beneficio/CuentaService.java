package gt.umg.progra3.cafecito.service.beneficio;

import gt.umg.progra3.cafecito.DTO.beneficio.CuentaRequest;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.agricultor.EstadoPesajeMaestro;
import gt.umg.progra3.cafecito.model.beneficio.Cuenta;
import gt.umg.progra3.cafecito.model.beneficio.EstadoCuenta;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.beneficio.CuentaRepository;
import gt.umg.progra3.cafecito.repository.beneficio.EstadoCuentaRepo;
import gt.umg.progra3.cafecito.repository.beneficio.ParcialidadBenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepo;

    @Autowired
    private EstadoCuentaRepo estadoCuentaRepo;

    @Autowired
    private ParcialidadBenRepository parcialidadBenRepo;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepo;

    @Autowired
    private RestTemplate restTemplate;

    public void saveCuentaAgricultorScheme(CuentaRequest cuenta){
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setIdPesajeAgricultor(cuenta.getIdPesajeAgricultor());
        nuevaCuenta.setNitAgricultor(cuenta.getNitAgricultor());
        nuevaCuenta.setPesoTotalEsperado(cuenta.getPesoTotalEsperado());

        // Búsquedda de unidad de medida
        UnidadMedida unidadMedida = unidadMedidaRepo.findById(cuenta.getUnidadMedida())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));
        nuevaCuenta.setUnidadMedida(unidadMedida);

        // Búsquedda de estados de cuentas
        EstadoCuenta estadoCuenta = estadoCuentaRepo.findById(1)
                .orElseThrow(() -> new RuntimeException("No existe el estado"));

        nuevaCuenta.setTolerancia(0);
        nuevaCuenta.setDiferenciaTotal(0);
        nuevaCuenta.setEstado(estadoCuenta);
        nuevaCuenta.setFechaCreacion(cuenta.getFechaCreacion());
        nuevaCuenta.setOrigen(cuenta.getOrigen());

        cuentaRepo.save(nuevaCuenta);
    }

    @Transactional
    public void validarEstadoCuenta(Long noCuenta){
        int cantParcialesE = 0;

        Long cantParcialesA = parcialidadBenRepo.countByCuentaId(noCuenta);

        Cuenta cuenta = cuentaRepo.findById(noCuenta)
                .orElseThrow(() -> new RuntimeException("No existe el número de cuenta"));

        // Búsquedda de estados de cuentas
        EstadoCuenta estadoCuenta = estadoCuentaRepo.findById(2)
                .orElseThrow(() -> new RuntimeException("No existe el estado"));

        String urlPesajeMaestroAg = "http://localhost:8080/agricultor/pesaje-maestro/cant-par/" + cuenta.getIdPesajeAgricultor();

        try {
            HttpServletRequest requestOriginal = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = requestOriginal.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (token != null) {
                headers.set("Authorization", token);
            }

            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Map<String, Integer>> response = restTemplate.exchange(
                    urlPesajeMaestroAg,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Map<String, Integer>>() {}
            );

            Map<String, Integer> body = response.getBody();

            if (body != null && body.containsKey("cantP")) {
                cantParcialesE = body.get("cantP");
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (cuenta.getEstado().getId() != estadoCuenta.getId()){
            cuenta.setEstado(estadoCuenta);
        } else if (cantParcialesE >= cantParcialesA) {
            EstadoCuenta estadoCuentaCerrada = estadoCuentaRepo.findById(3)
                    .orElseThrow(() -> new RuntimeException("No existe el estado"));

            cuenta.setEstado(estadoCuentaCerrada);
        }
    }
}
