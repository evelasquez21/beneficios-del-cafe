package gt.umg.progra3.cafecito.rest;

import gt.umg.progra3.cafecito.DTO.UnidadMedidaDTO;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.service.UnidadMediadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidad-medida/")
@CrossOrigin(origins = "*")
public class UnidadMedidaREST {

    @Autowired
    private UnidadMediadService umService;

    @GetMapping("{um}")
    private ResponseEntity<UnidadMedidaDTO> obtenerUnidadMedida(@PathVariable("um") String um){
        return ResponseEntity.ok(umService.getUnidadMedida(um));
    }

    @GetMapping()
    private ResponseEntity<List<UnidadMedidaDTO>> obtenerUnidades(){
        return ResponseEntity.ok(umService.getUnidadesMedida());
    }
}
