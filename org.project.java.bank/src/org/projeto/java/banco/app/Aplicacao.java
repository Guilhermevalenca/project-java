package org.projeto.java.banco.app;

import java.util.Scanner;

import org.projeto.java.banco.cliente.Cliente;
import org.projeto.java.banco.cliente.ContaBancaria;
import org.projeto.java.banco.dao.ClienteDAO;
import org.projeto.java.banco.database.ConectarBanco;

public class Aplicacao {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClienteDAO clienteDAO;
		ContaBancaria conta;
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
				clienteDAO = new ClienteDAO(new ConectarBanco());
				clienteDAO.add(novoCliente);
			}else if(selecionado == 2) {
				System.out.println("digite seu cpf");
				String cpf = scanner.nextLine();
				clienteDAO = new ClienteDAO(new ConectarBanco());
				Cliente cliente = clienteDAO.findById(cpf);
				if(cliente != null) {
					boolean exec = true;
					while(exec) {
					
						System.out.println("Escolha uma das opções abaixo:");
						System.out.println("1. criar conta");
						System.out.println("2. vizualizar contas");
						System.out.println("3. entrar na conta");
						System.out.println("4. informações do cliente");
						System.out.println("5. saldo total");
						System.out.println("6. Sair");
						int novaSelecao = scanner.nextInt();
						
						if(novaSelecao == 1) {
							conta = new ContaBancaria();
							clienteDAO.addConta(conta, cpf);
							System.out.println("conta criada com sucesso");
						}else if(novaSelecao == 2) {
							cliente.setContas(clienteDAO.searchContas(cpf));
							System.out.println(cliente.getContas());
						}else if(novaSelecao == 3) {
						
							System.out.println("digite o numero da conta");
							int numero = scanner.nextInt();
							if(cliente.getContaByNumber(numero) != null) {
								
								ContaBancaria contaSelecionada = cliente.getContaByNumber(numero);
								
								while(true) {
									System.out.println("Escolha uma das opções abaixo:");
									System.out.println("1. saldo");
									System.out.println("2. sacar");
									System.out.println("3. depositar");
									System.out.println("4. transferir");
									System.out.println("5. desativar conta");
									System.out.println("6. ativar conta");
									System.out.println("7. sair");
									int selecaoConta = scanner.nextInt();
									
									if(selecaoConta == 1) {
										System.out.println(contaSelecionada.getSaldo());
									}else if(selecaoConta == 2) {
										System.out.println("digite a quantia que deseja sacar");
										double sacar = scanner.nextDouble();
										contaSelecionada.sacar(sacar);
										clienteDAO.updateConta(contaSelecionada.getSaldo(), contaSelecionada.getStatus(), contaSelecionada.getNumeroConta());
									}else if(selecaoConta == 3) {
										System.out.println("digite a quantia que deseja depositar");
										double depositar = scanner.nextDouble();
										contaSelecionada.depositar(depositar);
										clienteDAO.updateConta(contaSelecionada.getSaldo(), contaSelecionada.getStatus(), contaSelecionada.getNumeroConta());
									}else if(selecaoConta == 4) {
										System.out.println("numero da conta de destino");
										int numeroDestino = scanner.nextInt();
										System.out.println("quantia a ser transferida");
										double quantia = scanner.nextDouble();
										ContaBancaria contaDestino = cliente.getContaByNumber(numeroDestino);
										if(contaSelecionada.transferir(quantia, contaDestino)) {
											clienteDAO.updateConta(contaDestino.getSaldo(), contaDestino.getStatus(), contaDestino.getNumeroConta());
										}
									}else if(selecaoConta == 5) {
										contaSelecionada.desativar();
										clienteDAO.updateConta(contaSelecionada.getSaldo(), contaSelecionada.getStatus(), contaSelecionada.getNumeroConta());
									}else if(selecaoConta == 6) {
										clienteDAO.updateConta(contaSelecionada.getSaldo(), contaSelecionada.getStatus(), contaSelecionada.getNumeroConta());
										contaSelecionada.ativar();
									}else if(selecaoConta == 7) {
										break;
									}
								}
								
							}else {
								System.out.println("conta não existe");
							}
							
						}else if(novaSelecao == 4){
							
							while(true) {
								System.out.println("Suas informações:" + cliente);
								System.out.println("opções:");
								System.out.println("1. atualizar dados");
								System.out.println("2. deletar cliente");
								System.out.println("3. sair");
								int selecaoCliente = scanner.nextInt();
								
								if(selecaoCliente == 1) {
									System.out.println("digite seu novo nome:");
									String nome = scanner.nextLine();
									clienteDAO.update(cpf, nome);
								}else if(selecaoCliente == 2) {
									clienteDAO.delete(cliente);
									exec = false;
									break;
								}else if(selecaoCliente == 3) {
									break;
								}
							}
							
						}else if(novaSelecao == 5) {
							System.out.println("Saldo total: " + cliente.saldoTotal());
						}else if(novaSelecao == 6) {
							break;
						}
						
					}
					
					
				}else {
					System.out.println("cliente nao cadastrado");
				}
			}else if(selecionado == 3) {
				System.out.println("volte sempre");
				break;
			}
		}
	}
}
