
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class Estoque implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long idEstoque;
     
    @OneToOne
    private Material material;
    
    @OneToOne
    private EstoqueGerador estoqueGerador;
    
    //Qtd em Toneladas
    private double quantidadeMaterial;
    
    public Estoque(){
        idEstoque = -1;
        material = null;
        estoqueGerador = null;
    }

    /**
     * @return the idEstoque
     */
    public long getIdEstoque() {
        return idEstoque;
    }

    /**
     * @param idEstoque the idEstoque to set
     */
    public void setIdEstoque(long idEstoque) {
        this.idEstoque = idEstoque;
    }


    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the quantidadeMaterial
     */
    public double getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    /**
     * @param quantidadeMaterial the quantidadeMaterial to set
     */
    public void setQuantidadeMaterial(double quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    /**
     * @return the estoqueGerador
     */
    public EstoqueGerador getEstoqueGerador() {
        return estoqueGerador;
    }

    /**
     * @param estoqueGerador the estoqueGerador to set
     */
    public void setEstoqueGerador(EstoqueGerador estoqueGerador) {
        this.estoqueGerador = estoqueGerador;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estoqueGerador == null) ? 0 : estoqueGerador.hashCode());
		result = prime * result + (int) (idEstoque ^ (idEstoque >>> 32));
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantidadeMaterial);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Estoque other = (Estoque) obj;
		if (estoqueGerador == null) {
			if (other.estoqueGerador != null)
				return false;
		} else if (!estoqueGerador.equals(other.estoqueGerador))
			return false;
		if (idEstoque != other.idEstoque)
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (Double.doubleToLongBits(quantidadeMaterial) != Double.doubleToLongBits(other.quantidadeMaterial))
			return false;
		return true;
	}
    
}
