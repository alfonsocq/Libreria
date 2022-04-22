package com.libros.us.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="No te olvides llenar el campo de nombre, es obligatorio.")
	@Size(min=3, max=20, message="Nombre debe de tener entre 3 y 20 caracteres")
	private String first_name;
	
	@NotEmpty(message="No te olvides llenar el campo de apellido, es obligatorio.")
	@Size(min=2, max=20, message="Nombre debe de tener entre 2 y 20 caracteres")
	private String last_name;
	
	@NotEmpty(message="No te olvides llenar el campo de email, es obligatorio.")
	@Email(message="Ingrese un correo electrónico válido")
	private String email;
	
	@NotEmpty(message="El campo de passsword es obligatorio.")
	@Size(min=8, max=120, message="La contraseña debe de ser entre 8 y 120 caracteres")
	private String password;
	
	@Transient
	@NotEmpty(message="El campo de confirmación es obligatorio.")
	@Size(min=8, max=120, message="La confirmación de contraseña debe de ser entre 8 y 120 caracteres")
	private String confirm;
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)
	private List<Libro> mis_libros;
		
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;

	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Libro> getMis_libros() {
		return mis_libros;
	}

	public void setMis_libros(List<Libro> mis_libros) {
		this.mis_libros = mis_libros;
	}
	
	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
	
	
}

}