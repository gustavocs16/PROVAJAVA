package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import entidade.Carro;
import dao.CarroDAO;
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

public class ControllerTableViewFXML  implements Initializable {

	
	@FXML
	private TableView<Carro> table;
	
	@FXML
	private TableColumn<Carro, Integer> colID;
	
	@FXML
	private TableColumn<Carro, String> colNome;
	
	@FXML
	private TableColumn<Carro, String> colCor;
	
	@FXML
	private TableColumn<Carro, String> colDesc;
	
	@FXML
	private TableColumn<Carro, Integer> colKm;
	
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnPesquisa;
    
    @FXML
    private TextField txPesquisa;
    
    @FXML
    private Button btnAlterar;
    
    @FXML
    private Button btnAtualiza;
	
	private ArrayList<Carro> ListContact = new ArrayList<>();
	private Carro selecionada;
	
	private ObservableList<Carro> carros = FXCollections.observableArrayList();
	
	
	public void deleta() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SAIR");
		String s = "Você tem certeza que deseja excluir esse carro?";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			if(selecionada != null) {
		CarroDAO dao = new CarroDAO();
		dao.excluiCarro(selecionada);
		table.setItems(atualizatabela());
		Alert b = new Alert(AlertType.INFORMATION);
		b.setHeaderText("Carro excluido com sucesso!!!");
		b.show();
	
	}else{
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText("Selecione um usuario");
		a.show();
	}
		}

}
		
	
	@FXML
	void alterar(ActionEvent event) {
		if(selecionada != null) {
		alteraCarro alt = new alteraCarro(selecionada);
		try {
			alt.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Selecione um usuario");
			a.show();
		}
			
		

	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnPesquisa.setOnMouseClicked((MouseEvent e) ->{
			table.setItems(busca());
		});
		
		btnAtualiza.setOnMouseClicked((MouseEvent e) ->{
			table.setItems(atualizatabela());
		});
				
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colKm.setCellValueFactory(new PropertyValueFactory<>("km"));		
		
		popula();
        table.setItems(atualizatabela());
		
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Carro>()
		{
			@Override
			public void changed(ObservableValue<? extends Carro> observable, Carro oldValue, Carro newValue) {
				selecionada = (Carro) newValue ;				
		}
		});
	}
	
	
	private void popula() {
		ListContact = new CarroDAO().listarTodosCarro();
	}
	
	public ObservableList<Carro> atualizatabela(){
		CarroDAO dao = new CarroDAO();
		carros = FXCollections.observableArrayList(dao.listarTodosCarro());
		return carros;
	}
	
	private ObservableList<Carro> busca(){
	ObservableList<Carro> carroPesquisa = FXCollections.observableArrayList();
	for(int x=0; x<carros.size(); x++) {
		if(carros.get(x).getNome().toLowerCase().contains(txPesquisa.getText().toLowerCase())) {
			carroPesquisa.add(carros.get(x));
		}
	}	
	return carroPesquisa;	
	}
	
	public void fechar() {
    	tableView.getStage().close();
    }
	
	
	
}