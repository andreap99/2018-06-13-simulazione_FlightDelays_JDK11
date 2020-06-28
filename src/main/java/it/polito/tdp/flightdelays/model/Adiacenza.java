package it.polito.tdp.flightdelays.model;

public class Adiacenza implements Comparable<Adiacenza>{
	
	private Airport partenza;
	private Airport arrivo;
	private Double peso;
	
	public Adiacenza(Airport partenza, Airport arrivo, Double peso) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.peso = peso;
	}


	public Airport getPartenza() {
		return partenza;
	}

	public Airport getArrivo() {
		return arrivo;
	}

	public Double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "partenza=" + partenza + ", arrivo=" + arrivo + ", peso="+ peso;
	}

	@Override
	public int compareTo(Adiacenza other) {
		return -this.peso.compareTo(other.peso);
	}
	
	

}
