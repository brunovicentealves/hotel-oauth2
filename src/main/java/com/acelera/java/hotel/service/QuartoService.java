package com.acelera.java.hotel.service;

import com.acelera.java.hotel.domain.Quarto;
import com.acelera.java.hotel.exception.QuartoNotFoundException;
import com.acelera.java.hotel.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    private QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public List<Quarto> getListaDeQuartosParaLimpeza() {
        return quartoRepository.findByEstaOcupadoFalseAndEstaLimpoFalse();
    }

    public List<Quarto> getListaDeQuartosLimpos() {
        return quartoRepository.findByEstaOcupadoFalseAndEstaLimpoTrue();
    }

    public Quarto atualizaParaQuartoLimpo(Long quartoId) {
        Optional<Quarto> quartoOptional = quartoRepository.findById(quartoId);
        if (quartoOptional.isPresent()) {
            Quarto quarto = quartoOptional.get();
            quarto.setEstaLimpo(Boolean.TRUE);
            return quartoRepository.save(quarto);
        } else {
            throw new QuartoNotFoundException();
        }
    }

    public Quarto marcarParaLimpezaAposCheckout(Long quartoId) {
        Optional<Quarto> quartoOptional = quartoRepository.findById(quartoId);
        if (quartoOptional.isPresent()) {
            return colocaQuartoParaLimpeza(quartoOptional);
        } else {
            String message = String.format("Quarto %d não encontrado.", quartoId);
            throw new QuartoNotFoundException(message);
        }
    }

    private Quarto colocaQuartoParaLimpeza(Optional<Quarto> quartoOptional) {
        Quarto quarto = quartoOptional.get();
        quarto.setEstaLimpo(Boolean.FALSE);
        quarto.setEstaOcupado(Boolean.FALSE);
        return quartoRepository.save(quarto);
    }
}
