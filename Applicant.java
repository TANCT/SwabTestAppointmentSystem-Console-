public class Applicant{
	private String applicantIC;
	private String applicantName;
	private String email;
	private String address;
	private String gender;
	private String phone;
	private String position;
	private Result result;
	private Appointment appointment;


	public Applicant(String ic, String name, String email,String add, String gender, String phone, String position, Result result){		//constructor
		applicantIC=ic;
		applicantName=name;
		this.email=email;
		address=add;
		this.gender=gender;
		this.phone=phone;
		this.position=position;
		this.result=result;
	}

	public void makeAppointment(Appointment app){		//method to assign appointnment for applicant
		appointment=app;
	}

	public void setResult(String status){			//method to set the result of applicant
		result.setStatus(status);

	}

	public String getStatus(){						//method to get the result of applicant
		return result.getStatus();
	}

	public void displayResult(){					//method to display the result of applicant
		System.out.println("Result  : "+result.getStatus());
	}

	public String getIC(){							//getter method to get the IC of applicant
		return applicantIC;
	}

	public String getName(){						//getter method to get the name of applicant
		return applicantName;
	}

	public String getEmail(){						//getter method to get the email of applicant
		return email;
	}

	public String getAddress(){						//getter method to get the address of applicant
			return address;
	}

	public String getGender(){						//getter method to get the gender of applicant
				return gender;
	}

	public String getPhone(){						//getter method to get the phone number of applicant
				return phone;
	}

	public String getPosition(){					//getter method to get the position of applicant
		return position;
	}

	public Result getResult(){						//getter method to get the Result object for applicant
		return result;
	}

	public Appointment getAppointment(){			//getter method to get the Appointment object for applicant
		return appointment;
	}

	public void displayAppointment(){				//method to display the appointment details of applicant
		System.out.println("********Appointment Details********");
		appointment.displayApp();
	}
	public void displayApplicantInfo(){				//method to display the personal information of applicant
		System.out.println("********Applicant's Details********");
		System.out.println("IC      : "+applicantIC);
		System.out.println("Name    : "+applicantName);
		System.out.println("Email   : "+email);
		System.out.println("Address : "+address);
		System.out.println("Gender  : "+gender);
		System.out.println("Phone   : "+phone);
		System.out.println("Position: "+position+"\n");
	}
	public void displayApplicant(){					//method to display the applicant details
		System.out.printf("%-15s%-30s%-30s%-50s%-8s%-15s%-10s%n",applicantIC,applicantName,email, address, gender, phone, position);

	}
}



