package com.br.uepb.business;

/**
 * Classe que define o local de origem, local de destino, data da carona,
 * hor�rio de sa�da e a quantidade de vaga no carro.
 * 
 * @author Lucas Miranda
 * @author Bruno Jos� Clementino
 *
 */
public class Carona {

	private String localDeOrigem;
	private String localDeDestino;
	private String data;
	private String horarioDeSaida;
	private String qtdDeVagas;

	private String idCarona;
	private String idSessao;
	/**
	 * M�todo construtor default.
	 */
	public Carona() {
		// TODO Auto-generated constructor stub
	}

	public String getLocalDeOrigem() {
		return localDeOrigem;
	}

	public void setLocalDeOrigem(String localDeOrigem) {
		this.localDeOrigem = localDeOrigem;
	}

	public String getLocalDeDestino() {
		return localDeDestino;
	}

	public void setLocalDeDestino(String localDeDestino) {
		this.localDeDestino = localDeDestino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorarioDeSaida() {
		return horarioDeSaida;
	}

	public void setHorarioDeSaida(String horarioDeSaida) {
		this.horarioDeSaida = horarioDeSaida;
	}

	public String getQtdDeVagas() {
		return qtdDeVagas;
	}

	public void setQtdDeVagas(String qtdDeVagas) {
		this.qtdDeVagas = qtdDeVagas;
	}

	public String getIdCarona() {
		return idCarona;
	}

	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	
}
