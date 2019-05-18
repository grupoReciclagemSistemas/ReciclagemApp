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
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 * classe para Transportador e Reciclador negociarem o preço do transporte, etc.
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

}
