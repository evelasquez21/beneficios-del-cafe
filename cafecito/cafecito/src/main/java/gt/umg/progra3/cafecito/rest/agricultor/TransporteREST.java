package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteRegDTO;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import gt.umg.progra3.cafecito.service.agricultor.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agricultor/transportes/")
@CrossOrigin(origins = "*")
public class TransporteREST {

    @Autowired
    private TransporteService transporteService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarTransporte(@RequestBody TransporteRegDTO transporte){

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", transporteService.saveTransporte(transporte));

        return ResponseEntity.ok(response);
    }

    @GetMapping("disponibles")
    private ResponseEntity<List<Transporte>> obtenerTransDisponibles(){
        return ResponseEntity.ok(transporteService.getTransportesDisponibles());
    }
}
