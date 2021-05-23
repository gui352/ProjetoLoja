package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.senai.model.CarrinhoModel;
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

	public CarrinhoModel cadastrarItemCarrinho(int id) {
		PreparedStatement preparedStatement;

		carrinhoModel = new CarrinhoModel();
		ListaProduto = new ListaProduto();

		int quantidadeDeEstoque = 0;
		double preco = -1;
		String nomeDoUsuario = "";

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
			String nome = "SELECT nome FROM usuarios WHERE id = ? ";
			preparedStatement = connection.prepareStatement(nome);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			nomeDoUsuario = resultSet.getString("nome");

			if (!resultSet.next()) {
				System.out.println("Login inexistente");
				return null;
			}
			resultSet.previous();

		} catch (Exception e) {
			System.out.println("Error");
			return null;
		}

		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM produto WHERE codigo = ? ");
			prepareStatement.setInt(1, idDoProduto);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			preco = resultSet.getDouble("precoDoProduto");
			quantidadeDeEstoque = resultSet.getInt("quantidadeDeProduto");
		} catch (Exception e) {
			System.out.println("Nem entrou");
			e.printStackTrace();
		}
		System.out.print("Informe a quantidade desejada: ");
		qtde = dgt.nextInt();
		quantidadeDeEstoque -= qtde;
		carrinhoModel.setValorTotalPorItem(preco * qtde);

		try {

			String sql = "INSERT INTO produtosDoCarrinho (codigo, nomeDoProduto, precoDoProduto, quantidadeDeProduto, totalPorItem, idDoUsuario, nomeDoUsuario) "
					+ " SELECT ?, nomeDoProduto, precoDoProduto, ?, ?, ?, ? " + " FROM produto " + " WHERE codigo = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.setInt(2, qtde);
			preparedStatement.setDouble(3, carrinhoModel.getValorTotalPorItem());
			preparedStatement.setInt(4, id);
			preparedStatement.setString(5, nomeDoUsuario);
			preparedStatement.setInt(6, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao cadastrar o produto.");
		}

		try {

			String sql = ("UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigo = ? ");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantidadeDeEstoque);
			preparedStatement.setDouble(2, quantidadeDeEstoque * preco);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir esta informação!");
			return null;
		}

		EditarProduto.atualizarQuantidadeEValor(quantidadeDeEstoque, idDoProduto, (quantidadeDeEstoque * preco));
		return carrinhoModel;
	}
}
