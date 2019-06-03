/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class EstoqueGerador implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idEstoqueGerador;
    
    @ManyToOne
    private Gerador gerador;
    
    @OneToOne (cascade = CascadeType.ALL)
    private Estoque estoque;
   
    public EstoqueGerador(){
        idEstoqueGerador = -1;
    }

    /**
     * @return the idEstoqueGerador
     */
    public long getIdEstoqueGerador() {
        return idEstoqueGerador;
    }

    /**
     * @param idEstoqueGerador the idEstoqueGerador to set
     */
    public void setIdEstoqueGerador(long idEstoqueGerador) {
        this.idEstoqueGerador = idEstoqueGerador;
    }

    /**
     * @return the gerador
     */
    public Gerador getGerador() {
        return gerador;
    }

    /**
     * @param gerador the gerador to set
     */
    public void setGerador(Gerador gerador) {
        this.gerador = gerador;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((gerador == null) ? 0 : gerador.hashCode());
		result = prime * result + (int) (idEstoqueGerador ^ (idEstoqueGerador >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstoqueGerador other = (EstoqueGerador) obj;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (gerador == null) {
			if (other.gerador != null)
				return false;
		} else if (!gerador.equals(other.gerador))
			return false;
		if (idEstoqueGerador != other.idEstoqueGerador)
			return false;
		return true;
	}
}
