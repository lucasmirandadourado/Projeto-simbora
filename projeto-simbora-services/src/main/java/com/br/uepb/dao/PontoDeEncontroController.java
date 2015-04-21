package com.br.uepb.dao;

import java.util.ArrayList; 
import java.util.List;

import com.br.uepb.domain.PontoDeEncontro;


public class PontoDeEncontroController {
	
	PontoDeEncontro pontoDeEncontro;
	List<PontoDeEncontro> pontoDeEncontros = new ArrayList<>();
	
	public void zerarSistema(){
		pontoDeEncontros.clear();
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos){
		
		pontoDeEncontro = new PontoDeEncontro();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(pontos);
		
		pontoDeEncontros.add(pontoDeEncontro);
		
		return "";
	}

}
