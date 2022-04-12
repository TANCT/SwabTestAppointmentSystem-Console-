public abstract class Staff{					//parent class for MedicalStaff & Registrar
	private String staffName;
	private String staffID;
	private String phone;
	private String email;

	public Staff(String name, String id, String p, String e){	//constructor of Staff class
		staffName=name;
		staffID=id;
		phone=p;
		email=e;
	}

	public String getName(){					//getter methods
		return staffName;
	}
	public String getID(){
		return staffID;
	}
	public String getPhone(){
			return phone;
	}
	public String getEmail(){
			return email;
	}

	public abstract void displayStaff();		//abstract method that applies polymorphism to display staff details
}