package menjacnica.gui.models;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import menjacnica.Valuta;

public class MenjacnicaTableModel extends AbstractTableModel {

	private final String[] kolone = new String[] { "Sifra", "Skr.naziv", "Naziv", "Kupovni", "Srednji", "Prodajni" };
	private List<Valuta> valute = new LinkedList<Valuta> ();

	public MenjacnicaTableModel(List<Valuta> valute) {
		if(valute==null){
			this.valute = new LinkedList<Valuta>();
			return;
		}
		this.valute = valute;
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		return valute.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Valuta v = valute.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return v.getSifra();
		case 1:
			return v.getSkraceniNaziv();
		case 2:
			return v.getNaziv();
		case 3:
			return v.getKupovni();
		case 4:
			return v.getSrednji();
		case 5:
			return v.getProdajni();
		default:
			return "NN";
		}
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	public void staviSveValuteUModel(List<Valuta> valute) {
		this.valute = valute;
		fireTableDataChanged();
	}

	public Valuta vratiValutu(int index) {
		return valute.get(index);
	}

}
