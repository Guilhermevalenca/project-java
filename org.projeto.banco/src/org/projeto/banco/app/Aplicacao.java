package org.projeto.banco.app;

import java.util.Scanner;

import org.projeto.banco.cliente.Cliente;
import org.projeto.banco.cliente.ContaBancaria;

public class Aplicacao {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1. Realizar cadastro");
			System.out.println("2. cliente ja cadastrado");
			System.out.println("3. Sair");
			
			int selecionado = scanner.nextInt();
			scanner.nextLine();
			
			if(selecionado == 1) {
				System.out.println("Digite seu nome");
				String nome = scanner.nextLine();
				System.out.println("Digite seu cpf");
				String cpf = scanner.nextLine();
				Cliente novoCliente = new Cliente(nome,cpf);
			}
		}
	}
}
