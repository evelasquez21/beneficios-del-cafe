package gt.umg.progra3.cafecito.DTO.beneficio;

import java.time.LocalDateTime;

public record ObservacionesAgDTO(
        long idAsignasion,
        long idAgricultor,
        long idObservacion,
        String descripcion,
        LocalDateTime fechaObservacion
) {
}
