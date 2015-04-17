package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;

import com.model.PontoDeEncontro;

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
