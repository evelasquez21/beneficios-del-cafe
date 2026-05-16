package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.model.Usuario;
import gt.umg.progra3.cafecito.model.agricultor.BitacoraAgricultor;
import gt.umg.progra3.cafecito.repository.UsuarioRepository;
import gt.umg.progra3.cafecito.repository.agricultor.BitacoraAgricultorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BitacoraAgService {

    @Autowired
    private BitacoraAgricultorRepo bitacoraAgRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public void registrarAccion(String accion){
        // Extracción del usuario que esta haciendo la petición
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Validación de sesión activa
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")){
            String nombreUsuario = auth.getName();

            Usuario usuarioActual = usuarioRepo.findByNombreUsuario(nombreUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            BitacoraAgricultor bitacora = new BitacoraAgricultor();
            bitacora.setUsuario(usuarioActual);
            bitacora.setAccion(accion);
            bitacora.setFechaYHora(LocalDateTime.now());

            bitacoraAgRepo.save(bitacora);
        }
    }
}
