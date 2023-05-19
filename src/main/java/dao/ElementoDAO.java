package dao;

import java.util.*;
import javax.persistence.*;

import progetto.Elemento;
import progetto.Libro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementoDAO {
	public final EntityManager em;
	public ElementoDAO (EntityManager em) {
		this.em = em;
	}
	
	public void saveElemento(Elemento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("elemento salvato");
	}
	
	public void deleteByISBN(UUID id) {
		Elemento e = em.find(Elemento.class, id);
		if (e != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(e);
			transaction.commit();
			log.info("elemento con id " + id + " eliminato");
		} else {
			log.info("elemento con id " + id + " non trovato");
		}
	}
	
	public Elemento getByISBN(UUID id) {
		Elemento e = em.find(Elemento.class, id);
		return e ;
	}
	
	public List<Elemento> getByYear(int year) {
		TypedQuery<Elemento> query = em.createNamedQuery("findByYear", Elemento.class);
		query.setParameter("anno", year);
		return query.getResultList();
	}
	
	
	public List<Libro> getByAuthor(String autore) {
		TypedQuery<Libro> query = em.createNamedQuery("findByAuthor", Libro.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

	public List<Elemento> getByTitle(String titolo) {
		TypedQuery<Elemento> query = em.createNamedQuery("findByTitle", Elemento.class);
		query.setParameter("titolo", titolo);
		return query.getResultList();
	}
}
