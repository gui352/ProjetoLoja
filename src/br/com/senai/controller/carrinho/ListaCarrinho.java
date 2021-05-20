package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarItensNoCarrinho() {

		PreparedStatement preparedStatement;
		double valorTotalDoCarrinho = 0;

		try {
			String sql = ("SELECT SUM(totalPorItem) FROM produtosDoCarrinho");
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			valorTotalDoCarrinho = resultSet.getDouble("SUM(totalPorItem)");

		} catch (Exception e) {
			System.out.println("Nem entrou");
			e.printStackTrace();
		}

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM produtosDoCarrinho ORDER BY codigo ASC;");
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui produtos cadastrados.");
				return null;
			}

			System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

			resultSet.previous();

			while (resultSet.next()) {

				System.out.printf("| %2s | %15s | %8s | %4s | %9.2f |\n", resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("totalPorItem"));
			}

			System.out.println("\nValor total: R$" + valorTotalDoCarrinho);

			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}
