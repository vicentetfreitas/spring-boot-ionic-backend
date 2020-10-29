package com.vicente.modelagemconceitual.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.modelagemconceitual.domain.Categoria;
import com.vicente.modelagemconceitual.domain.Cidade;
import com.vicente.modelagemconceitual.domain.Cliente;
import com.vicente.modelagemconceitual.domain.Endereco;
import com.vicente.modelagemconceitual.domain.Estado;
import com.vicente.modelagemconceitual.domain.ItemPedido;
import com.vicente.modelagemconceitual.domain.Pagamento;
import com.vicente.modelagemconceitual.domain.PagamentoComBoleto;
import com.vicente.modelagemconceitual.domain.PagamentoComCartao;
import com.vicente.modelagemconceitual.domain.Pedido;
import com.vicente.modelagemconceitual.domain.Produto;
import com.vicente.modelagemconceitual.domain.enums.EstadoPagamento;
import com.vicente.modelagemconceitual.domain.enums.TipoCliente;
import com.vicente.modelagemconceitual.repositories.CategoriaRepository;
import com.vicente.modelagemconceitual.repositories.CidadeRepository;
import com.vicente.modelagemconceitual.repositories.ClienteRepository;
import com.vicente.modelagemconceitual.repositories.EnderecoRepository;
import com.vicente.modelagemconceitual.repositories.EstadoRepository;
import com.vicente.modelagemconceitual.repositories.ItemPedidoRepository;
import com.vicente.modelagemconceitual.repositories.PagamentoRepository;
import com.vicente.modelagemconceitual.repositories.PedidoRepository;
import com.vicente.modelagemconceitual.repositories.ProdutoRepository;

@Service
public class DBService {
	// Depedências
		@Autowired
		private CategoriaRepository categoriaRepository;
		@Autowired
		private ProdutoRepository produtoRepository;
		@Autowired
		private EstadoRepository estadoRepository;
		@Autowired
		private CidadeRepository cidadeRepository;
		@Autowired
		private ClienteRepository clienteRepository;
		@Autowired
		private EnderecoRepository enderecoRepository;
		@Autowired
		private PedidoRepository pedidoRepository;
		@Autowired
		private PagamentoRepository pagamentoRepository;
		@Autowired
		private ItemPedidoRepository itemPedidoRepository;

		public void instantiateTestDatabase() throws ParseException {
		// Instanciando Categoria
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat6 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat4 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		// Instanciando Produto
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto prod5 = new Produto(null, "Toalha de banho", 50.00);
		Produto prod6 = new Produto(null, "Colcha", 300.00);
		Produto prod7 = new Produto(null, "TV True color", 1200.00);
		Produto prod8 = new Produto(null, "Roçadeira", 300.00);
		Produto prod9 = new Produto(null, "Abajour", 300.00);
		Produto prod10 = new Produto(null, "Pendente", 300.00);
		Produto prod11 = new Produto(null, "Shampoo", 300.00);

		// Instanciando Categorias de Produtos
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));

		// Instanciando Produtos pertecentes as Categorias
		prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategorias().addAll(Arrays.asList(cat2));
		prod4.getCategorias().addAll(Arrays.asList(cat3));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat4));
		prod7.getCategorias().addAll(Arrays.asList(cat5));
		prod8.getCategorias().addAll(Arrays.asList(cat6));
		prod9.getCategorias().addAll(Arrays.asList(cat6));
		prod10.getCategorias().addAll(Arrays.asList(cat7));
		prod11.getCategorias().addAll(Arrays.asList(cat1, cat4));

		// Salvando no BD
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository
				.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		//////////////////////////////////////////////////////////////

		// Instanciando Estado
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		// Instanciando Estado
		Cidade ci1 = new Cidade(null, "Uberlândia", est1);
		Cidade ci2 = new Cidade(null, "São Paulo", est2);
		Cidade ci3 = new Cidade(null, "Campinas", est2);

		// Instanciando Cidades dos Estados
		est1.getCidades().addAll(Arrays.asList(ci1));
		est2.getCidades().addAll(Arrays.asList(ci2, ci3));

		// Salvando no BD
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3));

		//////////////////////////////////////////////////////////////

		// Instanciando Cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		// Inserindo Telefones do Cliente
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		// Instanciando Enderecos á Cidades
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardins", "38220834", cli1, ci1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, ci2);

		// Instanciando o relacionamento dos Enderecos a Cidades
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		// Salvando no BD
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		//////////////////////////////////////////////////////////////

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		//////////////////////////////////////////////////////////////

		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
