package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class DeletaProduto {
	Scanner dgt = new Scanner(System.in);
	ListaProduto listaProduto = new ListaProduto();
	
	public void removerProdutos(List<ProdutoModel> produtos) {
		listaProduto = new ListaProduto();
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
			return;
		}

		listaProduto.consultarProdutos(produtos);

		System.out.println("Informe o ID do produto  ser removido");
		int id = dgt.nextInt();

		if (id > produtos.size()) {
			System.out.println("Produto não cadastrado");
			return;
		}
		produtos.remove(id - 1);
	}
}
