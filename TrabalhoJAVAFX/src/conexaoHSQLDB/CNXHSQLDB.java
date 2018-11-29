package conexaoHSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CNXHSQLDB {

	private String usuario = "SA";
	private String senha = "";
	private String PathBase = "C:\\Users\\2014100747\\Desktop\\TrabalhoJAVAFX\\base\\cadastro";
	private String URL = "jdbc:hsqldb:file:" + PathBase + ";";

	public Connection conectar() {
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
//			throw new RuntimeException();
			e.printStackTrace();
		}
		return null;
	}

}