package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteRegDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.TransportistaRegDTO;
import gt.umg.progra3.cafecito.model.Persona;
import gt.umg.progra3.cafecito.model.agricultor.Transportista;
import gt.umg.progra3.cafecito.projection.TransportistaDetalleProjection;
import gt.umg.progra3.cafecito.repository.PersonaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportistaService {

    @Autowired
    private TransportistaRepository transportistaRepo;

    @Autowired
    private BitacoraAgService bitacoraAgService;

    @Autowired
    private PersonaRepository personaRepo;

    public String saveTransportista(TransportistaRegDTO transportista){
        Persona nuevaPersona = new Persona();
        Transportista nuevoTransportista = new Transportista();

        // Validaciones de repetición
        if (transportistaRepo.existsById(transportista.id())){
            throw new RuntimeException("Ya existe un transportista con este ID");
        } else if (personaRepo.existsById(transportista.cui())) {
            throw new RuntimeException("Ya existe un transportista con este CUI");
        }

        // Guardar persona
        nuevaPersona.setCui(transportista.cui());
        nuevaPersona.setNombreCompleto(transportista.nombreCompleto());
        nuevaPersona.setEstado(1);

        Persona personaGuardada = personaRepo.save(nuevaPersona);

        // Guardar Transportista
        nuevoTransportista.setPersona(personaGuardada);
        nuevoTransportista.setTipoLicencia(transportista.tipoLicencia());
        nuevoTransportista.setFechaVenLicencia(transportista.fechaVenLicencia());

        Transportista transportistaGuardado = transportistaRepo.save(nuevoTransportista);

        bitacoraAgService.registrarAccion("Registró un nuevo transportista con CUI: " + transportistaGuardado.getId());

        return "Transportista creado";
    }

    public String updateTransportista(TransportistaRegDTO transportista){
        // Validación de registro existente con ID
        Transportista transportistaEx = transportistaRepo.findById(transportista.id())
                .orElseThrow(() -> new RuntimeException("No existe transportista  con ese ID"));

        if (!transportistaEx.getPersona().getCui().equals(transportista.cui())) {
            throw new RuntimeException("No coincide el CUI ingresado con el registrado");
        }

        Persona nuevaPersona = new Persona();

        // Guardar persona
        nuevaPersona.setCui(transportista.cui());
        nuevaPersona.setNombreCompleto(transportista.nombreCompleto());
        nuevaPersona.setEstado(1);

        Persona personaGuardada = personaRepo.save(nuevaPersona);

        // Actualizar transportista
        transportistaEx.setPersona(personaGuardada);
        transportistaEx.setTipoLicencia(transportista.tipoLicencia());
        transportistaEx.setFechaVenLicencia(transportista.fechaVenLicencia());

        transportistaRepo.save(transportistaEx);

        bitacoraAgService.registrarAccion("Actualizó parametros del transportista con ID: " + transportistaEx.getId());

        return "Transportista modificado";
    }

    public String deleteTransportista(Long  id){
        // Validación de registro existente con ID
        Transportista transportistaEx = transportistaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe transportista  con ese ID"));

        Persona persona = personaRepo.findById(transportistaEx.getPersona().getCui())
                .orElseThrow(() -> new RuntimeException("No existe transportista con ese CUI"));

        Persona personaEx = new Persona();

        personaEx.setCui(persona.getCui());
        personaEx.setNombreCompleto(persona.getNombreCompleto());
        personaEx.setEstado(2);

        personaRepo.save(personaEx);

        bitacoraAgService.registrarAccion("Eliminó transportista con ID: " + transportistaEx.getId());

        return "Transportista eliminado";
    }

    public List<TransportistaDetalleProjection> getListaTransportistas(){
        return transportistaRepo.obtenerDetalleTransportistas();
    }
}
