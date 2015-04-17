package com.br.uepb.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.Carona;
import com.br.uepb.controller.CaronaController;
import com.br.uepb.controller.SessaoController;
import com.br.uepb.controller.UsuarioController;

public class CaronaTest {
	
	Carona carona;
	CaronaController controller;
	UsuarioController usuarioController;
	SessaoController sessaoController;

	@Before
	public void test() {
		carona = new Carona();
		controller = new CaronaController();
		usuarioController = new UsuarioController();
		sessaoController = new SessaoController();
		
		usuarioController.zerarSistema();
	}
	
	@Test
	public void testCadastrarCarona() throws Exception{
		
		usuarioController.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");
		String sessaoMark = sessaoController.abrirSessao("mark", "m@rk");
		Assert.assertEquals("{}", controller.localizarCarona(sessaoMark, "Campina Grande", "Jo�o Pessoa"));
		Assert.assertEquals("{}", controller.localizarCarona(sessaoMark, "S�o Francisco", "Palo Alto"));
		Assert.assertEquals("{}", controller.localizarCarona(sessaoMark, "Rio de Janeiro", "S�o Paulo"));
		
		sessaoMark = sessaoController.abrirSessao("mark", "m@rk");
		
		String carona1ID = controller.cadastrarCarona(sessaoMark, "Campina Grande", "Jo�o Pessoa", "23/06/2013", "16:00", "3");
		Assert.assertEquals("Campina Grande", controller.getAtributoCarona(carona1ID, "origem"));
		Assert.assertEquals("Jo�o Pessoa", controller.getAtributoCarona(carona1ID, "destino"));
		Assert.assertEquals("Campina Grande - Jo�o Pessoa", controller.getTrajeto(carona1ID));
		
		String carona2ID = controller.cadastrarCarona(sessaoMark, "Rio de Janeiro", "S�o Paulo", "31/05/2013", "08:00", "2");
		Assert.assertEquals("31/05/2013", controller.getAtributoCarona(carona2ID, "data"));
		Assert.assertEquals("2", controller.getAtributoCarona(carona2ID, "vagas"));
		
		Assert.assertEquals("{}", controller.localizarCarona(sessaoMark, "S�o Francisco", "Palo Alto"));
		Assert.assertEquals("{"+carona2ID+"}", controller.localizarCarona(sessaoMark, "Rio de Janeiro", "S�o Paulo"));
		
		Assert.assertEquals("{"+carona1ID+"}", controller.localizarCarona(sessaoMark, "", "Jo�o Pessoa"));	
		
	}

}
