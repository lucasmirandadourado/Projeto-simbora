package com.br.uepb.controller;

import java.util.ArrayList;  
import java.util.List;

import com.br.uepb.dao.UsuarioDao;
import com.br.uepb.domain.Sessao;
import com.br.uepb.domain.Usuario;
import com.br.uepb.exception.SessaoException;

/**
 * Controla a {@link Sessao}. Métodos: <li>abrirSessao</li> <li>encerrarSessao</li>
 * <li>getSessao</li> <li>setUsuarios</li>
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoController {

	private static List<Sessao> sessoes = new ArrayList<Sessao>();
	/**
	 * Foi necessario colocar <code>static</code> para não ser necessário
	 * instanciar outra {@link List}.
	 */
	private static List<Usuario> usuarios = new UsuarioDao().list();

	/**
	 * Ao abrir a sessão é necessário o login e senha do usuário. Esse método
	 * retornará o idSessao que é o login do usuário. Caso os parametros tenham
	 * sido inseridos errados será gerado um erro de <b>Usuário inexistente</b>
	 *
	 * <br>
	 * 
	 * @see {@link Usuario}
	 * 
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

	/**
	 * Encerra a Sess�o, para isso ele busca na {@link List} de {@link Usuario}
	 * e quando achar o usu�rio correspondente ao login remove ele da lista de
	 * {@link Sessao}.
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login)) {
				sessoes.remove(usuario);
				break;
			}
		}
	}

	/**
	 * Verifica se a idSessao passada existe.
	 * @param idSessao
	 * @return {@link Boolean}
	 */
	public static boolean hasSessao(String idSessao) {
		for (Sessao sessao : sessoes) {
			if (sessao.getIdSessao().equals(idSessao)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a variavel da List<Sessao>.
	 * @return vari�vel da List<Sessao> 
	 */
	public static List<Sessao> getSessoes() { 
		return sessoes;
	}

	/**
	 * Retorna a vari�vel da List<Usuario>
	 * @return Variavel da lista de usuarios.
	 */
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Adiciona uma Lista de usuarios na lista privada da classe.
	 * (List<Usuario>).
	 * 
	 * @param usuarios
	 */
	public static void setUsuarios(List<Usuario> usuarios) {
		SessaoController.usuarios = usuarios;
	}

}
