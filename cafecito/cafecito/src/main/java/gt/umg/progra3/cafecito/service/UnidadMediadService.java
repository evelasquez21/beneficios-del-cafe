package gt.umg.progra3.cafecito.service;

import gt.umg.progra3.cafecito.DTO.UnidadMedidaDTO;
import gt.umg.progra3.cafecito.model.UnidadMedida;
import gt.umg.progra3.cafecito.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadMediadService {
    @Autowired
    private UnidadMedidaRepository umRepo;

    public UnidadMedidaDTO getUnidadMedida(String um){
        UnidadMedida unidadMedida = umRepo.getReferenceById(um);

        UnidadMedidaDTO unidadMedidaDTO = new UnidadMedidaDTO(
                unidadMedida.getAbrev(),
                unidadMedida.getDescripcion()
        );

        return unidadMedidaDTO;
    }

    public List<UnidadMedidaDTO> getUnidadesMedida(){

        List<UnidadMedida> unidadesMedidas = umRepo.findAll();

        List<UnidadMedidaDTO> dtos = unidadesMedidas.stream()
                .map(unidadMedida -> new UnidadMedidaDTO(
                        unidadMedida.getAbrev(),
                        unidadMedida.getDescripcion()
                        )
                ).collect(Collectors.toList());

        return dtos;
    }
}
