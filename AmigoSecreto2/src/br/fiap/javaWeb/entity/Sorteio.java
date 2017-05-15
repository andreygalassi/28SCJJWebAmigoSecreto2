package br.fiap.javaWeb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sorteio")
public class Sorteio implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	private Usuario participante;
	
	@ManyToOne
	private Usuario sorteado;
	
	@ManyToOne
	private Grupo grupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getParticipante() {
		return participante;
	}

	public void setParticipante(Usuario participante) {
		this.participante = participante;
	}

	public Usuario getSorteado() {
		return sorteado;
	}

	public void setSorteado(Usuario sorteado) {
		this.sorteado = sorteado;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	

}
