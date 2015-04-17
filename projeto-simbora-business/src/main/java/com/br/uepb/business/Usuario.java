package com.br.uepb.business;

/**
 * Usu�rio b�sico. Ele tem como fun��es: 
 * <li>logar ao sistema</li>
 * <li>buscar carona</li>
 * <li>solicitar carona</li>
 * <li>sugerir local da carona</li>
 * 
 * @author Lucas Miranda
 * @author Bruno Jos� Clementino
 *
 */

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	/** 
	 * M�todo contrutor Default.
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
