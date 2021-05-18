package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;
import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.EditarProduto;
import br.com.senai.controller.produto.ListaProduto;

public class AdicionarCarrinho {
	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	ListaProduto ListaProduto = new ListaProduto();
	EditarProduto EditarProduto = new EditarProduto();
	CarrinhoModel carrinhoModel = new CarrinhoModel();
	
	public AdicionarCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public boolean verificaSeExiste(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public CarrinhoModel cadastrarItemCarrinho() {
		PreparedStatement preparedStatement;
		
		carrinhoModel = new CarrinhoModel();
		ListaProduto = new ListaProduto();
		
		int idDoProduto, qtde;
		
		if (ListaProduto.consultarProdutos() == (null)) {
			return null;
		}
		
		
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = dgt.nextInt();
		if (!verificaSeExiste(idDoProduto)) {
			return null;
		}
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from produto where codigo = " + idDoProduto);
			ResultSet resultSet = prepareStatement.executeQuery();
			carrinhoModel.setValorTotalPorItem(resultSet.getDouble("precoDoProduto"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.print("Informe a quantidade desejada: ");
		qtde = dgt.nextInt();
		carrinhoModel.setValorTotalPorItem(carrinhoModel.getValorTotalPorItem() * qtde);
		System.out.println("a");
		System.out.println(carrinhoModel.getValorTotalPorItem());
		
		try {
			
			String sql = "INSERT INTO produtosdocarrinho (nomeDoProduto, precoDoProduto, quantidadeDeProduto, totalPorItem) "
					+ " SELECT nomeDoProduto, precoDoProduto, ?, ? "
					+ " FROM produto "
					+ " WHERE codigo = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, qtde);
			preparedStatement.setDouble(2, carrinhoModel.getValorTotalPorItem());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar o produto.");
		}
		
		/*if(idProduto > produtos.size()) {
			System.out.println("Este produto não está cadastrado");
			return null;
		} 
		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeItens(dgt.nextInt());
		
		
		if(carrinhoModel.getQuantidadeItens() > produto.get(carrinhoModel.getIdProduto()).getQuantidadeDeProduto()) {
			System.out.println("O produto não possui a quantidade desejada");
			
		}*/
		
	/*	EditarProduto.atualizarQuantidadeEValor(produtos, carrinhoModel.getQuantidadeItens(), idProduto);
		
		carrinhoModel.setProduto(produtos.get(idProduto));
		carrinhoModel.setValorTotalPorItem(carrinhoModel.getQuantidadeItens() *
				produtos.get(idProduto).getPrecoDoProduto());
		if(produtos.get(idProduto).getQuantidadeDeProduto() == 0) {
			produtos.remove(idProduto);
		}*/
		return carrinhoModel;
	}
}
