package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.ParcialidadRegDTO;
import gt.umg.progra3.cafecito.DTO.beneficio.ParcialidadBenDTO;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.agricultor.Parcialidad;
import gt.umg.progra3.cafecito.model.agricultor.PesajeMaestro;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import gt.umg.progra3.cafecito.model.agricultor.Transportista;
import gt.umg.progra3.cafecito.projection.ParcialidadesProjection;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.ParcialidadRepository;
import gt.umg.progra3.cafecito.repository.agricultor.PesajeMaestroRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParcialidadService {

    @Autowired
    private ParcialidadRepository parcialidadRepo;

    @Autowired
    private TransporteRepository transporteRepo;

    @Autowired
    private TransportistaRepository transportistaRepo;

    @Autowired
    private PesajeMaestroRepository pesajeMRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepo;

    @Autowired
    private BitacoraAgService bitacoraAgService;


    public String saveParcialidad(ParcialidadRegDTO parcialidad){
        Parcialidad nuevaparcialidad = new Parcialidad();

        // Validaciones de repetición
        if (parcialidadRepo.existsById(parcialidad.id())){
            throw new RuntimeException("Ya existe una parcialidad con ese ID");
        }

        nuevaparcialidad.setNoCuenta(parcialidad.noCuenta());
        nuevaparcialidad.setPeso(parcialidad.peso());

        // Búsquedda de transporte por medio de la parcialidad
        Transporte transporte = transporteRepo.findById(parcialidad.transportePlaca())
                        .orElseThrow(() -> new RuntimeException("No existe transporte con esa placa registrado"));
        nuevaparcialidad.setTransporte(transporte);

        // Búsquedda de transporotista por medio de la parcialidad
        Transportista transportista = transportistaRepo.findById(parcialidad.idTransportista())
                        .orElseThrow(() -> new RuntimeException("No existe transportista con ese ID"));
        nuevaparcialidad.setTransportista(transportista);

        // Búsquedda de pesaje maestro por medio de la parcialidad
        PesajeMaestro pesajeMaestro = pesajeMRepository.findById(parcialidad.idPesajeMaestro())
                        .orElseThrow(() -> new RuntimeException("No existe pesaje maestro con ese ID"));
        nuevaparcialidad.setPesajeMaestro(pesajeMaestro);

        // Búsquedda de unidad de medida por medio de la parcialidad
        UnidadMedida  unidadMedida = unidadMedidaRepo.findById(parcialidad.unidadMedida())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));
        nuevaparcialidad.setUnidadMedida(unidadMedida);

        nuevaparcialidad.setFechaEnvio(LocalDateTime.now());

        Parcialidad parcialidadGuardada = parcialidadRepo.save(nuevaparcialidad);

        bitacoraAgService.registrarAccion("Registró nueva parcialidad con ID:" + parcialidadGuardada.getId());

        return "Se ha registrado parcialidad";
    }

    public String updateParcialidad(ParcialidadRegDTO parcialidad){
        Parcialidad parcialidadEx = parcialidadRepo.findById(parcialidad.id())
                .orElseThrow(() -> new RuntimeException("No existe parcialidad con ese ID"));

        parcialidadEx.setNoCuenta(parcialidad.noCuenta());
        parcialidadEx.setPeso(parcialidad.peso());

        // Búsquedda de transporte por medio de la parcialidad
        Transporte transporte = transporteRepo.findById(parcialidad.transportePlaca())
                .orElseThrow(() -> new RuntimeException("No existe transporte con esa placa registrado"));
        parcialidadEx.setTransporte(transporte);

        // Búsquedda de transporotista por medio de la parcialidad
        Transportista transportista = transportistaRepo.findById(parcialidad.idTransportista())
                .orElseThrow(() -> new RuntimeException("No existe transportista con ese ID"));
        parcialidadEx.setTransportista(transportista);

        // Búsquedda de pesaje maestro por medio de la parcialidad
        PesajeMaestro pesajeMaestro = pesajeMRepository.findById(parcialidad.idPesajeMaestro())
                .orElseThrow(() -> new RuntimeException("No existe pesaje maestro con ese ID"));
        parcialidadEx.setPesajeMaestro(pesajeMaestro);

        // Búsquedda de unidad de medida por medio de la parcialidad
        UnidadMedida  unidadMedida = unidadMedidaRepo.findById(parcialidad.unidadMedida())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));
        parcialidadEx.setUnidadMedida(unidadMedida);

        parcialidadRepo.save(parcialidadEx);

        bitacoraAgService.registrarAccion("Actualizó parametros de la parcialidad con ID: " + parcialidadEx.getId());

        return "Parcialidad modificada";
    }

    public List<ParcialidadesProjection> getParcialidades(){
        return parcialidadRepo.buscarParcialidades();
    }

    public void assignCuentaPar(ParcialidadBenDTO parcialidad){
        Parcialidad parcialidadEx = parcialidadRepo.findById(parcialidad.id())
                .orElseThrow(() -> new RuntimeException("No existe parcialidad con ese ID"));

        parcialidadEx.setNoCuenta(parcialidad.noCuenta());

        parcialidadRepo.save(parcialidadEx);
    }
}
