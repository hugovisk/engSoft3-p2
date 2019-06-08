package com.pattern.composite;

public class Cliente {
	
	public static void main(String[] args) {
		TarefasColecao website = new TarefasColecao("WEBSITE");
		TarefasColecao frontend = new TarefasColecao("Frontend");
		TarefasColecao backend = new TarefasColecao("Backend");
		
		Tarefa bancoDados = new Tarefa("Criar Banco de Dados");
		Tarefa api = new Tarefa("Criar Sevidor de API");
		Tarefa paginaHtml = new Tarefa("Criar Pagina Html");
		Tarefa testarFormulario = new Tarefa("Testar Fomulario");
		Tarefa dockerFile = new Tarefa("Criar DockerFile");
		
		website.adiciona(frontend);
		website.adiciona(backend);
		
		backend.adiciona(dockerFile);
		backend.adiciona(api);
		backend.adiciona(bancoDados);
		
		frontend.adiciona(paginaHtml);
		frontend.adiciona(testarFormulario);
		
		website.listarTarefa();
		
		// frontend.listarTarefa();
		
		// dockerFile.listarTarefa();
		
	}

}
