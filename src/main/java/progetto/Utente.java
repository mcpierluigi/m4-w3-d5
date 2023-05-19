package progetto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue
	private UUID tessera;
	private String nome, cognome;
	private LocalDate dataDiNascita;
	@OneToMany(mappedBy = "utente")
	private Set<Prestito> prestiti;
	
	public Utente(String nome, String cognome, LocalDate dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}
	
	@Override
	public String toString() {
		return "Utente [numeroDiTessera: " + tessera + ", nome: " + nome + ", cognome: " + cognome
				+ ", dataDiNascita: " + dataDiNascita + "]";
	}
}
