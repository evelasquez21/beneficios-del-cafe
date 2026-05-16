package gt.umg.progra3.cafecito.rest.pesaje;

import gt.umg.progra3.cafecito.DTO.pesaje.QrPesaje;
import gt.umg.progra3.cafecito.model.pesaje.Boleta;
import gt.umg.progra3.cafecito.service.pesaje.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/peso-cabal/boletas/")
@CrossOrigin(origins = "*")
public class BoletaREST {

    @Autowired
    private BoletaService boletaService;

    @PostMapping
    private ResponseEntity<Map<String, String>> GenerarBoleta(QrPesaje pesaje){
        Map<String, String> response = new HashMap<>();
        response.put("message", boletaService.generarBoleta(pesaje));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }
}
