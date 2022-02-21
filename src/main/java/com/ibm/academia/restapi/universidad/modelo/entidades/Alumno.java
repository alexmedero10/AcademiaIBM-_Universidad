package com.ibm.academia.restapi.universidad.modelo.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alumnos", schema = "universidad")
//@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona{

	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "carrera_id", foreignKey = @ForeignKey(name = "FK_CARRERA_ID"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "alumnos","profesores"})
	private Carrera carrera;
	
	public Alumno(Long id, String nombre, String apellido, String dni, Direccion direccion, String usuarioCreacion) {
		super(id, nombre, apellido, dni, direccion, usuarioCreacion);
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Alumno [carrera=");
		builder.append(carrera);
		builder.append("]");
		return builder.toString();
	}



	private static final long serialVersionUID = -8379547640695878570L;
}
