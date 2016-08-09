package handlers;

import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;
import javafx.collections.ObservableList;


import xmldao.XmlDAO;
import data.Employee;
import uiFunctions.Constants;

public class EditHndlr extends Handler implements Constants, EventHandler<ActionEvent>{
	private ObservableList<String> namesList;
	private ArrayList<Employee> employees;	
	private TextField[] tfs;
	private ListView listView;
	private XmlDAO xmlDAO;

	public EditHndlr(ObservableList<String> ov, ArrayList<Employee> empList,
		TextField[] tfs, ListView lv, XmlDAO xd){
		namesList = ov;
		employees = empList;
		this.tfs = tfs;
		listView = lv;
		xmlDAO = xd;
	}

	public void handle(ActionEvent e){
		System.out.println("Hello from Edit");			

		if(!tfs[FIRST].isEditable() || tfs[FIRST].getText().equals("")){//If the employee is being viewed
			System.out.println("Setting isEditable true");
			setFieldsEditable(tfs, true);

			viewSelection();
		}
		else{
			//Get values of the text fields
			int indexOfEmp= 0;

			Employee editedEmp = new Employee(
				tfs[FIRST].getText(),
				tfs[LAST].getText(),
				Double.parseDouble(tfs[PAY].getText()),
				Integer.parseInt(tfs[IDNUM].getText())
			);

			indexOfEmp = listView.getSelectionModel().getSelectedIndex();

			employees.remove(indexOfEmp);
			employees.add(editedEmp);
			namesList.remove(indexOfEmp);
			namesList.add(editedEmp.getFirstName());
			xmlDAO.saveEmployees(employees);

			setFieldsEditable(tfs, true);
			setTextFields(tfs, "");

		}

	}//End handle

	private void viewSelection(){
		//Get the selected index from the list view
		int index = listView.getSelectionModel().getSelectedIndex();

		//pass index to the employees list
		//and put information in to a temporary Employee
		Employee viewEmp = employees.get(index);

		//parse the information t the textFields
		setTextFields(tfs, viewEmp);
	}
}//End EditHndlr