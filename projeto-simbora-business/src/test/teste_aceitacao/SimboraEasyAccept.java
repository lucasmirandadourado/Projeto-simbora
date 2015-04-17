package easyaccept;

import com.controller.CaronaController; 
import com.controller.SessaoController;
import com.controller.SolicitacaoPontoDeEncontroController;
import com.controller.SolicitacaoVagasController;
import com.controller.UsuarioController;
import com.excessoes.CaronaException;
import com.excessoes.SessaoException;
import com.excessoes.UsuarioException;
import com.model.SolicitacaoVagas;
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
	
	public void zerarSistema() {
		usuarioController.zerarSistema();
		caronaController.zerarSistema();
		solicitacaoEncontroController.zerarSistema();
		solicitacaoVagasController.zerarSistema();
		//pontoDeEncontroController.zerarSistema();
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
		usuarioController.encerrarSistema();
	}
	
	
	//Metodos US02
	
	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {
		return caronaController.localizarCarona(idSessao, origem, destino);
	}
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String qtdDeVagas) throws CaronaException {
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
	public String solicitarVaga(String idSessao, String idCarona){
		return solicitacaoVagasController.solicitarVaga(idSessao, idCarona);
	}
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		solicitacaoVagasController.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao){
		solicitacaoVagasController.rejeitarSolicitacao(idSessao, idSolicitacao);
	}	
	
}
