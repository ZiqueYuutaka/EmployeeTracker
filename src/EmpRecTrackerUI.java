import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import uiFunctions.UIFunctions;
import uiFunctions.Constants;

public class EmpRecTrackerUI extends BorderPane implements Constants{

	UIFunctions uiFunctions = null;
	private String[] btnNames = {"Add", "View", "Edit", "Delete", "Search"};

	private TextField[] textFields = null;
	private Button[] buttons = null;
	private Label[] fieldLabels = null;
	private GridPane txtPane = null;
	private GridPane srchPane = null;
	private GridPane txtAndSrchPane = null;
	private BorderPane navBar = null;
	//private ListView<String> list = null;
	private VBox btnBox = null;
	private ScrollPane navList = null;	

	public EmpRecTrackerUI(){
		//uiFunctions = new UIFunctions();
		setPadding(new Insets(10, 10, 10, 10));
		navBar = new BorderPane();
		btnBox = new VBox(5);
		textFields = new TextField[NUM_FIELDS];

		//Create array for textfield labels and buttons
		fieldLabels = new Label[NUM_FIELDS];
		buttons = new Button[NUM_BTN];

		//Create labels
		fieldLabels[FIRST] = new Label("FistName:");
		fieldLabels[LAST] = new Label("LastName:");
		fieldLabels[PAY] = new Label("PayRate:");
		fieldLabels[IDNUM] = new Label("IDNum:");
		fieldLabels[SEARCH] = new Label("Search(By First Name):");

		//Create textFields
		for(int i = FIRST; i < NUM_FIELDS; i++){
			textFields[i] = new TextField();
		}
		
		//Create buttons
		for(int i = ADD; i <= SEARCH; i++){
			buttons[i] = new Button(btnNames[i]);
			buttons[i].setMaxWidth(NAVLIST_WIDTH);
		}

		//Build UI functionality
		uiFunctions = new UIFunctions(textFields, buttons);

		//Put textfields in txtPane
		txtPane = new GridPane();
		txtPane.setVgap(5);
		txtPane.setHgap(5);

		for(int r = FIRST; r <= IDNUM; r++){
			txtPane.add(fieldLabels[r], 1, r);
			txtPane.add(textFields[r], 2, r);
		}

		//Set the search bar in it's Pane
		srchPane = new GridPane();
		srchPane.setVgap(5);
		srchPane.setHgap(5);
		srchPane.add(fieldLabels[SEARCH], 1,0);
		srchPane.add(textFields[SEARCH], 1,1);
		srchPane.add(buttons[SEARCH],1,2);

		//Place search bar and txtPane in txtAndSrchPane
		txtAndSrchPane = new GridPane();
		txtAndSrchPane.setVgap(40);
		txtAndSrchPane.setHgap(5);
		txtAndSrchPane.add(txtPane, 1,0);
		txtAndSrchPane.add(srchPane,1,1);

		//Get is from the uifunctions object

		navList = new ScrollPane(uiFunctions.getListView());
		navList.setMaxHeight(NAVLIST_HEIGHT);
		navList.setMaxWidth(NAVLIST_WIDTH);
		navList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		navBar.setTop(navList);

		//Place txtAndSrchPane in Center
		setCenter(txtAndSrchPane);

		//Add buttons excluding search to btnBox
		for(int i = ADD; i <= DELETE; i++){
			btnBox.getChildren().add(buttons[i]);
		}

		navBar.setBottom(btnBox);

		setRight(navBar);

		setAlignment(navBar, Pos.CENTER_LEFT);
	}
}