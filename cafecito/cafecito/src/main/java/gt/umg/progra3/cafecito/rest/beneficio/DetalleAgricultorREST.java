package gt.umg.progra3.cafecito.rest.beneficio;

import gt.umg.progra3.cafecito.DTO.beneficio.ObservacionesAgDTO;
import gt.umg.progra3.cafecito.service.beneficio.DetAgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficio/agricultores/")
@CrossOrigin(origins = "*")
public class DetalleAgricultorREST {

    @Autowired
    private DetAgricultorService detalleAgricultorService;

    @GetMapping("observacion/{id}")
    private ResponseEntity<List<ObservacionesAgDTO>> obtenerObservacionesAg(@PathVariable ("id") Long idAgricultor){
        return ResponseEntity.ok(detalleAgricultorService.getObservacionesAgricultor(idAgricultor));
    }
}
