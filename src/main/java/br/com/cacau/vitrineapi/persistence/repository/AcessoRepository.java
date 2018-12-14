package br.com.cacau.vitrineapi.persistence.repository;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cacau.vitrineapi.persistence.model.Acesso;

@Repository
@ManagedBean
public class AcessoRepository extends AbstractJpaDAO<Acesso>{

	public AcessoRepository() {
		setClazz(Acesso.class);
	}

	public Acesso login(String email, String senha) {
		
		String query = "select c from Acesso c where c.email = :email and c.senha = :senha";
		TypedQuery<Acesso> acessoquery = getEntityManager().createQuery(query, Acesso.class);
		acessoquery.setParameter("email", email);
		acessoquery.setParameter("senha", senha);
		Acesso acesso = acessoquery.getSingleResult();
		
		if(!acesso.getEmail().isEmpty()) {
			acesso.setLogavel(true);
			return acesso;
		}else {
			acesso.setLogavel(false);
			return acesso;
		}
	}

	@Transactional
	public List<Acesso> findAll() {
		return super.findAll();
	}

	@Transactional
	public void create(Acesso entity) {
		super.create(entity);
	}

	@Transactional
	public void deletebyId(long entityId) {
		super.deletebyId(entityId);
	}

	@Transactional
	public void delete(Acesso entity) {
		super.delete(entity);
	}

	@Transactional
	public Acesso update(Acesso entity) {
		return super.update(entity);
	}
}
