package com.br.uepb.exception;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioException(String mensagem) {
		super(mensagem);
	}
}
