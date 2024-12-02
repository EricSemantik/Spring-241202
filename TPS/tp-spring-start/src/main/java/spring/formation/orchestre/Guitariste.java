package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {

	private IInstrument instrument = null;
	private String morceau;

	public Guitariste() {
		super();
	}

	public Guitariste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	@Autowired
	public Guitariste(@Qualifier("guitare") IInstrument instrument, @Value("We are a champion") String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le guitariste joue : " + this.morceau + "(" + this.instrument.son() + ")");
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

	public void commeVousVoulez() {
		this.morceau = this.morceau.toUpperCase();
		System.out.println("J'ai créé un guitariste");
	}

}
