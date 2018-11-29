package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexaoHSQLDB.CNXHSQLDB;
import entidade.Carro;

public class CarroDAO {
	private final String SQL_INSERE_CARRO = "INSERT INTO carro(nome,descricao,cor,km) VALUES (?,?,?,?);";
	private final String SQL_ALTERA_CARRO = "UPDATE carro SET nome=?, descricao=?, cor=?, km=? WHERE ID=?;";
	private final String SQL_EXCLUI_CARRO = "DELETE FROM carro WHERE ID=?";
	private final String SQL_SELECIONA_CARRO = "SELECT * FROM carro";

	public void inserirCarro(Carro umCarro) {
		try (	Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CARRO);) {
			
			pst.setString(1, umCarro.getNome());
			pst.setString(2, umCarro.getDescricao());
			pst.setString(3, umCarro.getCor());
			pst.setInt(4, umCarro.getKm());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	public ArrayList<Carro> listarTodosCarro() {
		ArrayList<Carro> listaDeCarros = new ArrayList<Carro>();

		Carro umCarro;
		try (	Connection conn = new CNXHSQLDB().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CARRO);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				umCarro = new Carro();
				umCarro.setId(rs.getInt("ID"));
				umCarro.setNome(rs.getString("nome"));
				umCarro.setDescricao(rs.getString("descricao"));
				umCarro.setCor(rs.getString("cor"));
				umCarro.setKm(rs.getInt("km"));
				
				listaDeCarros.add(umCarro);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return listaDeCarros;
	}

	public void updateCarro(Carro umCarro) {
		try (	Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_CARRO);) {
			pst.setString(1, umCarro.getNome());
			pst.setString(2, umCarro.getDescricao());
			pst.setString(3, umCarro.getCor());
			pst.setInt(4, umCarro.getKm());
			pst.setInt(5, umCarro.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

	public void excluiCarro(Carro umCarro) {
		try (Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_CARRO);) {
			pst.setInt(1, umCarro.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

}
