package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.business.SolicitacaoVagas;

public class SolicitacaoVagasController {

	SolicitacaoVagas solicitacaoVagas;
	static List<SolicitacaoVagas> solicitacoesVagas = new ArrayList<>();
	
	public void zerarSistema(){
		solicitacoesVagas.clear();
	}
	
	public String solicitarVaga(String idSessao, String idCarona){
		
		solicitacaoVagas=new SolicitacaoVagas();
		
		solicitacaoVagas.setIdSessao(idSessao);
		solicitacaoVagas.setIdCarona(idCarona);
		
		solicitacoesVagas.add(solicitacaoVagas);
		
		solicitacaoVagas.setIdSolicitacao(solicitacoesVagas.indexOf(solicitacaoVagas) +"");
		
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
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao){
		for(SolicitacaoVagas solicitacao : solicitacoesVagas){
			
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				solicitacao.setStatus("Recusada");
				return;
			}
			
		}
	}
	
}
