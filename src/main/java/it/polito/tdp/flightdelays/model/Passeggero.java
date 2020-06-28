package it.polito.tdp.flightdelays.model;

public class Passeggero {
	
	private int id;
	private boolean libero;
	private String posizione;
	private int ritardoTot;
	private int voliPresi;
	
	public Passeggero(int id, boolean libero, String string) {
		super();
		this.id = id;
		this.libero = libero;
		this.posizione = string;
		this.ritardoTot = 0;
		this.voliPresi = 0;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	public int getId() {
		return id;
	}

	public boolean isLibero() {
		return libero;
	}

	public String getPosizione() {
		return posizione;
	}

	public int getRitardoTot() {
		return ritardoTot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passeggero other = (Passeggero) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Passeggero " + id + "   ritardo accumulato=" + ritardoTot;
	}

	public int getVoliPresi() {
		return voliPresi;
	}

	public void addVoliPresi() {
		this.voliPresi ++;
	}
	
	public void addRitardo(int rit) {
		this.ritardoTot += rit;
	}

	public void setPosizione(String origin) {
		this.posizione = origin;
	}

}
