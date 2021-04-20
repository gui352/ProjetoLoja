package br.com.senai.controller;

import java.util.List;

import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class ProdutoController {

	private Scanner input;

	public ProdutoController() {
		input = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print(">  ");
		return input.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1 - Cadastrar Itens");
		System.out.println("2 - Listar Estoque");
		System.out.println("3 - Editar Item");
		System.out.println("4 - Remover Item");
		System.out.println("5 - Realizar Venda");
		System.out.println("9 - Sair do Sistema");
		System.out.println("--------------------");
	}

	public ProdutoModel cadastrarProduto() {

		ProdutoModel produtoModel = new ProdutoModel();
		System.out.println("--- CADASTRAR ITENS ---");
		System.out.print("Produto: ");
		input.nextLine();
		produtoModel.setNomeDoProduto(input.nextLine());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(input.nextDouble());
		System.out.print("Quantidade: ");
		produtoModel.setQuantidadeDeProduto(input.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
		return produtoModel;
	}

	public void consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		for(ProdutoModel produtoModel : produtos) {
			System.out.println(produtoModel + "\n");
		}
	}
}
