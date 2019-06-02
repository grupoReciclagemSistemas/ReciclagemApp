package br.com.granderio.appreciclagem.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name="controladorError")
public class ControladorError {
	
	private Throwable excecao;
	
	public ControladorError() {
		
	}
	
	public Throwable getExcecao() {
		return excecao;
	}

	public void setExcecao(Throwable excecao) {
		this.excecao = excecao;
	}
}
