package com.dojo.main;

import com.dojo.entity.Jogo;
import com.dojo.entity.Partida;
import com.dojo.helper.Arquivo;

public class Start {

	public static void main(String[] args) throws Exception {

		Arquivo arquivo = new Arquivo();
		Jogo jogo = arquivo.carregarArquivo("src-logs/Partida - 16.09.txt");
		
		for (Partida partida : jogo.getJogo().values()) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Partida: " + partida.getNumeroPartida());
			System.out.println("Assassinos: " + partida.getAssasinatos());
			System.out.println("Mortes: " + partida.getMortes());
			System.out.println("Armas: " + partida.getArmas());
		}
		
		
	}

}
