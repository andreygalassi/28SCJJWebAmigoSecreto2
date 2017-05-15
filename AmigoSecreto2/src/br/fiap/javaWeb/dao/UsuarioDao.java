package br.fiap.javaWeb.dao;

import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.hibernate.Query;

import br.fiap.javaWeb.entity.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public Usuario getUsuario(String email, String senha){
		StringBuilder sb = new StringBuilder();
		sb.append("select u from Usuario u where u.email=:email and u.senha=:senha");
		
		Map<String, Object> qp = newQueryParametros();
		qp.put("email", email);
		qp.put("senha", senha);
		
		List<Usuario> list = getList(sb.toString(), qp);
		
		if (list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
 
}
