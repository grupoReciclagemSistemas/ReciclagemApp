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
public class Reciclador extends PessoaJuridica {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean transportadoraPropria;
    
    @OneToMany(mappedBy="reciclador", fetch = FetchType.LAZY)
    private List<PedidoReciclagem> pedidosDeReciclagens;
    
    //Reciclador tem uma lista de negociações
    @OneToMany(mappedBy="reciclador", fetch = FetchType.LAZY)
    private List<Negociacao> negociacoes;
          
    public Reciclador(){
        super();
        transportadoraPropria = false;
        pedidosDeReciclagens = new ArrayList();
    }
    
    public void adicionarNegociacao(Negociacao neg){
        negociacoes.add(neg);
    }

    public boolean isPossuiTransportadora() {
        return transportadoraPropria;
    }

    
    public void setPossuiTransportadora(boolean transportadoraPropria) {
        this.transportadoraPropria= transportadoraPropria;
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

    /**
     * @return the negociacoes
     */
    public List<Negociacao> getNegociacoes() {
        return negociacoes;
    }

    /**
     * @param negociacoes the negociacoes to set
     */
    public void setNegociacoes(List<Negociacao> negociacoes) {
        this.negociacoes = negociacoes;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((negociacoes == null) ? 0 : negociacoes.hashCode());
		result = prime * result + ((pedidosDeReciclagens == null) ? 0 : pedidosDeReciclagens.hashCode());
		result = prime * result + (transportadoraPropria ? 1231 : 1237);
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
		Reciclador other = (Reciclador) obj;
		if (negociacoes == null) {
			if (other.negociacoes != null)
				return false;
		} else if (!negociacoes.equals(other.negociacoes))
			return false;
		if (pedidosDeReciclagens == null) {
			if (other.pedidosDeReciclagens != null)
				return false;
		} else if (!pedidosDeReciclagens.equals(other.pedidosDeReciclagens))
			return false;
		if (transportadoraPropria != other.transportadoraPropria)
			return false;
		return true;
	}
     
}
