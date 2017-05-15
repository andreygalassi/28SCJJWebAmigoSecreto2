 package br.fiap.javaWeb.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.fiap.javaWeb.dao.UsuarioDao;
import br.fiap.javaWeb.entity.Usuario;

@ManagedBean
@SessionScoped
public class MenuBean {
	
	private Usuario usuario;
	
	public MenuBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Integer idUsuarioSession = (Integer) session.getAttribute("ID_USUARIO");
		if (idUsuarioSession!=null){
			usuario = new UsuarioDao().buscar(idUsuarioSession);
//			System.out.println("Usuario: "+usuario.getEmail());
//	        addMessage(usuario.getEmail(), "Data saved");
		}
	}
	
    public String usuarios() {
//        addMessage("Usuarios", "Data saved");
		return "/admin/usuarios";
    }
     
    public String grupos() {
//    	if (!usuario.getPerfil().isAdministrador()){
//    		addMessage("Sem permissão", "");
//    	}
		return "/admin/grupos";
    }
     
    public String sorteio() {
//    	if (!usuario.getPerfil().isAdministrador()){
//    		addMessage("Sem permissão", "");
//    	}
		return "/admin/sorteio";
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
