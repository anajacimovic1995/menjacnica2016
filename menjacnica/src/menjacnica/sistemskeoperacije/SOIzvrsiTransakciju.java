package menjacnica.sistemskeoperacije;

import menjacnica.Valuta;

public class SOIzvrsiTransakciju {
	
	public static double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos) {
		if (prodaja)
			return iznos*valuta.getProdajni();
		else
			return iznos*valuta.getKupovni();
	}
}
