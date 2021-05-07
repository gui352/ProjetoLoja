package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.Controller;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {
	Scanner dgt = new Scanner(System.in);
	ListaProduto listaProduto = new ListaProduto();
	EditaProduto editaProduto = new EditaProduto();

	public CarrinhoModel cadastrarItemCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos. ");
			return null;
		}

		listaProduto.consultarProdutos(produtos);

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.println("Informar o ID do produto: ");
		carrinhoModel.setIdProduto(dgt.nextInt() - 1);

		int idProduto = (carrinhoModel.getIdProduto());

		if (idProduto > produtos.size()) {
			System.out.println("Este produto não está cadastrado");
			return null;
		}
		System.out.println("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeItens(dgt.nextInt());

		if (carrinhoModel.getQuantidadeItens() > produtos.get(carrinhoModel.getIdProduto()).getQuantidadeDeProduto()) {
			System.out.println("O produto não possui a quantidade desejada");

		}

		editaProduto.atualizarQuantidadeEValor(produtos, carrinhoModel.getQuantidadeItens(), idProduto);

		carrinhoModel.setProduto(produtos.get(idProduto));
		carrinhoModel
				.setValorTotalPorItem(carrinhoModel.getQuantidadeItens() * produtos.get(idProduto).getPrecoDoProduto());

		return carrinhoModel;
	}

}
