package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class DeletaUsuario {

	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	private ListaUsuario listaUsuario = new ListaUsuario();

	public boolean verificaSeExite(int id) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM usuarios WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este usu�rio n�o existe.");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removerUsuario() {
		PreparedStatement preparedStatement;
		listaUsuario = new ListaUsuario();

		System.out.println("--- REMOVER USU�RIO ---");

		if (listaUsuario.listarCliente() == null) {
			return;
		}

		System.out.print("Informe o ID do usu�rio  ser removido: ");
		int id = dgt.nextInt();

		try {

			if (!verificaSeExite(id)) {
				return;
			}

			String sql = "DELETE FROM usuarios WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("N�o foi poss�vel excluir este usu�rio!");
			return;
		}
	}

}
