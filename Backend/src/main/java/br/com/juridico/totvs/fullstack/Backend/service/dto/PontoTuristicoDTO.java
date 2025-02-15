package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;

public class PontoTuristicoDTO {
    private Long id;
    private String nome;
    private String cidade;
    private String pais;
    private String melhorEstacao;
    private String resumo;

    public PontoTuristicoDTO(Long id, String nome, String cidade, String pais, String melhorEstacao, String resumo) {
        this.id = id;
        this.nome = nome;
    }

    public PontoTuristicoDTO(PontoTuristico pontoTuristico) {
        this.id = pontoTuristico.getId();
        this.nome = pontoTuristico.getNome();
        this.cidade = pontoTuristico.getCidade();
        this.pais = pontoTuristico.getPais();
        this.melhorEstacao = pontoTuristico.getMelhorEstacao();
        this.resumo = pontoTuristico.getResumo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMelhorEstacao() {
        return melhorEstacao;
    }

    public void setMelhorEstacao(String melhorEstacao) {
        this.melhorEstacao = melhorEstacao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}