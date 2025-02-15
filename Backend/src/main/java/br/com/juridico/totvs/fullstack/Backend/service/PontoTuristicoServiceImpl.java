package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PontoTuristicoServiceImpl implements PontoTuristicoService {
    List<PontoTuristico> listPontoTuristico = null;

    PontoTuristicoServiceImpl(){
        this.listPontoTuristico = new ArrayList<>();
        this.listPontoTuristico.add(new PontoTuristico(1L,
                "Brasil","Brasil","1","Brasil",
                "Brasil é um país localizado na América do Sul"
                
            ));
    }

    @Override
    public PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO) {
        PontoTuristico novoPontoTuristico = new PontoTuristico(
                this.getNewId(),
                PontoTuristicoCreateUpdateDTO.getNome(),
                PontoTuristicoCreateUpdateDTO.getCidade(),
                PontoTuristicoCreateUpdateDTO.getPais(),
                PontoTuristicoCreateUpdateDTO.getMelhorEstacao(),
                PontoTuristicoCreateUpdateDTO.getResumo());

        this.listPontoTuristico.add(novoPontoTuristico);

        return new PontoTuristicoDTO(novoPontoTuristico);
    }

    @Override
    public PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO) {
        PontoTuristico PontoTuristico = this.getPontoTuristicoById(id);
        int index = this.listPontoTuristico.indexOf(PontoTuristico);
        if (PontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PontoTuristico.setNome(PontoTuristicoCreateUpdateDTO.getNome());


        this.listPontoTuristico.set(index, PontoTuristico);
        return new PontoTuristicoDTO(PontoTuristico);
    }

    @Override
    public void delete(Long id) {
        PontoTuristico PontoTuristico = this.getPontoTuristicoById(id);
        this.listPontoTuristico.remove(PontoTuristico);
    }

    @Override
    public PontoTuristicoDTO getPontoTuristicobyId(Long id) {
        PontoTuristico PontoTuristico = this.getPontoTuristicoById(id);
        if (PontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new PontoTuristicoDTO(PontoTuristico);
    }



    @Override
    public List<PontoTuristicoDTO> getAllPontoTuristico() {
        return this.listPontoTuristico.stream()
                .map(PontoTuristico -> new PontoTuristicoDTO(PontoTuristico))
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listPontoTuristico.size() > 0){
            return this.listPontoTuristico.stream().max(Comparator
                            .comparingDouble(PontoTuristico::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }

    private PontoTuristico getPontoTuristicoById(Long id){
        return this.listPontoTuristico.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private int getPontoTuristicoIndexById(Long id){
        AtomicInteger index = new AtomicInteger();
        return this.listPontoTuristico.stream()
                .peek(p -> index.incrementAndGet())
                .anyMatch(x -> x.getId().equals(id)) ?
                index.get() - 1 : -1;
    }

}
