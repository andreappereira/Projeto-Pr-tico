// Aluno: André Aparecido Pereira da Conceição
// RA: 202111601

public class Veiculo {

	String cor, placa, descricao;
	int quantidadePortas;
	Proprietario pess;

	public Veiculo(String cor, String placa, String descricao, int quantidadePortas, Proprietario pess) {
		this.cor = cor;
		this.placa = placa;
		this.descricao = descricao;
		this.quantidadePortas = quantidadePortas;

	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidadePortas() {
		return quantidadePortas;
	}

	public void setQuantidadePortas(int quantidadePortas) {
		this.quantidadePortas = quantidadePortas;
	}

	public Proprietario getPess() {
		return pess;
	}

	public void setPess(Proprietario pess) {
		this.pess = pess;
	}

}
