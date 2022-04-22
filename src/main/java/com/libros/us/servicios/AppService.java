package com.libros.us.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.libros.us.modelos.Libro;
import com.libros.us.modelos.LoginUser;
import com.libros.us.modelos.Usuario;
import com.libros.us.repositorios.RepositorioLibro;
import com.libros.us.repositorios.RepositorioUsuario;


@Service
public class AppService {
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	@Autowired
	private RepositorioLibro repositorio_libro;
	
	
	public Usuario register(Usuario nuevoUsuario, BindingResult result) {
		
		String nuevoEmail = nuevoUsuario.getEmail();
		
		if(repositorio_usuario.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "Unique", "Ese correo ya existe.");
		}
		
		if(! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm()) ) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coiniciden");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			
			String contraseña_secreta = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contraseña_secreta);
			
			return repositorio_usuario.save(nuevoUsuario);
		}
		
	}
	
	public Usuario login(LoginUser nuevoLogin, BindingResult result) {
		
		if(result.hasErrors()) {
			return null;
		}
		
		
		Optional<Usuario> posibleUsuario = repositorio_usuario.findByEmail(nuevoLogin.getEmail());
		if(!posibleUsuario.isPresent()) {
			result.rejectValue("email", "Unique", "El correo ingresado no existe");
			return null;
		}
		
		Usuario user_login = posibleUsuario.get();
		
		
		if(! BCrypt.checkpw(nuevoLogin.getPassword(), user_login.getPassword()) ) {
			result.rejectValue("password", "Matches", "Contraseña inválida");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return user_login; 
		}
		
		
	}
	
	public Usuario save_user(Usuario updatedUser) {
		return repositorio_usuario.save(updatedUser);
	}
	
	public Usuario find_user(Long id) {
		Optional<Usuario> optionalUser = repositorio_usuario.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	
	public Libro save_libro(Libro nuevoLibro) {
		return repositorio_libro.save(nuevoLibro);
	}
	
	public Libro find_libro(Long id) {
		Optional<Libro> optionalLibro = repositorio_libro.findById(id);
		if(optionalLibro.isPresent()) {
			return optionalLibro.get();
		} else {
			return null;
		}
	}
	
	public List<Libro> find_my_libro(Usuario miUsuario) {
		return repositorio_libro.findAllByUsuario(miUsuario);
	}
	
	public List<Libro> find_other_libro(Usuario miUsuario){
		return repositorio_libro.findByUsuarioNotContains(miUsuario);
	}
	
	public List<Libro> find_all_libro(){
		return repositorio_libro.findAll();
	}
}
