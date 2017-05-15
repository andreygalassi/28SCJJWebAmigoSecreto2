package br.fiap.javaWeb.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.fiap.javaWeb.dao.GrupoDao;
import br.fiap.javaWeb.dao.UsuarioDao;
import br.fiap.javaWeb.entity.Grupo;
import br.fiap.javaWeb.entity.Usuario;

@ManagedBean
@SessionScoped
public class GrupoBean {
	
//	@ManagedProperty(value = "")
	private Grupo grupo;
	private Grupo grupoSelecionado;
	private Integer idSelecionado;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosSelecionados;

	GrupoDao dao = new GrupoDao();
	public GrupoBean() {
		grupo = new Grupo();
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarios = usuarioDao.listar();
	}
	
	//action
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
//			for (Usuario usuario : usuariosSelecionados) {
//				if (grupo.getUsuarios()==null) grupo.setUsuarios(new HashSet<Usuario>());
//				grupo.getUsuarios().add(usuarioDao.buscar(usuario.getId()));
////				grupo.getUsuarios().add(u);
//			}			
//			grupo.setUsuarios(usuariosSelecionados);
			grupo.setUsuarios(usuarioDao.listar());
			if (grupo.getId()==null){
//				grupo.setUsuarios(usuarioDao.listar());
				dao.adicionar(grupo);
			}else{
//				grupo.setUsuarios(usuarioDao.listar());
				dao.atualizar(grupo);
//				dao.merge(grupo);
			}
			msg.setSummary("OK");
			msg.setDetail("Grupo " + grupo.getDescricao() + " incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
	        grupo = new Grupo();
			
		} catch (Exception e) {
			
			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);			
		}
		context.addMessage(null, msg);		
	}
	
	public List<Grupo> lista(){
		return dao.listar();
	}
	
	public void onRowSelect(SelectEvent event) {
		usuariosSelecionados=new ArrayList<Usuario>();
		UsuarioDao usuarioDao = new UsuarioDao();
//		usuarios = usuarioDao.listar();
		idSelecionado=((Grupo) event.getObject()).getId();
		grupo = dao.buscar(((Grupo) event.getObject()).getId());
		grupoSelecionado = dao.buscar(((Grupo) event.getObject()).getId());
		usuariosSelecionados = grupoSelecionado.getUsuarios();
    }
 
    public void onRowUnselect(UnselectEvent event) {
        grupo = new Grupo();
        grupoSelecionado = new Grupo();
    }

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public Integer getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Integer idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}
}
