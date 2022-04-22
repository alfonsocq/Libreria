package com.libros.us.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libros.us.modelos.Libro;
import com.libros.us.modelos.Usuario;


@Repository
public interface RepositorioLibro extends CrudRepository<Libro, Long>{
	
	List <Libro> findAll();
	List<Libro> findById(long id);
	
	Libro save(Libro nuevoLibro);
	
	List<Libro> findByUsuarioNotContains(Usuario usuario); 
	
	List<Libro> findAllByUsuario(Usuario usuario); 

}
