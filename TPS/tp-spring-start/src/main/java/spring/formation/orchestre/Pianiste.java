package spring.formation.orchestre;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {

	private IInstrument instrument;
	private String morceau;

	public Pianiste() {
		super();
	}

	public Pianiste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	public Pianiste(IInstrument instrument, String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le pianiste joue : " + this.morceau + "(" + this.instrument.son() + ")");
//		throw new RuntimeException("Fausse note");
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	@Autowired
	@Qualifier("piano")
	public void setInstrument(IInstrument instrument) {
		System.out.println("setInstrument");
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	@Value("${musique.instrument.pianiste.morceau}")
	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}
	
	@PostConstruct
	public void apresCreationBean() {
		System.out.println("Et voilà après l'injection de l'instrument");
	}

}
