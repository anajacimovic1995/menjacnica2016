package menjacnica.sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SOObrisiValutu {
	
	public static void obrisiValutu(Valuta valuta, LinkedList<Valuta> kursnaLista) {
		if (!kursnaLista.contains(valuta))
			throw new RuntimeException("Valuta ne postoji u kursnoj listi");
		
		kursnaLista.remove(valuta);
	}
}
