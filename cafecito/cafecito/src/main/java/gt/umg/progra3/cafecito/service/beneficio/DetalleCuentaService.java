package gt.umg.progra3.cafecito.service.beneficio;

import gt.umg.progra3.cafecito.model.beneficio.DetalleCuenta;
import gt.umg.progra3.cafecito.repository.beneficio.DetalleCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCuentaService {

    @Autowired
    private DetalleCuentaRepository detCuentaRepo;

    public DetalleCuenta findByCuenta(Long noCuenta){
        DetalleCuenta detalleCuenta = detCuentaRepo.findById(noCuenta).orElseThrow(() -> new RuntimeException("No se ha encontrado la cuenta"));

        return detalleCuenta;
    }
}
