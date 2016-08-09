package handlers;

import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import java.util.ArrayList;

import data.Employee;
import uiFunctions.Constants;

//Needs textField, listView
public class SearchHndlr extends Handler implements Constants, EventHandler<ActionEvent>{
	private ObservableList<String> namesList;//Links directly with ListView
	private ArrayList<Employee> employees;
	private TextField[] tfs;
	private ListView listView;

	public SearchHndlr(ObservableList<String> ov, ArrayList<Employee> empList, 
			TextField[] tfs, ListView lv){
		namesList = ov;
		employees = empList;
		this.tfs = tfs;
		listView = lv;
	}

	public void handle(ActionEvent e){
		searching();
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