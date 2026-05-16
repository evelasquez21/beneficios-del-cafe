package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransportistaRegDTO;
import gt.umg.progra3.cafecito.projection.TransportistaDetalleProjection;
import gt.umg.progra3.cafecito.service.agricultor.TransportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agricultor/transportistas/")
@CrossOrigin(origins = "*")
public class TransportistaREST {

    @Autowired
    private TransportistaService transportistaService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarTransportista(@RequestBody TransportistaRegDTO transportista){

        Map<String, String> response = new HashMap<>();
        response.put("message", transportistaService.saveTransportista(transportista));
        response.put("action", "create");

        return  ResponseEntity.ok(response);
    }

    @PutMapping("update")
    private ResponseEntity<Map<String, String>> actualizarTransportista(@RequestBody TransportistaRegDTO transportista){

        Map<String, String> response = new HashMap<>();
        response.put("message", transportistaService.updateTransportista(transportista));
        response.put("action", "update");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    private ResponseEntity<Map<String, String>> eliminarTransportista(@PathVariable ("id") Long id){

        Map<String, String> response = new HashMap<>();
        response.put("message", transportistaService.deleteTransportista(id));
        response.put("action", "delete");

        return ResponseEntity.ok(response);
    }

    @GetMapping("list")
    private ResponseEntity<List<TransportistaDetalleProjection>> obtenerTransportistas(){
        return ResponseEntity.ok(transportistaService.getListaTransportistas());
    }
}
