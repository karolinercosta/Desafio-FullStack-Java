package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentarios;
import br.com.juridico.totvs.fullstack.Backend.service.ComentariosService;
import br.com.juridico.totvs.fullstack.Backend.service.ComentariosServiceImpl;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/comentarios")
public class ComentariosController {
    private final ComentariosService ComentariosService;

    public ComentariosController(ComentariosService ComentariosService){
        this.ComentariosService = ComentariosService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public ComentariosDTO create(@RequestBody ComentariosCreateUpdateDTO ComentariosCreateUpdateDTO){
        return this.ComentariosService.create(ComentariosCreateUpdateDTO);
    }

    @GetMapping
    public List<ComentariosDTO> getAll(){
        return this.ComentariosService.getAllComentarios();
    }



    @GetMapping("{idComentarios}")
    public ComentariosDTO getComentariosById(@PathVariable Long idComentarios){
        return this.ComentariosService.getComentariosbyId(idComentarios);
    }

    @DeleteMapping("{idComentarios}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idComentarios){
        this.ComentariosService.delete(idComentarios);
    }

    @PutMapping("{idComentarios}")
    public ComentariosDTO update(@PathVariable Long idComentarios,
                          @RequestBody ComentariosCreateUpdateDTO ComentariosCreateUpdateDTO ){
        return this.ComentariosService.update(idComentarios, ComentariosCreateUpdateDTO);
    }
}
