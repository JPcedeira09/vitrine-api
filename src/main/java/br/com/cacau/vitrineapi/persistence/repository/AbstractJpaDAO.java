package br.com.cacau.vitrineapi.persistence.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDAO <T extends Serializable>{

	private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
	public final void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T findOne(long id) {
		return getEntityManager().find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("from " + clazz.getName())
				.getResultList();
	}

	public void create(T entity) {
		EntityManager manager = getEntityManager();
		manager.persist(entity);
	}
	
	public T update(T entity) {
		EntityManager manager = getEntityManager();
		T merged = manager.merge(entity);
		return merged;
	}

	public void delete(T entity) {
		EntityManager manager = getEntityManager();
		manager.remove(entity);
	}

	public void deletebyId(long entityId) {
		delete(findOne(entityId));
	}
}