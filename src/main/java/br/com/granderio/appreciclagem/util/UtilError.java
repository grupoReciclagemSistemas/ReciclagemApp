/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.util;

/**
 *
 * @programador 
 * Since 2018
 * Rafael Nunes - rafaelnunes.inf@gmail.com - Desenvolvedor Java
 */
public class UtilError {
    
    
    //Método que retorna a mensagem da última exceção
    public static String getMensagemErro(Exception e) {
      while( e.getCause() != null) {
	  e = (Exception) e.getCause();
	}       
	String mensagem = e.getMessage();
	return mensagem;	   
    }
    
    public static Exception getUltimaExcecao(Exception e) {
      while( e.getCause() != null) {
	  e = (Exception) e.getCause();
	}        
	return e;	 
    }
    
}
