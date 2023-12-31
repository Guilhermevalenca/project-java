package org.projeto.java.banco.cliente;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Cliente {
	
	private String nome;
	private String cpf;
	private List<ContaBancaria> contas;
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		contas = new ArrayList<>();
	}
	
	public Cliente(String nome,String cpf,List<ContaBancaria> contas) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public List<ContaBancaria> getContas() {
		return this.contas;
	}
	
	public ContaBancaria getContaByNumber(int numero) {
		for(ContaBancaria conta : this.contas) {
			if(conta.getNumeroConta() == numero) {
				return conta;
			}
		}return null;
	}
	
	public void setContas(List<ContaBancaria> contas) {
		this.contas = contas;
	}
	
	public void criarConta(ContaBancaria c) {
		if(this.contas.contains(c)) {
			System.out.println("conta ja adicionada");
		}else {
			this.contas.add(c);
			System.out.println("conta adicionada");
		}
	}
	
	public void removerConta(ContaBancaria c) {
		if(this.contas.contains(c)) {
			this.contas.remove(c);
			System.out.println("conta removida");
		}else {
			System.out.println("conta ja foi removida");
		}
	}
	
	public ContaBancaria localizarConta(int n) {
		for(ContaBancaria c : contas) {
			if(c.getNumeroConta() == n) {
				return c;
			}
		}
		System.out.println("Conta nao encontrada");
		return null;
	}
	
	public double saldoTotal() {
		double soma = 0;
		for(ContaBancaria c : contas) {
			soma += c.getSaldo();
		}
		return soma;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", contas=" + contas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contas, cpf, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(contas, other.contas) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(nome, other.nome);
	}
	
	
}
