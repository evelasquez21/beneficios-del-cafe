package gt.umg.progra3.cafecito.rest;

import gt.umg.progra3.cafecito.DTO.UsuarioLoginDTO;
import gt.umg.progra3.cafecito.DTO.UsuarioRegistroDTO;
import gt.umg.progra3.cafecito.model.Usuario;
import gt.umg.progra3.cafecito.security.JwtUtil;
import gt.umg.progra3.cafecito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios/")
@CrossOrigin(origins = "*")
public class UsuarioREST {

    @Autowired
    private UsuarioService usuarioService;

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UsuarioREST(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    private ResponseEntity<Usuario> saveUsuario (@RequestBody Usuario usuario){
        try {
            Usuario usuarioNuevo = usuarioService.saveUsuario(usuario);
            return ResponseEntity.created(new URI("/usuarios/"+usuarioNuevo.getId())).body(usuarioNuevo);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("register")
    private ResponseEntity<String> registrar(@RequestBody UsuarioRegistroDTO dto) {

        try {
            return ResponseEntity.ok(usuarioService.registrarUsuario(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("login")
    private ResponseEntity<?> login(@RequestBody UsuarioLoginDTO credenciales) {
        try {
            // 1. Spring verifica usuario y contraseña internamente
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credenciales.nombreUsuario(), credenciales.password())
            );

            // 2. Si es correcto, buscamos los datos del usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(credenciales.nombreUsuario());

            // 3. Generamos el Token
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // 4. Se lo enviamos de vuelta a Angular
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }
}
