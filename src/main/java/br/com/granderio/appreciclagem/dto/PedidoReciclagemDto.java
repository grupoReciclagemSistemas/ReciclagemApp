/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dto;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class PedidoReciclagemDto {
    
    //Classe DTO para pedido
    private long idPedido;
    private double valorTotal;
    private double pesoTotal;
    private String cidade;
    private String nomeMaterial;
    private String distancia;
    
    private double lat;
    private double lng;
    
    public PedidoReciclagemDto(){
        
    }
    
    public PedidoReciclagemDto(long idPedido, double valorTotal, double pesoTotal, String cidade, String nomeMaterial, String distancia){
        this.idPedido = idPedido;
        this.valorTotal = valorTotal;
        this.pesoTotal = pesoTotal;
        this.cidade = cidade;
        this.nomeMaterial = nomeMaterial;
        this.distancia = distancia;
    }

    /**
     * @return the idPedido
     */
    public long getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
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
     * @return the pesoTotal
     */
    public double getPesoTotal() {
        return pesoTotal;
    }

    /**
     * @param pesoTotal the pesoTotal to set
     */
    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the nomeMaterial
     */
    public String getNomeMaterial() {
        return nomeMaterial;
    }

    /**
     * @param nomeMaterial the nomeMaterial to set
     */
    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    /**
     * @return the distancia
     */
    public String getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    
    public String retornaLatLng(){
        return lat + ", " + lng;
    }

}
