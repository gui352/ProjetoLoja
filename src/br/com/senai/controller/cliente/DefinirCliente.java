package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class DefinirCliente {
	PreparedStatement preparedStatement;
	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	ResultSet resultSet;

	public DefinirCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public int verificarAcesso(int id) {

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ? ");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui usuários cadastrados.");
				return -1;
			}

			return resultSet.getInt("acesso");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int loginCliente() {
		System.out.println("--- SISTEMA DE ENTRADA ---");
		System.out.print("Informe seu ID: ");
		int id = dgt.nextInt();
		System.out.print("Informe sua senha: ");
		int senha = dgt.nextInt();

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from usuarios where id = ? and senha = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, senha);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return -1;
			}

			System.out.println("Login realizado com sucesso!");
			return id;

		} catch (Exception e) {
			return -1;
		}

	}

}
