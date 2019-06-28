package br.unitins.girobike.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.girobike.application.Util;
import br.unitins.girobike.model.Bike;
import br.unitins.girobike.model.Categoria;
import br.unitins.girobike.model.Marca;
import br.unitins.girobike.model.Modelo;
import br.unitins.girobike.model.TamanhoAro;

public class BikeDAO extends DAO<Bike>  {
	
	@Override
	public boolean create(Bike obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO bike ( "
										+ "  tamanhoaro, "
										+ "  categoria, "
										+ "  modelo, "
										+ "  cpf, "
										+ "  nome, "
										+ "  email, "
										+ "  marca ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			stat.setInt(1, obj.getTamanhoAro().getValue());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setInt(3, obj.getModelo().getValue());
			stat.setString(4, obj.getCpf());
			stat.setString(5, obj.getNome());
			stat.setString(6, obj.getEmail());
			stat.setInt(7, obj.getMarca().getValue());
			
			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean update(Bike obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE bike SET "
												   + "  tamanhoaro = ?, "
												   + "  categoria = ?, "
												   + "  modelo = ?, "
												   + "  cpf = ?, "
												   + "  nome = ?, "
												   + "  email = ?, "
												   + "  marca = ?  " 
												   + "WHERE id = ? ");
			stat.setInt(1, obj.getTamanhoAro().getValue());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setInt(3, obj.getModelo().getValue());
			stat.setString(4, obj.getCpf());
			stat.setString(5, obj.getNome());
			stat.setString(6, obj.getEmail());
			stat.setInt(7, obj.getMarca().getValue());
			stat.setInt(8, obj.getId());
			
			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
		
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("DELETE FROM bike WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclusão realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public Bike findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Bike bike = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Bike WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setTamanhoAro(TamanhoAro.valueOf("tamanhoaro"));
				bike.setModelo(Modelo.valueOf(rs.getInt("modelo")));
				bike.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				bike.setMarca(Marca.valueOf(rs.getInt("marca")));
				bike.setCpf(rs.getString("cpf"));
				bike.setNome(rs.getString("nome"));
				bike.setEmail(rs.getString("email"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bike;
	}

	@Override
	public List<Bike> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Bike> listaBike = new ArrayList<Bike>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Bike");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Bike c = new Bike();
				c.setId(rs.getInt("id"));
				c.setTamanhoAro(TamanhoAro.valueOf("tamanhoaro"));
				c.setModelo(Modelo.valueOf(rs.getInt("modelo")));
				c.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				c.setMarca(Marca.valueOf(rs.getInt("marca")));
				c.setCpf(rs.getString("cpf"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));

				listaBike.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaBike = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaBike;
	}
	

}
