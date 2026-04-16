package gt.umg.progra3.cafecito.DTO;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
        LocalDateTime timestamp,
        int status,
        String error,
        String message
){
}
