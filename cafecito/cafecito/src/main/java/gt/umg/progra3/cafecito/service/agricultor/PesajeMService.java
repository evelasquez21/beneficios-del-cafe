package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.PesajeMRegDTO;
import gt.umg.progra3.cafecito.DTO.beneficio.CuentaRequest;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import gt.umg.progra3.cafecito.model.agricultor.EstadoPesajeMaestro;
import gt.umg.progra3.cafecito.model.agricultor.PesajeMaestro;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorRepository;
import gt.umg.progra3.cafecito.repository.agricultor.EstadoPesajeMRepository;
import gt.umg.progra3.cafecito.repository.agricultor.PesajeMaestroRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Service
public class PesajeMService {

    @Autowired
    private PesajeMaestroRepository pesajeMRepo;

    @Autowired
    private EstadoPesajeMRepository estadoPesajeMRepo;

    @Autowired
    private AgricultorRepository agricultorRepo;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepo;

    @Autowired
    private BitacoraAgService bitacoraAgService;

    @Autowired
    private RestTemplate restTemplate;

    public String savePesajeMaestro(PesajeMRegDTO pesajeMaestro){
        PesajeMaestro nuevoPesajeM = new PesajeMaestro();

        if (pesajeMRepo.existsById(pesajeMaestro.id())){
            throw new RuntimeException("Ya existe un pesaje maestro con ese ID");
        }

        nuevoPesajeM.setCantParcialidades(pesajeMaestro.cantParcialidades());
        nuevoPesajeM.setPesoTotal(pesajeMaestro.pesoTotal());

        // Validación de nit existente de agricultor
        Agricultor agricultor = agricultorRepo.findByNit(pesajeMaestro.nitAgricultor())
                .orElseThrow(() -> new RuntimeException(("Nit de agricultor no válido")));

        nuevoPesajeM.setAgricultor(agricultor);

        // Búsquedda de estados de pesaje maestro
        EstadoPesajeMaestro estadoPesajeM = estadoPesajeMRepo.findById(pesajeMaestro.estado())
                .orElseThrow(() -> new RuntimeException("No existe el estado"));

        nuevoPesajeM.setEstado(estadoPesajeM);

        // Búsquedda de unidad de medida
        UnidadMedida  unidadMedida = unidadMedidaRepo.findById(pesajeMaestro.unidadMedida())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));

        nuevoPesajeM.setUnidadMedida(unidadMedida);
        nuevoPesajeM.setFechaCreacion(LocalDateTime.now());

        PesajeMaestro pesajeMGuardado = pesajeMRepo.save(nuevoPesajeM);

        bitacoraAgService.registrarAccion("Registró un nuevo pesaje maestro con ID: " + pesajeMGuardado.getId());

        CuentaRequest cuentaBeneficio = new CuentaRequest();
        cuentaBeneficio.setIdPesajeAgricultor(pesajeMGuardado.getId());
        cuentaBeneficio.setNitAgricultor(pesajeMaestro.nitAgricultor());
        cuentaBeneficio.setOrigen("Esquema agricultor");
        cuentaBeneficio.setPesoTotalEsperado(pesajeMaestro.pesoTotal());
        cuentaBeneficio.setFechaCreacion(LocalDateTime.now());
        cuentaBeneficio.setUnidadMedida(pesajeMaestro.unidadMedida());


        String urlBeneficio = "http://localhost:8080/beneficio/cuentas/";

        try {
            HttpServletRequest requestOriginal = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = requestOriginal.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (token != null) {
                headers.set("Authorization", token);
            }

            HttpEntity<?> requestEntity = new HttpEntity<>(cuentaBeneficio, headers);
            restTemplate.exchange(urlBeneficio, HttpMethod.POST, requestEntity, Void.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "Pesaje maestro registrado";
    }

    public String updatePesajeMaestro(PesajeMRegDTO pesajeMaestro){

        PesajeMaestro pesajeMEx = pesajeMRepo.findById(pesajeMaestro.id())
                .orElseThrow(() -> new RuntimeException("No existe pesaje maestro con ese ID"));

        pesajeMEx.setCantParcialidades(pesajeMaestro.cantParcialidades());
        pesajeMEx.setPesoTotal(pesajeMaestro.pesoTotal());

        // Búsquedda de estados de pesaje maestro
        EstadoPesajeMaestro estadoPesajeM = estadoPesajeMRepo.findById(pesajeMaestro.estado())
                .orElseThrow(() -> new RuntimeException("No existe el estado"));

        pesajeMEx.setEstado(estadoPesajeM);

        pesajeMRepo.save(pesajeMEx);

        bitacoraAgService.registrarAccion("Actualizó pesaje maestro con ID:  " + pesajeMEx.getId());

        return "Pesaje maestro modificado";
    }

    public String cancelarPesajeMaestro(Long id){

        PesajeMaestro pesajeMEx = pesajeMRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe pesaje maestro con ese ID"));

        // Búsquedda de estados de pesaje maestro
        EstadoPesajeMaestro estadoPesajeM = estadoPesajeMRepo.findById(5)
                .orElseThrow(() -> new RuntimeException("No existe el estado"));

        pesajeMEx.setEstado(estadoPesajeM);

        pesajeMRepo.save(pesajeMEx);

        bitacoraAgService.registrarAccion("Canceló el pesaje maestro con ID: " + pesajeMEx.getId());

        return "Pesaje maestro cancelado";
    }

    public int cantidadParcialidades(Long idPesajeM){
        PesajeMaestro pesajeM = pesajeMRepo.findById(idPesajeM)
                .orElseThrow(() -> new RuntimeException("No existe pesaje maestro con ese ID"));

        return pesajeM.getCantParcialidades();
    }
}
