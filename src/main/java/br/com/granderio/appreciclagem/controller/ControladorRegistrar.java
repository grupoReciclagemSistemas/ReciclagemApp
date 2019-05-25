/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.dao.DAOTransportador;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.Consulta;
import br.com.granderio.appreciclagem.util.UtilMap;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import com.google.maps.errors.ApiException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="controladorRegistrar")
@SessionScoped
public  class ControladorRegistrar implements Serializable {
    
    // 1 --> Reciclador
    // 2 --> Gerador
    // 3 --> Transportador
    private int tipoDePessoa;
    
    private Gerador novoGerador;
    private Reciclador novoReciclador;
    private Transportador novoTransportador;
    
    public String trans(){
       boolean possui = novoReciclador.isPossuiTransportadora();
       if(possui){
           return "Quando você for comprar algum produto no Sistema, sua empresa que efetuará o Transporte!";
       }
       return "Quando você for comprar algum produto no Sistema, poderá alugar um Transportador!";
    }
     
    public ControladorRegistrar(){
        novoGerador = new Gerador();
        novoReciclador = new Reciclador();
        novoTransportador = new Transportador();
        tipoDePessoa = 0;
    }
    
    public void registrarTipoPessoa(int valor){
        this.setTipoDePessoa(valor);    
    }
    
    public void listenerRecicladorCEP(){  
        try {
            String cep = novoReciclador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.restCEP(cep);
            novoReciclador.setEndereco(retornoDoWebService);
            
          if(novoReciclador.getEndereco().getLogradouro() == null){
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoReciclador.getEndereco().setBairro(null);
            novoReciclador.getEndereco().setCidade(null);
            novoReciclador.getEndereco().setLogradouro(null);
            novoReciclador.getEndereco().setUf(null);  
            }
          else{
            UtilMensagens.mensagemInfo("CEP consultado com Sucesso!");
          }
          
        } catch (Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoReciclador.getEndereco().setBairro(null);
            novoReciclador.getEndereco().setCidade(null);
            novoReciclador.getEndereco().setLogradouro(null);
            novoReciclador.getEndereco().setUf(null); 
            return;
        } 
    }
    
    public void listenerGeradorCEP(){  
        try {
            String cep = novoGerador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.restCEP(cep);
            novoGerador.setEndereco(retornoDoWebService);
            
            if(novoGerador.getEndereco().getLogradouro() == null){
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoGerador.getEndereco().setBairro(null);
            novoGerador.getEndereco().setCidade(null);
            novoGerador.getEndereco().setLogradouro(null);
            novoGerador.getEndereco().setUf(null);  
            }
          else{
            UtilMensagens.mensagemInfo("CEP consultado com Sucesso!");
          }       
        } catch (Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoGerador.getEndereco().setBairro(null);
            novoGerador.getEndereco().setCidade(null);
            novoGerador.getEndereco().setLogradouro(null);
            novoGerador.getEndereco().setUf(null);   
        }
    }
    
    public void listenerTransportadorCEP(){  
        try {
            String cep = novoTransportador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.restCEP(cep);
            novoTransportador.setEndereco(retornoDoWebService);
             if(novoTransportador.getEndereco().getLogradouro() == null){
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoTransportador.getEndereco().setBairro(null);
            novoTransportador.getEndereco().setCidade(null);
            novoTransportador.getEndereco().setLogradouro(null);
            novoTransportador.getEndereco().setUf(null);  
            }
          else{
            UtilMensagens.mensagemInfo("CEP consultado com Sucesso!");
          }     
             
        } catch (Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            UtilMensagens.mensagemAdvertencia("O CEP digitado é Inválido. Digite um Válido!");
            novoReciclador.getEndereco().setBairro(null);
            novoReciclador.getEndereco().setCidade(null);
            novoReciclador.getEndereco().setLogradouro(null);
            novoReciclador.getEndereco().setUf(null);       
        }
    }
    

     public String registrarTransportador(){
        String retorno = null;
       DAOTransportador acesso = new DAOTransportador(novoTransportador);
       novoTransportador.getEndereco().setPessoa(novoTransportador);
       novoTransportador.setPedidosDeReciclagens(null);
       String email = novoTransportador.getEmail();
       String cnpj = novoTransportador.getCnpj();
       if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Transportador no Sistema!");   
            return "";
        }
       //Insere LAT E LNG
       Endereco end = novoTransportador.getEndereco();
        try {
            retorno = UtilMap.codigoGeo(end.getLogradouro() + "," + end.getNumero() + "," +end.getCidade());
            String[] split = retorno.split(",");
            novoTransportador.getEndereco().setLat(Double.valueOf(split[0]));
            novoTransportador.getEndereco().setLng(Double.valueOf(split[1]));
        } catch (ApiException | InterruptedException | IOException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
       acesso.inserir();
       novoTransportador = new Transportador();
       tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Transportador cadastrado com sucesso!");
        return "index";
    }
     
    public String registrarGerador(){
        String retorno = null;
        DAOGerador acesso = new DAOGerador(novoGerador);
        novoGerador.getEndereco().setPessoa(novoGerador);
        novoGerador.setEstoques(null);
        String email = novoGerador.getEmail();
        String cnpj = novoGerador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Gerador no Sistema!");
            return "";
        }
        //Insere LAT E LNG
       Endereco end = novoGerador.getEndereco();
        try {
            retorno = UtilMap.codigoGeo(end.getLogradouro() + "," + end.getNumero() + "," +end.getCidade());
            String[] split = retorno.split(",");
            novoGerador.getEndereco().setLat(Double.valueOf(split[0]));
            novoGerador.getEndereco().setLng(Double.valueOf(split[1]));
        } catch (ApiException | InterruptedException | IOException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        acesso.inserir();
        novoGerador = new Gerador();
        tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Gerador cadastrado com sucesso!");
        return "index";
    }
    
    public String registrarReciclador(){
        String retorno = null;
        DAOReciclador acesso = new DAOReciclador(novoReciclador);
        novoReciclador.getEndereco().setPessoa(novoReciclador);
        novoReciclador.setPedidosDeReciclagens(null);
        String email = novoReciclador.getEmail();
        String cnpj = novoReciclador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Reciclador no Sistema!");
            return "";
        }  
       //Insere LAT E LNG
       Endereco end = novoReciclador.getEndereco();
        try {
            retorno = UtilMap.codigoGeo(end.getLogradouro() + "," + end.getNumero() + "," +end.getCidade());
            String[] split = retorno.split(",");
            novoReciclador.getEndereco().setLat(Double.valueOf(split[0]));
            novoReciclador.getEndereco().setLng(Double.valueOf(split[1]));
        } catch (ApiException | InterruptedException | IOException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        acesso.inserir();
        novoReciclador = new Reciclador();
        tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Reciclador cadastrado com sucesso!");
        return "index";
    }
      
    public void voltar(){
        tipoDePessoa = 0;
        novoReciclador = new Reciclador();
        novoGerador = new Gerador();
        novoTransportador = new Transportador();   
    }
   
    /**
     * @return the tipoDePessoa
     */
    public int getTipoDePessoa() {
        return tipoDePessoa;
    }

    /**
     * @param tipoDePessoa the tipoDePessoa to set
     */
    public void setTipoDePessoa(int tipoDePessoa) {
        this.tipoDePessoa = tipoDePessoa;
    }

    /**
     * @return the novoGerador
     */
    public Gerador getNovoGerador() {
        return novoGerador;
    }

    /**
     * @param novoGerador the novoGerador to set
     */
    public void setNovoGerador(Gerador novoGerador) {
        this.novoGerador = novoGerador;
    }

    /**
     * @return the novoReciclador
     */
    public Reciclador getNovoReciclador() {
        return novoReciclador;
    }

    /**
     * @param novoReciclador the novoReciclador to set
     */
    public void setNovoReciclador(Reciclador novoReciclador) {
        this.novoReciclador = novoReciclador;
    }

    /**
     * @return the novoTransportador
     */
    public Transportador getNovoTransportador() {
        return novoTransportador;
    }

    /**
     * @param novoTransportador the novoTransportador to set
     */
    public void setNovoTransportador(Transportador novoTransportador) {
        this.novoTransportador = novoTransportador;
    }

    

 
}
