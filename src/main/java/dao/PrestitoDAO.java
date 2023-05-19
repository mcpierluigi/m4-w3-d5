package dao;

import java.util.*;
import javax.persistence.*;
import progetto.Prestito;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrestitoDAO {
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void savePrestito(Prestito prestito) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(prestito);
		transaction.commit();
		log.info("prestito salvato");

	}

	public Prestito getById(UUID id) {
		Prestito prestitoTrovato = em.find(Prestito.class, id);
		return prestitoTrovato;
	}
}
