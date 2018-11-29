package controller;


import java.util.Optional;


import dao.CarroDAO;
import dao.ConcessionariaDAO;
import entidade.Carro;
import entidade.Concessionaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ui.cadastraConcessionaria;
import ui.tableView;
import ui.viewConcessionaria;



public class ControllerConcessionariaFXML {


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private Button btnInserir;


    @FXML
	void inserir(ActionEvent event) {
    	
    	if(!tfNome.getText().equals("") && !tfLocalidade.getText().equals("")) {
    		try {
    		Concessionaria umaConcessionaria = new Concessionaria();
    		umaConcessionaria.setNome(tfNome.getText());
    		umaConcessionaria.setLocalidade(tfLocalidade.getText());
    		new ConcessionariaDAO().inserirConcessionaria(umaConcessionaria);
    		
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setHeaderText("Concessionaria cadastrada com sucesso!!!");
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
		viewConcessionaria vc = new viewConcessionaria();
		try {
			vc.start(new Stage());
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
		}}

	
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

















