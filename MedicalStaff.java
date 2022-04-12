import java.util.ArrayList;

public class MedicalStaff extends Staff{

	private ArrayList<Appointment> appList;
	Location location;

	public MedicalStaff(String name, String id, String p, String e, Location l){				//constructor
		super(name,id,p,e);
		location=l;
		appList=new ArrayList<Appointment>();
	}

	public void displayStaff(){																	//display staff details
			System.out.printf("%-10s%-20s%-15s%-20s%-10s", getID(), getName(), getPhone(), getEmail(), location.toString());
	}

	public void addAppointment(Appointment a){													//add appointment to the medstaff applist
		appList.add(a);
	}

	public Location getLocation(){
		return location;
	}

	void checkAppointment(String date, String time){											//to find the appointment from user input
		boolean found=false;
		for(int i=0; i<appList.size(); i++){
			if(appList.get(i).getDate().equals(date) && appList.get(i).getTime().equals(time)){
				found=true;
				break;
			}
		}
		if(!found){
			System.out.println("No Record Found!");												//if not found display no record
			return;
		}

		for(int i=0; i<appList.size(); i++){
			if(appList.get(i).getDate().equals(date) && appList.get(i).getTime().equals(time))	//if found display the appointment details
				appList.get(i).displayApp();
		}
	}

	public void assignResult(Applicant a, String status){										//assign result to the appointment
		a.setResult(status);
	}

}