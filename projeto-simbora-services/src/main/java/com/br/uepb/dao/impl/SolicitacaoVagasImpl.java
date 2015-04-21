package com.br.uepb.dao.impl;

import java.util.List; 

import com.br.uepb.domain.SolicitacaoVagas;

public interface SolicitacaoVagasImpl {

	public void save(SolicitacaoVagas solicitacaoVagas);
	public SolicitacaoVagas getSolicitacaoVagas(String idSolicitacaoVagas);
	public List<SolicitacaoVagas> list();
	public void remove(SolicitacaoVagas solicitacaoVagas);
	public void update(SolicitacaoVagas solicitacaoVagas);
	public void excluirTudo();
	 
}
