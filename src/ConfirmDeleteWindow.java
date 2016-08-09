package popup;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import xmldao.XmlDAO;
import data.Employee;
import uiFunctions.Constants;

public class ConfirmDeleteWindow extends BorderPane implements Constants{
	private Button btnDelete;
	private ObservableList<String> namesList;
	private ListView listView;
	private Label nameToDelete;
	private BorderPane labelPane;
	private BorderPane btnPane;

	public ConfirmDeleteWindow(ObservableList<String> ov, ListView lv, Button del){
		
		btnDelete = del;
		namesList = ov;
		listView = lv;
		nameToDelete = new Label();
		labelPane = new BorderPane();
		btnPane = new BorderPane();

		int indexOfEmp = listView.getSelectionModel().getSelectedIndex();

		nameToDelete.setText("Would you like to delete " + namesList.get(indexOfEmp) +
			"?");

		labelPane.setCenter(nameToDelete);
		labelPane.setAlignment(nameToDelete,Pos.CENTER);

		btnPane.setCenter(btnDelete);
		btnPane.setAlignment(btnDelete, Pos.CENTER);

		setCenter(labelPane);
		setBottom(btnPane);


	}

	
}