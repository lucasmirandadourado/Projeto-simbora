package com.br.uepb.dao.impl;

import java.util.List; 

import com.br.uepb.domain.SolicitacaoPontoDeEncontro;

public interface SolicitacaoPontoDeEncontroImpl {

	public void save(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro);
	public SolicitacaoPontoDeEncontro getSolicitacaoPontoDeEncontro(String idSolicitacao);
	public List<SolicitacaoPontoDeEncontro> list();
	public void remove(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro);
	public void update(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro);
	public void excluirTudo();
	 
}
