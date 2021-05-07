package br.com.senai.controller.produto;

import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class CadastraProduto {
	
	ProdutoModel produtoModel = new ProdutoModel();
	Scanner dgt = new Scanner(System.in);

	public ProdutoModel cadastrarProduto() {

		produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(dgt.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(dgt.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(dgt.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;
	}

}
