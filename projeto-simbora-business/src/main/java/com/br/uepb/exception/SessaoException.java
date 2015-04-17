/**
 * 
 */
package com.br.uepb.exception;

import com.br.uepb.business.Sessao;

/**
 * Tratando errro de acesso da {@link Sessao}
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SessaoException(String mensagem) {
		super(mensagem);
	}
}
