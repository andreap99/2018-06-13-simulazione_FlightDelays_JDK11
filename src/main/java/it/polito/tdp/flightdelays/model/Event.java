package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		PARTENZA, ARRIVO
	}
	
	private Passeggero passeggero;
	private Flight volo;
	private EventType type;
	private LocalDateTime time;
	
	public Event(Passeggero pass, Flight volo, EventType type, LocalDateTime time) {
		super();
		this.passeggero = pass;
		this.volo = volo;
		this.type = type;
		this.time = time;
	}

	public Passeggero getPasseggero() {
		return passeggero;
	}

	public Flight getVolo() {
		return volo;
	}

	public EventType getType() {
		return type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Event [passeggero=" + passeggero + ", volo=" + volo + ", type=" + type + ", time=" + time + "]";
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time);
	}
	
	
	
	
}
