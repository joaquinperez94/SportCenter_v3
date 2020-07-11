
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comentario extends DomainEntity {

	// Atributos -------------------------------------------------------------
	private String	texto;
	private int		valoracion;
	private Date	fechaCreacion;


	@NotBlank
	//@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTexto() {
		return this.texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	public Integer getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(final Integer valoracion) {
		this.valoracion = valoracion;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
	@Past
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	// Relaciones ---------------------------------------------------------------

	private Centro	centro;


	@Valid
	@ManyToOne(optional = false)
	public Centro getCentro() {
		return this.centro;
	}

	public void setCentro(final Centro centro) {
		this.centro = centro;
	}

}
