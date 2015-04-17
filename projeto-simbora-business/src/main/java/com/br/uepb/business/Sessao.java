package com.br.uepb.business;

import javax.validation.constraints.NotNull;


/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */

public class Sessao {
	
	@NotNull (message = "A idSessao não pode ser nullo")	
	private String idSessao;
	private String idUsuario;
	
	private boolean statusSessao = false;

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(boolean statusSessao) {
		this.statusSessao = statusSessao;
	}
	
	
	

}
