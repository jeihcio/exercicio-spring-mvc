package br.com.alura.mvc.mudi.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import br.com.alura.mvc.mudi.model.Pedido;

@Repository
public class PedidosRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Pedido> recuperaTodosOsPedidos(){
		Query query = entityManager.createQuery("Select p From Pedido p", Pedido.class);
		return query.getResultList();
	}
}
