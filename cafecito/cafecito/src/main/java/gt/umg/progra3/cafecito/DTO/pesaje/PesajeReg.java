package gt.umg.progra3.cafecito.DTO.pesaje;

public record pesaje(
        Long idPesaje,
        Long noCuenta,
        String placaTransporte,
        Long cuiTransportista,
        String um,
        float pesoObtenido
) {
}
