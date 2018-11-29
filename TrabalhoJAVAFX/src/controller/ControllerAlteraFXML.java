package controller;

import java.net.URL;
import java.util.ResourceBundle;
import dao.CarroDAO;
import entidade.Carro;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ui.alteraCarro;




public class ControllerAlteraFXML implements Initializable {

	@FXML
	private Text lbid;
	
	@FXML
    private TextField tfKm;

    @FXML
    private TextField tfCor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;

	@FXML
	 private Button btnAtualizar;
    
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Button btnSair;
	
	
	
   private static Carro carro;


    public void initialize(URL url, ResourceBundle rb)
    {
    	initPerson();
    }
    
    public void initPerson()
    {
    	lbid.setText(Integer.toString(carro.getId()));
    	tfNome.setText(carro.getNome());
    	tfDescricao.setText(carro.getDescricao());
    	tfCor.setText(carro.getCor());
    	tfKm.setText(Integer.toString(carro.getKm()));
    	
    }

	public static Carro getC() {
		return carro;
	}


	public static void setC(Carro c1) {
		ControllerAlteraFXML.carro = c1;
		
	}
	
   public  void atualiza() {
	   CarroDAO dao = new CarroDAO();
	   int id = Integer.parseInt(lbid.getText());
	   int km = Integer.parseInt(tfKm.getText());
	   String nome = tfNome.getText(), descricao = tfDescricao.getText(), cor = tfCor.getText();
	   Carro umCarro = new Carro(id,nome,descricao,cor,km);
	   dao.updateCarro(umCarro);
	   }
   
   public void fechar() {

   	alteraCarro.getStage().close();
   }
}


	


















