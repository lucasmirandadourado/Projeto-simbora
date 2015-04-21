package com.br.uepb.testsUnidade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.controller.CaronaController;
import com.controller.SessaoController;
import com.controller.SolicitacaoVagasController;
import com.controller.UsuarioController;
import com.exception.SessaoException;
import com.model.SolicitacaoPontoDeEncontro;

public class SolicitacaoPontoEncontroTest {

	UsuarioController usuario;
	SessaoController sessao;
	SolicitacaoVagasController solicitarVagas;
	CaronaController carona;
	SolicitacaoPontoDeEncontro pontoEncontro;
	
	@Before
	public void inicilizarTest() {
		usuario = new UsuarioController();
		sessao = new SessaoController();
		solicitarVagas = new SolicitacaoVagasController();
		carona = new CaronaController();
		
		usuario.usuarios.clear();
		solicitarVagas.solicitacoesVagas.clear();
		
	}
	
	@Test
	public void test() {
		
		/**
		 * Os Tests de cria��o de usu�rio e abrir sess�o foram feitas em UsuarioTest
		 */
		usuario.criarUsuario("hebert", "hebertPS", "Hebert Viana",
				"S�o Paulo", "hebert@facebook.com");
		try {
			sessao.abrirSessao("hebert", "hebertPS");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("hebert", "S�o Paulo", "Campina Grande", "30/11/2015", "22:00", "4");
		} catch (Exception e) {
			assertEquals("Sess�o inexis tente", e.getMessage());
		}

		
		solicitarVagas.solicitarVaga("hebert", "2");
		
	
	}

}
