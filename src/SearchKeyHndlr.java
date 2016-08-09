package handlers;

import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import data.Employee;
import uiFunctions.Constants;

public class SearchKeyHndlr extends Handler implements Constants, EventHandler<KeyEvent>{
	private ObservableList<String> namesList;//Links directly with ListView
	private ArrayList<Employee> employees;
	private TextField[] tfs;
	private ListView listView;

	public SearchKeyHndlr(ObservableList<String> ov, ArrayList<Employee> empList, 
			TextField[] tfs, ListView lv){
		namesList = ov;
		employees = empList;
		this.tfs = tfs;
		listView = lv;
	}

	public void handle(KeyEvent ke){
		if(ke.getCode() == KeyCode.ENTER){
			
			searching();
		}
	}

	private void searching(){
		System.out.println("Hello from Search");
		
		String searchStr = tfs[SEARCH].getText();
		if(searchStr.equals("") || 
			searchStr.equals("Please enter a name")){
			
			tfs[SEARCH].setText("Please enter a name");
		}
		else{
			//get the text from the search field
			String temp = capitalize(searchStr);

			//look for the text in the employees list
			if(namesList.contains(temp)){
				listView.getSelectionModel().select(temp);

				viewSelection();
				setFieldsEditable(tfs, false);
				tfs[SEARCH].setText("");
			}
			else{
				tfs[SEARCH].setText("Entry not found");
			}
		}

		tfs[SEARCH].requestFocus();
	}

	private String capitalize(String str){
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	private void viewSelection(){
		//Get the selected index from the list view
		int index = listView.getSelectionModel().getSelectedIndex();

		//pass index to the employees list
		//and put information in to a temporary Employee
		Employee viewEmp = employees.get(index);

		//parse the information t the textFields
		setTextFields(tfs, viewEmp);
	}
}