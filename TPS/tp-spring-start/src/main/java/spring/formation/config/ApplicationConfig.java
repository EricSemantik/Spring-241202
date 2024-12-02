package spring.formation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("spring.formation.orchestre")
public class ApplicationConfig {

//	@Bean
//	public IInstrument guitare() {
//		return new Guitare();
//	}
//	
//	@Bean
//	public IMusicien guitariste(Guitare guitare) {
//		return new Guitariste(guitare, "We are a champion");
//	}
//	
//	@Bean
//	public IInstrument piano() {
//		return new Piano();
//	}
//	
//	@Bean
//	public IInstrument synthe() {
//		return new Synthe();
//	}
//	
//	@Bean
//	public IInstrument ukulele() {
//		return new Ukulele();
//	}
//	
//	@Bean
//	public IMusicien pianiste(Synthe synthe) {
//		Pianiste pianiste = new Pianiste();
//		pianiste.setInstrument(synthe);
//		pianiste.setMorceau("5ème Symphonie");
//		
//		return pianiste;
//	}
}
