package br.com.juridico.totvs.fullstack.Backend.domain;

import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentariosDTO;
import java.util.Date;

public class Comentarios {

    private Long id;
    private String user;
    private String pontoTuristico;
    private String comentario;
    private Date data;

    public Comentarios(Long id, String user, String pontoTuristico, String comentario, Date data) {
        this.id = id;
        this.user = user;
        this.pontoTuristico = pontoTuristico;
        this.comentario = comentario;
        this.data = data;
    }

    public Comentarios(ComentariosDTO comentariosDTO) {
        this.id = comentariosDTO.getId();
        this.user = comentariosDTO.getUser();
        this.pontoTuristico = comentariosDTO.getPontoTuristico();
        this.comentario = comentariosDTO.getComentario();
        this.data = comentariosDTO.getData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPontoTuristico() {
        return pontoTuristico;
    }

    public void setPontoTuristico(String pontoTuristico) {
        this.pontoTuristico = pontoTuristico;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}