/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMap;
import com.google.maps.errors.ApiException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorPedidoReciclagem")
@SessionScoped
public class ControladorPedidoReciclagem extends ControladorPrincipal<PedidoReciclagem> {
    
    private  DAOPedidoReciclagem dao = new DAOPedidoReciclagem(new PedidoReciclagem());
    private List<PedidoReciclagem> pedidosVendendo = new ArrayList<PedidoReciclagem>();
    
    private Material materialFiltro;
    private boolean listarPorCidadeEndereco = false;
    private String cidadeFiltro;
    
    public ControladorPedidoReciclagem(){
        super (new PedidoReciclagem());
        this.updateList();
    }
    
    public List<PedidoReciclagem> listagemVendas(){
        return (List<PedidoReciclagem>) getDao().listaDeMateriaisVendendo();
    }
    
    public List<PedidoReciclagem> geradorItensVendendo(Integer id){
        String query = "SELECT * FROM pedidoreciclagem as P WHERE P.reciclador_idPessoaJuridica IS NULL AND P.transportador_idPessoaJuridica IS NULL"
                + "AND P.gerador_idPessoaJuridica = " + id;
        DAO<PedidoReciclagem> dao = new DAO(new PedidoReciclagem());
        return (List<PedidoReciclagem>) dao.executarConsultaPersonalizada(query);
    }
    
    public String calcularDistancia(PedidoReciclagem pedido, Reciclador logado){
        Endereco end1 = pedido.getGerador().getEndereco();
        Endereco end2 = logado.getEndereco();
        String endStr1 = end1.getLogradouro() + "," + end1.getNumero() + "," + end1.getCidade();
        String endStr2 = end2.getLogradouro() + "," + end2.getNumero() + "," + end2.getCidade();
        try {
            return UtilMap.calcularDistancia(endStr1, endStr2);
        } catch (ApiException | IOException | InterruptedException ex) {
            Logger.getLogger(ControladorPedidoReciclagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateList(){
        if(materialFiltro != null && cidadeFiltro != null && cidadeFiltro.length() > 5){
            pedidosVendendo = getDao().listarPorCidadeMaterial(cidadeFiltro, materialFiltro.getIdMaterial());
        }else if(materialFiltro != null && cidadeFiltro == null){
            pedidosVendendo = getDao().listarPorMaterial(materialFiltro.getIdMaterial());
        }else if(materialFiltro == null && cidadeFiltro != null){
            pedidosVendendo = getDao().listarPorCidade(cidadeFiltro);
        }else{
            pedidosVendendo = getDao().listarTodos();
        }
    }

    /**
     * @return the pedidosVendendo
     */
    public List<PedidoReciclagem> getPedidosVendendo() {
        return pedidosVendendo;
    }

    /**
     * @param pedidosVendendo the pedidosVendendo to set
     */
    public void setPedidosVendendo(List<PedidoReciclagem> pedidosVendendo) {
        this.pedidosVendendo = pedidosVendendo;
    }

    /**
     * @return the materialFiltro
     */
    public Material getMaterialFiltro() {
        return materialFiltro;
    }

    /**
     * @param materialFiltro the materialFiltro to set
     */
    public void setMaterialFiltro(Material materialFiltro) {
        this.materialFiltro = materialFiltro;
    }

 
    /**
     * @return the listarPorCidadeEndereco
     */
    public boolean isListarPorCidadeEndereco() {
        return listarPorCidadeEndereco;
    }

    /**
     * @param listarPorCidadeEndereco the listarPorCidadeEndereco to set
     */
    public void setListarPorCidadeEndereco(boolean listarPorCidadeEndereco) {
        this.listarPorCidadeEndereco = listarPorCidadeEndereco;
    }

    /**
     * @return the dao
     */
    public DAOPedidoReciclagem getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DAOPedidoReciclagem dao) {
        this.dao = dao;
    }

    /**
     * @return the cidadeFiltro
     */
    public String getCidadeFiltro() {
        return cidadeFiltro;
    }

    /**
     * @param cidadeFiltro the cidadeFiltro to set
     */
    public void setCidadeFiltro(String cidadeFiltro) {
        this.cidadeFiltro = cidadeFiltro;
    }
    
}
