package br.fiap.javaWeb.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Dao<T> {
	
	void adicionar(T entidade);
	
	void atualizar(T entidade);
	
	void merge(T entidade);

	List<T> listar();

	T buscar(Integer id);
	
	Collection<T> getCollection(String query, Map<String,Object> parametros);
	
	List<T> getList(String query, Map<String,Object> parametros);
}
