/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.maps.errors.ApiException;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.dto.PedidoReciclagemDto;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMap;

@ManagedBean(name="controladorPedidoReciclagem")
@SessionScoped
public class ControladorPedidoReciclagem extends ControladorPrincipal<PedidoReciclagem> {
    
    private  DAOPedidoReciclagem dao = new DAOPedidoReciclagem(new PedidoReciclagem());
    private List<PedidoReciclagem> pedidosVendendo = new ArrayList<>();
    
    private List<PedidoReciclagemDto> pedidosDto = new ArrayList<>();
    
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
        this.popularListaDto();
    }
    
    private void popularListaDto(){
        if(pedidosVendendo != null && pedidosVendendo.size() > 0){
            pedidosDto = new ArrayList<>();
            for(PedidoReciclagem pedido : pedidosVendendo){
                if(pedido.getReciclador() == null){
                        PedidoReciclagemDto dto = new PedidoReciclagemDto();
                        dto.setCidade(pedido.getGerador().getEndereco().getCidade());
                        dto.setIdPedido(pedido.getIdPedidoReciclagem());
                        dto.setNomeMaterial(pedido.getItem().getMaterial().getNome());
                        dto.setPesoTotal(pedido.getItem().getQuantidade());
                        dto.setValorTotal(pedido.getValorTotal());
                        dto.setLat(pedido.getGerador().getEndereco().getLat());
                        dto.setLng(pedido.getGerador().getEndereco().getLng());
                        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("recicladorLogado") != null){
                           Reciclador reci = (Reciclador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("recicladorLogado");
                            dto.setDistancia(this.calculaDistancia(pedido.getGerador().getEndereco(), reci.getEndereco()));
                        }
                        pedidosDto.add(dto);
                }
            }       
        }
    }
    
    public String calculaDistancia(Endereco enderecoRemetente, Endereco enderecoDestinatario){
        String endereco1 = enderecoRemetente.getLogradouro() + "," + enderecoRemetente.getNumero() + ',' + enderecoRemetente.getCidade();
        String endereco2 = enderecoDestinatario.getLogradouro() + "," + enderecoDestinatario.getNumero() + "," + enderecoDestinatario.getCidade();
        try {
            return UtilMap.calcularDistancia(endereco1, endereco2);
        } catch (ApiException | IOException | InterruptedException ex) {
            Logger.getLogger(ControladorPedidoReciclagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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

    /**
     * @return the pedidosDto
     */
    public List<PedidoReciclagemDto> getPedidosDto() {
        return pedidosDto;
    }

    /**
     * @param pedidosDto the pedidosDto to set
     */
    public void setPedidosDto(List<PedidoReciclagemDto> pedidosDto) {
        this.pedidosDto = pedidosDto;
    }
 
}
