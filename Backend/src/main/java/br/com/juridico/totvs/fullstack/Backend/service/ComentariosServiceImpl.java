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
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.FEBRUARY, 15);
        this.listComentarios.add(new Comentarios(1L,
                "User1", "1", "Este é um comentário", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(2L,
                "User2", "1", "Este é um comentário1", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(3L,
                "User3", "1", "Este é um comentário2", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(4L,
                "User4", "1", "Este é um comentário3", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(5L,
                "User5", "3", "Este é um comentário4", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(6L,
                "User6", "3", "Este é um comentário5", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(7L,
                "User7", "4", "Este é um comentário6", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(8L,
                "User8", "4", "Este é um comentário7", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(9L,
                "User9", "5", "Este é um comentário8", calendar.getTime()
        ));
        this.listComentarios.add(new Comentarios(10L,
                "User10", "5", "Este é um comentário9", calendar.getTime()
        ));
    }

    @Override
    public ComentariosDTO create(ComentariosCreateUpdateDTO comentariosCreateUpdateDTO) {
        Comentarios novoComentarios = new Comentarios(
                this.getNewId(),
                comentariosCreateUpdateDTO.getUser(),
                comentariosCreateUpdateDTO.getPontoTuristico(),
                comentariosCreateUpdateDTO.getComentario(),
                comentariosCreateUpdateDTO.getData()
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
        comentarios.setData(comentariosCreateUpdateDTO.getData());

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