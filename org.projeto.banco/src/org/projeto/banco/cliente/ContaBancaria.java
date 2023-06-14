package org.projeto.banco.cliente;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class ContaBancaria {
	
	private int numeroConta;
	private double saldo;
	private LocalDateTime dataAbertura;
	private boolean status;
	
	public ContaBancaria() {
		this.numeroConta = new Random().nextInt(999999999 - 100000000) + 100000000;
		this.saldo = 0.0;
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
	}
	public int getNumeroConta() {
		return this.numeroConta;
	}
	public double getSaldo() {
		return this.saldo;
	}
	public LocalDateTime getDataAbertura() {
		return this.dataAbertura;
	}
	public boolean getStatus() {
		return this.status;
	}
	public void depositar(double quantia) {
		if(!this.status) {
			System.out.println("conta desativada");
		}else if(quantia < 0) {
			System.out.println("quantia invalida");
		}else {
			this.saldo += quantia;
		}
	}
	public void sacar(double quantia) {
		if(!this.status) {
			System.out.println("conta desativada");
		}else if(quantia < 0 || quantia >  this.saldo) {
			System.out.println("quantia invalida");
		}else {
			this.saldo -= quantia;
		}
	}
	public void transferir(double quantia, ContaBancaria destino) {
		if(!this.status) {
			System.out.println("conta desativada");
		}else if(!destino.status) {
			System.out.println("conta de destino esta desativada");
		}else if(quantia < 0 || quantia > this.saldo) {
			System.out.println("quantia invalida");
		}else {
			this.saldo -= quantia;
			destino.saldo += quantia;
		}
	}
	public void desativar() {
		if(!this.status) {
			System.out.println("conta ja desativada");
		}else {
			this.status = false;
			System.out.println("conta desativada");
		}
	}
	public void ativar() {
		if(this.status) {
			System.out.println("conta ja ativada");
		}else {
			this.status = true;
			System.out.println("conta ativada");
		}
	}
	
	@Override
	public String toString() {
		return "ContaBancaria [numeroConta=" + numeroConta + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura
				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataAbertura, numeroConta, saldo, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		return Objects.equals(dataAbertura, other.dataAbertura) && numeroConta == other.numeroConta
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo) && status == other.status;
	}
	
}
