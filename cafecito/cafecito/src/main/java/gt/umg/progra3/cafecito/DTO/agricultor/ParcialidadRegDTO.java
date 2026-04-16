package gt.umg.progra3.cafecito.DTO.agricultor;

public record ParcialidadRegDTO(
        Long id,
        Long noCuenta,
        float peso,
        String transportePlaca,
        Long idTransportista,
        Long idPesajeMaestro,
        String unidadMedida

) {
}
