package spring.formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;
import spring.formation.orchestre.Pianiste;

public class PrincipalSpringJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);
		
		guitariste.jouer();
		
		IMusicien pianiste = context.getBean("pianiste", IMusicien.class);
		
		
		pianiste.jouer();
		
		context.close();		
	}
}
