**************************************************
	    EMPLOYEE RECORD TRACKER
**************************************************

DESCRIPTION:
This application keeps track of employees in a 
company.  Data is saved to an .xml file titled
"employees.xml" located in the class directory.

**************************************************
		DEVELOPMENT TOOLS
**************************************************

SOURCE FILES:
Located in the src directory.

CLASS FILES:
Located in the class directory.  Packages are also
located here.

COMPILING:
Utilize the .bat files located in the src director.
Change path information located in each .bat file
to reflect the location of the \Final parent
directory.

cclass.bat	-for compiling the Employee.java 
		and Constants.java files.
cui.bat		-for compiling the XmlDAO.java,
		UIFunctions.java, all hndlr.java 
		and Handler.java files, 
		EmpRecTrackerUI.java, and
		ConfirmDeleteWindow.java.
cmain.bat	-for compiling EmpRecApp.java
rmain.bat	-for launching EmpRecApp.class

COMPILING INSTRUCTIONS:
Before compiling ensure all .bat files are changed
to reflect the home directory for the \Final 
directory.  For instance, if \Final is located in
"C:\" change "E:\CIT285_Assignments\" to "C:\".

After changing the home directory for \Final 
compile each .java file in the following order
using the specified .bat file.

cclass.bat
-Employee.java
-Constants.java

cui.bat
-XmlDAO.java
-Handler.java
-all files that have "Hndlr.java" in the name
 in any order
-ConfirmDeleteWindow.java
-UIFunctions.java
-EmpRecTrackerUI.java

cmain.bat
-EmpRecApp.java