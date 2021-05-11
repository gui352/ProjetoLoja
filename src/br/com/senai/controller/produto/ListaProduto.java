package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class ListaProduto {

	private Connection connection;
	private ResultSet resultSet;

	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet consultarProdutos() {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from produto");
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
			while (resultSet.next()) {

				System.out.printf("| %2s | %15s | %8s | %4s | %9.2f |\n", resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("saldoEmEstoque"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}
