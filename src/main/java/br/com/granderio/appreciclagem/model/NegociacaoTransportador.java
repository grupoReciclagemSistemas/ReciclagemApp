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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
@NamedQueries({
    @NamedQuery(name="NegociacaoTransportador.listarPorIdPedidoReciclagem", 
            query="SELECT n FROM NegociacaoTransportador n inner join n.transportador inner join n.reciclador inner join n.chatTransportador inner join n.pedido WHERE "
                    + "n.pedido.idPedidoReciclagem = :id")
//    @NamedQuery(name="NegociacaoTransportador.listarPorPedidoReciclador", 
//            query="SELECT n FROM Negociacao n inner join n.transportador inner join n.reciclador inner join n.chatTransportador inner join n.pedido WHERE "
//                    + "n.pedido.idPedidoReciclagem = :id AND n.gerador.idPessoaJuridica = :idReciclador")
})
public class NegociacaoTransportador implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNegociacaoTransportador;
    
    @ManyToOne
    private Transportador transportador;
    
    @ManyToOne
    private Reciclador reciclador;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ChatTransportador chatTransportador;
    
    @OneToOne
    private PedidoReciclagem pedido;
    
    private String status;
    
    private boolean recicladorFinalizou;
    
    private boolean transportadorFinalizou;
    
    public NegociacaoTransportador(){
        transportadorFinalizou = false;
        recicladorFinalizou = false;
        status = "Em Negociação";
    }

    /**
     * @return the idNegociacaoTransportador
     */
    public long getIdNegociacaoTransportador() {
        return idNegociacaoTransportador;
    }

    /**
     * @param idNegociacaoTransportador the idNegociacaoTransportador to set
     */
    public void setIdNegociacaoTransportador(long idNegociacaoTransportador) {
        this.idNegociacaoTransportador = idNegociacaoTransportador;
    }

    /**
     * @return the transportador
     */
    public Transportador getTransportador() {
        return transportador;
    }

    /**
     * @param transportador the transportador to set
     */
    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }

    /**
     * @return the reciclador
     */
    public Reciclador getReciclador() {
        return reciclador;
    }

    /**
     * @param reciclador the reciclador to set
     */
    public void setReciclador(Reciclador reciclador) {
        this.reciclador = reciclador;
    }

    /**
     * @return the pedido
     */
    public PedidoReciclagem getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(PedidoReciclagem pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the chatTransportador
     */
    public ChatTransportador getChatTransportador() {
        return chatTransportador;
    }

    /**
     * @param chatTransportador the chatTransportador to set
     */
    public void setChatTransportador(ChatTransportador chatTransportador) {
        this.chatTransportador = chatTransportador;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the recicladorFinalizou
     */
    public boolean isRecicladorFinalizou() {
        return recicladorFinalizou;
    }

    /**
     * @param recicladorFinalizou the recicladorFinalizou to set
     */
    public void setRecicladorFinalizou(boolean recicladorFinalizou) {
        this.recicladorFinalizou = recicladorFinalizou;
    }

    /**
     * @return the transportadorFinalizou
     */
    public boolean isTransportadorFinalizou() {
        return transportadorFinalizou;
    }

    /**
     * @param transportadorFinalizou the transportadorFinalizou to set
     */
    public void setTransportadorFinalizou(boolean transportadorFinalizou) {
        this.transportadorFinalizou = transportadorFinalizou;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatTransportador == null) ? 0 : chatTransportador.hashCode());
		result = prime * result + (int) (idNegociacaoTransportador ^ (idNegociacaoTransportador >>> 32));
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((reciclador == null) ? 0 : reciclador.hashCode());
		result = prime * result + (recicladorFinalizou ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transportador == null) ? 0 : transportador.hashCode());
		result = prime * result + (transportadorFinalizou ? 1231 : 1237);
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
		NegociacaoTransportador other = (NegociacaoTransportador) obj;
		if (chatTransportador == null) {
			if (other.chatTransportador != null)
				return false;
		} else if (!chatTransportador.equals(other.chatTransportador))
			return false;
		if (idNegociacaoTransportador != other.idNegociacaoTransportador)
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (reciclador == null) {
			if (other.reciclador != null)
				return false;
		} else if (!reciclador.equals(other.reciclador))
			return false;
		if (recicladorFinalizou != other.recicladorFinalizou)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transportador == null) {
			if (other.transportador != null)
				return false;
		} else if (!transportador.equals(other.transportador))
			return false;
		if (transportadorFinalizou != other.transportadorFinalizou)
			return false;
		return true;
	}

}
