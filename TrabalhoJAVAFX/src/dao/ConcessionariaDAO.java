package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexaoHSQLDB.CNXHSQLDB;
import entidade.Concessionaria;

public class ConcessionariaDAO {
	private final String SQL_INSERE_CONCESSIONARIA = "INSERT INTO concessionaria(nome,localidade) VALUES (?,?);";
	private final String SQL_EXCLUI_CONCESSIONARIA = "DELETE FROM concessionaria WHERE ID=?";
	private final String SQL_SELECIONA_CONCESSIONARIA = "SELECT * FROM concessionaria";

	public void inserirConcessionaria(Concessionaria umaConcessionaria) {
		try (	Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CONCESSIONARIA);) {
			
			pst.setString(1, umaConcessionaria.getNome());
			pst.setString(2, umaConcessionaria.getLocalidade());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	public ArrayList<Concessionaria> listarTodasConcessionaria() {
		ArrayList<Concessionaria> listaDeCarros = new ArrayList<Concessionaria>();

		Concessionaria umaConcessionaria;
		try (	Connection conn = new CNXHSQLDB().conectar();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CONCESSIONARIA);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				umaConcessionaria = new Concessionaria();
				umaConcessionaria.setId(rs.getInt("ID"));
				umaConcessionaria.setNome(rs.getString("nome"));
				umaConcessionaria.setLocalidade(rs.getString("localidade"));

				
				listaDeCarros.add(umaConcessionaria);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return listaDeCarros;
	}


	public void excluiConcessionaria(Concessionaria umaConcessionaria) {
		try (Connection conn = new CNXHSQLDB().conectar(); 
				PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_CONCESSIONARIA);) {
			pst.setInt(1, umaConcessionaria.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}

}
