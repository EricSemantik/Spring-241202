package spring.formation.aspect;

public class SpectateurAspect {
	// avant que les musiciens jouent
	public void assoir() {
		System.out.println("Les spectateurs s'assoient");
	}
	
	// après si tout va bien
	public void applaudir() {
		System.out.println("Les spectateurs applaudissent");
	}
	
	// après si mauvaise prestation
	public void rembourser() {
		System.out.println("BOUH ! Remboursez-nous !!!");
	}
}
