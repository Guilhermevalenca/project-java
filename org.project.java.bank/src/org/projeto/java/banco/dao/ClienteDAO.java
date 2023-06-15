package org.projeto.java.banco.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.projeto.java.banco.cliente.Cliente;
import org.projeto.java.banco.database.ConectarBanco;

public class ClienteDAO extends ContasDAO {

	private ConectarBanco conn;
	
	public ClienteDAO(ConectarBanco connect) {
		super(connect);
		this.conn = connect;
	}


	
	public void add(Cliente c) {
		String sql;
		boolean addCliente = true;
		ResultSet rs;
		sql = "SELECT cli_cpf FROM CLIENTE WHERE cli_cpf = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setString(1, c.getCpf());
			rs = pstm.executeQuery();
			while(rs.next()) {
				System.out.println("cpf ja cadastrado");
				addCliente = false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(addCliente) {
			sql = "INSERT INTO CLIENTE VALUES (?,?);";
			try {
				PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
				pstm.setString(1,c.getCpf());
				pstm.setString(2, c.getNome());
				pstm.executeUpdate();
				System.out.println("cliente cadastrado com sucesso");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void delete(Cliente c) {
		String sql = "DELETE FROM CLIENTE WHERE cli_cpf = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setString(1, c.getCpf());
			pstm.executeUpdate();
			System.out.println("cliente deletado do sistema");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Cliente findById(String cpf) {
		Cliente c = null;
		ResultSet rs;
		String sql = "SELECT * FROM CLIENTE WHERE cli_cpf = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			while(rs.next()) {
				if(searchContas(cpf) != null) {
					c = new Cliente(rs.getString("cli_nome"),rs.getString("cli_cpf"),searchContas(cpf));
				}else {
					c = new Cliente(rs.getString("cli_nome"),rs.getString("cli_cpf"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	public void update(String cpf, String nome) {
		String sql = "UPDATE CLIENTE SET cli_nome = ?,cli_cpf = ? WHERE cli_cpf = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setString(1, nome);
			pstm.setString(2, cpf);
			pstm.executeUpdate();
			System.out.println("dados do cliente atualizados com sucesso!");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
