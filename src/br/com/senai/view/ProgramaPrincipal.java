package br.com.senai.view;

import java.util.List;
import java.util.ArrayList;

import br.com.senai.model.ProdutoModel;
import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensDoCarrinho = new ArrayList<CarrinhoModel>();
		Controller produtoController = new Controller();

		// Controle do loop de saída
		boolean sair = false;

		AdicionaCliente adicionaCliente = new AdicionaCliente();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();

		do {
			produtoController.menu();
			int opcao = produtoController.opcao();
			switch (opcao) {
			
			case 1:
				cadastraProduto.cadastrarProduto();
				break;
			case 2:
				listaProduto.consultarProdutos(produtos);
				break;
			case 3:
				editaProduto.editarProduto(produtos);
				break;
			case 4:
				deletaProduto.removerProdutos(produtos);
				break;
			case 5:
				itensDoCarrinho.add(adicionaItemNoCarrinho.cadastrarItemCarrinho(produtos));
				break;
			case 6:
				listaCarrinho.listarItensNoCarrinho(itensDoCarrinho);
				break;
			case 7:
				listaCarrinho.gerarCupom(itensDoCarrinho, adicionaCliente.definirCliente());
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);
		System.out.println("Programa encerrado!");
	}
}
