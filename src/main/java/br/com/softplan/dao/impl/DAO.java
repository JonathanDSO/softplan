package br.com.softplan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	public void open() throws Exception {
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/softplan", "postgres", "admin");
	}

	public void close() throws Exception {
		con.close();
	}

	public static void main(String[] args) {
		DAO d = new DAO();
		try {
			d.open();
			d.close();
			System.out.println("Banco Aberto ...");
		} catch (Exception ex) {
			System.out.println("Error :" + ex.getMessage());
		}
	}

}
