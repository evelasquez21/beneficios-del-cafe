package gt.umg.progra3.cafecito.rest.beneficio;

import gt.umg.progra3.cafecito.model.beneficio.DetalleCuenta;
import gt.umg.progra3.cafecito.service.beneficio.DetalleCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficio/detalle-cuenta/")
@CrossOrigin(origins = "*")
public class DetalleCuentaREST {

    @Autowired
    private DetalleCuentaService detCuentaService;

    @GetMapping(value = "{id}")
    private DetalleCuenta obtenerCuentaPorId (@PathVariable ("id") Long noCuenta) {
        return detCuentaService.findByCuenta(noCuenta);
    }

}
