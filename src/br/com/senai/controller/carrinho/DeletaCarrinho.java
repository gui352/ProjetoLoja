package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class DeletaCarrinho {

	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	private ListaCarrinho listaCarrinho;

	public DeletaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean verificaSeExite(int id) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produtosDoCarrinho WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

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

	public void removerProdutos(int id) {

		int atualizaProduto = 0;
		double valorTotal = 0;
		int quantidade = 0;

		PreparedStatement preparedStatement;
		listaCarrinho = new ListaCarrinho();

		System.out.println("--- REMOVER PRODUTO ---");

		if (listaCarrinho.listarItensNoCarrinho(id) == null) {
			return;
		}

		System.out.print("Informe o ID do produto  ser removido: ");
		int idDoProduto = dgt.nextInt();

		try {

			if (!verificaSeExite(idDoProduto)) {
				return;
			}

			String sql = ("SELECT * FROM produtosDoCarrinho WHERE codigo = ? AND idDoUsuario = ?");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			atualizaProduto = resultSet.getInt("quantidadeDeProduto");
			valorTotal = resultSet.getDouble("totalPorItem");
			preparedStatement.clearParameters();
			sql = ("SELECT * FROM produtosDoCarrinho WHERE codigo = ?");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			quantidade = resultSet.getInt("quantidadeDeProduto");

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			String sql = "DELETE FROM produtosDoCarrinho WHERE codigo = ? AND idDoUsuario = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();

			sql = ("UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigo = ? ");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantidade);
			preparedStatement.setDouble(2, valorTotal);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir esta informação!");
			return;
		}
	}
}
