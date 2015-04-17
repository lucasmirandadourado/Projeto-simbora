package com.br.uepb.controller;

import java.util.ArrayList; 
import java.util.List;

import com.br.uepb.business.Sessao;
import com.br.uepb.business.Usuario;
import com.br.uepb.exception.SessaoException;



/**
 * Controla a {@link Sessao}. M�todos: <li>abrirSessao</li> <li></li>
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoController {

	private static List<Sessao> sessoes = new ArrayList<Sessao>();
	/**
	 * Foi necessario colocar <code>static</code> para n�o ser necess�rio
	 * instanciar outra {@link List}.
	 */
	private static List<Usuario> usuarios = new ArrayList<>();

	/**
	 * Ao abrir a sess�o � necess�rio o login e senha do usu�rio. Esse metodo
	 * retornar� o idSessao que � o login do usu�rio. Caso os parametros tenham sido 
	 * inseridos errados ser� gerado um erro de <b>Usu�rio inexistente</b>
	 *
	 *<br>@see {@link Usuario} 
	 * @param login
	 * @param senha
	 * @return
	 * @throws SessaoException
	 *
	 */
	public String abrirSessao(String login, String senha)
			throws SessaoException {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)
					&& usuario.getSenha().equals(senha)) {
				Sessao ss = new Sessao();
				ss.setIdSessao(usuario.getLogin());
				ss.setIdUsuario(login);
				sessoes.add(ss);
				return ss.getIdSessao();
			} else if (login == null || login.isEmpty()
					|| usuario.getLogin().equals(login)
					|| usuario.getSenha().equals(senha)) {
				throw new SessaoException("Login inv�lido");
			}
		}
		throw new SessaoException("Usu�rio inexistente");
	}

	public void encerrarSessao(String login) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				sessoes.remove(usuario); // N�o deveria retirar o usu�rio da
											// sessao?
				// usuarios.remove(usuario); // Remover o usu�rio seria tirar
				// ele do cadastro n�o seria???
				break;
			}
		}
	}

	public static boolean hasSessao(String idSessao) {
		for (Sessao sessao : sessoes) {
			if (sessao.getIdSessao().equals(idSessao)) {
				return true;
			}
		}
		return false;
	}

	public static List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		SessaoController.sessoes = sessoes;
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		SessaoController.usuarios = usuarios;
	}

}
