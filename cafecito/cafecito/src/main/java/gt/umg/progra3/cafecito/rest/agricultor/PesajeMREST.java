package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.EstadoPesajeMDto;
import gt.umg.progra3.cafecito.DTO.agricultor.PesajeMRegDTO;
import gt.umg.progra3.cafecito.model.agricultor.EstadoPesajeMaestro;
import gt.umg.progra3.cafecito.service.agricultor.EstadoPesajeMService;
import gt.umg.progra3.cafecito.service.agricultor.PesajeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agricultor/pesaje-maestro/")
@CrossOrigin(origins = "*")
public class PesajeMREST {

    @Autowired
    private PesajeMService pesajeMService;

    @Autowired
    private EstadoPesajeMService estadoPesajeMService;

    @PostMapping
    private ResponseEntity<Map<String, String>> guardarPesajeM(@RequestBody PesajeMRegDTO pesajeMaestro){

        Map<String, String> response = new HashMap<>();
        response.put("message", pesajeMService.savePesajeMaestro(pesajeMaestro));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    private ResponseEntity<Map<String, String>> actualizarPesajeM(@RequestBody PesajeMRegDTO pesajeMaestro){

        Map<String, String> response = new HashMap<>();
        response.put("message", pesajeMService.updatePesajeMaestro(pesajeMaestro));
        response.put("action", "update");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("cancel/{id}")
    private ResponseEntity<Map<String, String>> cancelarPesajeM(@PathVariable ("id") Long id){

        Map<String, String> response = new HashMap<>();
        response.put("message", pesajeMService.cancelarPesajeMaestro(id));
        response.put("action", "delete");

        return ResponseEntity.ok(response);
    }

    @GetMapping("cant-par/{id}")
    private ResponseEntity<Map<String, Integer>> obtenerCantP(@PathVariable ("id") Long id){

        Map<String, Integer> response = new HashMap<>();
        response.put("cantP", pesajeMService.cantidadParcialidades(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("estado/{id}")
    private ResponseEntity<EstadoPesajeMDto> obtenerEstado(@PathVariable("id") int id){
        return ResponseEntity.ok(estadoPesajeMService.getEstadoPesajeM(id));
    }

    @GetMapping("estados")
    private ResponseEntity<List<EstadoPesajeMDto>> obtenerEstados(){
        return ResponseEntity.ok(estadoPesajeMService.getEstadosPesajesM());
    }
}
