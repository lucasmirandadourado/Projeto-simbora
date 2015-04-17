package com.br.uepb.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.controller.SessaoController;
import com.br.uepb.controller.UsuarioController;
import com.br.uepb.exception.SessaoException;

public class SolicitacaoCarona {
	
	SolicitacaoCarona solicitacao;
	UsuarioController usuario;
	SessaoController sessao;

	@Before
	public void test() {
		solicitacao = new SolicitacaoCarona();
		usuario = new UsuarioController();
		sessao = new SessaoController();
	}

	@Test(expected = SessaoException.class)
	public void criarUsuarioErro() throws SessaoException {
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		Assert.assertEquals("mark", sessao.abrirSessao("mark", "m@rk"));
		Assert.assertEquals("lucas", sessao.abrirSessao("lucas", "12345"));
	}

	@Test
	public void criarCaronas() {
		
	}
}
