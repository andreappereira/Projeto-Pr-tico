// Aluno: André Aparecido Pereira da Conceição
// RA: 202111601

public class Proprietario {

	long cpf, numeroCnh;
	String nome, email, sexo;
	double peso;
	
	public Proprietario(long cpf, String nome, String email, String sexo, double peso, long numeroCnh) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.sexo = sexo;
		this.peso = peso;
		this.numeroCnh =numeroCnh;
		
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public long getNumeroCnh() {
		return numeroCnh;
	}

	public void setNumeroCnh(long numeroCnh) {
		this.numeroCnh = numeroCnh;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	

	
	
	
}
