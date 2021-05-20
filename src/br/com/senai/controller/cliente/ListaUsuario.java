package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaUsuario {
	
	private Connection connection;
	
	public ListaUsuario() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarCliente() {
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM usuarios ORDER BY id ASC;");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui usuários cadastrados.");
				return null;
			}
			
			System.out.println("\n----- USUÁRIOS CADASTRASDOS -----\n");
			System.out.printf("| %2s | %25s | %4s | \n", "ID", "Nome", "Senha");
			
			resultSet.previous();
			
			while (resultSet.next()) {

				System.out.printf("| %2s | %25s | %4s |\n", resultSet.getInt("id"),
						resultSet.getString("nome"), resultSet.getDouble("senha"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
		
	}

}
