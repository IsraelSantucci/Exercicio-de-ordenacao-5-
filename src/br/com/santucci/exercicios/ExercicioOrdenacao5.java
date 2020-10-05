package br.com.santucci.exercicios;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExercicioOrdenacao5 {

	private static Scanner scan = new Scanner(System.in);
	private static List<Jogador> jogadores = new ArrayList<>();


	public static void main(String[] args) throws IOException {

		int quant = 0;
		quant = 1;
		quant = scan.nextInt();
		
		while (quant > 0) {
			completarLista(quant);
			jogar();
			quant = scan.nextInt();

		}
		

	}

	//metodo para realizar a logica do jogo
	public static void jogar() {
		int posJogadorVez = 0;
		int numJogador = jogadores.get(0).getNumero(); // pegar numero do primeiro jogador
		int pos = 0;
		
		//ver proximo jogar de inicio para caso par ou impar
		if (numJogador % 2 == 0) {
			posJogadorVez = jogadores.size() - 1;
		} else {
			posJogadorVez = 1;
		}

		//loop para ler eliminar jogadores ate sobrar apenas 1
		while (jogadores.size() > 1) {

			pos = retornarPosicao(numJogador, posJogadorVez);

			numJogador = jogadores.get(pos).getNumero();
			posJogadorVez = proximoJogador(numJogador, pos);

			jogadores.remove(pos);
			if (posJogadorVez == jogadores.size()) {

				posJogadorVez--;
			}

		}
		System.out.println("Vencedor(a): " + jogadores.get(0).getNome());
		jogadores.clear();

	}

	// retornar posicao do jogador que sera escluido
	private static int retornarPosicao(int num, int posicao) {
		if (num % 2 == 0) {
			posicao = rodarHorario(num, posicao);

		} else {
			posicao = rodarAntHorario(num, posicao);
		}
		return posicao;
	}

	// <--  começo dos dois metodos para rodar no sentido horario e anti-horario -->
	private static int rodarHorario(int num, int posicao) {

		for (int i = 0; i < num - 1; i++) {
			posicao = posicao - 1;
			if (posicao < 0) {
				posicao = jogadores.size() - 1;
			}
		}

		return posicao;
	}

	private static int rodarAntHorario(int num, int posicao) {

		for (int i = 0; i < num - 1; i++) {

			posicao = posicao + 1;

			if (posicao >= jogadores.size()) {
				posicao = 0;
			}
		}

		return posicao;
	}
	// <---  fim metodos -->
	
	
	//<-- metodos para ver qual o proximo jogador -->
	
	// proximo jogador
	private static int proximoJogador(int num, int posicao) {

		if (num % 2 == 0) {
			posicao = rodarHor(1, posicao);
		} else {
			posicao = rodarAnt(0, posicao);
		}

		return posicao;
	}

	// sentido anti-horario

	private static int rodarAnt(int num, int posicao) {
		for (int i = 0; i < num; i++) {
			posicao = posicao + 1;
			if (posicao >= jogadores.size()) {
				posicao = 0;
			}
		}
		return posicao;
	}
	// sentido horario
	private static int rodarHor(int num, int posicao) {

		for (int i = 0; i < num; i++) {

			posicao = posicao - 1;

			if (posicao < 0) {
				posicao = jogadores.size() - 1;
			}
		}
		return posicao;
	}
	//<--  fim metodos -->
	
	
	// preencher a lista
	public static void completarLista(int num) {
		for (int i = 0; i < num; i++) {
			String nome = scan.next();
			int numero = Integer.parseInt(scan.next());
			Jogador jogador = new Jogador(nome, numero);
			jogadores.add(jogador);

		}
	}

	// objeto para guardar nome e o numero do jogador
	static class Jogador {
		private String nome;
		private int numero;

		public Jogador(String nome, int numero) {
			this.nome = nome;
			this.numero = numero;
		}

		public String getNome() {
			return nome;
		}

		public int getNumero() {
			return numero;
		}

		public String toString() {
			return getNome() + " " + getNumero();
		}
	}

}
