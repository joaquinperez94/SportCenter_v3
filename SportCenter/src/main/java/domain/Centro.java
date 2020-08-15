
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Centro extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	nombre;
	private String	imagen;
	private String	descripcion;
	private String	tipo;
	private String	direccion;
	private int		valoracion;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@Lob
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(final String imagen) {
		this.imagen = imagen;
	}

	@NotBlank
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@Pattern(regexp = "(Polideportivo)|(Pabellón)|(Gimnasio)|(Campo Fútbol)|(Pista)")
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	@NotBlank
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	public Integer getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(final Integer valoracion) {
		this.valoracion = valoracion;
	}


	// Relationships--------------------------------------------------------------
	private Gestor					gestor;
	private Collection<Servicio>	servicios;
	private Collection<Comentario>	comentarios;
	private Collection<Usuario>		usuarios;


	@Valid
	@ManyToOne(optional = false)
	public Gestor getGestor() {
		return this.gestor;
	}

	public void setGestor(final Gestor gestor) {
		this.gestor = gestor;
	}

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "centro")
	public Collection<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(final Collection<Servicio> servicios) {
		this.servicios = servicios;
	}

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "centro")
	public Collection<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(final Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@NotNull
	@Valid
	@ManyToMany
	public Collection<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	/*
	 * public String getImagen() {
	 * return this.imagen;
	 * }
	 * 
	 * public void setImagen(final String imagen) {
	 * this.imagen = imagen;
	 * }
	 */

}
