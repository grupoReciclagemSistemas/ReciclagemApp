/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Chat implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idChat;
   
   @OneToMany(mappedBy="chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<ChatAplicacao> chatAplicacao;
   
   @OneToOne
   private Negociacao negociacao;
   
   public Chat(){
       chatAplicacao = new ArrayList<>();
   }
   
    public void adicionarChatAplica(ChatAplicacao e){
        chatAplicacao.add(e);
    }
    /**
     * @return the idChat
     */
    public long getIdChat() {
        return idChat;
    }

    /**
     * @param idChat the idChat to set
     */
    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    /**
     * @return the chatAplicacao
     */
    public List<ChatAplicacao> getChatAplicacao() {
        return chatAplicacao;
    }

    /**
     * @param chatAplicacao the chatAplicacao to set
     */
    public void setChatAplicacao(List<ChatAplicacao> chatAplicacao) {
        this.chatAplicacao = chatAplicacao;
    }

    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }

    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(Negociacao negociacao) {
        this.negociacao = negociacao;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatAplicacao == null) ? 0 : chatAplicacao.hashCode());
		result = prime * result + (int) (idChat ^ (idChat >>> 32));
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
		Chat other = (Chat) obj;
		if (chatAplicacao == null) {
			if (other.chatAplicacao != null)
				return false;
		} else if (!chatAplicacao.equals(other.chatAplicacao))
			return false;
		if (idChat != other.idChat)
			return false;
		if (negociacao == null) {
			if (other.negociacao != null)
				return false;
		} else if (!negociacao.equals(other.negociacao))
			return false;
		return true;
	}
   
}
