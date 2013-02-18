package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class GerenciaAmizades {
	
	private List<SolicitacaoAmizade> listSolicitacoes;

	public GerenciaAmizades(){
		this.listSolicitacoes = new ArrayList<SolicitacaoAmizade>();
	}
	public String enviarSolicitacaoAmizade(String loginSolicitante,String loginSolicitado){
		String idSolicitacao = "solicitacao"+this.listSolicitacoes.size()+"ID";
		SolicitacaoAmizade solicitacao = new SolicitacaoAmizade(idSolicitacao, loginSolicitante,loginSolicitado);
		this.listSolicitacoes.add(solicitacao);
		return idSolicitacao;
	}

	public void aceitarSolicitacaoAmizade(String sessao, String idsolicitacao){
		int sizeList =this.listSolicitacoes.size();
	}

}
