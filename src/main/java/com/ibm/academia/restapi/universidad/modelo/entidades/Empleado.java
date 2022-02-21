package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "empleados", schema = "universidad")
//@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona{
	
	@Positive
	@Column(name = "sueldo")
	private BigDecimal sueldo;
	
	@Column(name = "tipo_empleado")
	@Enumerated(EnumType.STRING)
	private TipoEmpleado tipoEmpleado;
	
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	private Pabellon pabellon;
	
	public Empleado(Long id, String nombre, String apellido, String dni, Direccion direccion, String usuarioCreacion, BigDecimal sueldo) {
		super(id, nombre, apellido, dni, direccion, usuarioCreacion);
		this.sueldo = sueldo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Empleado [sueldo=");
		builder.append(sueldo);
		builder.append(", tipoEmpleado=");
		builder.append(tipoEmpleado);
		builder.append("]");
		return builder.toString();
	}


	private static final long serialVersionUID = 3761180219638663519L;
}
