package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class CadastraUsuario {
	
	Scanner dgt = new Scanner(System.in);
	ClienteModel clienteModel = new ClienteModel();
	private Connection connection;
	
	public CadastraUsuario() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ClienteModel cadastrarUsuario() {
		
		System.out.println("\n--- CADASTRAR CLIENTE ---\n");
		System.out.print("Nome: ");
		clienteModel.setNome(dgt.next());
		System.out.print("Senha:");
		clienteModel.setSenha(dgt.nextInt());
		System.out.print("Acesso:");
		clienteModel.setAcesso(dgt.nextInt());
		
		try {
			String sql = "INSERT INTO usuarios (nome, senha, acesso) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clienteModel.getNome());
			preparedStatement.setInt(2, clienteModel.getSenha());
			preparedStatement.setInt(3, clienteModel.getAcesso());
			preparedStatement.execute();
		}catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}
		
		return clienteModel;
	}
	
}
