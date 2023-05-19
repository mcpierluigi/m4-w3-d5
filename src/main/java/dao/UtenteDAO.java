package dao;

import java.util.*;
import javax.persistence.*;
import progetto.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDAO {
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void saveUtente(Utente u) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(u);
		transaction.commit();
		log.info("Utente salvato!");
	}

	public Utente findById(UUID id) {
		Utente utenteTrovato = em.find(Utente.class, id);
		return utenteTrovato;
	}
}
