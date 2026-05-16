package gt.umg.progra3.cafecito.rest.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.TransporteRegDTO;
import gt.umg.progra3.cafecito.model.agricultor.EstadoTransporte;
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
        response.put("message", transporteService.saveTransporte(transporte));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    private ResponseEntity<Map<String, String>> actualizarTransporte(@RequestBody TransporteRegDTO transporte){

        Map<String, String> response = new HashMap<>();
        response.put("message", transporteService.updateTransporte(transporte));
        response.put("action", "update");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "delete/{id}")
    private ResponseEntity <Map<String, String>> eliminarTransporte(@PathVariable ("id") String placa){

        Map<String, String> response = new HashMap<>();
        response.put("message", transporteService.deleteTransporte(placa));
        response.put("action", "delete");

        return ResponseEntity.ok(response);
    }

    @GetMapping("disponibles")
    private ResponseEntity<List<Transporte>> obtenerTransDisponibles(){
        return ResponseEntity.ok(transporteService.getTransportesDisponibles());
    }

    @GetMapping("estados")
    private ResponseEntity<List<EstadoTransporte>> obtenerEstadosTrans(){
        return ResponseEntity.ok(transporteService.getEstadosTransporte());
    }

    @GetMapping("estado/{id}")
    private ResponseEntity<List<Transporte>> obtenerTransPorEs(@PathVariable ("id") int estado){
        return ResponseEntity.ok(transporteService.getTransportesPorEstado(estado));
    }

    @GetMapping("estadisticas")
    private ResponseEntity<Map<String, Long>> obtenerInfoTrans(){
        return ResponseEntity.ok(transporteService.getInfoTransportes());
    }

    @GetMapping
    private ResponseEntity<List<TransporteDTO>> obtenerTransportes(){
        return ResponseEntity.ok(transporteService.getTransportes());
    }
}
