package gt.umg.progra3.cafecito.rest.beneficio;

import gt.umg.progra3.cafecito.DTO.beneficio.CuentaRequest;
import gt.umg.progra3.cafecito.service.beneficio.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficio/cuentas/")
@CrossOrigin(origins = "*")
public class CuentaREST {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    private ResponseEntity<Void> recibirNuevoPesaje(@RequestBody CuentaRequest cuenta){
        cuentaService.saveCuentaAgricultorScheme(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).build();    }
}
