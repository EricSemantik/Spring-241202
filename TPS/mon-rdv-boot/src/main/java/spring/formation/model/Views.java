package spring.formation.model;

public interface Views {
	public static interface ViewBase {}
	
	public static interface ViewLieu extends ViewBase {}
	
	public static interface ViewLieuDetail extends ViewLieu {}
	
	public static interface ViewIndividu extends ViewBase {}
	
	public static interface ViewPraticien extends ViewIndividu {}
}
