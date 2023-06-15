package org.projeto.java.banco.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.projeto.java.banco.cliente.ContaBancaria;
import org.projeto.java.banco.database.ConectarBanco;

public class ContasDAO {
	private ConectarBanco conn;

	public ContasDAO(ConectarBanco connect) {
		this.conn = connect;
	}
	
	public List<ContaBancaria> searchContas(String cpf){
		String sql = "SELECT * FROM CONTA WHERE con_cli_cpf = ?;";
		List<ContaBancaria> contas = null;
		ResultSet rs;
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setString(1, cpf);
			rs = pstm.executeQuery();
			contas = new ArrayList<>();
			ContaBancaria c = new ContaBancaria();
			while(rs.next()) {
				c.setData(rs.getDate("con_data"));
				c.setNumeroConta(rs.getInt("con_numero"));
				c.setSaldo(rs.getDouble("con_saldo"));
				c.setStatus(rs.getBoolean("con_status"));
				contas.add(c);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro da conta dao");
		}
		return contas;
	}
	public void addConta(ContaBancaria conta, String cpf) {
		String sql = "INSERT INTO CONTA VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setInt(1, conta.getNumeroConta());
			pstm.setDouble(2,conta.getSaldo());
			pstm.setDate(3, conta.getDataAbertura());
			pstm.setBoolean(4, conta.getStatus());
			pstm.setString(5, cpf);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateConta(double saldo, boolean status,int numero) {
		String sql = "UPDATE CONTA SET con_saldo = ?, con_status = ? WHERE con_numero = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setDouble(1, saldo);
			pstm.setBoolean(2, status);
			pstm.setInt(3, numero);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeConta(int numero) {
		String sql = "REMOVE FROM CONTA WHERE con_numero = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setInt(1, numero);
			pstm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ContaBancaria getContaByNumber(int number) {
		String sql = "SELECT * FROM CONTA WHERE con_numero = ?";
		ResultSet rs;
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setInt(1, number);
			rs = pstm.executeQuery();
			ContaBancaria c = new ContaBancaria();
			while(rs.next()) {
				c.setData(rs.getDate("con_data"));
				c.setNumeroConta(rs.getInt("con_numero"));
				c.setSaldo(rs.getDouble("con_saldo"));
				c.setStatus(rs.getBoolean("con_status"));
				return c;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
