package gt.umg.progra3.cafecito.service;

import gt.umg.progra3.cafecito.DTO.UsuarioLoginDTO;
import gt.umg.progra3.cafecito.DTO.UsuarioRegistroDTO;
import gt.umg.progra3.cafecito.model.Persona;
import gt.umg.progra3.cafecito.model.Usuario;
import gt.umg.progra3.cafecito.repository.PersonaRepository;
import gt.umg.progra3.cafecito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario getUsuario(Long id) {
        return usuarioRepo.getById(id);
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public String registrarUsuario(UsuarioRegistroDTO dto) {
        Usuario usuario = new Usuario();
        Persona nuevaPersona = new Persona();

        // Validación de nombre de usuario (no repetido)
        usuarioRepo.findByNombreUsuario(dto.nombreUsuario())
                .ifPresent(u -> {
                    throw new RuntimeException("El nombre de usuario ya esta en uso");
                });

        usuario.setNombreUsuario(dto.nombreUsuario());

        nuevaPersona = personaRepo.findById(dto.cui()).orElse(new Persona());

        personaRepo.findById(dto.cui())
                .ifPresent(p -> {
                    throw new RuntimeException("Ya existe un usuario con ese CUI");
                });

        // Validación de contraseña - min 8 caracteres, una mayusc y un caracter especial

        // Guardar persona
        nuevaPersona.setCui(dto.cui());
        nuevaPersona.setNombreCompleto(dto.nombreCompleto());
        nuevaPersona.setEstado(1);

        personaRepo.save(nuevaPersona);

        usuario.setPersona(nuevaPersona);
        usuario.setRol(dto.rol());

        // Lógica de hasheo de password
        String passwordHasheada = encoder.encode(dto.password());
        usuario.setPassword(passwordHasheada);

        usuarioRepo.save(usuario);
        return "Usuario registrado";
    }
}
