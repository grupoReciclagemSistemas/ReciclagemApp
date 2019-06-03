package br.com.granderio.appreciclagem.controller;

import java.io.IOException;

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
	
	public void inicio() {
		String contexto = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(contexto + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
