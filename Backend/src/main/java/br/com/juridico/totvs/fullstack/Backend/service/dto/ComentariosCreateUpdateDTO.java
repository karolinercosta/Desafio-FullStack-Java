package br.com.juridico.totvs.fullstack.Backend.service.dto;

public class ComentariosCreateUpdateDTO {
    private String user;
    private String pontoTuristico;
    private String comentario;

    public ComentariosCreateUpdateDTO() {
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