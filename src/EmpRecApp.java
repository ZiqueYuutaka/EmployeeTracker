//Launch Application from here
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class EmpRecApp extends Application{
	private double width = 375;
	private double height = 325;

	EmpRecTrackerUI ui = null;

	public void start(Stage primaryStage){
		ui = new EmpRecTrackerUI();

	    Scene scene = new Scene(ui, width, height);
	    primaryStage.setTitle("Employee Record App");
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show();

	}
}