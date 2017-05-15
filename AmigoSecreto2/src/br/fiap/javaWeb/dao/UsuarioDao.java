package br.fiap.javaWeb.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.hibernate.Query;

import br.fiap.javaWeb.entity.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public Usuario getUsuario(String nome, String senha){
		StringBuilder sb = new StringBuilder();
		sb.append("select u from Usuario u where u.nome=:nome and u.senha=:senha");
		
		Map<String, Object> qp = newQueryParametros();
		qp.put("nome", nome);
		qp.put("senha", senha);
		
		List<Usuario> list = getList(sb.toString(), qp);
		
		if (list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public List<Usuario> getUsuarios(List<Usuario> usuarios) {
		StringBuilder sb = new StringBuilder();
		sb.append("select u from Usuario u where u in (:usuarios)");
		
		Map<String, Collection> qp = newQueryParametros2();
		qp.put("usuarios", usuarios);
		
		List<Usuario> list = getList2(sb.toString(), qp);
		
		return list;
	}
	
 
}
