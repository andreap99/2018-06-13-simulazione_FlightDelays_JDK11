package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	
	private FlightDelaysDAO dao;
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<String, Airport> idMap;
	private Airline linea;
	
	public Model() {
		this.dao = new FlightDelaysDAO();
		this.idMap = new HashMap<>();
		for(Airport a : this.dao.loadAllAirports())
			this.idMap.put(a.getId(), a);
	}

	public List<Airline> getAirlines() {
		return this.dao.loadAllAirlines();
	}

	public String creaGrafo(Airline linea) {
		this.linea = linea;
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, idMap.values());
		for(Adiacenza a : this.dao.getAdiacenze(linea, idMap)) {
			Graphs.addEdge(grafo, a.getPartenza(), a.getArrivo(), a.getPeso());
		}
		String output = String.format("Grafo Creato!\n%d vertici e %d archi\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
		output += "Elenco 10 peggiori tratte:\n";
		List<Adiacenza> peggiori = new ArrayList<>();
		for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
			peggiori.add(new Adiacenza(this.grafo.getEdgeSource(d), this.grafo.getEdgeTarget(d), 
					this.grafo.getEdgeWeight(d)));
		}
		Collections.sort(peggiori);
		for(int i=0; i<10; i++) {
			output += peggiori.get(i) + "\n";
		}
		return output;
	}

	public String simula(Integer k, Integer v) {
		
		Simulator sim = new Simulator();
		List<Flight> voli = this.dao.loadAllFlights();
		sim.init(k, v, voli);
		String output = sim.run();
		
		return output;
	}

}
