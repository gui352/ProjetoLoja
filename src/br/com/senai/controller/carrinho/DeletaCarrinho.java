package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

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
		
	public void removerProdutos() {
			PreparedStatement preparedStatement;
			listaCarrinho = new ListaCarrinho();
			
			System.out.println("--- REMOVER PRODUTO ---");

			if (listaCarrinho.listarItensNoCarrinho() == null) {
				return;
			}

			System.out.print("Informe o ID do produto  ser removido: ");
			int id = dgt.nextInt();

			try {

				if (!verificaSeExite(id)) {
					return;
				}
				
				

				String sql = "DELETE FROM produtosDoCarrinho WHERE codigo = ?";
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


