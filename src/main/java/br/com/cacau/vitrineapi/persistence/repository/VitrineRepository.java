package br.com.cacau.vitrineapi.persistence.repository;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cacau.vitrineapi.persistence.model.ProdutoVitrine;
import br.com.cacau.vitrineapi.persistence.model.Vitrine;

@Repository
@ManagedBean
public class VitrineRepository extends AbstractJpaDAO<Vitrine>{

	public VitrineRepository() {
		setClazz(Vitrine.class);
	}

	@Transactional(readOnly = true)
	public List<Vitrine> findAll() {
		return super.findAll();
	}

	@Transactional
	public void create(Vitrine entity) {
		super.create(entity);
	}
	
	@Transactional
	public void deletebyId(long entityId) {
		super.deletebyId(entityId);
	}
	
	@Transactional
	public void delete(Vitrine entity) {
		super.delete(entity);
	}
	
	@Transactional
	public Vitrine update(Vitrine entity) {
		return super.update(entity);
	}

	public List<ProdutoVitrine> getProdutos(Long id_vitrine) {
			
		String queryJoin = "SELECT p FROM ProdutoVitrine p WHERE p.id_vitrine = :id_vitrine";
		
		TypedQuery<ProdutoVitrine> estoque = getEntityManager().createQuery(queryJoin, ProdutoVitrine.class);
		estoque.setParameter("id_vitrine", id_vitrine);

		List<ProdutoVitrine> resultado = estoque.getResultList();
		
		return resultado;
	}

}
