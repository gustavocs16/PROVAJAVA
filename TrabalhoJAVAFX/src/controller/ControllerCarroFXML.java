package controller;


import java.util.Optional;


import dao.CarroDAO;
import entidade.Carro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ui.cadastraConcessionaria;
import ui.tableView;



public class ControllerCarroFXML {



	@FXML
    private TextField tfKm;

    @FXML
    private TextField tfCor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

    @FXML
	void inserir(ActionEvent event) {
    	
    	if(!tfNome.getText().equals("") && !tfDescricao.getText().equals("") && !tfCor.getText().equals("") ) {
    		try {
    		Carro umCarro = new Carro();
    		umCarro.setNome(tfNome.getText());
    		umCarro.setDescricao(tfDescricao.getText());
    		umCarro.setCor(tfCor.getText());
    		umCarro.setKm(Integer.parseInt(tfKm.getText()));
    		new CarroDAO().inserirCarro(umCarro);
    		
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setHeaderText("Carro cadastrado com sucesso!!!");
    		a.show();
    		} catch (Exception ex) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText("Verifique os campos!!!");
				a.show();
			}
        	}else {
        		Alert a = new Alert(AlertType.WARNING);
    			a.setHeaderText("Preencha todos os dados!");
    			a.show();
        	}
    	}
	
	

	@FXML
	void listar(ActionEvent event) {
		tableView tbl = new tableView();
		try {
			tbl.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@FXML
	
	void concessionaria(ActionEvent event) {
		cadastraConcessionaria con = new cadastraConcessionaria();
		try {
			con.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@FXML
	void sair(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SAIR");
		String s = "Você tem certeza que deseja sair do sistema?";
		alert.setContentText(s);

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			System.exit(0);
		}

}
	
}

















