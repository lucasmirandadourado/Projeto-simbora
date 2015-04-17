/**
 * 
 */
package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.business.PontoDeEncontro;
import com.br.uepb.business.SolicitacaoPontoDeEncontro;
import com.br.uepb.business.SolicitacaoVagas;
import com.br.uepb.exception.CaronaException;

/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SolicitacaoPontoDeEncontroController {

	// US04
	/**
	 * Quando o caroneiro aceitar, deve-se atualizar a quantidade de vagas do
	 * carro.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 */

	private static List<SolicitacaoPontoDeEncontro> solicitacoes = new ArrayList<>();
	private List<SolicitacaoVagas> solicitacoesVagas = SolicitacaoVagasController.solicitacoesVagas;
	
	
	private SolicitacaoPontoDeEncontro solicitacaoEncontro;
	private PontoDeEncontro pontoDeEncontro;

	public void zerarSistema(){
		solicitacoes.clear();
	}
	
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {

		if (pontos == null || pontos.isEmpty()) {
			throw new Exception("Ponto Inv�lido");
		}
		pontoDeEncontro = new PontoDeEncontro();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(pontos);
		pontoDeEncontro.setIdSessao(idSessao);

		solicitacaoEncontro = new SolicitacaoPontoDeEncontro();
		// 0 indica que esse ponto � o sugerido pelo caroneiro
		solicitacaoEncontro.setPontoDeEncontro(pontoDeEncontro, 0);

		solicitacoes.add(solicitacaoEncontro);
		solicitacaoEncontro.setIdSugestao(solicitacoes.indexOf(solicitacaoEncontro)
				+ "");// Para gerar o id da solicita��o

		return solicitacaoEncontro.getIdSugestao();
	}

	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {

		if (pontos == null || pontos.isEmpty()) {
			throw new Exception("Ponto Inv�lido");
		}

		pontoDeEncontro = new PontoDeEncontro();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(pontos);
		pontoDeEncontro.setIdSessao(idSessao);

		for (SolicitacaoPontoDeEncontro solicitacao : solicitacoes) {
			solicitacaoEncontro = solicitacao;
			if (solicitacao.getIdSugestao().equals(idSugestao)) {
				// 1 indica que esse ponto � resposta do motorista
				solicitacaoEncontro.setPontoDeEncontro(pontoDeEncontro, 1);
			}
		}

		return "";
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) {

		pontoDeEncontro = new PontoDeEncontro();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(ponto);
		pontoDeEncontro.setIdSessao(idSessao);

		PontoDeEncontro encontro;
		for (SolicitacaoPontoDeEncontro solicitacao : solicitacoes) {
			encontro = solicitacao.getPontoDeEncontro(0);
			if (encontro.getIdSessao().equals(idSessao)
					&& encontro.getIdCarona().equals(idCarona)) {
				// 2 indica que esse ponto � a confirma��o do encontro pelo caroneiro
				solicitacao.setPontoDeEncontro(pontoDeEncontro, 2);
				return solicitacao.getIdSugestao();
			}
		}

		return "";
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws CaronaException {
		for (SolicitacaoPontoDeEncontro solicitacao : solicitacoes) {
			if (solicitacao.getIdSugestao().equals(idSolicitacao)) {
				// return new
				// CaronaController().getAtributoCarona(encontro.getIdCarona(),
				// atributo);
				return getAtributo(solicitacao, atributo);
			}
		}
		return "";
	}

	private String getAtributo(SolicitacaoPontoDeEncontro solicitacao, String atributo) {
		PontoDeEncontro encontro = solicitacao.getPontoDeEncontro(2);
		try {
			return new CaronaController().getAtributoCarona(
					encontro.getIdCarona(), atributo);
		} catch (Exception e) {
		}

		if (atributo.equals("Dono da carona")) {
			encontro = solicitacao.getPontoDeEncontro(1);
			return new UsuarioController().getAtributoUsuario(
					encontro.getIdSessao(), "nome");
		}

		if (atributo.equals("Dono da solicitacao")) {
			encontro = solicitacao.getPontoDeEncontro(0);
			return new UsuarioController().getAtributoUsuario(
					encontro.getIdSessao(), "nome");
		}

		if (atributo.equals("Ponto de Encontro")) {
			encontro = solicitacao.getPontoDeEncontro(2);
			// retorna apenas o ponto de encontro marcado
			return encontro.getPontos();
		}

		return "";
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {

		for (SolicitacaoPontoDeEncontro solicitacao : solicitacoes) {

			if (solicitacao.getIdSugestao().equals(idSolicitacao)
					&& solicitacao.isEmAndamento()) {
				PontoDeEncontro encontro = solicitacao.getPontoDeEncontro(2);
				new CaronaController().reduzQtdVagas(encontro.getIdCarona());
				solicitacao.setEmAndamento(false);
				return;
			}
		}
		throw new Exception("Solicita��o inexistente");

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws Exception {
		for (SolicitacaoPontoDeEncontro solicitacao : solicitacoes) {

			if (solicitacao.getIdSugestao().equals(idSolicitacao)
					&& !solicitacao.isEmAndamento()) {
				PontoDeEncontro encontro = solicitacao.getPontoDeEncontro(2);
				new CaronaController().aumentaQtdVagas(encontro.getIdCarona());
				solicitacao.setEmAndamento(true);
				return;
			}
		}
		throw new Exception("Solicita��o inexistente");
	}
	
	
	
	

}