package com.utc.bancario1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "cedula",nullable = false,length = 13, unique= true)
	private String cedula;
	
	@Column(name= "nombre",nullable = false,length = 255)
	private String nombre;
	
	@Column(name= "apellido",nullable = false,length = 255)
	private String apellido;
	
	@Column(name= "email",nullable = false,length = 255)
	private String email;
	
	@Column(name= "direccion",nullable = false,length = 255)
	private String direccion;
	
	public Cliente () {
		
	}

	public Cliente(Long id, String cedula, String nombre, String apellido, String email, String direccion) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
		
}
