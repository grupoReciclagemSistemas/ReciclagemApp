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
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class ChatTransportador implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatAplicacao == null) ? 0 : chatAplicacao.hashCode());
		result = prime * result + (int) (idChatTransportador ^ (idChatTransportador >>> 32));
		result = prime * result + ((negociacao == null) ? 0 : negociacao.hashCode());
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
		ChatTransportador other = (ChatTransportador) obj;
		if (chatAplicacao == null) {
			if (other.chatAplicacao != null)
				return false;
		} else if (!chatAplicacao.equals(other.chatAplicacao))
			return false;
		if (idChatTransportador != other.idChatTransportador)
			return false;
		if (negociacao == null) {
			if (other.negociacao != null)
				return false;
		} else if (!negociacao.equals(other.negociacao))
			return false;
		return true;
	}

}
