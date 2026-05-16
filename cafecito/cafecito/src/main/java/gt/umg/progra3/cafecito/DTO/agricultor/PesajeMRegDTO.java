package gt.umg.progra3.cafecito.DTO.agricultor;

import java.time.LocalDateTime;

public record PesajeMRegDTO(
        Long  id,
        float pesoTotal,
        int cantParcialidades,
        Long nitAgricultor,
        String unidadMedida,
        int estado
) {
}
