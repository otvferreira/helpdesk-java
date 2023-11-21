/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author otvfe
 */
public class Ticket {
    private Integer id;
    private String assunto;
    private String setor;
    private String status;
    private String tipo_ocorr;
    private String dt_previsao;
    private String prioridade;
    private String solicitante;
    private String responsavel;
    private String conteudo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo_ocorr() {
        return tipo_ocorr;
    }

    public void setTipo_ocorr(String tipo_ocorr) {
        this.tipo_ocorr = tipo_ocorr;
    }

    public String getDt_previsao() {
        return dt_previsao;
    }

    public void setDt_previsao(String dt_previsao) {
        this.dt_previsao = dt_previsao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
}
