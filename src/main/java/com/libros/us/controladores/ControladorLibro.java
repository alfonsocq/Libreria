package com.libros.us.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libros.us.modelos.Libro;
import com.libros.us.modelos.Usuario;
import com.libros.us.servicios.AppService;


@Controller
@RequestMapping("/libros")
public class ControladorLibro {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/new")

	public String new_libro(@ModelAttribute("libro") Libro libro, HttpSession session) {
		
		Usuario currentUsuario = (Usuario)session.getAttribute("usuario_session");
		
		if(currentUsuario == null) {
			return "redirect:/";
		}
		
		
		return "agregarlibro.jsp";
		
	}
	
	@PostMapping("/create")	
	public String create_libro(@Valid @ModelAttribute("libro") Libro libro, 
								 BindingResult result,
								 HttpSession session) {
		
		Usuario currentUsuario = (Usuario)session.getAttribute("usuario_session"); //Usuario en sesión
		
		if(currentUsuario == null) {
			return "redirect:/";
		}
	
		
		if(result.hasErrors()) {
			return "agregarlibro.jsp";
		} else {
			
			servicio.save_libro(libro);
			
			Usuario miUsuario = servicio.find_user(currentUsuario.getId()); 
			
			servicio.save_user(miUsuario);
			
			return "redirect:/dashboard";
			
		}
	}
	
	@GetMapping("/{libro_id}")
	public String show_project(@PathVariable("libro_id") Long libro_id, HttpSession session, Model model) {
		
		Usuario currentUsuario = (Usuario)session.getAttribute("usuario_session"); 
		
		if(currentUsuario == null) {
			return "redirect:/";
		}
		
		
		Libro thisLibro = servicio.find_libro(libro_id);
		model.addAttribute("libro", thisLibro);
		
		return "verlibro.jsp";		
	}
	
	
	
}
