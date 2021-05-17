package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListaProduto;

public class DeletarProduto {

	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	private ListaProduto listaProduto;

	public DeletarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean verificaSeExiste(int id) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
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

	public void removerProdutos() {
		PreparedStatement preparedStatement;
		listaProduto = new ListaProduto();

		System.out.println("--- REMOVER PRODUTO ---");

		if (listaProduto.consultarProdutos() == null) {
			return;
		}

		System.out.print("Informe o ID do produto  ser removido: ");
		int id = dgt.nextInt();

		try {

			if (!verificaSeExiste(id)) {
				return;
			}

			String sql = "DELETE FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir esta informação!");
			return;
		}

	}
}
