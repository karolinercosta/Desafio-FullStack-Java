package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentarios;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ComentariosServiceImpl implements ComentariosService {
    List<Comentarios> listComentarios = null;

    public ComentariosServiceImpl(){
        this.listComentarios = new ArrayList<>();
        this.listComentarios.add(new Comentarios(1L,
                "User1", "1", "Este é um comentário"
        ));
    }

    @Override
    public ComentariosDTO create(ComentariosCreateUpdateDTO comentariosCreateUpdateDTO) {
        Comentarios novoComentarios = new Comentarios(
                this.getNewId(),
                comentariosCreateUpdateDTO.getUser(),
                comentariosCreateUpdateDTO.getPontoTuristico(),
                comentariosCreateUpdateDTO.getComentario()
        );

        this.listComentarios.add(novoComentarios);

        return new ComentariosDTO(novoComentarios);
    }

    @Override
    public ComentariosDTO update(Long id, ComentariosCreateUpdateDTO comentariosCreateUpdateDTO) {
        Comentarios comentarios = this.getComentariosById(id);
        int index = this.listComentarios.indexOf(comentarios);
        if (comentarios == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        comentarios.setUser(comentariosCreateUpdateDTO.getUser());
        comentarios.setPontoTuristico(comentariosCreateUpdateDTO.getPontoTuristico());
        comentarios.setComentario(comentariosCreateUpdateDTO.getComentario());

        this.listComentarios.set(index, comentarios);
        return new ComentariosDTO(comentarios);
    }

    @Override
    public void delete(Long id) {
        Comentarios comentarios = this.getComentariosById(id);
        this.listComentarios.remove(comentarios);
    }

    @Override
    public ComentariosDTO getComentariosbyId(Long id) {
        Comentarios comentarios = this.getComentariosById(id);
        if (comentarios == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ComentariosDTO(comentarios);
    }

    @Override
    public List<ComentariosDTO> getAllComentarios() {
        return this.listComentarios.stream()
                .map(ComentariosDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentariosDTO> getComentariosbyPontoTuristico(Long id) {
        List<Comentarios> comentarios = this.listComentarios.stream()
                .filter(x -> Objects.equals(x.getPontoTuristico(), id.toString()))
                .collect(Collectors.toList());
        if (comentarios.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return comentarios.stream()
                .map(ComentariosDTO::new)
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listComentarios.size() > 0){
            return this.listComentarios.stream().max(Comparator
                            .comparingDouble(Comentarios::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }

    private Comentarios getComentariosById(Long id){
        return this.listComentarios.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private int getComentariosIndexById(Long id){
        AtomicInteger index = new AtomicInteger();
        return this.listComentarios.stream()
                .peek(p -> index.incrementAndGet())
                .anyMatch(x -> x.getId().equals(id)) ?
                index.get() - 1 : -1;
    }
}