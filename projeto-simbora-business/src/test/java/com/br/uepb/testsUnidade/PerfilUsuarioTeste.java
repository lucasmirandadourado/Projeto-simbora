package com.br.uepb.testsUnidade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PerfilUsuarioTeste {

	UsuarioController usuarioController;
	PerfilController perfilUsuario;
	SessaoController sessao;
	CaronaController caronaController;
	
	@Before
	public void test() {
		usuarioController = new UsuarioController();
		perfilUsuario = new PerfilController();
		sessao = new SessaoController();
		caronaController = new CaronaController();
		
		caronaController.caronas.clear();
		usuarioController.usuarios.clear();
	}

	@Test
	public void criarUsuario() {

		usuarioController.criarUsuario("ana", "@na", "Ana Tenorio",
				"Guanabira, Para�ba", "anagatinha95@msn.com");

		try {
			sessao.abrirSessao("ana", "@na");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		// Ana vai criar uma carona
		try {
			caronaController.cadastrarCarona("ana", "Campina Grande", "Patos", "30/11/2015", "22:00", "2");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		// Fazer os Tests de login errado
		try {
			perfilUsuario.getAtributoPerfil(null, "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}

		// Fazer os Tests de login errado
		try {
			perfilUsuario.getAtributoPerfil(" ", "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}

		// Fazer os tests com login inexistente

		try {
			perfilUsuario.getAtributoPerfil("rodolfo", "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}

		// Acessar os perfis que n�o existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", null);

		} catch (PerfilException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "      ");

		} catch (PerfilException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}		
		
		// Atributos que n�o existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", "quantidade de filhos");
		} catch (PerfilException e) {
			assertEquals("Login inv�lido", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}		
		// Acessar os perfis que existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de caronas");

		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		 
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "");
		} catch (PerfilException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
	}
}
