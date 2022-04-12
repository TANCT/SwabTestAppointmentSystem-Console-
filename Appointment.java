public class Appointment{
	private String appointID;		//appointment ID
	private DateTime datetime;
	private Location location;
	private Applicant applicant;
	private MedicalStaff pic;

	public Appointment(String id, DateTime dt,Applicant applicant){		//Constructor
		appointID=id;
		datetime=dt;
		this.applicant=applicant;
	}

	public String getAppID(){			//getter method to get appoitnment ID
		return appointID;
	}

	public String getDate(){			//getter method to get appointment date
		return datetime.getDate();
	}

	public String getTime(){			//getter method to get appointment time
		return datetime.getTime();
	}

	public DateTime getDateTime(){		//getter method to get DateTime object
		return datetime;
	}

	public Location getLocation(){		//getter method to get Location object
		return location;
	}

	public Applicant getApplicant(){	//getter method to get Applicant object
		return applicant;
	}

	public MedicalStaff getPIC(){		//getter method to get PIC object
		return pic;
	}

	public void setPIC(MedicalStaff p){		//setter method to set pic
		pic=p;
	}

	public void setLocation(Location l){	//setter method to set location
		location=l;
	}

	public void displayApp(){				//method to display appointment details
		if(location!=null){
			System.out.println("Appointment ID   : "+appointID);
			System.out.println("Appointment date : "+datetime.getDate());
			System.out.println("Appointment time : "+datetime.getTime());
			System.out.println("Appointment venue: "+location.toString());
			System.out.println("Applicant IC     : "+applicant.getIC());
			System.out.println("Applicant Name   : "+applicant.getName());
			System.out.println("PIC ID           : "+pic.getID());
			System.out.println("PIC Name         : "+pic.getName());
		}
		else{
			System.out.println("Appointment ID   : "+appointID);
			System.out.println("Appointment date : "+datetime.getDate());
			System.out.println("Appointment time : "+datetime.getTime());
			System.out.println("Appointment venue: "+"None");
			System.out.println("Applicant IC     : "+applicant.getIC());
			System.out.println("Applicant Name   : "+applicant.getName());
			System.out.println("PIC ID           : "+"None");
			System.out.println("PIC Name         : "+"None");
		}

	}

}