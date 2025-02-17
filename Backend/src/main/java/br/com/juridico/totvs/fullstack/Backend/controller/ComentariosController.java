package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.service.ComentariosService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/comentarios")
public class ComentariosController {
    private final ComentariosService comentariosService;

    public ComentariosController(ComentariosService comentariosService){
        this.comentariosService = comentariosService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentariosDTO create(@RequestBody ComentariosCreateUpdateDTO comentariosCreateUpdateDTO){
        return this.comentariosService.create(comentariosCreateUpdateDTO);
    }

    @GetMapping
    public List<ComentariosDTO> getAll(){
        return this.comentariosService.getAllComentarios();
    }

    @GetMapping("/ponto-turistico/{id}")
    public ResponseEntity<List<ComentariosDTO>> getComentariosbyPontoTuristico(@PathVariable Long id) {
        List<ComentariosDTO> comentarios = comentariosService.getComentariosbyPontoTuristico(id);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("{idComentarios}")
    public ComentariosDTO getComentariosById(@PathVariable Long idComentarios){
        return this.comentariosService.getComentariosbyId(idComentarios);
    }

    @DeleteMapping("{idComentarios}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idComentarios){
        this.comentariosService.delete(idComentarios);
    }

    @PutMapping("{idComentarios}")
    public ComentariosDTO update(@PathVariable Long idComentarios,
                                 @RequestBody ComentariosCreateUpdateDTO comentariosCreateUpdateDTO){
        return this.comentariosService.update(idComentarios, comentariosCreateUpdateDTO);
    }
}