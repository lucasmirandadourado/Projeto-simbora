package com.br.uepb.controller;

import java.util.List; 

import com.br.uepb.dao.UsuarioDaoImp;
import com.br.uepb.domain.Usuario;
import com.br.uepb.exception.UsuarioException;

/**
 * Essa Classe faz todas as opera��es de cria��o de usuario, getting e settings,
 * verificar se o login � valido e retorna nos atributos (origem, destino,
 * horario da corona e data).
 * 
 * 
 * @author Lucas Miranda e Bruno Clementino
 * @see Usuario
 */
public class UsuarioController {

	Usuario usuario;
	String mensagemErro = "";

	/**
	 * Aqui acredito que foi uma paradigma de programa��o bem conhecida para
	 * poder solucionar esse problema. POG (Programa��o Orientada a Gambiarra).
	 * Basicamente o que eu fiz: Como tivemos que criar a Class
	 * {@link SessaoController} ai no metodo {@link SessaoController}
	 */
	public static List<Usuario> usuarios = SessaoController.getUsuarios();

	public void zerarSistema() {
		for (Usuario usuario : usuarios) {
			try {
				new UsuarioDaoImp().save(usuario);
			} catch (Exception e) {
			}
		}
		usuarios.clear();
	}

	/**
	 * Para criar o usuario, ele faz uma verifica��o se o usu�rio � valido ou
	 * n�o
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws UsuarioException
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws UsuarioException {
		usuario = new Usuario();

		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setEndereco(endereco);
		usuario.setEmail(email);

		if (ehUsuarioValido(usuario) && ehUsuarioNovo(usuario)) {
			usuarios.add(usuario);
		} else {
			throw new UsuarioException(mensagemErro);
		}
	}

	/**
	 * Percorre a lista de {@link Usuario} e verifica se j� existe um Login ou
	 * email iguais. Caso n�o tenha returna um <code>true</code>.
	 * 
	 * @param user
	 * @return {@link Boolean}
	 */
	private boolean ehUsuarioNovo(Usuario user) { 
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(user.getLogin())) {
				mensagemErro = "J� existe um usu�rio com este login";
				return false;
			}
			if (usuario.getEmail().equals(user.getEmail())) {
				mensagemErro = "J� existe um usu�rio com este email";
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se o login, nome e e-mail est�o <code>null</code> ou v�zios.
	 * 
	 * @param usuario
	 * @return
	 */
	private boolean ehUsuarioValido(Usuario usuario) {
		if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
			mensagemErro = "Login inv�lido";
			return false;
		}
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			mensagemErro = "Nome inv�lido";
			return false;
		}
		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			mensagemErro = "Email inv�lido";
			return false;
		}
		return true;
	}

	/**
	 * Retorna o atributo que usuario precisar (nome, endere�o ou email). Para isso 
	 * ele chama os metoso ehLoginExistente e  ehAtributoExistente.
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws UsuarioException
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws UsuarioException {

		if (login == null || login.isEmpty()) {
			throw new UsuarioException("Login inv�lido");
		}
		if (atributo == null || atributo.isEmpty()) {
			throw new UsuarioException("Atributo inv�lido");
		}
		if (!ehLoginExistente(login)) {
			throw new UsuarioException("Usu�rio inexistente");
		}
		if (!ehAtributoExistente(atributo)) {
			throw new UsuarioException("Atributo inexistente");
		}

		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				switch (atributo) {
				case "endereco":
					return usuario.getEndereco();
				case "nome":
					return usuario.getNome();
				case "email":
					return usuario.getEmail();
				}
				return usuario.getNome();
			}
		}
		return "";
	}

	/**
	 * Verifica se os atributos passados fazem parte dos atributos que seram mostrados.
	 * @param atributo
	 * @return {@link Boolean}
	 */
	private boolean ehAtributoExistente(String atributo) {
		if (atributo.equals("nome") || atributo.equals("endereco")
				|| atributo.equals("email")) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se o login existe.
	 * @param login
	 * @return {@link Boolean}
	 */
	private boolean ehLoginExistente(String login) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login))
				return true;
		}
		return false;
	}

	public void encerrarSistema() {

	}
	/**
	 * retorna a quantidade de usuarios.
	 * @return int
	 */
	public int getSize() {
		return usuarios.size();
	}

}
