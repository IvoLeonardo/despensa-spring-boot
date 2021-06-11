package br.com.ilsc.despensa.spring.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();

	@ManyToOne
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private List<ItemCompra> itens = new ArrayList<>();

	public Compra() {

	}

	public Long getId() {
		return id;
	}

	public Compra(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * Método que faz com que ambas as entidades se conheçam, pois são
	 * bi-direcionais
	 */
	public void adicionarItem(ItemCompra item) {
		item.setCompra(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}

	public LocalDate getData() {
		return data;
	}

	public void setDataDaCompra(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
