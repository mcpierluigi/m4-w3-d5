package progetto;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends Elemento {
	@Column(name = "periodicità")
	@Enumerated(EnumType.STRING)
	private Periodicità periodicità;
	
	public Rivista(String titolo, Integer anno, Integer pagine, Periodicità periodo) {
		super(titolo, anno, pagine);
		this.periodicità = periodo;
	}

	@Override
	public String toString() {
		return "Rivista [periodicità " + periodicità + ", titolo: " + getTitolo() + ",anno: "
				+ getAnnoPubblicazione() + "]";
	}
}
