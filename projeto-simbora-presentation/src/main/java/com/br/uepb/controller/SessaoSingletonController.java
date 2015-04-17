/**
 * 
 */
package com.br.uepb.controller;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;

import com.br.uepb.business.Sessao;
import com.br.uepb.business.Usuario;
import com.br.uepb.exception.SessaoException;


/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoSingletonController {

	/**
	 * Aqui esse atributo j� � criado como uma instancia da pr�pria classe j�
	 * que nenhuma classe poder� clama-l�!
	 */
	private static SessaoSingletonController sessaoSingleton = new SessaoSingletonController();

	
	static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	private static HashMap<Sessao, Usuario> listaSessao = new HashMap<Sessao, Usuario>();

	/**
	 * Garantindo que ser� instanciado apenas uma unica vez pela mesma classe!
	 */
	private SessaoSingletonController() {
	}

	/**
	 * Ao abrir a sess�o � necess�rio o login e senha do usu�rio. Esse m�todo
	 * retornar� o idSessao que � o login do usu�rio. Caso os parametros tenham
	 * sido inseridos errados ser� gerado um erro de <b>Usu�rio inexistente</b>
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws SessaoException
	 */

	public static String abrirSessao(String login, String senha)
			throws SessaoException {
		for (Usuario usuario : listaUsuarios) {
			if (usuario.getLogin().equals(login)
					&& usuario.getSenha().equals(senha)) {
				Sessao ss = new Sessao();
				ss.setIdSessao(usuario.getLogin());
				ss.setIdUsuario(login);
				listaSessao.put(ss, usuario);
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
	 * Esse m�todo faz uma busca no {@link HashMap} para encontrar o 
	 * usu�rio com o <code>login</code> correspondente ao usu�rio. Ap�s 
	 * encontrar ser� removido, se n�o encontrar significa que n�o existe 
	 * nenhum usu�rio com esse login. 
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		for (Usuario usuario : listaUsuarios) {
			if (listaSessao.get(usuario).getLogin().equals(login)) {
				listaSessao.remove(usuario);
				break;
			}
		}			
	}
}
