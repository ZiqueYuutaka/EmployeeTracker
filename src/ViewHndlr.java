package handlers;

import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;

import xmldao.XmlDAO;
import data.Employee;
import uiFunctions.Constants;

public class ViewHndlr extends Handler implements Constants, EventHandler<ActionEvent>{
	private ListView listView;
	private ArrayList<Employee> employees;
	private TextField[] tfs;	
	
	public ViewHndlr(ListView lv, ArrayList<Employee> empList, TextField[] tfs){
		listView = lv;
		employees = empList;
		this.tfs = tfs;
	}
	
	public void handle(ActionEvent e){
			
		viewSelection();

		//set text fields to uneditable
		setFieldsEditable(tfs, false);

	}//End handle

	private void viewSelection(){
		//Get the selected index from the list view
		int index = listView.getSelectionModel().getSelectedIndex();

		//pass index to the employees list
		//and put information in to a temporary Employee
		Employee viewEmp = employees.get(index);

		//set the information t the textFields
		setTextFields(tfs, viewEmp);
	}
}//End ViewHndlr