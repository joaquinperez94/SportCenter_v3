
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CentroRepository;
import domain.Centro;
import domain.Comentario;
import domain.Gestor;
import domain.Horario;
import domain.Servicio;
import forms.CentroForm;

@Service
@Transactional
public class CentroService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CentroRepository	centroRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	GestorService				gestorService;
	@Autowired
	private Validator			validator;
	@Autowired
	HorarioService				horarioService;


	// Constructors -----------------------------------------------------------
	public CentroService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	//Crear -----------------------------------------------------------
	public Centro crearCentro() {
		Centro result;
		Gestor gestorPrincipal;
		Collection<Servicio> servicios;
		Collection<Comentario> comentarios;
		Collection<Horario> horarios;
		Collection<String> diasSemana;

		horarios = new ArrayList<>();
		diasSemana = new ArrayList<>();
		diasSemana.add("Lunes");
		diasSemana.add("Martes");
		diasSemana.add("Miércoles");
		diasSemana.add("Jueves");
		diasSemana.add("Viernes");
		diasSemana.add("Sábado");
		diasSemana.add("Domingo");

		for (final String dia : diasSemana) {
			final Horario h = this.horarioService.create();
			h.setDiaSemana(dia);
			horarios.add(h);
		}

		result = new Centro();
		servicios = new ArrayList<Servicio>();
		comentarios = new ArrayList<Comentario>();
		gestorPrincipal = this.gestorService.findByPrincipal();

		result.setGestor(gestorPrincipal);
		result.setServicios(servicios);
		result.setComentarios(comentarios);
		result.setHorarios(horarios);

		return result;
	}

	//Guardar -----------------------------------------------------------
	public Centro save(final Centro centro) {
		Centro result;

		Assert.notNull(centro);

		Assert.isTrue(centro.getGestor().equals(this.gestorService.findByPrincipal()));

		result = this.centroRepository.save(centro);
		return result;
	}

	//Eliminar -----------------------------------------------------------
	public void delete(final Centro centro) {

		Assert.notNull(centro);
		Assert.notNull(this.gestorService.findByPrincipal());

		//TODO: Para eliminar un centro si sus servicios tienen reservas hay que eliminarlas.
		//Obtener servicios del centro y luego sus reservas.

		this.centroRepository.delete(centro);
	}

	public Collection<Centro> findAll() {

		Collection<Centro> result;

		result = this.centroRepository.findAll();

		return result;

	}

	public Centro findOne(final int centroId) {

		Assert.isTrue(centroId != 0);
		Centro result;
		result = this.centroRepository.findOne(centroId);

		return result;

	}

	public Collection<Centro> findCentroByName(final String name) {
		Collection<Centro> result;

		result = this.centroRepository.findCentroByName(name);
		return result;
	}

	public Collection<Centro> findCentrosByGestor(final int gestorId) {
		Collection<Centro> result;

		result = this.centroRepository.findCentrosByGestor(gestorId);
		return result;
	}

	public CentroForm reconstruct(final CentroForm centroForm, final BindingResult bindingResult) {
		CentroForm result = null;
		Centro centroBD;
		centroBD = centroForm.getCentro();

		if (centroBD.getId() == 0) {
			Gestor gestor;
			Collection<Servicio> servicios;
			Collection<Comentario> comentarios;
			Collection<Horario> horarios;

			gestor = this.gestorService.findByPrincipal();
			servicios = new ArrayList<Servicio>();
			comentarios = new ArrayList<Comentario>();
			horarios = new ArrayList<Horario>(centroForm.getHorarios());

			//Alomejor falta cogerle en centro al centroForm
			centroForm.getCentro().setComentarios(comentarios);
			centroForm.getCentro().setHorarios(horarios);
			centroForm.getCentro().setServicios(servicios);
			centroForm.getCentro().setGestor(gestor);

			result = centroForm;
		} else {
			centroBD = this.centroRepository.findOne(centroForm.getCentro().getId());
			centroForm.getCentro().setId(centroBD.getId());
			centroForm.getCentro().setVersion(centroBD.getVersion());
			centroForm.getCentro().setServicios(centroBD.getServicios());
			centroForm.getCentro().setComentarios(centroBD.getComentarios());
			centroForm.getCentro().setHorarios(centroBD.getHorarios());
			centroForm.getCentro().setGestor(centroBD.getGestor());
			result = centroForm;
		}
		this.validator.validate(result, bindingResult);
		return result;
	}

}
