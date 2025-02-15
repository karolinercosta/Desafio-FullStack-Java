package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PontoTuristicoService {
    List<PontoTuristico> listPontoTuristico = null;
    public PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO);
    public PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO);
    public void delete(Long id);
    public PontoTuristicoDTO getPontoTuristicobyId(Long id);
    public List<PontoTuristicoDTO> getAllPontoTuristico();
}
