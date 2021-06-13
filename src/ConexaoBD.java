// Aluno: André Aparecido Pereira da Conceição
// RA: 202111601

import java.sql.*;

public class ConexaoBD {

	private static Connection conexao = null;

	private String fonte = "projeto_pratico";

	private ConexaoBD() {

		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/" + fonte, "root", "zuck5758");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro de class nÃ£o encontrada!!!");
		}
	}

	public static Connection getInstance() {
		if (conexao == null) {
			new ConexaoBD();
		}
		return conexao;
	}
}
