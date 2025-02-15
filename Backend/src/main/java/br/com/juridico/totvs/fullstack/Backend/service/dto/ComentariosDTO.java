package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentarios;

public class ComentariosDTO {
    private Long id;
    private String user;
    private String pontoTuristico;
    private String comentario;

    public ComentariosDTO(Long id, String user, String pontoTuristico, String comentario) {
        this.id = id;
        this.user = user;
        this.pontoTuristico = pontoTuristico;
        this.comentario = comentario;
    }

    public ComentariosDTO(Comentarios comentarios) {
        this.id = comentarios.getId();
        this.user = comentarios.getUser();
        this.pontoTuristico = comentarios.getPontoTuristico();
        this.comentario = comentarios.getComentario();
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
}