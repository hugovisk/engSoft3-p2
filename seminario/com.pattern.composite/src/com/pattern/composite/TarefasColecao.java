package com.pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class TarefasColecao implements Projeto {
	
	private String nome;
	List<Projeto> adicionarTarefas = new ArrayList<>();
	
	public TarefasColecao(String nome) {
		this.nome = nome;
	}
	
	public void adiciona(Projeto tarefa) {
		adicionarTarefas.add(tarefa);
	}
	
	public void remove(Projeto tarefa) {
		adicionarTarefas.remove(tarefa);
	}
	
	@Override
	public void listarTarefa() {
		System.out.println(" ");
		System.out.println("--" + nome);		
		for (Projeto adicionarTarefa : adicionarTarefas) {
			adicionarTarefa.listarTarefa();
		}
	}
}
