package progetto;

import javax.persistence.*;
import lombok.*;

@Entity
@NamedQuery(name = "findByAuthor", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
@Getter
@Setter
@NoArgsConstructor
public class Libro extends Elemento {
	String autore, genere;
	
	public Libro(String titolo, Integer anno, Integer pagine, String autore, String genere) {
		super(titolo, anno, pagine);
		this.autore = autore;
		this.genere = genere;
	}
	
	@Override
	public String toString() {
		return "Libro [autore: " + autore + ", genere: " + genere + ", titolo: " + getTitolo() + "]";
	}
}
