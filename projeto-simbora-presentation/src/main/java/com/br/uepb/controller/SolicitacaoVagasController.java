package com.br.uepb.controller;


import java.util.List;

import com.DAO.SolicitacaoVagasDaoImp;
import com.model.Carona;
import com.model.SolicitacaoVagas;

public class SolicitacaoVagasController {

	SolicitacaoVagas solicitacaoVagas;
	public static List<SolicitacaoVagas> solicitacoesVagas = new SolicitacaoVagasDaoImp().list();
	
	public void zerarSistema(){
		for (SolicitacaoVagas solicitacaoVagas : solicitacoesVagas) {
			try {
				new SolicitacaoVagasDaoImp().save(solicitacaoVagas);
			} catch (Exception e) {
			}
		}
		solicitacoesVagas.clear();
	}
	
	public String solicitarVaga(String idSessao, String idCarona){
		
		solicitacaoVagas=new SolicitacaoVagas();
		
		solicitacaoVagas.setIdSessao(idSessao);
		solicitacaoVagas.setIdCarona(idCarona);
		
		solicitacoesVagas.add(solicitacaoVagas);
		
		solicitacaoVagas.setIdSolicitacao(solicitacoesVagas.indexOf(solicitacaoVagas) +"V");//id da solicita��o de vaga
		
		return solicitacaoVagas.getIdSolicitacao();
	}
	
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){
			
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				solicitacao.setStatus("Aceita");
				new CaronaController().reduzQtdVagas(solicitacao.getIdCarona());
				return;
			}			
		}		
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){
			
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				if(solicitacao.getStatus().equals("Pendente")){

					solicitacao.setStatus("Recusada");
					return;
				}else{
					throw new Exception("Solicita��o inexistente");
				}
				
				
			}
			
		}
		
	}
	
	public String getAtributo(String idSolicitacao, String atributo) {
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				try {
					return new CaronaController().getAtributoCarona(solicitacao.getIdCarona(), atributo);
				} catch (Exception e) {
				}	
				if (atributo.equals("Dono da carona")) {
					for(Carona carona: CaronaController.getCaronas()){
						if(carona.getIdCarona().equals(solicitacao.getIdCarona())){
							return new UsuarioController().getAtributoUsuario(carona.getIdSessao(), "nome");
						}
					}
						
				}

				if (atributo.equals("Dono da solicitacao")) {
					return new UsuarioController().getAtributoUsuario(
							solicitacao.getIdSessao(), "nome");
				}
			}
		}

		return "";
	}
	
	public static boolean ehCaroneiro(String login){
		
		return false;
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){

			if(solicitacao.getIdCarona().equals(idCarona) && 
					CaronaController.ehMotorista(idSessao, idCarona) &&
					solicitacao.getStatus().equals("Aceita")){
				
				if (!flag) {
					ids += ",";
				}
				ids += solicitacao.getIdSolicitacao();
				flag = false;
				
			}			
		}
		return ids + "}";
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona) {

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){

			if(solicitacao.getIdCarona().equals(idCarona) && 
					CaronaController.ehMotorista(idSessao, idCarona) &&
					solicitacao.getStatus().equals("Pendente")){

				if (!flag) {
					ids += ",";
				}
				ids += solicitacao.getIdSolicitacao();
				flag = false;

			}			
		}
		return ids + "}";
	}
}
