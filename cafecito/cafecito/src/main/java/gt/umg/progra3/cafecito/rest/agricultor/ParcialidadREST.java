package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.ParcialidadRegDTO;
import gt.umg.progra3.cafecito.DTO.beneficio.ParcialidadBenDTO;
import gt.umg.progra3.cafecito.projection.ParcialidadesProjection;
import gt.umg.progra3.cafecito.service.agricultor.ParcialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        response.put("message", parcialidadService.saveParcialidad(parcialidad));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    private ResponseEntity<Map<String, String>> actualizarParcialidad(@RequestBody ParcialidadRegDTO parcialidad){

        Map<String, String> response = new HashMap<>();
        response.put("message", parcialidadService.updateParcialidad(parcialidad));
        response.put("action", "update");

        return ResponseEntity.ok(response);
    }

    @GetMapping("list")
    private ResponseEntity<List<ParcialidadesProjection>> obtenerParcialidades(){
        return ResponseEntity.ok(parcialidadService.getParcialidades());
    }

    @PutMapping("assign")
    private ResponseEntity<Void> asignarNoCuenta(@RequestBody ParcialidadBenDTO parcialidad){
        parcialidadService.assignCuentaPar(parcialidad);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
