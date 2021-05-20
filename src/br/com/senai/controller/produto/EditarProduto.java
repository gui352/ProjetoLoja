package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditarProduto {

	private ProdutoModel produto = new ProdutoModel();
	private ListaProduto ListaProduto = new ListaProduto();
	private Scanner dgt = new Scanner(System.in);
	private Connection connection;

	public EditarProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;

		produto = new ProdutoModel();
		ListaProduto = new ListaProduto();

		int idDoProduto, index;

		if (ListaProduto.consultarProdutos() == (null)) {
			return null;
		}

		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = dgt.nextInt();

		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return null;
			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDeProduto"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		System.out.println("--- CAMPOS ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		index = dgt.nextInt();

		switch (index) {
		case 1:
			editarNomeDoProduto(idDoProduto);
			break;
		case 2:
			editarPrecoDoProduto(idDoProduto);
			break;
		case 3:
			editarQuantidadeDeProduto(idDoProduto);
			break;
		default:
			System.out.println("Opção inválida!!");
			break;
		}
		return produto;
	}

	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;

		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(dgt.next());

		try {
			String sql = "UPDATE produto SET nomeDoProduto = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;

	}

	private ProdutoModel editarPrecoDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;

		System.out.print("Informe o novo preço para o produto: ");
		produto.setPrecoDoProduto(dgt.nextDouble());

		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());

		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? " + " WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;

	}

	private ProdutoModel editarQuantidadeDeProduto(int idDoProduto) {
		PreparedStatement preparedStatement;

		System.out.print("Informe a nova quantidade do produto: ");
		produto.setQuantidadeDeProduto(dgt.nextInt());

		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());

		try {
			String sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? " + " WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;

	}

	public void atualizarQuantidadeEValor (int quantidade, int idDoProduto, double valorTotal) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = ("UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigo = ? ");
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, quantidade);
			preparedStatement.setDouble(2, valorTotal);
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
