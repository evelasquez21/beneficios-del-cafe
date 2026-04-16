package gt.umg.progra3.cafecito.DTO.agricultor;

import java.time.LocalDate;

public record TransportistaRegDTO(
        Long id,
        String tipoLicencia,
        String nombreCompleto,
        LocalDate fechaVenLicencia,
        Long cui
) {
}
