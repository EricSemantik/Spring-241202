package spring.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;
import spring.formation.orchestre.Pianiste;
import spring.formation.orchestre.Piano;
import spring.formation.orchestre.Synthe;
import spring.formation.orchestre.Ukulele;

@Configuration
public class ApplicationConfig {

	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IMusicien guitariste(Guitare guitare) {
		return new Guitariste(guitare, "We are a champion");
	}
	
	@Bean
	public IInstrument piano() {
		return new Piano();
	}
	
	@Bean
	public IInstrument synthe() {
		return new Synthe();
	}
	
	@Bean
	public IInstrument ukulele() {
		return new Ukulele();
	}
	
	@Bean
	public IMusicien pianiste(Piano piano) {
		Pianiste pianiste = new Pianiste();
		pianiste.setInstrument(piano);
		pianiste.setMorceau("5ème Symphonie");
		
		return pianiste;
	}
}
