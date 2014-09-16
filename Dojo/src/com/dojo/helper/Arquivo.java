package com.dojo.helper;

import java.io.BufferedReader;
import java.io.FileReader;

import com.dojo.entity.Jogo;
import com.dojo.entity.Partida;

public class Arquivo {

	public Jogo carregarArquivo(String path) throws Exception {

		FileReader reader = new FileReader(path);
		BufferedReader leitor = new BufferedReader(reader);
		String linhaArquivo = leitor.readLine();
		
		Partida partida = new Partida();
		Jogo jogo = new Jogo();
		
		 while (linhaArquivo != null) {
			
			System.out.println("Linha: " + linhaArquivo);


				String[] palavras = linhaArquivo.split(" ");
				
				for (int i = 0; i < palavras.length; i++) {

					if (palavras[i].equalsIgnoreCase("match") && partida.getNumeroPartida() == null) {
						partida.setNumeroPartida(palavras[++i]);
						break;
					} else if (palavras[i].equalsIgnoreCase("match") && partida.getNumeroPartida() != null) {
						partida.getAssasinatos().remove("<WORLD>");
						jogo.getJogo().put(partida.getNumeroPartida(), partida);
						partida = new Partida();
						break;
					}
					
					if (palavras[i].equalsIgnoreCase("killed")) {
						
						String palavraAnterior = palavras[i - 1];
						String palavraPosterior = palavras[i + 1];
						
						if (partida.getAssasinatos().containsKey(palavraAnterior)) {
							partida.getAssasinatos().put(palavraAnterior, partida.getAssasinatos().get(palavraAnterior) + 1);
						} else {
							partida.getAssasinatos().put(palavraAnterior, 1);
						}
						
						if (partida.getMortes().containsKey(palavraPosterior)) {
							partida.getMortes().put(palavraPosterior, partida.getMortes().get(palavraPosterior) + 1);
						} else {
							partida.getMortes().put(palavraPosterior, 1);
						}
					}
					
					if (palavras[i].equalsIgnoreCase("using") || palavras[i].equalsIgnoreCase("by")) {
						
						String palavraPosterior = palavras[i + 1];
						
						if (partida.getArmas().containsKey(palavraPosterior)) {
							partida.getArmas().put(palavraPosterior, partida.getArmas().get(palavraPosterior) + 1);
						} else {
							partida.getArmas().put(palavraPosterior, 1);
						}
						break;
					}
					
				}
				
				linhaArquivo = leitor.readLine();
		}

		leitor.close();
		reader.close();
		
		return jogo;
	}
}
