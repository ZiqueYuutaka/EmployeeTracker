package handlers;

import javafx.scene.control.TextField;
import data.Employee;
import uiFunctions.Constants;

//This class will hold all of the common functions

public abstract class Handler implements Constants{

	protected static void setTextFields(TextField[] tfs, String str){
		for(int i = FIRST; i <= IDNUM; i++){
			tfs[i].setText(str);
		}
	}

	protected static void setTextFields(TextField[] tfs, Employee emp){
		tfs[FIRST].setText(emp.getFirstName());
		tfs[LAST].setText(emp.getLastName());
		tfs[PAY].setText(Double.toString(emp.getPayRate()));
		tfs[IDNUM].setText(Integer.toString(emp.getIDNumber()));
	}

	protected static void setFieldsEditable(TextField[] tfs, boolean v){
		for(int i = FIRST; i <= IDNUM; i++){
			tfs[i].setEditable(v);
		}
	}
}