package com.cenfotec.examen2.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.examen2.domain.Actividad;
import com.cenfotec.examen2.domain.Categoria;
import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.service.ActividadService;
import com.cenfotec.examen2.service.CategoriaService;
import com.cenfotec.examen2.service.WorkshopService;

@Controller
public class WorkshopController {
	public static String paragraph1 = "poi-word-para1.txt";
	public static String paragraph2 = "poi-word-para2.txt";
	public static String paragraph3 = "poi-word-para3.txt";
	public static String output = "rest-with-spring.docx";

	XWPFDocument document = new XWPFDocument();

	@Autowired
	WorkshopService workshopService;

	@Autowired
	ActividadService actividadService;

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(value = "/registroWorkshop", method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Workshop());
		model.addAttribute("categorias", categoriaService.getAll());
		return "registroWorkshop";
	}

	@RequestMapping(value = "/registroWorkshop", method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "index";
	}

	@RequestMapping(value = "/mantenimientoCategoria", method = RequestMethod.GET)
	public String insertarCategoria(Model model) {
		model.addAttribute(new Categoria());
		model.addAttribute("categorias", categoriaService.getAll());
		return "mantenimientoCategoria";
	}

	@RequestMapping(value = "/mantenimientoCategoria", method = RequestMethod.POST)
	public String insertarActionCategoria(Categoria categoria, BindingResult result, Model model) {
		categoriaService.save(categoria);
		return "index";
	}

	@RequestMapping("/listarWorkshop")
	public String listar(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "listarWorkshop";
	}

	@RequestMapping(value = "/listarCategoria", method = RequestMethod.GET)
	public String listarCategoria(Model model) {
		model.addAttribute("categorias", categoriaService.getAll());
		return "listarCategoria";
	}

	@RequestMapping(value = "/filtrar")
	public String viewHomePage(Model model, @Param("categoria") String categoria) {
		List<Workshop> listWorkshops = workshopService.listCategoria(categoria);
		model.addAttribute("workshops", listWorkshops);

		return "listarWorkshopCategoria";
	}
	

	@RequestMapping("/listarWorkshopCategoria")
	public String listarWorkshopCategoria(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "listarWorkshopCategoria";
	}

	@RequestMapping("/listarWorkshopKeyword")
	public String listarWorkshopKeyword(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "listarWorkshopKeyword";
	}

	@RequestMapping("/listarWorkshopAutor")
	public String listarWorkshopAutor(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "listarWorkshopAutor";
	}

	@RequestMapping("/editarCategoria/{id}")
	public String findCategoryToEdit(Model model, @PathVariable long id) {
		Optional<Categoria> possibleData = categoriaService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("categoriaToEdit", possibleData.get());

			return "editarCategoria";
		}
		return "noEncontrado";
	}

	@RequestMapping(value = "/editarCategoria/{id}", method = RequestMethod.POST)
	public String saveEdition(Categoria categoria, Model model, @PathVariable long id) {
		categoriaService.save(categoria);
		return "index";
	}

	@RequestMapping("/editarWorkshop/{id}")
	public String findAnthologyToEdit(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopToEdit", possibleData.get());
			model.addAttribute("categorias", categoriaService.getAll());

			return "editarWorkshop";
		}
		return "noEncontrado";
	}

	@RequestMapping(value = "/editarWorkshop/{id}", method = RequestMethod.POST)
	public String saveEdition(Workshop workshop, Model model, @PathVariable long id) {
		workshopService.save(workshop);
		return "index";
	}

	@RequestMapping(value = "/detalle/{id}")
	public String saveEdition(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			List<Actividad> listaActividades = actividadService.getAll();
			LocalTime sumatoriaFinal = LocalTime.of(0, 0);
			LocalTime sumatoriaTotal = LocalTime.of(0, 0);
			LocalTime tiempoSumatoriaParcial = LocalTime.of(0, 0);
			for (int i = 0; i < listaActividades.size(); i++) {

				if (listaActividades.get(i).getWorkshop().getId() == possibleData.get().getId()) {
					sumatoriaTotal = sumatoriaTotal.plusHours(listaActividades.get(i).getTiempo().getHour());
					sumatoriaTotal = sumatoriaTotal.plusMinutes(listaActividades.get(i).getTiempo().getMinute());
				}
				sumatoriaFinal = sumatoriaTotal;
			}
			model.addAttribute("tiempoSumatoria", sumatoriaFinal);
			model.addAttribute("workshopData", possibleData.get());
			return "detalle";
		}
		return "noEncontrado";
	}

	@RequestMapping(value = "/agregarActividad/{id}")
	public String recoverForAddArticle(Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		Actividad newActividad = new Actividad();
		if (workshop.isPresent()) {
			newActividad.setWorkshop(workshop.get());
			model.addAttribute("workshop", workshop.get());
			model.addAttribute("actividad", newActividad);
			return "agregarActividad";
		}
		return "noEncontrado";
	}

	@RequestMapping(value = "/agregarActividad/{id}", method = RequestMethod.POST)
	public String saveActividad(Actividad actividad, Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		if (workshop.isPresent()) {
			actividad.setWorkshop(workshop.get());
			actividadService.save(actividad);
			return "index";
		}
		return "errorActividad";

	}

	@RequestMapping(value = "/generarDoc/{id}")
	public String saveDoc(Model model, @PathVariable long id) throws IOException {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			List<Actividad> listaActividades = actividadService.getAll();
			LocalTime sumatoriaFinal = LocalTime.of(0, 0);
			LocalTime sumatoriaTotal = LocalTime.of(0, 0);
			LocalTime tiempoSumatoriaParcial = LocalTime.of(0, 0);
			for (int i = 0; i < listaActividades.size(); i++) {

				if (listaActividades.get(i).getWorkshop().getId() == possibleData.get().getId()) {
					sumatoriaTotal = sumatoriaTotal.plusHours(listaActividades.get(i).getTiempo().getHour());
					sumatoriaTotal = sumatoriaTotal.plusMinutes(listaActividades.get(i).getTiempo().getMinute());
				}
				sumatoriaFinal = sumatoriaTotal;
			}
			model.addAttribute("tiempoSumatoria", sumatoriaFinal);
			model.addAttribute("workshopData", possibleData.get());
			this.configDoc(possibleData, listaActividades, sumatoriaFinal);
			this.generateDoc();

			return "generarDoc";
		}
		return "noEncontrado";
	}

	public String convertTextFileToString(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {

			return stream.collect(Collectors.joining(" "));
		} catch (IOException | URISyntaxException e) {
			return null;
		}
	}

	public void configDoc(Optional<Workshop> possibleData, List<Actividad> listaActividades,
			LocalTime sumatoriaTiempo) {
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Tiempo duración:" + sumatoriaTiempo);
		titleRun.setColor("009933");
		titleRun.setBold(true);
		titleRun.setFontFamily("Courier");
		titleRun.setFontSize(20);

		XWPFParagraph subTitle = document.createParagraph();
		subTitle.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun subTitleRun = subTitle.createRun();
		subTitleRun.setText("Nombre del workshop:" + possibleData.get().getNombre());
		subTitleRun.setColor("00CC44");
		subTitleRun.setFontFamily("Courier");
		subTitleRun.setFontSize(16);
		subTitleRun.setTextPosition(20);
		subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

		XWPFParagraph para1 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.CENTER);
		// String string1 = convertTextFileToString(paragraph1);
		XWPFRun para1Run = para1.createRun();
		para1Run.setText(" Autor:" + possibleData.get().getAutor());

		XWPFParagraph para2 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.CENTER);
		// String string1 = convertTextFileToString(paragraph1);
		XWPFRun para2Run = para2.createRun();
		para1Run.setText(" Categoria:" + possibleData.get().getCategoria());

		XWPFParagraph para3 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.CENTER);
		// String string1 = convertTextFileToString(paragraph1);
		XWPFRun para3Run = para3.createRun();
		para1Run.setText(" Objetivo:" + possibleData.get().getPalabraClave());

		XWPFParagraph para4 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.CENTER);
		// String string1 = convertTextFileToString(paragraph1);
		XWPFRun para4Run = para4.createRun();
		para1Run.setText(" Palabra Clave:" + possibleData.get().getPalabraClave());

		XWPFParagraph para5 = document.createParagraph();
		para1.setAlignment(ParagraphAlignment.LEFT);
		// String string1 = convertTextFileToString(paragraph1);
		XWPFRun para5Run = para5.createRun();
		para1Run.setText(" Listado de actividades:");

		for (int i = 0; i < listaActividades.size(); i++) {

			if (listaActividades.get(i).getWorkshop().getId() == possibleData.get().getId()) {
				XWPFParagraph paraX = document.createParagraph();
				XWPFRun paraXRun = paraX.createRun();
				paraXRun.setText(listaActividades.get(i).getNombre() + " " + listaActividades.get(i).getTiempo());
			}

		}

	}

	public void generateDoc() throws IOException {
		FileOutputStream out = new FileOutputStream(output);
		document.write(out);
		out.close();
		document.close();
	}

}
