package handlers;

import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;
import javafx.collections.ObservableList;


import xmldao.XmlDAO;
import data.Employee;
import uiFunctions.Constants;

public class AddKeyHndlr extends Handler implements Constants, EventHandler<KeyEvent>{

	private ObservableList<String> namesList;
	private ArrayList<Employee> employees;
	private TextField[] tfs;
	private Employee employee;
	private XmlDAO xmlDAO;

	public AddKeyHndlr(ObservableList<String> ov, ArrayList<Employee> empList,
		TextField[] tfs, XmlDAO xd){

		namesList = ov;
		employees = empList;
		this.tfs = tfs;
		xmlDAO = xd;

	}
	public void handle(KeyEvent ke){
		if(ke.getCode() == KeyCode.ENTER){
			//If there is something in the fields after view
			if(tfs[FIRST].isEditable() == false){
				setFieldsEditable(tfs, true);
				setTextFields(tfs, "Required Field");
			}
				
			try{
				if(!isValidInput()){
					setTextFields(tfs, "Required Field");
				}
				else{
					employee = new Employee();

					employee.setFirstName(tfs[FIRST].getText());
					employee.setLastName(tfs[LAST].getText());
					employee.setPayRate(Double.parseDouble(tfs[PAY].getText()));
					employee.setIDNumber(Integer.parseInt(tfs[IDNUM].getText()));

					//clear fields
					setTextFields(tfs, "");

					if(employee.getFirstName().equals("") || 
						employee.getLastName().equals("")){
						setTextFields(tfs, "Required Field");
					}
						
					employees.add(employee);
					namesList.add(employee.getFirstName());

					//Write to new file
					xmlDAO.saveEmployees(employees);
				}
			}
			catch(NumberFormatException ex){
				setTextFields(tfs, "Required Field");

			}
		}
			
	}//End handle

	private boolean isValidInput(){
		if(tfs[FIRST].getText().equals("") ||
			tfs[LAST].getText().equals("") ||
			tfs[PAY].getText().equals("") ||
			tfs[IDNUM].getText().equals("") || 
			tfs[FIRST].getText().equals("Required field")||
			tfs[LAST].getText().equals("Required field")||
			tfs[PAY].getText().equals("Required field")||
			tfs[IDNUM].getText().equals("Required field")){
			return false;
		}
		return true;
	}
}//End AddKeyHndlr