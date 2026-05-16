package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteDTO;
import gt.umg.progra3.cafecito.DTO.agricultor.TransporteRegDTO;
import gt.umg.progra3.cafecito.model.agricultor.EstadoTransporte;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import gt.umg.progra3.cafecito.repository.agricultor.EstadoTransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.ParcialidadRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransporteRepository;
import gt.umg.progra3.cafecito.repository.agricultor.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepo;

    @Autowired
    private EstadoTransporteRepository estadoTRepo;

    @Autowired
    private TransportistaRepository transportistaRepo;

    @Autowired
    private ParcialidadRepository parcialidadRepo;

    @Autowired
    private BitacoraAgService bitacoraAgService;

    public String saveTransporte(TransporteRegDTO transporte){
        Transporte nuevoTransporte = new Transporte();

        if (transporteRepo.existsById(transporte.placa())){
            throw new RuntimeException("Ya existe un transporte registrado con esa placa");
        }

        nuevoTransporte.setPlaca(transporte.placa());
        nuevoTransporte.setColor(transporte.color());
        nuevoTransporte.setLinea(transporte.linea());
        nuevoTransporte.setMarca(transporte.marca());
        nuevoTransporte.setModelo(transporte.modelo());

        EstadoTransporte estadoT = estadoTRepo.findById(1)
                .orElseThrow(() -> new RuntimeException("No existe estado de transporte"));
        nuevoTransporte.setEstado(estadoT);

        transporteRepo.save(nuevoTransporte);

        bitacoraAgService.registrarAccion("Registró un nuevo transporte con placa: " + nuevoTransporte.getPlaca());

        return "Transporte creado";
    }
    
    public String updateTransporte(TransporteRegDTO transporte){
        Transporte transporteEx = transporteRepo.findById(transporte.placa())
                .orElseThrow(() -> new RuntimeException("No existe trarnsporte con esa placa"));
        
        transporteEx.setPlaca(transporte.placa());
        transporteEx.setColor(transporte.color());
        transporteEx.setLinea(transporte.linea());
        transporteEx.setModelo(transporte.marca());
        transporteEx.setModelo(transporte.modelo());

        /**
        EstadoTransporte estadoT = estadoTRepo.findById(transporte.estado())
                .orElseThrow(() -> new RuntimeException("No existe estado de transporte"));
        transporteEx.setEstado(estadoT);
         **/

        transporteRepo.save(transporteEx);
        
        bitacoraAgService.registrarAccion("Actualizó parámetros del transporte con placa: " + transporteEx.getPlaca());

        return "Transporte modificado con placa: " + transporteEx.getPlaca();
    }

    public String deleteTransporte(String placa){
        // Validación de registro existente con ID
        Transporte transporteEx = transporteRepo.findById(placa)
                .orElseThrow(() -> new RuntimeException("No existe trarnsporte con esa placa"));

        EstadoTransporte estadoT = estadoTRepo.findById(6)
                .orElseThrow(() -> new RuntimeException("No existe estado de transporte"));
        transporteEx.setEstado(estadoT);

        transporteRepo.save(transporteEx);

        bitacoraAgService.registrarAccion("Eliminó trarnsporte con placa: " + transporteEx.getPlaca());

        return "Transporte eliminado con placa: " + transporteEx.getPlaca();
    }

    public List<Transporte> getTransportesDisponibles(){
        return transporteRepo.findByEstadoId(1);
    }

    public List<TransporteDTO> getTransportes(){
        List<TransporteDTO> transportes = transporteRepo.BuscarTransportePorPesajeAsociado(5);
        return transportes;
    }

    public List<EstadoTransporte> getEstadosTransporte(){
        return estadoTRepo.findAll();
    }

    public Map<String, Long> getInfoTransportes(){
        long tTotal = transporteRepo.count();
        long tActivos = transporteRepo.countByEstadoId(1);
        long tEnRuta = transporteRepo.countByEstadoId(5);

        Map<String, Long> informacion = new HashMap<>();
        informacion.put("transTotal", tTotal);
        informacion.put("transActivos", tActivos);
        informacion.put("enRuta", tEnRuta);

        return informacion;
    }

    public List<Transporte> getTransportesPorEstado(int estado){
        return transporteRepo.findByEstadoId(estado);
    }

}
