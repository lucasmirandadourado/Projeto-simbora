package com.br.uepb.dao.impl;

import java.util.List; 

import com.br.uepb.domain.PontoDeEncontro;

public interface PontoDeEncontroImpl {

	public void save(PontoDeEncontro pontoDeEncontro);
	public PontoDeEncontro getPontoDeEncontro(String idPontoDeEncontro);
	public List<PontoDeEncontro> list();
	public void remove(PontoDeEncontro pontoDeEncontro);
	public void update(PontoDeEncontro pontoDeEncontro);
	public void excluirTudo();
	 
}
