package br.fiap.javaWeb.dao;

import java.util.List;
import java.util.Map;

import br.fiap.javaWeb.entity.Sorteio;
import br.fiap.javaWeb.entity.Usuario;

public class SorteioDao extends GenericDao<Sorteio> {

	public SorteioDao() {
		super(Sorteio.class);
	}

	public List<Sorteio> getAmigosSecretos(Usuario usuarioLogado) {
		StringBuilder sb = new StringBuilder();
		sb.append("select u from Sorteio u where u.participante=:participante");
		
		Map<String, Object> qp = newQueryParametros();
		qp.put("participante", usuarioLogado);
		
		List<Sorteio> list = getList(sb.toString(), qp);
		
		return list;
	}

	
 
}
