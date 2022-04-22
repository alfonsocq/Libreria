package com.libros.us.repositorios;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.libros.us.modelos.Usuario;



@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
