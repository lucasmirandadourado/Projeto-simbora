package com.br.uepb.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.Usuario;
import com.br.uepb.controller.UsuarioController;
import com.br.uepb.exception.UsuarioException;

public class UsuarioTest {

	UsuarioController controller;
	Usuario usuario;
	
	
	@Before
	public void test() {
		usuario = new Usuario();
		controller = new UsuarioController();
	}
	
	@Test(expected = UsuarioException.class)
	public void testCriarUsuario(){
		
		controller.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");
		controller.criarUsuario("steve", "5t3v3", "Steven Paul Jobs", "Palo Alto, California", "jobs@apple.com");
		controller.criarUsuario("bill", "severino", "William Henry Gates III", "Medina, Washington", "billzin@msn.com");
		
		controller.criarUsuario("bill", "severino", "William Henry Gates III", "Medina, Washington", "billzin@msn.com");
		
		Assert.assertEquals(3, controller.getSize());		
		
	}
	
	
	@Test
	public void testGetAtributoUsuario(){
		
		Assert.assertEquals("Mark Zuckerberg", controller.getAtributoUsuario("mark", "nome"));
		Assert.assertEquals("Palo Alto, California", controller.getAtributoUsuario("mark", "endereco"));
				
		Assert.assertEquals("William Henry Gates III", controller.getAtributoUsuario("bill", "nome"));
		Assert.assertEquals("Medina, Washington", controller.getAtributoUsuario("bill", "endereco"));
		
		
	}
	
	/*public void testZerarLimparDados(){
		controller.zerarSistema();
		Assert.assertEquals(0, controller.getSize());
	}*/

}
