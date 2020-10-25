package com.vicente.modelagemconceitual.domain;

import javax.persistence.Entity;

import com.vicente.modelagemconceitual.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	private Integer numeroDeParcelas;

	// ASSOCIAÇÕES
	// (VAZIO)

	// CONSTRUTORES
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	// GETS E SETS
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
