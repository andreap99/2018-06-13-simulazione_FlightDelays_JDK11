package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;
import it.polito.tdp.flightdelays.model.Event.EventType;

public class Simulator {
	
	//PARAMETRI DELLA SIMULAZIONE
	private int K;
	private int V;
	
	//OUTPUT DA CALCOLARE
	
	//CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	//STATO DEL MONDO
	private List<Passeggero> passeggeri;
	private PriorityQueue<Flight> voli;
	private FlightDelaysDAO dao;
	
	//INIZIALIZZAZIONE
	public void init(Integer K, Integer V, List<Flight> voli) {
		this.queue = new PriorityQueue<Event>();
		this.voli = new PriorityQueue<>(voli);
		this.K = K;
		this.V = V;
		this.dao = new FlightDelaysDAO();
		this.passeggeri = new ArrayList<>();
		for(int i=0; i<K; i++) {
			Passeggero p = new Passeggero(i, true, posCasuale());
			this.passeggeri.add(p);
		}
		for(Flight f : voli) {
			this.queue.add(new Event(null, f, EventType.PARTENZA, f.getScheduledDepartureDate()));
		}
		
	}

	private String posCasuale() {
		Double x = Math.random();
		int n = (int) (x*this.dao.loadAllAirports().size());
		return this.dao.loadAllAirports().get(n).getId();
	}

	public String run() {
		
		while(!this.queue.isEmpty()) {
			
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
			
		}
		String output = "Simulazione:\n";
		for(Passeggero h : passeggeri) {
			output += h.toString() + "\n";
		}
		return output;
	}
	
	public void processEvent(Event e) {
		Flight f = e.getVolo();
		switch(e.getType()) {
		
		case PARTENZA:
			for(Passeggero p : this.passeggeri) {
				if(p.isLibero() && p.getPosizione().compareTo(f.getOriginAirportId())==0 && p.getVoliPresi()<V) {
					p.setLibero(false);
					this.queue.add(new Event(p, f, EventType.ARRIVO, f.getArrivalDate()));
					p.addVoliPresi();
				}
			}
			break;
			
		case ARRIVO:
			Passeggero p = e.getPasseggero();
			p.setLibero(true);
			p.addRitardo(f.getArrivalDelay());
			break;
		
		}
		
	}

}
