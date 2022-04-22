package com.libros.us.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libros.us.modelos.Libro;
import com.libros.us.modelos.LoginUser;
import com.libros.us.modelos.Usuario;
import com.libros.us.servicios.AppService;

@Controller
public class ControladorUsuario {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
						@ModelAttribute("nuevoLogin") LoginUser nuevoLogin) {

		return "registro.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
						   BindingResult result, Model model, HttpSession session) {
		
		servicio.register(nuevoUsuario, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoLogin", new LoginUser());
			return "registro.jsp";
		}
		
		session.setAttribute("user_session", nuevoUsuario);
		return "redirect:/dashboard";
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
						BindingResult result, Model model, HttpSession session) {
							
		Usuario usuario = servicio.login(nuevoLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoUsuario", new Usuario());
			return "registro.jsp";
		}
		
		session.setAttribute("usuario_session", usuario);
		return "redirect:/dashboard";
							
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuario_session");
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		
		Usuario currentUser = (Usuario)session.getAttribute("usuario_session");
		
		if(currentUser == null) {
			return "redirect:/";
		}
//		List<Libro> mis_libros = servicio.find_my_libro(currentUser);
//		List<Libro> otros_libros = servicio.find_other_libro(currentUser);
		List<Libro> todos_libros = servicio.find_all_libro();
//		model.addAttribute("mis_libros", mis_libros);
//		model.addAttribute("otros_libros", otros_libros);
		model.addAttribute("todos_libros", todos_libros);

		return "dashboard.jsp";
	}
	
	
	
}
