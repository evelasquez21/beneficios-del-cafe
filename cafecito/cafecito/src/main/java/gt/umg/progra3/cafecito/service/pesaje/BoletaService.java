package gt.umg.progra3.cafecito.service.pesaje;

import gt.umg.progra3.cafecito.DTO.pesaje.QrPesaje;
import gt.umg.progra3.cafecito.model.pesaje.Boleta;
import gt.umg.progra3.cafecito.model.pesaje.Pesaje;
import gt.umg.progra3.cafecito.repository.pesaje.BoletaRepository;
import gt.umg.progra3.cafecito.repository.pesaje.PesajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepo;

    @Autowired
    private PesajeRepository pesajeRepo;

    @Autowired
    private BitacoraPesService bitacoraPesService;

    public String generarBoleta(QrPesaje pesaje){

        Pesaje pesoAsig = pesajeRepo.findById(pesaje.getIdPesaje())
                .orElseThrow(() -> new RuntimeException("No se encontro el pesaje"));

        Boleta boleta = boletaRepo.findByPesajeId(pesoAsig.getId())
                        .orElse(new Boleta());

        boleta.setPesaje(pesoAsig);

        float merma = pesaje.getPesoEsperado() - pesaje.getPesoObtenido();
        float perMerma =  (pesaje.getPesoEsperado() > 0) ? (merma / pesaje.getPesoEsperado()) * 100 : 0;

        boleta.setMerma(merma);
        boleta.setPorcentajeMerma(perMerma);
        boleta.setFechaBoleta(LocalDateTime.now());

        bitacoraPesService.registrarAccion("Genero boleta de pesaje con ID: " + pesaje.getIdPesaje());

        boletaRepo.save(boleta);

        return "Se Genero la boleta del pesaje";
    }
}
