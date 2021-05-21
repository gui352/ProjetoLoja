package br.com.senai.model;

public class ClienteModel {
	
	private int id;
	private String nome;
	private int senha;
	private int acesso;
	
	
	public int getAcesso() {
		return acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

	public ClienteModel(int id, String nome, int senha, int acesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.acesso = acesso;
	}
	
	public ClienteModel() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
 	

}
 