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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/

@Entity
@NamedQueries({
    @NamedQuery(name="Negociacao.listarPorIdPedidoReciclagem", 
            query="SELECT n FROM Negociacao n inner join n.gerador inner join n.reciclador inner join n.chat inner join n.pedido WHERE "
                    + "n.pedido.idPedidoReciclagem = :id"),
    @NamedQuery(name="Negociacao.listarPorPedidoReciclador", 
            query="SELECT n FROM Negociacao n inner join n.gerador inner join n.reciclador inner join n.chat inner join n.pedido WHERE "
                    + "n.pedido.idPedidoReciclagem = :id AND n.gerador.idPessoaJuridica = :idReciclador"),
    @NamedQuery(name="Negociacao.listarPorPedidoRecicladorGerador", query="SELECT n FROM Negociacao n WHERE n.pedido.idPedidoReciclagem = :id1 AND "
            + "n.gerador.idPessoaJuridica = :id2 AND n.reciclador.idPessoaJuridica = :id3")
})
public class Negociacao implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNegociacao;
    
    @ManyToOne
    private Gerador gerador;
    
    @ManyToOne
    private Reciclador reciclador;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Chat chat;
    
    @OneToOne
    private PedidoReciclagem pedido;

    private String status;
    
    private boolean recicladorFinalizou;
    
    private boolean geradorFinalizou;
    
    public Negociacao(){
        geradorFinalizou = false;
        recicladorFinalizou = false;
        status = "Em Negociação";
    }
    
    public Negociacao(PedidoReciclagem pedido, Reciclador reciclador, Gerador gerador){
        this.gerador = gerador;
        this.pedido = pedido;
        this.reciclador = reciclador;
        chat = new Chat();
        chat.setNegociacao(this);
    }

    /**
     * @return the idNegociacao
     */
    public long getIdNegociacao() {
        return idNegociacao;
    }

    /**
     * @param idNegociacao the idNegociacao to set
     */
    public void setIdNegociacao(long idNegociacao) {
        this.idNegociacao = idNegociacao;
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
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
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
     * @return the geradorFinalizou
     */
    public boolean isGeradorFinalizou() {
        return geradorFinalizou;
    }

    /**
     * @param geradorFinalizou the geradorFinalizou to set
     */
    public void setGeradorFinalozou(boolean geradorFinalizou) {
        this.geradorFinalizou = geradorFinalizou;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		result = prime * result + ((gerador == null) ? 0 : gerador.hashCode());
		result = prime * result + (geradorFinalizou ? 1231 : 1237);
		result = prime * result + (int) (idNegociacao ^ (idNegociacao >>> 32));
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((reciclador == null) ? 0 : reciclador.hashCode());
		result = prime * result + (recicladorFinalizou ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Negociacao other = (Negociacao) obj;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		if (gerador == null) {
			if (other.gerador != null)
				return false;
		} else if (!gerador.equals(other.gerador))
			return false;
		if (geradorFinalizou != other.geradorFinalizou)
			return false;
		if (idNegociacao != other.idNegociacao)
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
		return true;
	}

}
