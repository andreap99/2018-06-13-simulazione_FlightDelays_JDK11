package it.polito.tdp.flightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Airline> cmbBoxLineaAerea;

    @FXML
    private Button caricaVoliBtn;

    @FXML
    private TextField numeroPasseggeriTxtInput;

    @FXML
    private TextField numeroVoliTxtInput;

    @FXML
    void doCaricaVoli(ActionEvent event) {
    	
    	Airline linea = this.cmbBoxLineaAerea.getValue();
    	if(linea == null) {
    		this.txtResult.appendText("Scegliere una linea aerea!\n");
    		return;
    	}
    	this.txtResult.appendText(this.model.creaGrafo(linea));
    	

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	this.txtResult.clear();
    	Integer k;
    	Integer v;
    	try {
    		k = Integer.parseInt(this.numeroPasseggeriTxtInput.getText());
    		v = Integer.parseInt(this.numeroVoliTxtInput.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire numero intero!!!\n");
    		return;
    	}
    	this.txtResult.appendText(this.model.simula(k,v));

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert cmbBoxLineaAerea != null : "fx:id=\"cmbBoxLineaAerea\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert caricaVoliBtn != null : "fx:id=\"caricaVoliBtn\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroPasseggeriTxtInput != null : "fx:id=\"numeroPasseggeriTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";
        assert numeroVoliTxtInput != null : "fx:id=\"numeroVoliTxtInput\" was not injected: check your FXML file 'FlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.cmbBoxLineaAerea.getItems().addAll(this.model.getAirlines());
	}
}
