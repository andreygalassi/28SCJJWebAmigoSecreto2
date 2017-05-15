package br.fiap.javaWeb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.fiap.javaWeb.dao.UsuarioDao;
import br.fiap.javaWeb.entity.Perfil;
import br.fiap.javaWeb.entity.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	
//	@ManagedProperty(value = "")
	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private Integer idSelecionado;
	
	public UsuarioBean() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Perfil> listaPerfil(){
		List<Perfil> lista = new ArrayList<>();
		for (Perfil perfil : Perfil.values()) {
			lista.add(perfil);
		}
		return lista;
	}
	
	//action
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			UsuarioDao dao = new UsuarioDao();
			if (usuario.getId()==null){
				dao.adicionar(usuario);
			}else{
				dao.atualizar(usuario);
			}
			msg.setSummary("OK");
			msg.setDetail("Usuário " + usuario.getNome() + " incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
	        usuario = new Usuario();
			
		} catch (Exception e) {
			
			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);			
		}
		context.addMessage(null, msg);		
	}
	
	public List<Usuario> lista(){
		UsuarioDao dao = new UsuarioDao();
		
		return dao.listar();
	}
	
	public void onRowSelect(SelectEvent event) {
		UsuarioDao dao = new UsuarioDao();
		idSelecionado=((Usuario) event.getObject()).getId();
		usuario = dao.buscar(((Usuario) event.getObject()).getId());
		usuarioSelecionado = dao.buscar(((Usuario) event.getObject()).getId());
    }
 
    public void onRowUnselect(UnselectEvent event) {
        usuario = new Usuario();
        usuarioSelecionado = new Usuario();
    }
//	public List<Nivel> getNiveis(){
//		List<Nivel> niveis = new ArrayList<Nivel>();
//		niveis.add(new Nivel(1, "Administrador"));
//		niveis.add(new Nivel(2, "Cliente"));
//		return niveis;
//	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
