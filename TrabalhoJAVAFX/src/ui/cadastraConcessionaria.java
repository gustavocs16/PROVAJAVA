package ui;
import controller.ControllerAlteraFXML;
import entidade.Carro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



		public class cadastraConcessionaria extends Application{
	    static Stage stage;
			public cadastraConcessionaria(Carro c1) {
		    	ControllerAlteraFXML.setC(c1);
		    	
		    }
			
		    public cadastraConcessionaria() {
				// TODO Auto-generated constructor stub
			}

			public static void main(String[] args) {
		        launch(tableView.class);
		    }
		    
		 
		    @Override
		    public void start(Stage stage) throws Exception {
		        Parent root = FXMLLoader.load(
		        getClass().getResource("FXMLConcessionaria.fxml"));
		        Scene scene = new Scene(root, 269, 270);
		        stage.setScene(scene);
		        stage.setTitle("Cadastro Concessionaria");
		        stage.show();
		        setStage(stage);
		    }
		    
			public static Stage getStage() {
				return stage;
			}
			public static void setStage(Stage stage) {
				cadastraConcessionaria.stage = stage;
			}
			}

