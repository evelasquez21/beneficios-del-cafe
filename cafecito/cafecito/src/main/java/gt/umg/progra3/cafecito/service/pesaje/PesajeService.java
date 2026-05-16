package gt.umg.progra3.cafecito.service.pesaje;

import gt.umg.progra3.cafecito.DTO.pesaje.PesajeReg;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.model.pesaje.Pesaje;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import gt.umg.progra3.cafecito.repository.pesaje.PesajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesajeService {
    @Autowired
    private PesajeRepository pesajeRepo;

    @Autowired
    private BitacoraPesService bitacoraPesService;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepo;


    public String savePesaje(PesajeReg pesaje){
        Pesaje guardarPesaje = new Pesaje();

        if(pesajeRepo.existsById(pesaje.idPesaje())){
            throw new RuntimeException("Ya existe un pesaje con ese ID");
        }

        guardarPesaje.setCuenta(pesaje.noCuenta());
        guardarPesaje.setCuiTransportista(pesaje.noCuenta());
        guardarPesaje.setPlacaTransporte(pesaje.placaTransporte());
        guardarPesaje.setPesoObtenido(pesaje.pesoObtenido());

        // Búsquedda de unidad de medida
        UnidadMedida unidadMedida = unidadMedidaRepo.findById(pesaje.um())
                .orElseThrow(() -> new RuntimeException("No existe unidad de medida"));

        guardarPesaje.setUnidadMedida(unidadMedida);

        Pesaje nuevoPesaje = pesajeRepo.save(guardarPesaje);

        bitacoraPesService.registrarAccion("Registro el pesaje con ID: " + nuevoPesaje.getId());

        return "Pesaje registrado";
    }

    public List<Pesaje> getPesajes(){
        return pesajeRepo.findAll();
    }
}
