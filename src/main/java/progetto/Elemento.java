package progetto;

import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "elementi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "findByYear", query = "SELECT e FROM Elemento e WHERE e.annoPubblicazione = :anno")
@NamedQuery(name = "findByTitle", query = "SELECT e FROM Elemento e WHERE e.titolo LIKE CONCAT('%', :titolo, '%')")
@Getter
@Setter
@NoArgsConstructor
public abstract class Elemento {
	@Id
	@GeneratedValue
	private UUID codiceISBN;
	private String titolo;
	private Integer annoPubblicazione, numeroPagine;
	@OneToMany(mappedBy = "elementoInPrestito", cascade = CascadeType.ALL)
	private Set<Prestito> elementiPrestati;
	
	public Elemento (String titolo, Integer anno, Integer pagine) {
		this.titolo = titolo;
		this.annoPubblicazione = anno;
		this.numeroPagine = pagine;
	}
	
	@Override
	public abstract String toString();
}
