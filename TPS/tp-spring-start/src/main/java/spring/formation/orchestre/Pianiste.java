package spring.formation.orchestre;

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
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	@Autowired
	@Qualifier("piano")
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

	@Value("5ème Symphonie")
	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

}
