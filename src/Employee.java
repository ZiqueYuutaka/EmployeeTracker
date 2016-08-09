package data;

public class Employee
{
	private String firstName;
	private String lastName;
	private double payRate;
	private int idNumber;

	public Employee()
	{
		this("", "", 0, 0);
	}

	public Employee(String firstName, String lastName, double payRate, int idNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.payRate = payRate;
		this.idNumber = idNumber;
	}
	
	// core logic methods go here

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setPayRate(double payRate)
	{
		this.payRate = payRate;
	}

	public double getPayRate()
	{
		return payRate;
	}

	public void setIDNumber(int idNumber){
		this.idNumber = idNumber;
	}

	public int getIDNumber(){
		return idNumber;
	}

    public boolean equals(Object object)
    {
        if (object instanceof Employee)
        {
            Employee employee = (Employee) object;
            if
            (
                this.firstName.equals(employee.getFirstName()) &&
                this.lastName.equals(employee.getLastName()) &&
                this.payRate == employee.getPayRate() &&
                this.idNumber == employee.getIDNumber()
            )
                return true;
        }
        return false;
    }

	public String toString()
	{
		return "FirstName:      " + firstName + "\n" +
			   "LastName: 		" + lastName + "\n" +
			   "PayRate:       	" + payRate + "\n" +
			   "IDNumber:		" + idNumber + "\n";
	}
}