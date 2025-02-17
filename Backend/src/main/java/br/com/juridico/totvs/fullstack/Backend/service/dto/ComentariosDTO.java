package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentarios;
import java.util.Date;

public class ComentariosDTO {
    private Long id;
    private String user;
    private String pontoTuristico;
    private String comentario;
    private Date data;

    public ComentariosDTO(Long id, String user, String pontoTuristico, String comentario, Date data) {
        this.id = id;
        this.user = user;
        this.pontoTuristico = pontoTuristico;
        this.comentario = comentario;
        this.data = data;
    }

    public ComentariosDTO(Comentarios comentarios) {
        this.id = comentarios.getId();
        this.user = comentarios.getUser();
        this.pontoTuristico = comentarios.getPontoTuristico();
        this.comentario = comentarios.getComentario();
        this.data = comentarios.getData();
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