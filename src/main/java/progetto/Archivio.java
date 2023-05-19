package progetto;
import java.util.*;
import java.time.LocalDate;
import javax.persistence.*;
import dao.*;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Archivio {
	
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();

		ElementoDAO ed = new ElementoDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		PrestitoDAO pd = new PrestitoDAO(em);
		
		//Book and Magazine
		Elemento libro1 = new Libro("Maze Runner", 2012, 346, "Dashmer", "Romanzo" );
		Elemento libro2 = new Libro("Harry Potter", 1997, 248, "Rowling", "Romanzo" );
		Elemento libro3 = new Libro("Il Signore degli Anelli", 1970, 128, "Tolkien", "Romanzo" );
		
		Elemento rivista1 = new Rivista("Focus" , 2023, 50, Periodicità.MENSILE);
		Elemento rivista2 = new Rivista("National Geographic" , 2023, 50, Periodicità.SEMESTRALE);
		Elemento rivista3 = new Rivista("Vogue" , 2023, 50, Periodicità.SETTIMANALE);
		
		Utente pier = new Utente("Pierluigi", "Monzon", LocalDate.of(1997, 1, 23));
		Utente nico = new Utente("Nicolò", "Pizzingrilli", LocalDate.of(1998, 3, 9));
		Utente anna = new Utente("Annamaria", "Vitellone", LocalDate.of(1960, 8, 16));
		Utente samu = new Utente("Samuele", "Pizzingrilli", LocalDate.of(1995, 11, 28));
		
		Prestito prestito1 = new Prestito(LocalDate.of(2023, 1, 23), LocalDate.of(2023, 2, 10), pier, libro1);
		Prestito prestito2 = new Prestito(LocalDate.of(2023, 2, 11), LocalDate.of(2023, 2, 20), nico, rivista1);
		Prestito prestito3 = new Prestito(LocalDate.of(2023, 3, 20), LocalDate.of(2023, 5, 20), anna, libro2);
		Prestito prestito4 = new Prestito(LocalDate.of(2023, 5, 20), null, samu, rivista2);
		//"34eeb279-cf11-4350-8352-ff322fc5c7d4"
		
		//Tests
/*
		ed.saveElemento(libro1);
		ed.saveElemento(libro2);
		ed.saveElemento(libro3);
		ed.saveElemento(rivista1);
		ed.saveElemento(rivista2);
		ed.saveElemento(rivista3);
		ed.deleteByISBN(UUID.fromString("4339bba6-5027-414a-aed5-0e4eb1a53f38"));
		
		ud.saveUtente(pier);
		ud.saveUtente(nico);
		ud.saveUtente(anna);
		ud.saveUtente(samu);
		
		pd.savePrestito(prestito1);
		pd.savePrestito(prestito2);
		pd.savePrestito(prestito3);
		pd.savePrestito(prestito4);
*/
		log.info("Elemento trovato per codice ISBN:");
		Elemento trovatoByISBN = ed.getByISBN(UUID.fromString("054b4773-c0dd-4684-899f-8492267feb09"));
		log.info(trovatoByISBN.toString());
		
		log.info("Elementi trovati per anno:");
		List<Elemento> trovatiByYear = ed.getByYear(2023);
		if(trovatiByYear.size() > 0) {
			trovatiByYear.stream().forEach(e -> log.info(e.toString()));
		}
		
		log.info("Elemento trovato per autore:");
		List<Libro> trovatiByAuthor = ed.getByAuthor("Dashmer");
		if(trovatiByAuthor.size() > 0) {
			trovatiByAuthor.stream().forEach(e -> log.info(e.toString()));
		}
		
		log.info("Elementi trovati per titolo/parte del titolo:");
		List<Elemento> trovatiByTitle = ed.getByTitle("Geographic");
		if(trovatiByTitle.size() > 0) {
			trovatiByTitle.stream().forEach(e -> log.info(e.toString()));
		}
		
		log.info("Prestiti trovati per utente e ancora da consegnare:");
		List<Prestito> trovatiByIdUtente = pd.findPrestitiByIdUtente(UUID.fromString("34eeb279-cf11-4350-8352-ff322fc5c7d4"));
		if (trovatiByIdUtente.size() > 0) {
			trovatiByIdUtente.stream().forEach(c -> log.info(c.toString()));
		}
		
		log.info("Prestiti scaduti:");
		List<Prestito> prestitiScaduti = pd.findPrestitiScaduti();
		if (prestitiScaduti.size() > 0) {
			prestitiScaduti.stream().forEach(c -> log.info("questo prestito è scaduto: " + c.toString()));
		} 
	}
}
