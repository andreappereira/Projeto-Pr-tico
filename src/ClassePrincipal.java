// Aluno: André Aparecido Pereira da Conceição
// RA: 202111601

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassePrincipal {

	static Connection conexao = null;

	public static void main(String[] args) throws Exception {

		String menu = "\n1. Cadastro de proprietário" 
				+ "\n2. Remoção de proprietário"
				+ "\n3. Busca de proprietário pelo e-mail"
				+ "\n4. Relatório de todos os proprietários de veículos, considerando o nome do proprietário em ordem alfabética, a placa e a descrição do ou dos veículos os quais ele é proprietário"
				+ "\n5. Altera proprietário"
				+ "\n6. Cadastro de veículo"
				+ "\n7. Remoção de veículo"
				+ "\n8. Busca veículo pela placa"
				+ "\n9. Relatório de todos os veículos de uma dada cor, com o nome do proprietário e a quantidade de portas"
				+ "\n10. Busca veículos pela cor"
				+ "\n11. Busca veículos pela quantidade de portas"
				+ "\n12. Altera veículo"
				+ "\n13. Sair";

		int opcao = 13;

		Scanner lerOpcao = new Scanner(System.in);

		do {
			System.out.println(menu);

			System.out.print("Digite uma opção: ");
			// opcao = lerOpcao.nextInt();
			opcao = Integer.parseInt(lerOpcao.next()); // conversor para inteiro primitivo

			switch (opcao) {
			case 1: { // Cadastro de proprietário
				Proprietario plida = lerDadosProprietario();
				inserirProprietario(plida);
				System.out.println("Proprietario cadastrado!");

				break;
			}

			case 2: { // Remoção de proprietário pelo cpf
				Scanner ler = new Scanner(System.in);
				long cpfDoProprietarioSeraRemovida;
				System.out.print("Digite o cpf do proprietario que sera removido: ");
				cpfDoProprietarioSeraRemovida = ler.nextLong();
				// ler.close();

				removerProprietarioPeloCpf(cpfDoProprietarioSeraRemovida);
				break;
			}

			case 3: {// Busca de proprietário pelo e-mail
				Scanner ler = new Scanner(System.in);
				String buscarEmail;

				System.out.print("Digite o email do proprietario: ");
				buscarEmail = ler.next();

				Proprietario proprietarioEncontrado = buscarProprietarioPeloEmail(buscarEmail);

				if (proprietarioEncontrado != null) {
					mostrarProprietario(proprietarioEncontrado);
				} else {
					System.out.println();
					System.out.print("Não ha proprietário cadastrado com esse email");
					System.out.println();
				}

				break;
			}
			case 6: { // cadastro de veiculo
				Veiculo clida = lerDadosVeiculo();
				inserirVeiculo(clida);
				System.out.println("veiculo cadastrado!");

				break;
			}

			case 7: { // remocao de veiculo
				Scanner lerVeiculo = new Scanner(System.in);
				String placaDoVeiculoQueSeraRemovido;
				System.out.print("Digite a placa do veiculo que sera removido: ");
				placaDoVeiculoQueSeraRemovido = lerVeiculo.next();
				// lerveiculo.close();

				removerVeiculoPelaPlaca(placaDoVeiculoQueSeraRemovido);
				break;

			}

			case 8: { // Busca veículo pela placa
				Scanner lerPlaca = new Scanner(System.in);
				String buscarVeiculo;

				System.out.print("Digite a placa do veiculo: ");
				buscarVeiculo = lerPlaca.next();

				Veiculo veiculoEncontrado = buscarVeiculoPelaPlaca(buscarVeiculo);

				if (veiculoEncontrado != null) {
					mostrarVeiculo(veiculoEncontrado);

				} else {
					System.out.println();
					System.out.print("Não ha veiculo cadastrado com essa placa");
					System.out.println();
				}

				break;

			}

			case 10: { // Busca veículos pela cor
				Scanner lerCor = new Scanner(System.in);
				String corBuscada;

				System.out.print("Digite a cor a ser buscada: ");
				corBuscada = lerCor.next();

				ArrayList<Veiculo> vetCor = listaVeiculosCor(corBuscada);

				if (vetCor.size() > 0) {
					System.out.println("Veiculos encontrados");

					for (int i = 0; i < vetCor.size(); i++) {
						Veiculo c = vetCor.get(i);
						mostrarVeiculo(c);

					}

				}

				else {
					System.out.println();
					System.out.print("Não possui veiculos cadastrados com essa cor");
					System.out.println();
				}

				break;
			}

			case 11: { // Busca veículos pela quantidade de portas
				Scanner lerPortas = new Scanner(System.in);
				String portasBuscada;

				System.out.print("Digite a quantidade de portas a ser buscada: ");
				portasBuscada = lerPortas.next();

				ArrayList<Veiculo> vetPortas = listaVeiculosPortas(portasBuscada);

				if (vetPortas.size() > 0) {
					System.out.println("Veiculos encontrados");

					for (int i = 0; i < vetPortas.size(); i++) {
						Veiculo c = vetPortas.get(i);
						mostrarVeiculo(c);

					}

				}

				else {
					System.out.println();
					System.out.print("Não possui veiculos cadastrados com essa essa quantidade de portas");
					System.out.println();
				}

				break;
			}

			case 13: {// sair
				System.out.println("\nObrigado por utilizar o sistema");
				break;
			}
			default: {
				System.out.println("\nOpção invalida!!!!");
				break;
			}

			}// fim do switch

		} while (opcao != 13);

		lerOpcao.close();

	}

	// *****************************************
	// METODOS PARA ENTRADA E SAÍDA PELO CONSOLE
	// *****************************************

	// item 01 do menu
	public static Proprietario lerDadosProprietario() {

		Scanner ler = new Scanner(System.in);

		long cpf, numeroCnh;
		String nome, email, sexo;
		double peso;

		System.out.println("Digite os dados do proprietario a ser cadastrado");

		System.out.print("\nCPF: ");
		cpf = ler.nextLong();

		System.out.print("\nNome: ");
		nome = ler.next();

		System.out.print("\nEmail: ");
		email = ler.next();

		System.out.print("\nSexo: ");
		sexo = ler.next();

		System.out.print("\nPeso: ");
		peso = ler.nextDouble();

		System.out.print("\nNúmero da CNH: ");
		numeroCnh = ler.nextLong();

		Proprietario p = new Proprietario(cpf, nome, email, sexo, peso, numeroCnh);

		// ler.close();

		return p;
	}

	// item 3 do menu
	public static void mostrarProprietario(Proprietario proprietarioEncontrado) {
		System.out.println();
		System.out.print("\nCPF...: " + proprietarioEncontrado.getCpf());
		System.out.print("\nNome..: " + proprietarioEncontrado.getNome());
		System.out.print("\nEmail.: " + proprietarioEncontrado.getEmail());
		System.out.print("\nSexo..: " + proprietarioEncontrado.getSexo());
		System.out.print("\nPeso..: " + proprietarioEncontrado.getPeso());
		System.out.print("\nNumero CNH..:" + proprietarioEncontrado.getNumeroCnh());
		System.out.println();

	}

	// item 6 do menu
	public static Veiculo lerDadosVeiculo() {

		Scanner ler = new Scanner(System.in);

		String placa, cor, descricao;
		int quantidadePortas;

		System.out.println("Digite os dados do veiculo a ser cadastrado");

		System.out.print("\nCor: ");
		cor = ler.next();

		System.out.print("\nPlaca: ");
		placa = ler.next();

		System.out.print("\nDescrição do veiculo: ");
		descricao = ler.next();

		System.out.print("\nQuantidade de portas: ");
		quantidadePortas = ler.nextInt();

		Veiculo c = new Veiculo(cor, placa, descricao, quantidadePortas, null);

		// ler.close();

		return c;
	}

	// item 8 do menu
	public static void mostrarVeiculo(Veiculo veiculoEncontrado) {
		System.out.println();
		System.out.print("\nCor..: " + veiculoEncontrado.getCor());
		System.out.print("\nPlaca..: " + veiculoEncontrado.getPlaca());
		System.out.print("\nDescrição..: " + veiculoEncontrado.getDescricao());
		System.out.print("\nQuantidade de portas..: " + veiculoEncontrado.getQuantidadePortas());
		System.out.println();

	}

	// item 10 do menu
	public static void listaVeiculosCor(Veiculo veiculoEncontrado) {
		System.out.println();
		System.out.print("\nCor..: " + veiculoEncontrado.getCor());
		System.out.print("\nPlaca..: " + veiculoEncontrado.getPlaca());
		System.out.print("\nDescrição..: " + veiculoEncontrado.getDescricao());
		System.out.print("\nQuantidade de portas..: " + veiculoEncontrado.getQuantidadePortas());
		System.out.println();

	}

	// item 11 do menu
	public static void listaVeiculosPortas(Veiculo veiculoEncontrado) {
		System.out.println();
		System.out.print("\nCor..: " + veiculoEncontrado.getCor());
		System.out.print("\nPlaca..: " + veiculoEncontrado.getPlaca());
		System.out.print("\nDescrição..: " + veiculoEncontrado.getDescricao());
		System.out.print("\nQuantidade de portas..: " + veiculoEncontrado.getQuantidadePortas());
		System.out.println();

	}

	// *******************************************************************
	// METODOS PARA MANIPULAÇÃO(PERSISTÊNCIA DOS DADOS) NO BANCO DE DADOS
	// *******************************************************************

	// item 01 do menu
	public static void inserirProprietario(Proprietario plida) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into proprietario (cpf, nome, email, peso, sexo, numeroCnh) values (?,?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setLong(1, plida.getCpf());
		stmt.setString(2, plida.getNome());
		stmt.setString(3, plida.getEmail());
		stmt.setDouble(4, plida.getPeso());
		stmt.setString(5, plida.getSexo());
		stmt.setLong(6, plida.getNumeroCnh());

		stmt.execute();

		stmt.close();

		// não feche a conexão com o banco para que possa continuar cadastrando
		// conexao.close();

	}

	// item 2 do menu
	public static void removerProprietarioPeloCpf(long cpfDoProprietarioSeraRemovida) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "delete from proprietario where cpf LIKE ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setLong(1, cpfDoProprietarioSeraRemovida);

		stmt.execute();

		stmt.close();

	}

	// item 03 do menu
	public static Proprietario buscarProprietarioPeloEmail(String buscarEmail) throws Exception {

		conexao = ConexaoBD.getInstance();

		String sql = "select * from proprietario where email like ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, buscarEmail);
		ResultSet resultado = stmt.executeQuery(); // importar a classe ResultSet para recuperar os dados que o select
													// selecionou

		Proprietario p = null;
		if (resultado.next()) {
			p = new Proprietario(resultado.getLong("cpf"), resultado.getString("nome"), resultado.getString("email"),
					resultado.getString("sexo"), resultado.getDouble("peso"), resultado.getLong("numeroCnh"));

		}
		resultado.close();
		stmt.close();

		return p;
	}

	// item 6 do menu
	public static void inserirVeiculo(Veiculo clida) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into veiculo (cor, placa, descricao, quantidadePortas) values (?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, clida.getCor());
		stmt.setString(2, clida.getPlaca());
		stmt.setString(3, clida.getDescricao());
		stmt.setInt(4, clida.getQuantidadePortas());

		stmt.execute();

		stmt.close();

	}

	// item 07 do menu
	public static void removerVeiculoPelaPlaca(String placaDoVeiculoQueSeraRemovido) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "delete from veiculo where placa LIKE ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, placaDoVeiculoQueSeraRemovido);

		stmt.execute();

		stmt.close();

	}
	
	//item 8 do menu
	public static Veiculo buscarVeiculoPelaPlaca(String buscarVeiculo) throws Exception {

		conexao = ConexaoBD.getInstance();

		String sql = "select * from veiculo where placa like ? ";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, buscarVeiculo);
		ResultSet resultado = stmt.executeQuery();

		Veiculo c = null;
		if (resultado.next()) {
			c = new Veiculo(resultado.getString("cor"), resultado.getString("placa"), resultado.getString("descricao"),
					resultado.getInt("quantidadePortas"), null);

		}
		resultado.close();
		stmt.close();

		return c;
	}


	// item 10 do menu
	public static ArrayList<Veiculo> listaVeiculosCor(String corBuscada) throws Exception {
		ArrayList<Veiculo> vetCor = new ArrayList<Veiculo>();

		conexao = ConexaoBD.getInstance();

		// Por exemplo, SELECT * FROM aula_bd.conta where agencia='1537-6';

		String sql = ("select * from projeto_pratico.veiculo where cor like ?");
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, corBuscada);
		ResultSet resultado = stmt.executeQuery();

		Veiculo c = null;

		while (resultado.next()) {
			c = new Veiculo(resultado.getString("cor"), resultado.getString("placa"), resultado.getString("descricao"),
					resultado.getInt("quantidadePortas"), null);

			vetCor.add(c); // adiciona a conta da agencia buscada
		}

		resultado.close();
		stmt.close();

		return vetCor;
	}

	// item 11 do menu
	public static ArrayList<Veiculo> listaVeiculosPortas(String corBuscada) throws Exception {
		ArrayList<Veiculo> vetPortas = new ArrayList<Veiculo>();

		conexao = ConexaoBD.getInstance();

		// Por exemplo, SELECT * FROM aula_bd.conta where agencia='1537-6';

		String sql = ("select * from projeto_pratico.veiculo where quantidadePortas like ?");
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, corBuscada);
		ResultSet resultado = stmt.executeQuery();

		Veiculo c = null;

		while (resultado.next()) {
			c = new Veiculo(resultado.getString("cor"), resultado.getString("placa"), resultado.getString("descricao"),
					resultado.getInt("quantidadePortas"), null);

			vetPortas.add(c); // adiciona a conta da agencia buscada
		}

		resultado.close();
		stmt.close();

		return vetPortas;
	}


}
