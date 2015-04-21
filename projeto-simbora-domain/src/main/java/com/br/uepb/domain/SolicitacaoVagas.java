package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOLICITACAOVAGAS")
public class SolicitacaoVagas {
	
	@Id
	@Column(name = "idSolicitacao")
	private String idSolicitacao;
	private String idSessao;//identifica��o de quem solicitou a vaga
	private String idCarona;
	private String status = "Pendente";//Se a solicita��o foi Aceita, est� Pendente, ou foi Recusada.
	
	public String getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public String getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	public String getIdCarona() {
		return idCarona;
	}
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
