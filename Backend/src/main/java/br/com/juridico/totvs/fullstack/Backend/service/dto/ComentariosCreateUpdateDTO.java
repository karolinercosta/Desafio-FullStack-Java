package br.com.juridico.totvs.fullstack.Backend.service.dto;

import java.sql.Date;

public class ComentariosCreateUpdateDTO {
    private String user;
    private String pontoTuristico;
    private String comentario;
    private Date data;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}