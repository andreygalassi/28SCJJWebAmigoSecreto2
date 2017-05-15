package br.fiap.javaWeb.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.fiap.javaWeb.dao.UsuarioDao;
import br.fiap.javaWeb.entity.Perfil;
import br.fiap.javaWeb.entity.Usuario;

@ManagedBean(name="loginUsuarios")
@SessionScoped
public class LoginUsuariosBean {

	private Usuario usuario;
	private UsuarioDao dao;
	
	public LoginUsuariosBean(){
		dao = new UsuarioDao();
		start();
		usuario = new Usuario();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
//	public static void main(String[] args) {
//		Usuario u = new Usuario("nome", "senha", 1);
//		UsuarioDao dao = new UsuarioDao();
//		dao.adicionar(u);
//		u.setNome("nome2");
//		dao.atualizar(u);
//		
//		u = dao.buscar(5);
//		
//		System.out.println(u.getId());
//		System.out.println(u.getId());
//		u.setNome("nome5");
//		dao.atualizar(u);
//	}

//	private Usuarios usuario;
//
//	public LoginUsuariosBean(){
//		usuario = new Usuarios();
//	}
//	public Usuarios getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuarios usuario) {
//		this.usuario = usuario;
//	}
	
	//action
	public String validarUsuario() throws Exception{
		if (usuario!=null){
			Usuario u = dao.getUsuario(usuario.getNome(), usuario.getSenha());
			if (u!=null){
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("ID_USUARIO", u.getId());
				if (u.getPerfil().isAdministrador()){
					return "/admin/menu";
				}else{
					return "/admin/sorteados";
				}
			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Erro Login: ");
		msg.setDetail("Usuário ou senha inválidos");
		msg.setSeverity(FacesMessage.SEVERITY_FATAL);
		
		context.addMessage(null, msg);
		return "/index";
		
//		UsuariosDao dao = RepositoryDao.getUsuariosDao();
//		if(dao.validar(usuario)){
//			return "/admin/menu";
//		}
//		else {
//			FacesContext context = FacesContext.getCurrentInstance();
//			FacesMessage msg = new FacesMessage();
//			msg.setSummary("Erro Login: ");
//			msg.setDetail("Usuário ou senha inválidos");
//			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
//			
//			context.addMessage(null, msg);
//			return "/index";
//		}
	}
	
	private void start(){
		if (dao.listar().size()==0){
			Usuario admin = new Usuario("admin", "admin", Perfil.ADMINISTRADOR);
			admin.setNome("admin");
			dao.adicionar(admin);
		}
	}
}
