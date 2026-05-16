package gt.umg.progra3.cafecito.service.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.EstadoPesajeMDto;
import gt.umg.progra3.cafecito.model.agricultor.EstadoPesajeMaestro;
import gt.umg.progra3.cafecito.repository.agricultor.EstadoPesajeMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoPesajeMService {

    @Autowired
    private EstadoPesajeMRepository estadoPesajeMRepo;


    public EstadoPesajeMDto getEstadoPesajeM(int id){
        EstadoPesajeMaestro estado = estadoPesajeMRepo.findById(id).orElseThrow(() -> new RuntimeException("No estado de pesaje"));

        EstadoPesajeMDto estadoDTO = new EstadoPesajeMDto(
                estado.getId(),
                estado.getDescripcion()
        );

        return estadoDTO;
    }

    public List<EstadoPesajeMDto> getEstadosPesajesM(){
        List<EstadoPesajeMaestro> estados = estadoPesajeMRepo.findAll();

        List<EstadoPesajeMDto> dtos = estados.stream()
                .map(estadoPesajeMaestro -> new EstadoPesajeMDto(
                        estadoPesajeMaestro.getId(),
                        estadoPesajeMaestro.getDescripcion()
                )).collect(Collectors.toList());

        return dtos;
    }
}
