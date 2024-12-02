package spring.formation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//@Component
@Aspect
public class RegisseurAspect {

	@Before("execution(* spring.formation.orchestre.*.jouer())")
	public void accorder(JoinPoint joinPoint) {
		System.out.println("Le régisseur accorde l'instrument ");
	}

	@AfterReturning(pointcut = "execution(String spring.formation.orchestre.IInstrument+.son())", returning = "result")
	public void arrangerSon(String result) {
		System.out.println("autotune : " + result.toLowerCase());
	}

	@AfterThrowing(pointcut = "execution(* spring.formation.orchestre.*.jouer())", throwing = "ex")
	public void reparer(Exception ex) {
		System.out.println("Le régisseur est dans le pétrin : " + ex.getMessage());
	}
}
