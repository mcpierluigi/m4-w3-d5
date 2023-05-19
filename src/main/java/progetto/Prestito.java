package progetto;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.*;
import lombok.*;

@Entity
@NamedQuery(name = "findPrestitiByIdUtente", query = "SELECT p FROM Prestito p WHERE p.utente.tessera = :id AND p.dataConsegna IS NULL")
@NamedQuery(name = "findPrestitiScaduti", query = "SELECT p FROM Prestito p WHERE p.dataConsegna > p.dataFine OR p.dataConsegna IS NULL")
@Table(name = "prestiti")
@Getter
@Setter
@NoArgsConstructor
public class Prestito {
	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate dataInizio, dataConsegna, dataFine;
	
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Elemento elementoInPrestito;
	
	public Prestito (LocalDate inizio, LocalDate consegna, Utente utente, Elemento elemento) {
		this.dataInizio = inizio;
		this.dataFine = inizio.plusMonths(1);
		this.dataConsegna = consegna;
		this.utente = utente;
		this.elementoInPrestito = elemento;
	}
	
	@Override
	public String toString() {
		return "Prestito [id: " + id + ", utente: " + utente + ", elemento prestato : " + elementoInPrestito
				+ ", data inizio prestito: " + dataInizio + ", data fine prestito: "
				+ dataFine + ", data consegna effettiva: " + dataConsegna + "]";
	}

}
