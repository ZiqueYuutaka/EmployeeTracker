package xmldao;

import java.util.*;
import java.io.*;
import javax.xml.stream.*;

import data.Employee;

public class XmlDAO{

	private String xmlFile = "E:\\CIT242_Assignments\\Final\\class\\employees.xml";
	private File employeesFile = null;

	//Constructor
	public XmlDAO(){
		employeesFile = new File(xmlFile);
	}

	private void checkFile() throws IOException{
		if(!employeesFile.exists()){
			employeesFile.createNewFile();
		}
	}

	public boolean saveEmployees(ArrayList<Employee> employees){

		//Creat an XMLOutputFactory object
		XMLOutputFactory xmlOutput = XMLOutputFactory.newInstance();

		try{
			//Check the file to make sure it exists
			this.checkFile();

			//Create an XMLStreamWriter object
			FileWriter fileWriter = new FileWriter(employeesFile);
			XMLStreamWriter xmlWriter = xmlOutput.createXMLStreamWriter(fileWriter);

			//Begin writing the xml file
			//Start with the version and start tag
			xmlWriter.writeStartDocument("1.0");
			xmlWriter.writeStartElement("Employees");

			//Write each individual employee to file
			for(Employee emp: employees){
				xmlWriter.writeStartElement("Employee");
				
				xmlWriter.writeStartElement("FirstName");
				xmlWriter.writeCharacters(emp.getFirstName());
				xmlWriter.writeEndElement();

				xmlWriter.writeStartElement("LastName");
				xmlWriter.writeCharacters(emp.getLastName());
				xmlWriter.writeEndElement();

				xmlWriter.writeStartElement("PayRate");
				xmlWriter.writeCharacters(Double.toString(emp.getPayRate()));
				xmlWriter.writeEndElement();

				xmlWriter.writeStartElement("ID");
				xmlWriter.writeCharacters(Integer.toString(emp.getIDNumber()));
				xmlWriter.writeEndElement();

				//Write end element of Employee
				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();
			xmlWriter.flush();
			xmlWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		catch(XMLStreamException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<Employee> getEmployees(){
		ArrayList<Employee> empList = new ArrayList<>();

		Employee emp = null;

		//Create an XMLInputFactory object
		XMLInputFactory xmlInput = XMLInputFactory.newInstance();

		try{
			//Check if the file exists
			this.checkFile();

			//Create a XMLStreamReader
			//First create a FileReader object
			FileReader fileReader = new FileReader(employeesFile);
			//Second wrap the FileReader inside an XML reader
			XMLStreamReader xmlReader = xmlInput.createXMLStreamReader(fileReader);

			//read the XML file
			while(xmlReader.hasNext()){
				int eventType = xmlReader.getEventType();

				switch(eventType){
					case XMLStreamConstants.START_ELEMENT:
						String elementName = xmlReader.getLocalName();
						if(elementName.equals("Employee")){
							emp = new Employee();
						}
						if(elementName.equals("FirstName")){
							String fn = xmlReader.getElementText();
							emp.setFirstName(fn);
						}
						if(elementName.equals("LastName")){
							String ln = xmlReader.getElementText();
							emp.setLastName(ln);
						}
						if(elementName.equals("PayRate")){
							String pr = xmlReader.getElementText();
							emp.setPayRate(Double.parseDouble(pr));
						}
						if(elementName.equals("ID")){
							String id = xmlReader.getElementText();
							emp.setIDNumber(Integer.parseInt(id));
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = xmlReader.getLocalName();
						if(elementName.equals("Employee")){
							empList.add(emp);
						}
						break;
					default:
						break;
				}
				xmlReader.next();	//move to the next line of xml text
			}
		}
		catch(IOException e){
			e.printStackTrace();
			return null;	// b/c method returns an arraylist
		}
		catch(XMLStreamException e){
			e.printStackTrace();
			return null;
		}

		return empList;
	}//End getEmployees


}