/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name="ChatAplicaTrans.buscarTodos", query="SELECT c FROM ChatAplicacaoTransportador c inner join c.chatTransportador WHERE c.chatTransportador.idChatTransportador = :idChat ORDER BY c.idChatAplicacaoTransportador DESC"),
    @NamedQuery(name="ChatAplicaTrans.buscarTodos2", query="SELECT c FROM ChatAplicacaoTransportador c inner join c.chatTransportador ORDER BY c.idChatAplicacaoTransportador DESC")
})
public class ChatAplicacaoTransportador implements Serializable{
    
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idChatAplicacaoTransportador;
  
  
  //Mensagem que irá ser enviada
  private String mensagem;
  
  //Associação bidirecional
  @ManyToOne
  private ChatTransportador chatTransportador;
  
  @ManyToOne
  private Transportador transportador;
  
  @ManyToOne
  private Reciclador reciclador;
 
  //Data e Hora que a mensagem irá ser enviada
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHora;

    /**
     * @return the idChatAplicacaoTransportador
     */
    public Long getIdChatAplicacaoTransportador() {
        return idChatAplicacaoTransportador;
    }

    /**
     * @param idChatAplicacaoTransportador the idChatAplicacaoTransportador to set
     */
    public void setIdChatAplicacaoTransportador(Long idChatAplicacaoTransportador) {
        this.idChatAplicacaoTransportador = idChatAplicacaoTransportador;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

}
