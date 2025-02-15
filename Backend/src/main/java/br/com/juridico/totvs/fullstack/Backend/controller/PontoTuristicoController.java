package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.PontoTuristicoService;
import br.com.juridico.totvs.fullstack.Backend.service.PontoTuristicoServiceImpl;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/pontos-turisticos")
public class PontoTuristicoController {
    private final PontoTuristicoService PontoTuristicoService;

    public PontoTuristicoController(PontoTuristicoService PontoTuristicoService){
        this.PontoTuristicoService = PontoTuristicoService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public PontoTuristicoDTO create(@RequestBody PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO){
        return this.PontoTuristicoService.create(PontoTuristicoCreateUpdateDTO);
    }

    @GetMapping
    public List<PontoTuristicoDTO> getAll(){
        return this.PontoTuristicoService.getAllPontoTuristico();
    }



    @GetMapping("{idPontoTuristico}")
    public PontoTuristicoDTO getPontoTuristicoById(@PathVariable Long idPontoTuristico){
        return this.PontoTuristicoService.getPontoTuristicobyId(idPontoTuristico);
    }

    @DeleteMapping("{idPontoTuristico}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idPontoTuristico){
        this.PontoTuristicoService.delete(idPontoTuristico);
    }

    @PutMapping("{idPontoTuristico}")
    public PontoTuristicoDTO update(@PathVariable Long idPontoTuristico,
                          @RequestBody PontoTuristicoCreateUpdateDTO PontoTuristicoCreateUpdateDTO ){
        return this.PontoTuristicoService.update(idPontoTuristico, PontoTuristicoCreateUpdateDTO);
    }
}
