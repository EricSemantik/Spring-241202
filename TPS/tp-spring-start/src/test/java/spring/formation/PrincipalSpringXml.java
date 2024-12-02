package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;

public class PrincipalSpringXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		
		IMusicien guitariste = context.getBean(IMusicien.class);
		
		guitariste.jouer();
		
		IMusicien pianiste = context.getBean(IMusicien.class);
		
		pianiste.jouer();
		
		context.close();		
	}
}
