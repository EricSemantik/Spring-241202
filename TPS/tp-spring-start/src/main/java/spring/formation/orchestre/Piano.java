package spring.formation.orchestre;

import org.springframework.stereotype.Component;

@Component
public class Piano implements IInstrument {

	@Override
	public String son() {
		return "PLINK PLINK PLINK";
	}

}
