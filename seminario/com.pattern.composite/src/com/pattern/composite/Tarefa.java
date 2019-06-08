package com.pattern.composite;

public class Tarefa  implements Projeto{
	private String nome;
	
	public Tarefa(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void listarTarefa() {
		System.out.println(nome);
	}
}
