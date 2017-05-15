package br.fiap.javaWeb.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.fiap.javaWeb.entity.Usuario;

public class GenericDao<T> implements Dao<T> {

	private final Class<T> classe;
	private Session session = null;
	private Transaction transaction = null;

//	public GenericDao() {
//		this.classe = getDelegateClass();
//	}
	public GenericDao(Class<T> classe) {
		this.classe = classe;
	}

//	protected Class<T> getDelegateClass() {
//		return getGenericTypeArgument(this.getClass(), 2);
////		if (this.classe == null) {
////			this.classe = getGenericTypeArgument(this.getClass(), 2);
////		}
////
////		return this.classe;
//	}
//	@SuppressWarnings("unchecked")
//	public <T> Class<T> getGenericTypeArgument(final Class<?> clazz, final int idx) {
//		final Type type = clazz.getGenericSuperclass();
//
//		ParameterizedType paramType;
//		try {
//			paramType = (ParameterizedType) type;
//		} catch (ClassCastException cause) {
//			paramType = (ParameterizedType) ((Class<T>) type).getGenericSuperclass();
//		}
//
//		return (Class<T>) paramType.getActualTypeArguments()[idx];
//	}

	@Override
	public void adicionar(T entidade) {
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(entidade);
    		transaction.commit();
    		
//    		return "Livro salvo";
    	}catch(Exception e){
    		e.printStackTrace();
    	}	
		if (session.isOpen()){
			session.close();
		}
	}

	@Override
	public void atualizar(T entidade) {
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.update(entidade);
    		transaction.commit();
    		
//    		return "Livro salvo";
    	}catch(Exception e){
    		e.printStackTrace();
//    		return e.getMessage();
    	}	
		if (session.isOpen()){
			session.close();
		}
	}

	@Override
	public void merge(T entidade) {
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.merge(entidade);
    		transaction.commit();
    		
//    		return "Livro salvo";
    	}catch(Exception e){
    		e.printStackTrace();
    	}	
		if (session.isOpen()){
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		List<T> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery("FROM "+classe.getSimpleName());
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
			e.printStackTrace();
		}    	
		if (session.isOpen()){
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String query, Map<String, Object> parametros){
		List<T> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery(query);
    		
    		for (String key : parametros.keySet()) {
				q.setParameter(key, parametros.get(key));
			}
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
			e.printStackTrace();
		}    		
		if (session.isOpen()){
			session.close();
		}
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<T> getList2(String query, Map<String, Collection> parametros){
		List<T> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery(query);
    		
    		for (String key : parametros.keySet()) {
				q.setParameterList(key, parametros.get(key));
			}
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
			e.printStackTrace();
		}    		
		if (session.isOpen()){
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getCollection(String query, Map<String, Object> parametros){
		Collection<T> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery(query);
    		
    		for (String key : parametros.keySet()) {
				q.setParameter(key, parametros.get(key));
			}
    		lista = q.list();
    		transaction.commit();
		} catch (Exception e) {		
			e.printStackTrace();
		}    		
		if (session.isOpen()){
			session.close();
		}
		return lista;
		
	}

	@Override
	public T buscar(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("from "+classe.getSimpleName()+" b where b.id=:id");
		
		Map<String, Object> qp = newQueryParametros();
		qp.put("id", id);
		
		List<T> list = getList(sb.toString(), qp);
		
		if (list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public T load(Integer id) {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	T t = (T)session.load(classe, id);
    	transaction.commit();

    	return t;
	}
	
	public Session getSession() {
		if (session==null){
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		if (transaction==null){
			if (session==null){
				session = HibernateUtil.getSessionFactory().getCurrentSession();
			}
			transaction = session.beginTransaction();
		}
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public static Map<String, Object> newQueryParametros(){
		return new HashMap<String, Object>();
	}
	
	public static Map<String, Collection> newQueryParametros2(){
		return new HashMap<String, Collection>();
	}
}
