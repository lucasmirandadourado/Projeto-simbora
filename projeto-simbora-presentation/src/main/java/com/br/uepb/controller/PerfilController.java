package com.br.uepb.controller;

import java.util.ArrayList;  
import java.util.List;

import com.br.uepb.domain.Carona;
import com.br.uepb.domain.SolicitacaoVagas;
import com.br.uepb.domain.Usuario;
import com.br.uepb.exception.PerfilException;
import com.br.uepb.exception.UsuarioException;

public class PerfilController {
	
	public static List<String> caronasSegurasTranquilas = new ArrayList<>();
	public static List<String> caronasNaoFuncionaram = new ArrayList<>();
	public static List<String> faltaramNasVagas = new ArrayList<>();
	public static List<String> presenteNasVagas = new ArrayList<>();
	
	public String visualizarPerfil(String idSessao, String login) throws PerfilException{
		
		if(login==null || login.trim().isEmpty()){
			throw new PerfilException("Login inv�lido");
		}
		
		if(idSessao==null || idSessao.trim().isEmpty()){
			throw new PerfilException("Sess�o inv�lida");
		}
		
		for(Usuario usuario : new UsuarioController().usuarios){
			if(usuario.getLogin().equals(login)){
				return usuario.getLogin();
			}
		}
		
		throw new PerfilException("Login inv�lido");
		
	}
	
	public String getAtributoPerfil(String login, String atributo) throws PerfilException{
		if(atributo == null || atributo.trim().isEmpty()){ 
			throw new PerfilException("Atributo inv�lido");
		}
		if(login==null || login.trim().isEmpty()){
			throw new PerfilException("Login inv�lido");
		}
	
		for(Usuario usuario : new UsuarioController().usuarios) {
			if(usuario.getLogin().equals(login)){
				return getAtributo(login, atributo); 
			}  
		}
		throw new UsuarioException("Login inv�lido");
		
	}

	private String getAtributo(String login, String atributo) throws PerfilException {
		
		if(atributo.equals("historico de caronas")){
			String caron = "[";
			for(Carona carona : CaronaController.getCaronas()){
				if(carona.getIdSessao().equals(login)){
					caron+=carona.getIdCarona();
				}
			} 
			return caron+"]";
		}
		
		if(atributo.equals("historico de vagas em caronas")){
			String caron = "[";
			for(SolicitacaoVagas solicitacaoVagas : SolicitacaoVagasController.solicitacoesVagas){
				if(solicitacaoVagas.getIdSessao().equals(login)){
					caron+=solicitacaoVagas.getIdCarona();
				}
			}
			return caron+"]";
		}
		
		if(atributo.equals("caronas seguras e tranquilas")){
			int caron = 0;
			for(String idCarona : caronasSegurasTranquilas){
				if(CaronaController.ehMotorista(login, idCarona)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("caronas que n�o funcionaram")){
			int caron = 0;
			for(String idCarona : caronasNaoFuncionaram){
				if(CaronaController.ehMotorista(login, idCarona)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("faltas em vagas de caronas")){
			int caron = 0;
			for(String idUsuario : faltaramNasVagas){
				if(idUsuario.equals(login) && SolicitacaoVagasController.ehCaroneiro(login)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("presen�as em vagas de caronas")){
			int caron = 0;
			for(String idUsuario : presenteNasVagas){
				if(idUsuario.equals(login) && SolicitacaoVagasController.ehCaroneiro(login)){
					caron++;
				}
			}
			return caron+"";
		}
		
		return new UsuarioController().getAtributoUsuario(login, atributo);
	}

}
