package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteRegDTO;
import gt.umg.progra3.cafecito.model.agricultor.EstadoTransporte;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import gt.umg.progra3.cafecito.repository.agricultor.EstadoTransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepo;

    @Autowired
    private EstadoTransporteRepository estadoTRepo;

    @Autowired
    private TransportistaRepository transportistaRepo;

    public String saveTransporte(TransporteRegDTO transporte){
        Transporte nuevoTransporte;

        nuevoTransporte = transporteRepo.findById(transporte.placa()).orElse(new Transporte());

        nuevoTransporte.setPlaca(transporte.placa());
        nuevoTransporte.setColor(transporte.color());
        nuevoTransporte.setLinea(transporte.linea());
        nuevoTransporte.setMarca(transporte.marca());
        nuevoTransporte.setModelo(transporte.modelo());

        EstadoTransporte estadoT = estadoTRepo.findById(transporte.estado())
                .orElseThrow(() -> new RuntimeException("No existe estado de transporte"));
        nuevoTransporte.setEstado(estadoT);

        transporteRepo.save(nuevoTransporte);
        return "Transporte creado";
    }

    public List<Transporte> getTransportesDisponibles(){
        return transporteRepo.findByEstadoId(1);
    }

}
