package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */

@Entity
@Table(name="PONTODEENCONTRO")
public class PontoDeEncontro {
	
	@Id
	@Column(name = "idPonto")
	private String idPonto;
	private String idCarona;
	private String pontos;
	private String idSessao;
	
	public String getIdCarona() {
		return idCarona;
	}
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}
	public String getPontos() {
		return pontos;
	}
	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
	public String getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	public String getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(String idPonto) {
		this.idPonto = idPonto;
	}
	
	
}
