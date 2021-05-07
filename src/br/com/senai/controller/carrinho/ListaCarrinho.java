package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {
	AdicionaCliente adicionaCliente = new AdicionaCliente();

	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");

		if (itensNoCarrinho.size() <= 0) {
			System.out.println("Não há itens no carrinho");
			return null;
		}
		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", item.getIdProduto() + 1,
					item.getProduto().getNomeDoProduto(), item.getProduto().getPrecoDoProduto(),
					item.getQuantidadeItens(), item.getValorTotalPorItem());
		});

		/*
		 * double valorTotalDoCarrinho = itensNoCarrinho.stream().
		 * mapToDouble(CarrinhoModel::getValorTotalPorItem).sum();
		 */
		double valorTotalDoCarrinho = itensNoCarrinho.stream().mapToDouble(item -> item.getValorTotalPorItem()).sum();

		System.out.println("\nValor total: R$ " + valorTotalDoCarrinho);

		return itensNoCarrinho;
	}

	public void gerarCupom(List<CarrinhoModel> itensDoCarrinho, String cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();

		if (itensDoCarrinho.size() <= 0) {
			System.out.println("Lista Vazia.");
			return;
		}

		listaCarrinho.listarItensNoCarrinho(itensDoCarrinho);
		System.out.println("Cliente: " + cliente);

	}
}
