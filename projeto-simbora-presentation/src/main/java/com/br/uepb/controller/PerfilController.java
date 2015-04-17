package com.br.uepb.controller;

import com.br.uepb.business.Carona;
import com.br.uepb.business.SolicitacaoVagas;
import com.br.uepb.business.Usuario;
import com.br.uepb.exception.PerfilException;
import com.br.uepb.exception.UsuarioException; 


public class PerfilController {
	
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
		
		for(Usuario usuario : new UsuarioController().usuarios){
			if(usuario.getLogin().equals(login)){
				return getAtributo(login, atributo);
			}
		}
		
		return null;
	}

	private String getAtributo(String login, String atributo) throws PerfilException {
		if(atributo==null || atributo.trim().isEmpty()){
			throw new PerfilException("Atributo inv�lido");
		}
		if(login==null || login.trim().isEmpty()){
			throw new PerfilException("Login inv�lido");
		}
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
		
		return new UsuarioController().getAtributoUsuario(login, atributo);
	}

}
