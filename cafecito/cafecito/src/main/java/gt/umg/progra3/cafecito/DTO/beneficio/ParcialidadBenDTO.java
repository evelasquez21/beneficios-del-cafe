package gt.umg.progra3.cafecito.DTO.beneficio;

import java.time.LocalDateTime;

public record ParcialidadBenDTO (
        Long id,
        Long noCuenta,
        float peso,
        String transportePlaca,
        Long idTransportista,
        Long idPesajeMaestro,
        String unidadMedida,
        LocalDateTime fechaEntrega
){
}
