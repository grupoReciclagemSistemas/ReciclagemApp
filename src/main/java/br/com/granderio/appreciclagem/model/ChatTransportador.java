/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class ChatTransportador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idChatTransportador;
   
   @OneToMany(mappedBy="chatTransportador", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<ChatAplicacaoTransportador> chatAplicacao;
   
   //Chat ficará vinculado a negociação entre gerador e reciclador
   @OneToOne
   private NegociacaoTransportador negociacao;

    /**
     * @return the idChatTransportador
     */
    public long getIdChatTransportador() {
        return idChatTransportador;
    }

    /**
     * @param idChatTransportador the idChatTransportador to set
     */
    public void setIdChatTransportador(long idChatTransportador) {
        this.idChatTransportador = idChatTransportador;
    }

    /**
     * @return the chatAplicacao
     */
    public List<ChatAplicacaoTransportador> getChatAplicacao() {
        return chatAplicacao;
    }

    /**
     * @param chatAplicacao the chatAplicacao to set
     */
    public void setChatAplicacao(List<ChatAplicacaoTransportador> chatAplicacao) {
        this.chatAplicacao = chatAplicacao;
    }

    /**
     * @return the negociacao
     */
    public NegociacaoTransportador getNegociacao() {
        return negociacao;
    }

    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(NegociacaoTransportador negociacao) {
        this.negociacao = negociacao;
    }

}
