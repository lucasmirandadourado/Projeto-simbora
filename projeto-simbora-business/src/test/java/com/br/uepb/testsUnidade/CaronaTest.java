package com.br.uepb.testsUnidade;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.br.uepb.controller.CaronaController;
import com.br.uepb.controller.SessaoController;
import com.br.uepb.controller.UsuarioController;
import com.br.uepb.dao.CaronaDao;
import com.br.uepb.dao.UsuarioDao;
import com.br.uepb.domain.Carona;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.exception.CaronaException;
import com.br.uepb.exception.SessaoException;


/**
 * Cria os tests referentes a carona. Utiliza do Anotation FixMethodOrder para
 * determina a ordem de execu��o dos metodos (Essa fun��o executa os metodos em
 * ordem alfabetica, por isso que os metodos est�o com o prefixo test[LETRA]_
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CaronaTest {

	Carona carona;
	CaronaController controller;
	UsuarioController usuarioController;
	SessaoController sessaoController;

	@Before
	public void test() {

		new CaronaDao().excluirTudo();
		new UsuarioDao().excluirTudo();
		
		carona = new Carona();
		controller = new CaronaController();
		usuarioController = new UsuarioController();

		sessaoController = new SessaoController();

	}

	@Test
	public void testA_CadastrarCarona() throws Exception {
		// Serão criados 3 usuarios. (cria usuario, abrir sessao e criarCarona)
		usuarioController.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		try {
			assertEquals("mark", sessaoController.abrirSessao("mark", "m@rk"));
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}

		try {
			String v = controller.cadastrarCarona("mark", "Campina Grande",
					"João Pessoa", "27/10/2014", "02:09", "4");
			System.out.println("idCarona_Mark: " + v);
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Criado o 2º usuário
		usuarioController.criarUsuario("thiago", "thi@go", "Thiago Batista",
				"Rua", "thiagobatista@gmail.com");
		try {
			sessaoController.abrirSessao("thiago", "thi@go");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		try {
			String v = controller.cadastrarCarona("thiago", "Campina Grande",
					"Jo�o Pessoa", "22/10/2014", "22:49", "3");
			System.out.println("IDCarona_thiago: " + v);
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Criando o 3� Usuario
		usuarioController.criarUsuario("lucas", "luc@s", "Lucas Miranda",
				"Rua", "lucas@gmail.com");
		try {
			sessaoController.abrirSessao("lucas", "luc@s");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		try {
			controller.cadastrarCarona("lucas", "Campina Grande",
					"Jo�o Pessoa", "22/10/2014", "03:00", "3");
		} catch (Exception e) {
			assertEquals("Sess�o inexistente", e.getMessage());
		}

		// Criar usu�rio: Querendo uma carona
		usuarioController.criarUsuario("bruno", "bruno2", "Bruno Clementino",
				"Rua", "bruno@gmail.com");
		try {
			sessaoController.abrirSessao("bruno", "bruno2");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		
		// Cadastro com erros!
		try {
			controller.cadastrarCarona("", "Adustina", "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}
		// Origem = "" ou null
		try {
			controller.cadastrarCarona("bruno", "", "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		try {
			controller.cadastrarCarona("bruno", null, "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// Destino = "" ou null
		try {
			controller.cadastrarCarona("bruno", "Adustina", " ", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		try {
			controller.cadastrarCarona("bruno", "Adustina", null, "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// Data = "" ou null
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", "", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inv�lida", e.getMessage());
		}
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", null, "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inv�lida", e.getMessage());
		}
		// Hora = "" ou null
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "", "3");
		} catch (CaronaException e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", null, "3");
		} catch (CaronaException e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}
		// Quantidade de vagas = "" ou null
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "10:00", "");
		} catch (CaronaException e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}
		try {
			controller.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "10:00", null);
		} catch (CaronaException e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}
		
		
	}

	// Verificar se localiza a carona com a sess�o null
	@Test
	public void testB_LocalizarCaronaNull() throws Exception {
		// Localizar a sessao. Tests com o idSessao = null
		try {
			assertEquals("{}", controller.localizarCarona(null,
					"Campina Grande", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}

		// origem = !
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "!", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = ()
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "()", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = -
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "-", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = !?
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "!?", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// destino = .
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "Campina Grande", "."));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// destino = ()
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "Campina Grande", "()"));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// destino = !?
		try {
			assertEquals("{}",
					controller.localizarCarona("bruno", "Campina Grande", "!?"));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		
		try {
			assertEquals("{1,2,3}", controller.localizarCarona("bruno",
					"Campina Grande", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}

		// idSessao = null
		try {
			assertEquals("{}", controller.localizarCarona(null,
					"S�o Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}

		try {
			assertEquals("{}", controller.localizarCarona("", "S�o Francisco",
					"Palo Alto"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}
	}

	@Test
	public void testC_origemDestino() {
		try {
			assertEquals("{1,2,3}", controller.localizarCarona("bruno", "", "Jo�o Pessoa"));
		} catch (Exception e) {
			assertEquals(null, e.getMessage());
		}
		try {
			controller.localizarCarona("bruno", "Campina Grande", "");
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}

		try {
			controller.localizarCarona("bruno", "Cabaceiras", "Iguat�");
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
		try {
			controller.localizarCarona("bruno", "", "");
		} catch (Exception e) {
			e.getMessage();
		}

		
		try {
			assertEquals("{1,2,3}", controller.localizarCarona("bruno", null, null));
		} catch (Exception e) {
			assertEquals(null, e.getMessage());
		}

		try {
			assertEquals("{0,1,2,3}",controller.localizarCarona("bruno", "", ""));
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testD_atributos() {
		// IdCarona == ""
		try {
			controller.getAtributoCarona("", "Origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona � inv�lido", e.getMessage());
		}

		// IdCarona = null
		try {
			controller.getAtributoCarona(null, "Origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona � inv�lido", e.getMessage());
		}

		// IdCarona inexistente
		try {
			controller.getAtributoCarona("9", "nome");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}

		// Atributo = ""
		try {
			controller.getAtributoCarona("0", "");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = null
		try {
			controller.getAtributoCarona("0", null);
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = Origem
		try {
			controller.getAtributoCarona("1", "origem");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}

		// Atributo = destino
		try {
			controller.getAtributoCarona("0", "destino");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = data
		try {
			controller.getAtributoCarona("0", "data");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = hora
		try {
			controller.getAtributoCarona("0", "vagas");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = hora
		try {
			controller.getAtributoCarona("0", "Quanto Custa");
		} catch (CaronaException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}
	}

	@Test
	public void testE_trajeto() {
		// IdCarona = null
		try {
			controller.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inv�lida", e.getMessage());
		}

		// IdCarona = ""
		try {
			controller.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}

		// IdCarona = Alfanumerico
		try {
			controller.getTrajeto("f");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			controller.getTrajeto("2");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}
		try {
			controller.getTrajeto("33");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testF_verCarona() {
		// IdCarona = "" ou null
		try {
			controller.getCarona(null);
		} catch (Exception e) {
			assertEquals("Carona Inv�lida", e.getMessage());
		}
		try {
			controller.getCarona("");
		} catch (Exception e) {
			assertEquals("Carona Inv�lida", e.getMessage());
		}
		try {
			controller.getCarona("100");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		try {
			assertEquals("S�o Paulo para Campina Grande, no dia 30/11/2015, as 22:00", controller.getCarona("0"));
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}		

	}

	@Test
	public void testG_getCaronaTest() {
		// IDCarona existente!
		try {
			controller.getCarona("bruno");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}

		try {
			controller.getCarona("9");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}

		try {
			controller.getCarona("k");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
	}

	@Test
	public void testH_encerrarSessao() {

		sessaoController.encerrarSessao("bruno");
		sessaoController.encerrarSessao("m@rk");

		// Limpar tudo do BD
		usuarioController.zerarSistema();
		controller.zerarSistema();
	}

	@Test
	public void testI_ZerarSistema() {
		controller.zerarSistema();
		assertEquals(null, controller.getCarona());
	}
}
