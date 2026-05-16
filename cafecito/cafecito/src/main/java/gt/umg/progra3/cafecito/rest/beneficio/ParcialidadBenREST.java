package gt.umg.progra3.cafecito.rest.beneficio;

import gt.umg.progra3.cafecito.DTO.agricultor.ParcialidadRegDTO;
import gt.umg.progra3.cafecito.DTO.beneficio.ParcialidadBenDTO;
import gt.umg.progra3.cafecito.service.beneficio.ParcialidadBenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/beneficio/parcialidades/")
public class ParcialidadBenREST {
    @Autowired
    private ParcialidadBenService parcialidadBenService;

    @PostMapping
    private ResponseEntity<Map<String, String>> AceptarParcialidad(@RequestBody ParcialidadBenDTO parcialidad) {
        Map<String, String> response = new HashMap<>();
        response.put("message", parcialidadBenService.aceptarParcialidad(parcialidad));
        response.put("action", "create");

        return ResponseEntity.ok(response);
    }

}
