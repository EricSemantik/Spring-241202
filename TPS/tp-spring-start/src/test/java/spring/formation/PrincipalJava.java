package spring.formation;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.Pianiste;
import spring.formation.orchestre.Piano;
import spring.formation.orchestre.Synthe;
import spring.formation.orchestre.Ukulele;

public class PrincipalJava {

	public static void main(String[] args) {
		Guitare guitare = new Guitare();
		
		// Exercice 1 :
		Guitariste guitariste = new Guitariste();	
		guitariste.setInstrument(guitare);
		guitariste.setMorceau("We are a champion");
		
		guitariste.jouer();
		
		// Exercice 2 :
		Guitariste myMusicien = new Guitariste(guitare, "We are a champions");
		
		guitariste.jouer();
		
		// Exercice 3 :
//		Piano piano = new Piano();
//		
//		Synthe synthe = new Synthe();
//		
//		Ukulele ukulele = new Ukulele();
//				
//		Pianiste pianiste = new Pianiste();	
//		pianiste.setInstrument(piano);
//		pianiste.setMorceau("5ème Symphonie");		
//		
//		pianiste.jouer();
	}

}
