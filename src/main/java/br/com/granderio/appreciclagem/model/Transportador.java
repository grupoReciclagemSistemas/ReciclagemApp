/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class Transportador extends PessoaJuridica {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="transportador", fetch = FetchType.LAZY)
    private List<PedidoReciclagem> pedidosDeReciclagens;
    
    public Transportador(){
        super();
        pedidosDeReciclagens = new ArrayList();
    }

    /**
     * @return the pedidosDeReciclagens
     */
    public List<PedidoReciclagem> getPedidosDeReciclagens() {
        return pedidosDeReciclagens;
    }

    /**
     * @param pedidosDeReciclagens the pedidosDeReciclagens to set
     */
    public void setPedidosDeReciclagens(List<PedidoReciclagem> pedidosDeReciclagens) {
        this.pedidosDeReciclagens = pedidosDeReciclagens;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pedidosDeReciclagens == null) ? 0 : pedidosDeReciclagens.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transportador other = (Transportador) obj;
		if (pedidosDeReciclagens == null) {
			if (other.pedidosDeReciclagens != null)
				return false;
		} else if (!pedidosDeReciclagens.equals(other.pedidosDeReciclagens))
			return false;
		return true;
	}
    
    
    
}
