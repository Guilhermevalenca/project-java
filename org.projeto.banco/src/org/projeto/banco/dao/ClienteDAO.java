package org.projeto.banco.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.projeto.banco.cliente.Cliente;
import org.projeto.banco.database.ConectarBanco;

public class ClienteDAO {
	
	private ConectarBanco conn;

	public ClienteDAO(ConectarBanco conn) {
		this.conn = conn;
	}
	public void save(Cliente c) {
		
	}
}
