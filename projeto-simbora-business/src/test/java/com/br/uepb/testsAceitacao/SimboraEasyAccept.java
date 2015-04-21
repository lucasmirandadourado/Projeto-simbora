package com.br.uepb.testsAceitacao;

import com.br.uepb.controller.CaronaController;
import com.br.uepb.controller.PerfilController;
import com.br.uepb.controller.SessaoController;
import com.br.uepb.controller.SolicitacaoPontoDeEncontroController;
import com.br.uepb.controller.SolicitacaoVagasController;
import com.br.uepb.controller.UsuarioController;
import com.br.uepb.dao.CaronaDao;
import com.br.uepb.dao.PontoDeEncontroDaoImp;
import com.br.uepb.dao.SolicitacaoPontoDeEncontroDaoImp;
import com.br.uepb.dao.UsuarioDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.exception.CaronaException;
import com.br.uepb.exception.PerfilException;
import com.br.uepb.exception.SessaoException;
import com.br.uepb.exception.UsuarioException;


/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SimboraEasyAccept {

	UsuarioController usuarioController = new UsuarioController();
	CaronaController caronaController = new CaronaController();
	SessaoController sessaoController = new SessaoController();
	//PontoDeEncontroController pontoDeEncontroController = new PontoDeEncontroController();
	SolicitacaoPontoDeEncontroController solicitacaoEncontroController = new SolicitacaoPontoDeEncontroController();
	SolicitacaoVagasController solicitacaoVagasController = new SolicitacaoVagasController();
	PerfilController perfilController = new PerfilController();
	
	public void zerarSistema() {
		/*usuarioController.zerarSistema();
		caronaController.zerarSistema();
		solicitacaoEncontroController.zerarSistema();
		solicitacaoVagasController.zerarSistema();*/
		//pontoDeEncontroController.zerarSistema();
		usuarioController.usuarios.clear();
		caronaController.caronas.clear();
		solicitacaoVagasController.solicitacoesVagas.clear();
		solicitacaoEncontroController.solicitacoes.clear();
		new UsuarioDaoImp().excluirTudo();
		new CaronaDao().excluirTudo();
		new SolicitacaoPontoDeEncontroDaoImp().excluirTudo();
		new SolicitacaoVagasDaoImp().excluirTudo();
		new PontoDeEncontroDaoImp().excluirTudo();
		
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws UsuarioException{
		usuarioController.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws SessaoException{
		return sessaoController.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo) throws UsuarioException{
		return usuarioController.getAtributoUsuario(login, atributo);
	}
	
	public void encerrarSessao(String login){
		sessaoController.encerrarSessao(login);
	}
	
	public void encerrarSistema(){
		usuarioController.zerarSistema();
		caronaController.zerarSistema();
		solicitacaoEncontroController.zerarSistema();
		solicitacaoVagasController.zerarSistema();
	}
	
	
	//Metodos US02
	
	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {
		return caronaController.localizarCarona(idSessao, origem, destino);
	}
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String qtdDeVagas) throws Exception {
		return caronaController.cadastrarCarona(idSessao, origem, destino, data, hora, qtdDeVagas);
	}
	
	public String getAtributoCarona(String idCarona, String atributo) throws CaronaException{
		return caronaController.getAtributoCarona(idCarona, atributo);
	}
	
	public String getTrajeto(String idCarona) throws CaronaException{
		return caronaController.getTrajeto(idCarona);
	}
	
	public String getCarona(String idCarona) throws CaronaException{
		return caronaController.getCarona(idCarona);
	}
	
	//Metodos US04
	
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws Exception{
		return solicitacaoEncontroController.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		solicitacaoEncontroController.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	
	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {
		return solicitacaoEncontroController.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto) {
		return solicitacaoEncontroController.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws CaronaException {
		return solicitacaoEncontroController.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao) throws Exception{
		solicitacaoEncontroController.desistirRequisicao(idSessao, idCarona, idSolicitacao);
	}
	
	//Metodos US05
	public String solicitarVaga(String idSessao, String idCarona) throws Exception{
		return solicitacaoVagasController.solicitarVaga(idSessao, idCarona);
	}
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		solicitacaoVagasController.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		solicitacaoVagasController.rejeitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public String visualizarPerfil(String idSessao, String login) throws PerfilException{
		return perfilController.visualizarPerfil(idSessao, login);
	}
	
	public String getAtributoPerfil(String login, String atributo) throws PerfilException{
		return perfilController.getAtributoPerfil(login, atributo);
	}
	
	public String getCaronaUsuario(String idSessao, String indexCarona){

		return caronaController.getCaronaUsuario(idSessao, indexCarona);
	}

	public void reiniciarSistema() {
		SessaoController.setUsuarios(new UsuarioDaoImp().list());
		UsuarioController.usuarios = SessaoController.getUsuarios();
		CaronaController.caronas = new CaronaDaoImp().list();
		SolicitacaoVagasController.solicitacoesVagas = new SolicitacaoVagasDaoImp().list();
		SolicitacaoPontoDeEncontroController.solicitacoes = new SolicitacaoPontoDeEncontroDaoImp().list();
		
	}

	public String getTodasCaronasUsuario(String idSessao) {
		
		return caronaController.getTodasCaronasUsuario(idSessao);
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		
		return solicitacaoVagasController.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		
		return solicitacaoVagasController.getSolicitacoesPendentes(idSessao, idCarona);
	}

	public String getPontosSugeridos(String idSessao, String idCarona) {
		
		return solicitacaoEncontroController.getPontosSugeridos(idSessao, idCarona);
	}

	public String getPontosEncontro(String idSessao, String idCarona) {
		
		return solicitacaoEncontroController.getPontosEncontro(idSessao, idCarona);
	}
	
}
