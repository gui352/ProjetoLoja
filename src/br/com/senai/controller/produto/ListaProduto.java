package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;

public class ListaProduto {

	private Connection connection;

	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet consultarProdutos() {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM produto ORDER BY codigo ASC;");
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui produtos cadastrados.");
				return null;
			}

			System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");

			resultSet.previous();

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
