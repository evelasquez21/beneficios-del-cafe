package gt.umg.progra3.cafecito.rest.pesaje;

import gt.umg.progra3.cafecito.DTO.pesaje.PesajeReg;
import gt.umg.progra3.cafecito.model.pesaje.Pesaje;
import gt.umg.progra3.cafecito.service.pesaje.PesajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peso-cabal/pesajes/")
@CrossOrigin(origins = "*")
public class PesajeRest {

    @Autowired
    private PesajeService pesajeService;

    @GetMapping
    private ResponseEntity<List<Pesaje>> obtenerPesajes(){
        return ResponseEntity.ok(pesajeService.getPesajes());
    }

    @PostMapping
    private ResponseEntity<Map<String, String>> registrarPesaje(@RequestBody PesajeReg pesaje){

        Map<String, String> response = new HashMap<>();
        response.put("message", pesajeService.savePesaje(pesaje));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }
}
