package br.fiap.javaWeb.entity;

public enum Perfil {
	USUARIO,
	ADMINISTRADOR;
	
	public Boolean isUsuario(){
		if (this.equals(USUARIO))
			return true;
		return false;
	}
	public Boolean isAdministrador(){
		if (this.equals(ADMINISTRADOR))
			return true;
		return false;
	}
}
