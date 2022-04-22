package com.libros.us.modelos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {

	@NotEmpty(message="No te olvides de ingresar email")
	@Email(message="Ingrese un email válido")
	private String email;
	
	@NotEmpty(message="No te olvides de la contraseña")
	@Size(min=8, max=120, message="La contraseña debe de ser entre 8 y 120 caracteres")
	private String password;

	public LoginUser() {
	
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
	
}
