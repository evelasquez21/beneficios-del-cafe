package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransportistaRegDTO;
import gt.umg.progra3.cafecito.service.agricultor.TransportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agricultor/transportistas/")
@CrossOrigin(origins = "*")
public class TransportistaREST {

    @Autowired
    private TransportistaService transportistaService;

    @PostMapping
    private ResponseEntity<String> guardarTransportista(@RequestBody TransportistaRegDTO transportista){
        try {
            return ResponseEntity.ok(transportistaService.saveTransportista(transportista));
        } catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
