
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
//import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Reserva extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date	fechaRealizacion;
	private Date	fechaReserva;
	private String	horaInicio;
	private String	horaFin;
	private String	comentario;
	//private String	estado;


	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	public void setFechaRealizacion(final Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy/MM/dd")
	@DateTimeFormat(pattern = "dd-MM-yy")
	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(final Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	@NotNull
	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(final String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@NotNull
	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(final String horaFin) {
		this.horaFin = horaFin;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(final String comentario) {
		this.comentario = comentario;
	}

	/*@Pattern(regexp = "(Activa)|(Cancelada)")
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(final String estado) {
		this.estado = estado;
	}*/


	// Relationships--------------------------------------------------------------
	private Usuario		usuario;
	private Servicio	servicio;


	@Valid
	@ManyToOne(optional = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	@Valid
	@ManyToOne(optional = false)
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(final Servicio servicio) {
		this.servicio = servicio;
	}

}
