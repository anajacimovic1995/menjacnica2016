package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
	private static MenjacnicaGUI glavniProzor;
	private static MenjacnicaInterface menjacnica;
	private static DodajKursGUI dodajKursGUI;
	private static IzvrsiZamenuGUI izvrsiZamenuGUI;
	private static ObrisiKursGUI obrisiKursGUI;
	private static Valuta valuta;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnica = new Menjacnica();
					glavniProzor = new MenjacnicaGUI();
					glavniProzor.setVisible(true);
					glavniProzor.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(glavniProzor.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				osveziGlavniProzor();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static LinkedList<Valuta> vratiKursnuListu() {
		return menjacnica.vratiKursnuListu();
	}
	
	
	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(glavniProzor.getContentPane());
		prozor.setVisible(true);
	}

	public static void prikaziObrisiKursGUI(JTable table) {
		
		MenjacnicaTableModel model = (MenjacnicaTableModel) (table.getModel());
		
		if (table.getSelectedRow() != -1) {
			GUIKontroler.valuta = model.vratiValutu(table.getSelectedRow());
			ObrisiKursGUI prozor = new ObrisiKursGUI();
			prozor.setLocationRelativeTo(glavniProzor.getContentPane());
			prozor.setVisible(true);
		}
	}
	
	public static void prikaziIzvrsiZamenuGUI(JTable table) {
		MenjacnicaTableModel model = (MenjacnicaTableModel) (table.getModel());
		
		if (table.getSelectedRow() != -1) {
			GUIKontroler.valuta = model.vratiValutu(table.getSelectedRow());
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI();
			prozor.setLocationRelativeTo(glavniProzor.getContentPane());
			prozor.setVisible(true);
		}
	}
	
	public static void unesiKurs(String naziv, String skraceniNaziv, int sifra, String prodajniKurs, String kupovniKurs,
			String srednjiKurs) {
		try {
			Valuta valuta = new Valuta();

			// Punjenje podataka o valuti
			valuta.setNaziv(naziv);
			valuta.setSkraceniNaziv(skraceniNaziv);
			valuta.setSifra((Integer) (sifra));
			valuta.setProdajni(Double.parseDouble(prodajniKurs));
			valuta.setKupovni(Double.parseDouble(kupovniKurs));
			valuta.setSrednji(Double.parseDouble(srednjiKurs));

			// Dodavanje valute u kursnu listu
			GUIKontroler.menjacnica.dodajValutu(valuta);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(dodajKursGUI.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void osveziGlavniProzor() {
		// Osvezavanje glavnog prozora
		glavniProzor.prikaziSveValute();
	}
	/*
	 * IzvrsiZamenuGUI
	 */

	public static double izvrsiZamenu(boolean isSelected, String iznos) {
		try {
			return GUIKontroler.menjacnica.izvrsiTransakciju(valuta, isSelected, Double.parseDouble(iznos));

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(izvrsiZamenuGUI.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
		return Double.MIN_VALUE;
	}

	public static Valuta vratiValutu() {
		return GUIKontroler.valuta;
	}

	public static void porukaGreskeBiranjeRedaZaZamenu() {
		JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Izaberite kurs za zamenu!", "Greska",
				JOptionPane.ERROR_MESSAGE);
	}

	/*
	 * ObrisiKursGUI
	 */
	public static void obrisiValutu() {
		try {
			GUIKontroler.menjacnica.obrisiValutu(valuta);

			glavniProzor.prikaziSveValute();
			obrisiKursGUI.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(obrisiKursGUI.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void porukaGreskeBiranjeRedaZaBrisanje() {
		JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Izaberite kurs za brisanje!", "Greska",
				JOptionPane.ERROR_MESSAGE);
	}

}
