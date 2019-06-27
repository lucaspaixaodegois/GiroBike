package br.unitins.girobike.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.girobike.application.Util;
import br.unitins.girobike.model.Produto;

public class ProdutoDAO extends DAO<Produto>  {
	
	@Override
	public boolean create(Produto obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO produto ( "
										+ "  descricao, "
										+ "  valor ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ? ) ");
			stat.setString(1, obj.getDescricao());
			stat.setDouble(2, obj.getValor());
			
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
	public boolean update(Produto obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE produto SET "
												   + "  descricao = ?, "
												   + "  valor = ?  " 
												   + "WHERE id = ? ");
			stat.setString(1, obj.getDescricao());
			stat.setDouble(2, obj.getValor());
			stat.setInt(3, obj.getId());
			
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
			stat =	getConnection().prepareStatement("DELETE FROM Produto WHERE id = ? ");
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
	public Produto findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Produto produto = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Produto WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
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
		return produto;
	}

	@Override
	public List<Produto> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Produto");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));

				listaProduto.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaProduto = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaProduto;
	}
	
	public List<Produto> findByDescricao(String descricao) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM produto WHERE descricao ILIKE ?");
			stat.setString(1, (descricao == null? "%" : "%"+descricao+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				listaProduto.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaProduto = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaProduto;

	}

	

}
