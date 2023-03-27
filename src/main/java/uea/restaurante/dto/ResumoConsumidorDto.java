package uea.restaurante.dto;

import java.time.LocalDate;

public class ResumoConsumidorDto {
	private Long codigo;
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	
	public ResumoConsumidorDto() {
	}

	public ResumoConsumidorDto(Long codigo, String nome, LocalDate dataNascimento, String cpf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
