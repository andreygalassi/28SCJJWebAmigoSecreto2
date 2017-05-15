package br.fiap.javaWeb.dao;

import br.fiap.javaWeb.entity.Grupo;

public class GrupoDao extends GenericDao<Grupo> {

	public GrupoDao() {
		super(Grupo.class);
	}
	
//	public Usuario getUsuario(String email, String senha){
//		StringBuilder sb = new StringBuilder();
//		sb.append("select u from Grupo u where u.email=:email and u.senha=:senha");
//		
//		Map<String, Object> qp = newQueryParametros();
//		qp.put("email", email);
//		qp.put("senha", senha);
//		
//		List<Usuario> list = getList(sb.toString(), qp);
//		
//		if (list.size()>0){
//			return list.get(0);
//		}else{
//			return null;
//		}
//	}
	
 
}
