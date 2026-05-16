package gt.umg.progra3.cafecito.DTO.agricultor;

import java.time.LocalDateTime;

public record AgricultorVistaDTO(
        Long id,
        String nit,
        Long cui,
        String nombreCompleto,
        int estado,
        String fechaCreación
) {
}
