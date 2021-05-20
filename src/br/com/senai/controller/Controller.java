package br.com.senai.controller;

import java.util.Scanner;

import br.com.senai.controller.produto.ListaProduto;

public class Controller {

	private Scanner dgt;
	ListaProduto ListaProduto = new ListaProduto();

	public Controller() {
		dgt = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return dgt.nextInt();
	}

	public void menuCliente() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Adicionar no Carrinho");
		System.out.println("2) Exibir carrinho ");
		System.out.println("3) Gerar Cupom ");
		System.out.println("4) Deletar Produto do Carrinho ");
		System.out.println("5) Sair do sistema");
		System.out.println("--------------------");
	}
	
	public void menuFuncionario() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Cadastrar Cliente");
		System.out.println("6) Listar Cliente");
		System.out.println("7) Editar Cliente");
		System.out.println("8) Remover Cliente");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

}
