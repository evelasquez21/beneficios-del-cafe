package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorRegDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.ObsAgricultorDTO;
import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import gt.umg.progra3.cafecito.service.agricultor.AgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agricultor/agricultores/")
@CrossOrigin(origins = "*")
public class AgricultorREST {

    @Autowired
    private AgricultorService agricultorService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarAgricultor(@RequestBody AgricultorRegDTO agricultor){

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", agricultorService.saveAgricultor(agricultor));

        return ResponseEntity.ok(response);
    }

    @GetMapping("list")
    private List<Agricultor> mostrarAgricultores(){
        return agricultorService.findAllAgricultor();
    }

    @PostMapping("observaciones")
    private ResponseEntity<Map<String, String>> agregarObservacion(@RequestBody ObsAgricultorDTO observacion){

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", agricultorService.addObservation(observacion));

        return  ResponseEntity.ok(response);
    }
}
