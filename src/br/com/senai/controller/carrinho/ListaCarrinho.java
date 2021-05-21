package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class ListaCarrinho {

	private Connection connection;
	Scanner dgt = new Scanner(System.in);
	double valorTotalDoCarrinho = 0;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarItensNoCarrinho(int id) {

		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection
					.prepareStatement("SELECT * FROM produtosDoCarrinho WHERE idDoUsuario = ? ORDER BY codigo ASC ");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui produtos cadastrados.");
				return null;
			}

			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

			resultSet.previous();

			while (resultSet.next()) {

				System.out.printf("| %2s | %15s | %8s | %4s | %9.2f |\n", resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("totalPorItem"));
				valorTotalDoCarrinho += resultSet.getDouble("totalPorItem");
			}

			System.out.println("\nValor total: R$" + valorTotalDoCarrinho);

			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}

	public void gerarCupom(int id) {
		PreparedStatement preparedStatement;

		try {
			String nome = "SELECT nome FROM usuarios WHERE id = ? ";
			preparedStatement = connection.prepareStatement(nome);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String cliente = resultSet.getString("nome");
			System.out.println("---- NOME DO CLIENTE: " + cliente + " ----");
			String sql = "SELECT * FROM produtosDocarrinho WHERE idDoUsuario = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();

			if (!resultSet.next()) {
				System.out.println("Voce não comprou nada");
				return;
			}
			resultSet.previous();

			this.listarItensNoCarrinho(id);
			System.out.println("--------------- O VALOR A SER PAGO É DE R$" + valorTotalDoCarrinho + "---------------");

		} catch (Exception e) {
			System.out.println("Error");
			return;
		}

	}
	
	public ResultSet gerarHistorico(int id) {
		PreparedStatement preparedStatement; 
		
		
		try {
			preparedStatement = connection.prepareStatement("SELECT codigo, nomeDoProduto, precoDoProduto, quantidadeDeProduto, totalPorItem, usuarios.nome FROM usuarios, produtosDoCarrinho;");
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui produtos cadastrados.");
				return null;
			}

			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |  %25s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total", "Nome do cliente");

			resultSet.previous();

			while (resultSet.next()) {

				System.out.printf("| %2s | %15s | %8s | %4s | %9.2f | %25s |\n", resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"), resultSet.getDouble("totalPorItem"), resultSet.getString("nome"));
				
			}

			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
