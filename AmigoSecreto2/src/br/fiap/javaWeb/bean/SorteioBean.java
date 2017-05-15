package br.fiap.javaWeb.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.fiap.javaWeb.dao.GrupoDao;
import br.fiap.javaWeb.dao.SorteioDao;
import br.fiap.javaWeb.dao.UsuarioDao;
import br.fiap.javaWeb.entity.Grupo;
import br.fiap.javaWeb.entity.Sorteio;
import br.fiap.javaWeb.entity.Usuario;

@ManagedBean
@SessionScoped
public class SorteioBean {
	
//	@ManagedProperty(value = "")
	private Sorteio sorteio;
	private Grupo grupoSelecionado;
	
	public SorteioBean() {
		sorteio = new Sorteio();
	}
	
	public List<Grupo> listaGrupo(){
		GrupoDao grupoDao = new GrupoDao();
		List<Grupo> lista = grupoDao.listar();
		return lista;
	}
	
	public List<Sorteio> listaSorteio(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Integer idUsuarioSession = (Integer) session.getAttribute("ID_USUARIO");
		Usuario usuarioLogado=null;
		if (idUsuarioSession!=null){
			usuarioLogado = new UsuarioDao().buscar(idUsuarioSession);
//			System.out.println("Usuario: "+usuario.getEmail());
//	        addMessage(usuario.getEmail(), "Data saved");
		}
		
		SorteioDao sorteioDao = new SorteioDao();
		List<Sorteio> lista = sorteioDao.getAmigosSecretos(usuarioLogado);
		return lista;
	}
	
	public void sortear(Grupo grupo){
		GrupoDao grupoDao = new GrupoDao();
		SorteioDao sorteioDao = new SorteioDao();
		Map<Usuario, Sorteio> sorteios = new HashMap<>();
		Queue<Usuario> sorteados = new LinkedList<>(grupo.getUsuarios());
		Set<Usuario> usuarios = new HashSet<>(grupo.getUsuarios());
//		grupoSelecionado = dao.buscar(((Grupo) event.getObject()).getId());
		
		for (Usuario usuario : usuarios) {
			Sorteio sorteio = new Sorteio();
			sorteio.setGrupo(grupo);
			sorteio.setParticipante(usuario);
			sorteios.put(usuario, sorteio);
		}
		for (Usuario p : usuarios) {
			Sorteio sorteio = sorteios.get(p);
			Usuario sorteado = sorteados.poll();
			if (sorteado.equals(p)){
				sorteados.add(sorteado);
				sorteado = sorteados.poll();
			}
			sorteio.setSorteado(sorteado);
		}
		for (Sorteio sorteio : sorteios.values()) {
			sorteioDao.adicionar(sorteio);
		}
		
		grupo.setConcluido(true);
		grupoDao.atualizar(grupo);
//		System.out.println(usuarios);
//		System.out.println(grupoSelecionado);
		
		

	}
	
	public void onRowSelect(SelectEvent event) {
		GrupoDao dao = new GrupoDao();
		grupoSelecionado = dao.buscar(((Grupo) event.getObject()).getId());
    }
 
    public void onRowUnselect(UnselectEvent event) {
//        usuario = new Usuario();
//        usuarioSelecionado = new Usuario();
    }

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

}
