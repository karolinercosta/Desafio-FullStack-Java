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

@Service
public class PontoTuristicoServiceImpl implements PontoTuristicoService {
    List<PontoTuristico> listPontoTuristico = null;

    PontoTuristicoServiceImpl(){
        this.listPontoTuristico = new ArrayList<>();
        this.listPontoTuristico.add(new PontoTuristico(1L,
                "Cristo Redentor", "Rio de Janeiro", "1", "Verão",
                "O Cristo Redentor é uma estátua de Jesus Cristo localizada no Rio de Janeiro, Brasil."
        ));
        this.listPontoTuristico.add(new PontoTuristico(2L,
                "Estátua da Liberdade", "Nova York", "2", "Outono",
                "A Estátua da Liberdade é um monumento icônico localizado em Nova York, Estados Unidos."
        ));
        this.listPontoTuristico.add(new PontoTuristico(3L,
                "Torre Eiffel", "Paris", "6", "Primavera",
                "A Torre Eiffel é uma torre de ferro localizada em Paris, França."
        ));
        this.listPontoTuristico.add(new PontoTuristico(4L,
                "Grande Muralha da China", "Pequim", "8", "Outono",
                "A Grande Muralha da China é uma série de fortificações feitas de pedra, tijolo, terra batida, madeira e outros materiais."
        ));

        this.listPontoTuristico.add(new PontoTuristico(5L,
                "Sydney Opera House", "Sydney", "9", "Verão",
                "A Sydney Opera House é um dos edifícios de artes performáticas mais famosos e distintos do mundo."
        ));

    }

    @Override
    public PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
        PontoTuristico novoPontoTuristico = new PontoTuristico(
                this.getNewId(),
                pontoTuristicoCreateUpdateDTO.getNome(),
                pontoTuristicoCreateUpdateDTO.getCidade(),
                pontoTuristicoCreateUpdateDTO.getPais(),
                pontoTuristicoCreateUpdateDTO.getMelhorEstacao(),
                pontoTuristicoCreateUpdateDTO.getResumo());

        this.listPontoTuristico.add(novoPontoTuristico);

        return new PontoTuristicoDTO(novoPontoTuristico);
    }

    @Override
    public PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        int index = this.listPontoTuristico.indexOf(pontoTuristico);
        if (pontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        pontoTuristico.setNome(pontoTuristicoCreateUpdateDTO.getNome());
        pontoTuristico.setCidade(pontoTuristicoCreateUpdateDTO.getCidade());
        pontoTuristico.setPais(pontoTuristicoCreateUpdateDTO.getPais());
        pontoTuristico.setMelhorEstacao(pontoTuristicoCreateUpdateDTO.getMelhorEstacao());
        pontoTuristico.setResumo(pontoTuristicoCreateUpdateDTO.getResumo());

        this.listPontoTuristico.set(index, pontoTuristico);
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public void delete(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        this.listPontoTuristico.remove(pontoTuristico);
    }

    @Override
    public PontoTuristicoDTO getPontoTuristicobyId(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        if (pontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public List<PontoTuristicoDTO> getAllPontoTuristico() {
        return this.listPontoTuristico.stream()
                .map(pontoTuristico -> new PontoTuristicoDTO(pontoTuristico))
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