package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import entidade.Carro;
import entidade.Concessionaria;
import dao.CarroDAO;
import dao.ConcessionariaDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.Main;
import ui.alteraCarro;
import ui.tableView;
import ui.viewConcessionaria;

public class ControllerViewConcessionariaFXML  implements Initializable {

	
	@FXML
	private TableView<Concessionaria> table;
	
	@FXML
	private TableColumn<Concessionaria, Integer> colID;
	
	@FXML
	private TableColumn<Concessionaria, String> colNome;
	
	@FXML
	private TableColumn<Concessionaria, String> colLocalidade;
	
    @FXML
    private Button btnDeletar;
    
    
    @FXML
    private Button btnAtualiza;
	
	private ArrayList<Concessionaria> ListContact = new ArrayList<>();
	private Concessionaria selecionada;
	
	private ObservableList<Concessionaria> Concessionarias = FXCollections.observableArrayList();
	
	
	public void deleta() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SAIR");
		String s = "Você tem certeza que deseja excluir essa concessionaria?";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			if(selecionada != null) {
		ConcessionariaDAO dao = new ConcessionariaDAO();
		dao.excluiConcessionaria(selecionada);
		table.setItems(atualizatabela());
		Alert b = new Alert(AlertType.INFORMATION);
		b.setHeaderText("Concessionaria excluido com sucesso!!!");
		b.show();
	
	}else{
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText("Selecione uma concessionaria");
		a.show();
	}
		}

}
		

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		
		btnAtualiza.setOnMouseClicked((MouseEvent e) ->{
			table.setItems(atualizatabela());
		});
				
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colLocalidade.setCellValueFactory(new PropertyValueFactory<>("localidade"));

		
		popula();
        table.setItems(atualizatabela());
		
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Concessionaria>()
		{
			@Override
			public void changed(ObservableValue<? extends Concessionaria> observable, Concessionaria oldValue, Concessionaria newValue) {
				selecionada = (Concessionaria) newValue ;				
		}
		});
	}
	
	
	private void popula() {
		ListContact = new ConcessionariaDAO().listarTodasConcessionaria();
	}
	
	public ObservableList<Concessionaria> atualizatabela(){
		ConcessionariaDAO dao = new ConcessionariaDAO();
		Concessionarias = FXCollections.observableArrayList(dao.listarTodasConcessionaria());
		return Concessionarias;
	}
	

	
	public void fechar() {
    	viewConcessionaria.getStage().close();
    }
	
	
	
}