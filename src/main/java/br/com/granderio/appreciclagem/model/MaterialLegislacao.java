/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class MaterialLegislacao implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMaterialLegislacao;

    @ManyToOne
    private Material material;
    
    @ManyToOne
    private Legislacao legislacao;
    
    public MaterialLegislacao(){
        
    }

    /**
     * @return the idMaterialLegislacao
     */
    public long getIdMaterialLegislacao() {
        return idMaterialLegislacao;
    }

    /**
     * @param idMaterialLegislacao the idMaterialLegislacao to set
     */
    public void setIdMaterialLegislacao(long idMaterialLegislacao) {
        this.idMaterialLegislacao = idMaterialLegislacao;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the legislacao
     */
    public Legislacao getLegislacao() {
        return legislacao;
    }

    /**
     * @param legislacao the legislacao to set
     */
    public void setLegislacao(Legislacao legislacao) {
        this.legislacao = legislacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.idMaterialLegislacao ^ (this.idMaterialLegislacao >>> 32));
        hash = 43 * hash + Objects.hashCode(this.material);
        hash = 43 * hash + Objects.hashCode(this.legislacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaterialLegislacao other = (MaterialLegislacao) obj;
        if (this.idMaterialLegislacao != other.idMaterialLegislacao) {
            return false;
        }
        if (!Objects.equals(this.material, other.material)) {
            return false;
        }
        if (!Objects.equals(this.legislacao, other.legislacao)) {
            return false;
        }
        return true;
    }

}
