package progetto;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "prestiti")
@Getter
@Setter
@NoArgsConstructor
public class Prestito {
	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate dataInizio, dataConsegna;
	private LocalDate dataFine;
	
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Elemento elementoInPrestito;
	
	public Prestito (LocalDate inizio, LocalDate consegna, Utente utente, Elemento elemento) {
		this.dataInizio = inizio;
		this.dataFine = inizio.plusDays(30);
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
