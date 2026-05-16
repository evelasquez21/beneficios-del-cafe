package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorRegDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorVistaDTO;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private BitacoraAgService bitacoraAgService;

    public String saveAgricultor(AgricultorRegDTO agricultor){
        Persona nuevaPersona;
        Agricultor nuevoAgricultor;

        // Validaciones de repetición - (actualización de datos)
        nuevaPersona = personaRepo.findById(agricultor.cui()).orElse(new Persona());
        nuevoAgricultor = agricultorRepo.findById(agricultor.id()).orElse(new Agricultor());
        agricultorRepo.findByNit(agricultor.nit()).orElseThrow(() -> new RuntimeException("Ya existe ese nit registrado"));

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
        bitacoraAgService.registrarAccion("Registró un nuevo agricultor con NIT: " + nuevoAgricultor.getNit());

        return "Agricultor registrado";
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

    public List<AgricultorVistaDTO> getAgricultores(){
        List<Agricultor> agricultores = agricultorRepo.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AgricultorVistaDTO> dtos = agricultores.stream()
                .map(agricultor -> new AgricultorVistaDTO(
                        agricultor.getId(),
                        nitFormatter(agricultor.getNit()),
                        agricultor.getPersona().getCui(),
                        agricultor.getPersona().getNombreCompleto(),
                        agricultor.getPersona().getEstado(),
                        agricultor.getFechaCreacion().format(formatter)

                )).collect(Collectors.toList());
        return dtos;
    }

    private String nitFormatter(Long nit) {
        if (nit == null) return "";

        String nitStr = nit.toString();

        if (nitStr.length() <= 1) {
            return nitStr;
        }

        String cuerpo = nitStr.substring(0, nitStr.length() - 1);
        String digito = nitStr.substring(nitStr.length() - 1);

        return cuerpo + "-" + digito;
    }
}
