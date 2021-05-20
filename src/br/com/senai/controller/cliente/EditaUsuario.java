package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class EditaUsuario {
	
	private ClienteModel clienteModel = new ClienteModel();
	private ListaUsuario listaUsuario = new ListaUsuario();
	private Scanner dgt = new Scanner(System.in);
	private Connection connection;
	
	public EditaUsuario() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ClienteModel editarCliente() {
		PreparedStatement preparedStatement;

		clienteModel = new ClienteModel();
		listaUsuario = new ListaUsuario();

		int id, index;

		if (listaUsuario.listarCliente() == (null)) {
			return null;
		}

		System.out.println("--------- EDITAR DADOS DE USUÁRIO ----------");
		System.out.print("Informe o ID do usuário: ");
		id = dgt.nextInt();

		try {
			String sql = "SELECT * FROM usuarios WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este usuário não existe.");
				return null;
			} else {
				clienteModel.setNome(resultSet.getString("nome"));
				clienteModel.setSenha(resultSet.getInt("senha"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		System.out.println("--- CAMPOS ---");
		System.out.println("1) Nome do usuário");
		System.out.println("2) Senha do usuário");
		System.out.print("Informe o campo que deseja editar: ");
		index = dgt.nextInt();

		switch (index) {
		case 1:
			editarNomeDoCliente(id);
			break;
		case 2:
			editarSenhaDoCliente(id);
			break;
		default:
			System.out.println("Opção inválida!!");
			break;
		}
		return clienteModel;
	}

	private ClienteModel editarNomeDoCliente(int id) {
		PreparedStatement preparedStatement;

		System.out.print("Informe o novo nome para o usuário: ");
		clienteModel.setNome(dgt.next());

		try {
			String sql = "UPDATE usuarios SET nome = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, clienteModel.getNome());
			preparedStatement.setInt(2, id);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return clienteModel;

	}

	private ClienteModel editarSenhaDoCliente(int id) {
		PreparedStatement preparedStatement;

		System.out.print("Informe a nova senha para o usuário: ");
		clienteModel.setSenha(dgt.nextInt());

		try {
			String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, clienteModel.getSenha());
			preparedStatement.setInt(2, id);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return clienteModel;

	}
}
