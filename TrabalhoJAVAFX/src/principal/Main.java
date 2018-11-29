package principal;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	private static Stage stage;
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setStage(primaryStage);
		primaryStage.setTitle("Cadastro de Carro");
		Pane root = FXMLLoader.load(getClass().getResource("/ui/FXMLCarro.fxml"));
		mainScene = new Scene(root);	
	primaryStage.setScene(mainScene);
	primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


	public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

}
