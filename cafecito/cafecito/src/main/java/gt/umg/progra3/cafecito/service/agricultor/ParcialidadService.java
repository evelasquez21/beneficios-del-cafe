package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.ParcialidadRegDTO;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.agricultor.Parcialidad;
import gt.umg.progra3.cafecito.model.agricultor.PesajeMaestro;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import gt.umg.progra3.cafecito.model.agricultor.Transportista;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.ParcialidadRepository;
import gt.umg.progra3.cafecito.repository.agricultor.PesajeMaestroRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping
    public String saveParcialidad(ParcialidadRegDTO parcialidad){
        Parcialidad nuevaparcialidad;

        nuevaparcialidad = parcialidadRepo.findById(parcialidad.id())
                .orElse(new Parcialidad());

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

        return "Se ha registrado parcialidad";
    }
}
