package menjacnica;

import java.util.LinkedList;

import menjacnica.sistemskeoperacije.SODodajValutu;
import menjacnica.sistemskeoperacije.SOIzvrsiTransakciju;
import menjacnica.sistemskeoperacije.SOObrisiValutu;
import menjacnica.sistemskeoperacije.SOSacuvajUFajl;
import menjacnica.sistemskeoperacije.SOUcitajIzFajla;

public class Menjacnica implements MenjacnicaInterface{
	
	private LinkedList<Valuta> kursnaLista = new LinkedList<Valuta>();

	@Override
	public void dodajValutu(Valuta valuta) {
		SODodajValutu.dodajValutu(valuta, kursnaLista);
	}

	@Override
	public void obrisiValutu(Valuta valuta) {
		SOObrisiValutu.obrisiValutu(valuta, kursnaLista);
	}

	@Override
	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos) {
		
		return SOIzvrsiTransakciju.izvrsiTransakciju(valuta, prodaja, iznos);
	}

	@Override
	public LinkedList<Valuta> vratiKursnuListu() {
		return kursnaLista;
	}

	@Override
	public void ucitajIzFajla(String putanja) {
		SOUcitajIzFajla.ucitajIzFajla(putanja, kursnaLista);
	}

	@Override
	public void sacuvajUFajl(String putanja) {
		SOSacuvajUFajl.sacuvajUFajl(putanja, kursnaLista);
	}

	
}
