
package bean;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entidade.Jogo;

@ManagedBean
@SessionScoped
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> lista = new ArrayList<Jogo>();

	public void salvar() {
		try {
			if (validarCamposNulo()) {
				if (validarNumeros()) {
					jogo.setDataCadastro(new Date());
					jogo.setNumeroSorteado(new Random().nextInt(10) + 1);
					JogoDAO.salvar(jogo);
					jogo = new Jogo();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Numeros Cadastrados"));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void excluir(Integer id) {
		JogoDAO.excluir(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Excluido com sucesso!!", "Numeros excluidos com sucesso!"));
	}

	public void editar(Jogo jogo) {
		JogoDAO.editar(jogo);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Editado com sucesso!!", "Numeros editado com sucesso!"));
	}

	public void exibirMaior(Jogo jogo) {
		int maior = JogoDAO.obterMaiorValor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "O maior numero é: " + maior, ""));
	}

	public void verificarSorteado(Jogo jogo) {
		boolean contem = jogo.getV1().equals(jogo.getNumeroSorteado()) || jogo.getV2().equals(jogo.getNumeroSorteado())
				|| jogo.getV3().equals(jogo.getNumeroSorteado()) || jogo.getV4().equals(jogo.getNumeroSorteado())
				|| jogo.getV5().equals(jogo.getNumeroSorteado());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Contem o numero Sorteado?", (contem ? "Sim" : "Não")));
	}

	private boolean validarNumeros() {

		if (jogo.getV1() < 1 || jogo.getV1() > 10) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 1 deve está entre 1 e 10!"));
			return false;
		}
		if (jogo.getV2() < 1 || jogo.getV2() > 10) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 2 deve está entre 1 e 10!"));
			return false;
		}
		if (jogo.getV3() < 1 || jogo.getV3() > 10) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 3 deve está entre 1 e 10!"));
			return false;
		}
		if (jogo.getV4() < 1 || jogo.getV4() > 10) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 4 deve está entre 1 e 10!"));
			return false;
		}
		if (jogo.getV5() < 1 || jogo.getV5() > 10) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 5 deve está entre 1 e 10!"));
			return false;
		}
			return true;
	}

	private boolean validarCamposNulo() {
	    
	    if (jogo.getV1() == null || jogo.getV1().equals("")){
	    	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 1 está em Branco!"));
	    	return false;
	    }
	    else if (jogo.getV2() == null || jogo.getV2().equals("")) {
	    	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 2 está em Branco!"));
	    	return false;
	    }
	    else if(jogo.getV3()==null||jogo.getV3().equals("")){
	    	FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 3 está em Branco!"));
	    	return false;
	    }
	    else if(jogo.getV4()==null||jogo.getV4().equals("")){
	    	FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 4 está em Branco!"));
	    	return false;
	    }
	    else if(jogo.getV5()==null||jogo.getV5().equals("")){
	    	FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O valor 5 está em Branco!"));
	    	return false;
}
	    return true;
}
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<Jogo> getLista() {
		if (lista != null) {
			lista = JogoDAO.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

}