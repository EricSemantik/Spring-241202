package spring.formation.model;

public interface Views {
	public static interface ViewNone {
	}

	public static interface ViewBase {
	}

	public static interface ViewConsultation extends ViewBase {
	}

	public static interface ViewLieu extends ViewBase {
	}

	public static interface ViewLieuDetail extends ViewLieu {
	}
	
	public static interface ViewMotif extends ViewBase {
	}

	public static interface ViewIndividu extends ViewBase {
	}

	public static interface ViewPatient extends ViewIndividu {
	}

	public static interface ViewPraticien extends ViewIndividu {
	}

	public static interface ViewPraticienWithSpecialites extends ViewPraticien {
	}
}
