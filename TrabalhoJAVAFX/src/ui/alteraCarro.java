package ui;
import controller.ControllerAlteraFXML;
import entidade.Carro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



		public class alteraCarro extends Application{
	    static Stage stage;
			public alteraCarro(Carro c1) {
		    	ControllerAlteraFXML.setC(c1);
		    	
		    }
			
		    public static void main(String[] args) {
		        launch(tableView.class);
		    }
		    
		 
		    @Override
		    public void start(Stage stage) throws Exception {
		        Parent root = FXMLLoader.load(
		        getClass().getResource("FXMLAltera.fxml"));
		        Scene scene = new Scene(root, 269, 270);
		        stage.setScene(scene);
		        stage.setTitle("Altera Carro");
		        stage.show();
		        setStage(stage);
		    }
		    
			public static Stage getStage() {
				return stage;
			}
			public static void setStage(Stage stage) {
				alteraCarro.stage = stage;
			}
			}

