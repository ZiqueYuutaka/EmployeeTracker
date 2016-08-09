package uiFunctions;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import xmldao.XmlDAO;
import data.Employee;
import handlers.ViewHndlr;
import handlers.AddHndlr;
import handlers.AddKeyHndlr;
import handlers.EditHndlr;
import handlers.SearchHndlr;
import handlers.SearchKeyHndlr;
import popup.ConfirmDeleteWindow;

public class UIFunctions implements Constants{
	//Listeners
	private ObservableList<String> namesList;//Links directly with ListView
	private ListView listView;
	private Button[] btns;
	private TextField[] tfs;
	
	private Employee employee; //For saving to a file
	private ArrayList<Employee> employees;

	private XmlDAO xmlDAO;

	private ConfirmDeleteWindow deleteWindow = null;
	private Stage deleteStage = null;
	private Scene deleteScene = null;
	private Button deleteWindowBtn;

	public UIFunctions(){
		employees = new ArrayList<>();
		namesList = FXCollections.observableArrayList();
		listView = null;
		btns = null;
		tfs = null;
		employee = null;
		xmlDAO = new XmlDAO();
		deleteWindowBtn = null;
	}

	public UIFunctions(TextField[] tfs, Button[] btns){
		this.tfs = tfs;
		employee = null;
		xmlDAO = new XmlDAO();
		employees = xmlDAO.getEmployees();
		populateListView();
		addBtnHndlrs(btns);
		this.btns = btns;
		deleteWindowBtn = new Button("Delete Selection");
		deleteWindowBtn.setOnAction(e->{
			buttonClicked(e);
		});
		
		this.tfs[SEARCH].setOnKeyPressed(new SearchKeyHndlr(namesList, employees,
											tfs, listView));
		this.tfs[IDNUM].setOnKeyPressed(new AddKeyHndlr(namesList, employees,
								tfs, xmlDAO));
					
		
	}

	public void addBtnHndlrs(Button[] b){
		b[ADD].setOnAction(new AddHndlr(namesList, employees,
								tfs, xmlDAO));
		b[VIEW].setOnAction(new ViewHndlr(listView, employees, tfs));
		b[EDIT].setOnAction(new EditHndlr(namesList, employees, tfs,
											listView, xmlDAO));
		b[DELETE].setOnAction(e->{
			buttonClicked(e);
		});

		b[SEARCH].setOnAction(new SearchHndlr(namesList, employees,
											tfs, listView));
	}

	public ListView<String> getListView(){
		return listView;
	}

	//PopulateListView from file
	private void populateListView(){

		namesList = FXCollections.observableArrayList();
		for(int i = 0; i < employees.size(); i++){
			namesList.add((employees.get(i)).getFirstName());
		}
		listView = new ListView<>(namesList);		
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listView.getSelectionModel().selectFirst();
	}

	//For showing and hiding a delete confirmation window
	private void buttonClicked(ActionEvent e){
		if(e.getSource() == btns[DELETE]){
			deleteWindow = new ConfirmDeleteWindow(namesList, 
								listView, deleteWindowBtn);
			deleteScene = new Scene(deleteWindow, 200, 100);
			deleteStage = new Stage();
			deleteStage.setScene(deleteScene);
			//Stop events from firing in other window
			deleteStage.initModality(Modality.APPLICATION_MODAL);
			deleteStage.showAndWait();
		}
		else if (e.getSource() == deleteWindowBtn){
			if(!namesList.isEmpty()){
				int indexOfEmp = listView.getSelectionModel().getSelectedIndex();
				employees.remove(indexOfEmp);
				namesList.remove(indexOfEmp);

				xmlDAO.saveEmployees(employees);
			}
			deleteStage.close();
		}
	}

}