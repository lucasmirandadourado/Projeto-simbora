package com.br.uepb.dao.impl;

import java.util.List;   

import com.br.uepb.domain.Carona;

public interface CaronaImpl {

	public void save(Carona carona);
	public Carona getCarona(String idCarona);
	public List<Carona> list();
	public void remove(Carona carona);
	public void update(Carona carona);
	public void excluirTudo();
	 
}
