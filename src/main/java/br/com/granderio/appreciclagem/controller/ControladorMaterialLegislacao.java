/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAOMaterialLegislacao;
import br.com.granderio.appreciclagem.model.Legislacao;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.MaterialLegislacao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorMaterialLegi")
@SessionScoped
public class ControladorMaterialLegislacao extends ControladorPrincipal<MaterialLegislacao> {
    
    private Material materialEscolhido;
    private Legislacao legislacaoEscolhida;
    
    public ControladorMaterialLegislacao(){
        super(new MaterialLegislacao());
    }
    
    public void relacionarMatLegi(){
        MaterialLegislacao matLegi = new MaterialLegislacao();
        matLegi.setLegislacao(legislacaoEscolhida);
        matLegi.setMaterial(materialEscolhido);
        DAOMaterialLegislacao dao = new DAOMaterialLegislacao(matLegi);
        dao.inserir();
    }
    
    public void limpar(){
        materialEscolhido = null;
        legislacaoEscolhida = null;
    }

    /**
     * @return the materialEscolhido
     */
    public Material getMaterialEscolhido() {
        return materialEscolhido;
    }

    /**
     * @param materialEscolhido the materialEscolhido to set
     */
    public void setMaterialEscolhido(Material materialEscolhido) {
        this.materialEscolhido = materialEscolhido;
    }

    /**
     * @return the legislacaoEscolhida
     */
    public Legislacao getLegislacaoEscolhida() {
        return legislacaoEscolhida;
    }

    /**
     * @param legislacaoEscolhida the legislacaoEscolhida to set
     */
    public void setLegislacaoEscolhida(Legislacao legislacaoEscolhida) {
        this.legislacaoEscolhida = legislacaoEscolhida;
    }

}
