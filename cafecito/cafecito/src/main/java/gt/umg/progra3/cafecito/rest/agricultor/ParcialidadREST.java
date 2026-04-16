package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.ParcialidadRegDTO;
import gt.umg.progra3.cafecito.service.agricultor.ParcialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agricultor/parcialidades/")
@CrossOrigin(origins = "*")
public class ParcialidadREST {

    @Autowired
    private ParcialidadService parcialidadService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarParcialidad(@RequestBody ParcialidadRegDTO parcialidad){

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", parcialidadService.saveParcialidad(parcialidad));

        return ResponseEntity.ok(response);
    }
}
