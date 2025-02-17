package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentarios;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ComentariosService {
    List<Comentarios> listComentarios = null;
    public ComentariosDTO create(ComentariosCreateUpdateDTO ComentariosCreateUpdateDTO);
    public ComentariosDTO update(Long id, ComentariosCreateUpdateDTO ComentariosCreateUpdateDTO);
    public void delete(Long id);
    public List<ComentariosDTO> getComentariosbyPontoTuristico(Long id);
    public ComentariosDTO getComentariosbyId(Long id);
    public List<ComentariosDTO> getAllComentarios();
}
