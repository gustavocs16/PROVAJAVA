package ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



		public class viewConcessionaria extends Application{
		    static Stage stage;
			public static void main(String[] args) {
		        launch(viewConcessionaria.class);
		    }
		    @Override
		    public void start(Stage stage) throws Exception {
		        Parent root = FXMLLoader.load(
		        getClass().getResource("FXMLViewConcessionaria.fxml"));
		        Scene scene = new Scene(root, 600, 400);
		        stage.setScene(scene);
		        stage.setTitle("Visualiza carros");
		        stage.show();
		        setStage(stage);
		    }

		
		public static Stage getStage() {
			return stage;
		}
		public static void setStage(Stage stage) {
			viewConcessionaria.stage = stage;
		}
		}
		
