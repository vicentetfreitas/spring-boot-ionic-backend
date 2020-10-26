package com.vicente.modelagemconceitual;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Instanciando Categoria
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		// Instanciando Produto
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		// Instanciando Categorias de Produtos
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		// Instanciando Produtos pertecentes as Categorias
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		// Salvando no BD
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

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
