package gt.umg.progra3.cafecito.DTO;

public record UsuarioRegistroDTO(
    String nombreUsuario,
    String password,
    String rol,
    Long cui,
    String nombreCompleto
) {}