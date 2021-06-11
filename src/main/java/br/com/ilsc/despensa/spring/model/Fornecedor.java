package br.com.ilsc.despensa.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nomeEmpresa;

//	@OneToMany(mappedBy = "fornecedor")
//	@JoinColumn(name = "fornecedor_id")
//	private List<Compra> compras;

	public Fornecedor() {

	}

	public Fornecedor(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

//	public List<Compra> getCompras() {
//		return compras;
//	}
//
//	public void setCompras(List<Compra> compras) {
//		this.compras = compras;
//	}

}
