package br.unitins.girobike.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoTest {
	public static void main(String[] args) {
		
		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection conn = null;
		try {
			// estabelecendo um conexao com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/giroBike",
												"topicos1", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (conn == null) {
			System.out.println("Conexao Falhou!");
			return;
		}else
			System.out.println("Conexao realizada com sucesso!");
		
		try {
			Statement stat =  conn.createStatement();
			stat.execute("INSERT INTO bike (tamAro, categoria, modelo, marca) "
					+ "				 VALUES ('2', 2, '2', 2)");
			conn.close();
			
			System.out.println("Insersao realizada com sucesso.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
