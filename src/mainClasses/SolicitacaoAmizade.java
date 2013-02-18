package mainClasses;

public class SolicitacaoAmizade {

	private String idSolicitacao,loginSolicitante,loginSolicitado;
	private boolean foiAceita;
	
	public SolicitacaoAmizade(String id,String solicitante, String solicitado){
		setFoiAceita(false);
		setLoginSolicitante(solicitante);
		setLoginSolicitado(solicitado);
		setIdSolicitacao(id);
	}

	public boolean isFoiAceita() {
		return foiAceita;
	}

	private void setFoiAceita(boolean foiAceita) {
		this.foiAceita = foiAceita;
	}

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	private void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	public String getLoginSolicitante() {
		return loginSolicitante;
	}

	private void setLoginSolicitante(String loginSolicitante) {
		this.loginSolicitante = loginSolicitante;
	}

	public String getLoginSolicitado() {
		return loginSolicitado;
	}

	private void setLoginSolicitado(String loginSolicitado) {
		this.loginSolicitado = loginSolicitado;
	}
}