/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
@NamedQueries({
    @NamedQuery(name="Pedido.buscarTodos", query="SELECT p FROM PedidoReciclagem p WHERE p.reciclador IS NULL"),
    @NamedQuery(name="Pedido.buscarPedidosPorCidade", query="SELECT p FROM PedidoReciclagem p inner join p.gerador g where g.endereco.cidade = :cidade AND p.reciclador IS NULL"),
    @NamedQuery(name="Pedido.buscarPedidosPorMaterialCidade", query="SELECT p FROM PedidoReciclagem p inner join p.gerador g inner join p.item i "
            + "where g.endereco.cidade = :cidade AND p.reciclador IS NULL"
            + " AND i.material.idMaterial = :idmaterial"),
    @NamedQuery(name="Pedido.buscarPedidosPorMaterial", query="SELECT p FROM PedidoReciclagem p inner join p.item i where i.material.idMaterial = :idmaterial AND p.reciclador IS NULL")
})
public class PedidoReciclagem implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idPedidoReciclagem;
    
    private int numero;
    
    @Temporal(TemporalType.DATE)
    private Calendar data;
    
    private double valorTotal;
      
    @OneToOne(mappedBy="pedidoDeReciclagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ItemPedido item;
    
    private String localEntrega;
    
    private String observações;
    
    // 1 Pedido De Reciclagem vai ter um gerador --> Empresa que está vendendo o ItemPedido
    @ManyToOne
    private Gerador gerador;
    
    // 1 Pedido De Reciclagem vai ter um reciclador --> Empresa que está comprando o ItemPedido de GERADOR
    @ManyToOne
    private Reciclador reciclador;
    
    // 1 Pedido De Reciclagem vai ter um transportador --> Vai fazer a entrega, buscando no Endereço de Gerador para entregar no Endereço de Reciclador
    @ManyToOne
    private Transportador transportador;
    
    public PedidoReciclagem(){
        idPedidoReciclagem = -1;
        data = Calendar.getInstance();
        gerador = new Gerador();
        reciclador = new Reciclador();
        transportador = new Transportador();
    }

    /**
     * @return the idPedidoReciclagem
     */
    public long getIdPedidoReciclagem() {
        return idPedidoReciclagem;
    }

    /**
     * @param idPedidoReciclagem the idPedidoReciclagem to set
     */
    public void setIdPedidoReciclagem(long idPedidoReciclagem) {
        this.idPedidoReciclagem = idPedidoReciclagem;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    

    /**
     * @return the localEntrega
     */
    public String getLocalEntrega() {
        return localEntrega;
    }

    /**
     * @param localEntrega the localEntrega to set
     */
    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    /**
     * @return the observações
     */
    public String getObservações() {
        return observações;
    }

    /**
     * @param observações the observações to set
     */
    public void setObservações(String observações) {
        this.observações = observações;
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
     * @return the item
     */
    public ItemPedido getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemPedido item) {
        this.item = item;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((gerador == null) ? 0 : gerador.hashCode());
		result = prime * result + (int) (idPedidoReciclagem ^ (idPedidoReciclagem >>> 32));
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((localEntrega == null) ? 0 : localEntrega.hashCode());
		result = prime * result + numero;
		result = prime * result + ((observações == null) ? 0 : observações.hashCode());
		result = prime * result + ((reciclador == null) ? 0 : reciclador.hashCode());
		result = prime * result + ((transportador == null) ? 0 : transportador.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PedidoReciclagem other = (PedidoReciclagem) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (gerador == null) {
			if (other.gerador != null)
				return false;
		} else if (!gerador.equals(other.gerador))
			return false;
		if (idPedidoReciclagem != other.idPedidoReciclagem)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (localEntrega == null) {
			if (other.localEntrega != null)
				return false;
		} else if (!localEntrega.equals(other.localEntrega))
			return false;
		if (numero != other.numero)
			return false;
		if (observações == null) {
			if (other.observações != null)
				return false;
		} else if (!observações.equals(other.observações))
			return false;
		if (reciclador == null) {
			if (other.reciclador != null)
				return false;
		} else if (!reciclador.equals(other.reciclador))
			return false;
		if (transportador == null) {
			if (other.transportador != null)
				return false;
		} else if (!transportador.equals(other.transportador))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}

    
    
    
}
