package br.com.senai.controller.produto;

import java.sql.Connection;
import java.util.Scanner;

import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class CadastrarProduto {
	Scanner dgt = new Scanner(System.in);
	ProdutoModel produtoModel = new ProdutoModel();
	private Connection connection;

	public CadastrarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel cadastrarProduto() {
	
			System.out.println("\n--- CADASTRAR ITENS ---\n");
			System.out.print("Produto: ");
			produtoModel.setNomeDoProduto(dgt.next());
			System.out.print("Pre?o: ");
			produtoModel.setPrecoDoProduto(dgt.nextDouble());
			System.out.print("Quantidade:");
			produtoModel.setQuantidadeDeProduto(dgt.nextInt());
			produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
	
			try {
				String sql = "INSERT INTO produto (nomeDoProduto, precoDoProduto, quantidadeDeProduto, saldoEmEstoque)" + 
			"VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, produtoModel.getNomeDoProduto());
				preparedStatement.setDouble(2, produtoModel.getPrecoDoProduto());
				preparedStatement.setInt(3, produtoModel.getQuantidadeDeProduto());
				preparedStatement.setDouble(4, produtoModel.getSaldoEmEstoque());
				
				preparedStatement.execute();
			}catch (Exception e) {
				System.out.println("Erro ao cadastrar os dados.");
			}
			
			return produtoModel;
	}
}
