package br.com.juridico.totvs.fullstack.Backend.domain;

import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoDTO;

public class PontoTuristico {

    private Long id;
    private String nome;
    private String cidade;
    private String pais;
    private String melhorEstacao;
    private String resumo;

    public PontoTuristico(Long id, String nome, String cidade, String pais, String melhorEstacao, String resumo) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.melhorEstacao = melhorEstacao;
        this.resumo = resumo;
    }

    public PontoTuristico(PontoTuristicoDTO pontoTuristicoDTO) {
        this.id = pontoTuristicoDTO.getId();
        this.nome = pontoTuristicoDTO.getNome();
        this.cidade = pontoTuristicoDTO.getCidade();
        this.pais = pontoTuristicoDTO.getPais();
        this.melhorEstacao = pontoTuristicoDTO.getMelhorEstacao();
        this.resumo = pontoTuristicoDTO.getResumo();
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