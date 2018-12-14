package br.com.cacau.vitrineapi.persistence.repository;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cacau.vitrineapi.persistence.model.ProdutoVitrine;

@Repository
@ManagedBean
public class ProdutoRepository extends AbstractJpaDAO<ProdutoVitrine>{

	public ProdutoRepository() {
		setClazz(ProdutoVitrine.class);
	}

	public List<ProdutoVitrine> findTipoProduto(String tipo_produto) {
		String query = "select c from ProdutoVitrine c where c.tipo_produto = :tipo_produto";
		TypedQuery<ProdutoVitrine> estoque = getEntityManager().createQuery(query, ProdutoVitrine.class);
		estoque.setParameter("tipo_produto", tipo_produto);
		List<ProdutoVitrine> resultado = estoque.getResultList();
		return resultado;
	}

	@Transactional(readOnly = true)
	public List<ProdutoVitrine> findAll() {
		return super.findAll();
	}

	@Transactional
	public void create(ProdutoVitrine entity) {
		super.create(entity);
	}
	
	@Transactional
	public void deletebyId(long entityId) {
		super.deletebyId(entityId);
	}
	
	@Transactional
	public void delete(ProdutoVitrine entity) {
		super.delete(entity);
	}
	
	@Transactional
	public ProdutoVitrine update(ProdutoVitrine entity) {
		return super.update(entity);
	}
}
