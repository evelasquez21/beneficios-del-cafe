package gt.umg.progra3.cafecito.service.beneficio;

import gt.umg.progra3.cafecito.DTO.agricultor.ObsAgricultorDTO;
import gt.umg.progra3.cafecito.DTO.beneficio.ObservacionesAgDTO;
import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import gt.umg.progra3.cafecito.model.agricultor.ObservacionAgricultor;
import gt.umg.progra3.cafecito.model.agricultor.ObservacionesAgricultor;
import gt.umg.progra3.cafecito.projection.AgricultorDetalleProjection;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorObsRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorObssRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetAgricultorService {

    @Autowired
    private BitacoraBenService bitacoraBenService;

    @Autowired
    private AgricultorRepository agricultorRepo;

    @Autowired
    private AgricultorObsRepository agricultorObsRepo;

    @Autowired
    private AgricultorObssRepository agricultorObssRepo;


    public List<AgricultorDetalleProjection> getListAgricultor(){
        return agricultorRepo.obtenerListadoAgricultores();
    }

    public String addObservation(ObsAgricultorDTO observacion){
        ObservacionesAgricultor observaciones;
        ObservacionAgricultor observacionA;

        // Validaciones de repetición - (actualización de datos)
        observaciones = agricultorObssRepo.findById(observacion.idObssAgri()).orElse(new ObservacionesAgricultor());
        observacionA = agricultorObsRepo.findById(observacion.idObsAgri()).orElse(new ObservacionAgricultor());

        // Guardar observación
        observacionA.setDescripcion(observacion.descripcion());

        Agricultor asignarAgricultor = agricultorRepo.findById(observacion.idAgricultor())
                .orElseThrow(() -> new RuntimeException("El agricultor asignado no existe"));

        ObservacionAgricultor nuevaObservacion = agricultorObsRepo.save(observacionA);

        observaciones.setAgricultor(asignarAgricultor);
        observaciones.setObsAgri(nuevaObservacion);
        observaciones.setFechaObservacion(LocalDateTime.now());

        agricultorObssRepo.save(observaciones);

        bitacoraBenService.registrarAccion("Agregó una nueva observación al agricultor con ID: " + asignarAgricultor.getId());

        return "Observación creada";
    }

    public List<ObservacionesAgDTO> getObservacionesAgricultor(Long idAgricultor){

        List<ObservacionesAgricultor> observaciones = agricultorObssRepo.findByAgricultorId(idAgricultor);

        return observaciones.stream().map(ag ->{
            ObservacionesAgDTO observacionesDTO = new ObservacionesAgDTO(
                    ag.getId(),
                    ag.getAgricultor().getId(),
                    ag.getObsAgri().getId(),
                    ag.getObsAgri().getDescripcion(),
                    ag.getFechaObservacion());
            return observacionesDTO;
        }).collect(Collectors.toList());

    }
}
