package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorRegDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.ObsAgricultorDTO;
import gt.umg.progra3.cafecito.model.Persona;
import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import gt.umg.progra3.cafecito.model.agricultor.ObservacionAgricultor;
import gt.umg.progra3.cafecito.model.agricultor.ObservacionesAgricultor;
import gt.umg.progra3.cafecito.repository.PersonaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorObsRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorObssRepository;
import gt.umg.progra3.cafecito.repository.agricultor.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgricultorService {

    @Autowired
    private AgricultorRepository agricultorRepo;

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private AgricultorObsRepository agricultorObsRepo;

    @Autowired
    private AgricultorObssRepository agricultorObssRepo;

    public String saveAgricultor(AgricultorRegDTO agricultor){
        Persona nuevaPersona;
        Agricultor nuevoAgricultor;

        // Validaciones de repetición - (actualización de datos)
        nuevaPersona = personaRepo.findById(agricultor.cui()).orElse(new Persona());
        nuevoAgricultor = agricultorRepo.findById(agricultor.id()).orElse(new Agricultor());

        // Guardar persona
        nuevaPersona.setCui(agricultor.cui());
        nuevaPersona.setNombreCompleto(agricultor.nombreCompleto());
        nuevaPersona.setEstado(1);

        Persona personaGuardada = personaRepo.save(nuevaPersona);

        // Guardar Agricultor
        nuevoAgricultor.setPersona(personaGuardada);
        nuevoAgricultor.setNit(agricultor.nit());
        nuevoAgricultor.setFechaCreacion(LocalDateTime.now());

        agricultorRepo.save(nuevoAgricultor);
        return "Agricultor registrado";
    }

    public List<Agricultor> findAllAgricultor(){
        return agricultorRepo.findAll();
    }

    public  String addObservation(ObsAgricultorDTO observacion){
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
        return "Observación creada";
    }
}
