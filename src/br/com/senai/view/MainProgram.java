package br.com.senai.view;

import java.util.Scanner;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.*;
import br.com.senai.controller.cliente.CadastraUsuario;
import br.com.senai.controller.cliente.DefinirCliente;
import br.com.senai.controller.cliente.DeletaUsuario;
import br.com.senai.controller.cliente.EditaUsuario;
import br.com.senai.controller.cliente.ListaUsuario;
import br.com.senai.controller.produto.*;

public class MainProgram {
	public static void main(String[] args) {

		Controller Controller = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionarCarrinho AdicionarCarrinho = new AdicionarCarrinho();
		CadastrarProduto CadastrarProduto = new CadastrarProduto();
		ListaProduto ListaProduto = new ListaProduto();
		EditarProduto EditarProduto = new EditarProduto();
		DeletarProduto DeletarProduto = new DeletarProduto();
		DeletaCarrinho deletaCarrinho = new DeletaCarrinho();
		DefinirCliente definirCliente = new DefinirCliente();
		DeletaUsuario deletaUsuario = new DeletaUsuario();
		EditaUsuario editaUsuario = new EditaUsuario();
		CadastraUsuario cadastraUsuario = new CadastraUsuario();
		ListaUsuario listaUsuario = new ListaUsuario();
		Scanner dgt = new Scanner(System.in);
		

		boolean sair = false;

		do {

			int id = definirCliente.loginCliente();
			while (id == -1) {
				System.out.println("Senha ou login inválidos!");
				id = definirCliente.loginCliente();
			}

			if (definirCliente.verificarAcesso(id) == 0) {
				do {
					Controller.menuCliente();
					int opc = Controller.opcao();
					switch (opc) {
					case 1:
						ListaProduto.consultarProdutos();
						break;
					case 2:
						AdicionarCarrinho.cadastrarItemCarrinho(id);
						break;
					case 3:
						listaCarrinho.listarItensNoCarrinho(id);
						break;
					case 4:
						listaCarrinho.gerarCupom(id);
						break;
					case 5:
						deletaCarrinho.removerProdutos(id);
						break;
					case 6:
						sair = true;
						break;

					default:
						System.out.println("Opção inválida!!!");
						break;
					}
				} while (!sair);
				System.out.println("Voce deseja encerrar o programa? sim - 1/ não - 2");
			} else {

				do {
					Controller.menuFuncionario();
					int opc = Controller.opcao();
					switch (opc) {
					case 1:
						CadastrarProduto.cadastrarProduto();
						break;
					case 2:
						ListaProduto.consultarProdutos();
						break;
					case 3:
						EditarProduto.editarProduto();
						break;
					case 4:
						DeletarProduto.removerProdutos();
						break;
					case 5:
						cadastraUsuario.cadastrarUsuario();
						break;
					case 6:
						listaUsuario.listarCliente();
						break;
					case 7:
						editaUsuario.editarCliente();
						break;
					case 8:
						deletaUsuario.removerUsuario();
						break;
					case 9:
						listaCarrinho.gerarHistorico(id);
						break;
					case 10:
						sair = true;
						break;
					default:
						System.out.println("Opção inválida!!!");
						break;
					}
				} while (!sair);
				System.out.println("Voce deseja encerrar o programa? sim - 1/ não - 2");
			}
			int opcao = dgt.nextInt();
			if (opcao == 1) {
				sair = true;
			}
			sair = false;
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}

	
}
