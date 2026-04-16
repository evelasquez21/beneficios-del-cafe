package gt.umg.progra3.cafecito.service.beneficio;

import gt.umg.progra3.cafecito.repository.beneficio.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepo;
}
