package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;
import spring.formation.orchestre.Pianiste;

public class PrincipalSpringXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);
		
		guitariste.jouer();
		
		IMusicien pianiste = context.getBean(Pianiste.class);
		
		pianiste.jouer();
		
		context.close();		
	}
}
