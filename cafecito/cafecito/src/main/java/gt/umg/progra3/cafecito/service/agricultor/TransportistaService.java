package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransportistaRegDTO;
import gt.umg.progra3.cafecito.model.Persona;
import gt.umg.progra3.cafecito.model.agricultor.Transportista;
import gt.umg.progra3.cafecito.repository.PersonaRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportistaService {

    @Autowired
    private TransportistaRepository transportistaRepo;

    @Autowired
    private PersonaRepository personaRepo;

    public String saveTransportista(TransportistaRegDTO transportista){
        Persona nuevaPersona;
        Transportista nuevoTransportista;

        // Validaciones de repetición - (actualización de datos)
        nuevaPersona = personaRepo.findById(transportista.cui()).orElse(new Persona());
        nuevoTransportista = transportistaRepo.findById(transportista.id()).orElse(new Transportista());

        // Guardar persona
        nuevaPersona.setCui(transportista.cui());
        nuevaPersona.setNombreCompleto(transportista.nombreCompleto());
        nuevaPersona.setEstado(1);

        Persona personaGuardada = personaRepo.save(nuevaPersona);

        // Guardar Transportista
        nuevoTransportista.setPersona(personaGuardada);
        nuevoTransportista.setTipoLicencia(transportista.tipoLicencia());
        nuevoTransportista.setFechaVenLicencia(transportista.fechaVenLicencia());

        transportistaRepo.save(nuevoTransportista);

        return "Transportista creado";
    }
}
