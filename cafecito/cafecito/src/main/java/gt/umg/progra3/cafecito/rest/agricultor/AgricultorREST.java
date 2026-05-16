package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorRegDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.AgricultorVistaDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.ObsAgricultorDTO;
import gt.umg.progra3.cafecito.projection.AgricultorDetalleProjection;
import gt.umg.progra3.cafecito.service.agricultor.AgricultorService;
import gt.umg.progra3.cafecito.service.beneficio.DetAgricultorService;
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

    @Autowired
    private DetAgricultorService detAgricultorService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarAgricultor(@RequestBody AgricultorRegDTO agricultor){

        Map<String, String> response = new HashMap<>();
        response.put("message", agricultorService.saveAgricultor(agricultor));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }

    @PostMapping("observaciones")
    private ResponseEntity<Map<String, String>> agregarObservacion(@RequestBody ObsAgricultorDTO observacion){

        Map<String, String> response = new HashMap<>();
        response.put("message", agricultorService.addObservation(observacion));
        response.put("action", "create");

        return  ResponseEntity.ok(response);
    }

    @GetMapping("list")
    private ResponseEntity<List<AgricultorDetalleProjection>> obtenerListaAg(){
        return ResponseEntity.ok(detAgricultorService.getListAgricultor());
    }

    @GetMapping()
    private ResponseEntity<List<AgricultorVistaDTO>> obtenerAgricultores(){
        return ResponseEntity.ok(agricultorService.getAgricultores());
    }
}
