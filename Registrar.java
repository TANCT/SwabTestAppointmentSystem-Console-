import java.util.*;

public class Registrar extends Staff{	//child class of Staff

	public Registrar(String name, String id, String p, String e){	//constructor
		super(name,id,p,e);
	}

	void checkAppointment(String date, String time, Location l, ArrayList<Appointment> appList){	//to find the appointment from user input
		boolean found=false;
		for(int i=0; i<appList.size(); i++){
			if(appList.get(i).getLocation()==null){
				continue;
			}
			if(appList.get(i).getDate().equals(date) && appList.get(i).getTime().equals(time)&& appList.get(i).getLocation().equals(l)){
				found=true;
				break;
			}
		}
		if(!found){																					//if not found display no record
			System.out.println("No Record Found!");
			return;
		}

		for(int i=0; i<appList.size(); i++){														//if found display the appointment details
			if(appList.get(i).getDate().equals(date) && appList.get(i).getTime().equals(time)&& appList.get(i).getLocation().equals(l))
				appList.get(i).displayApp();
		}
	}

	public void displayStaff(){ 																	//display staff details
		System.out.printf("%-10s%-20s%-15s%-20s%-10s", getID(), getName(), getPhone(), getEmail(),"None");
	}

	private void assignMedStaff(Appointment a, MedicalStaff medStaff){								//assign medical staff(pic) to the appointment
		a.setPIC(medStaff);
		medStaff.addAppointment(a);
	}

	public void assignLocation(Appointment a, Location l){											//assign location to appointment
		a.setLocation(l);
		assignMedStaff(a,l.getMedStaff());
	}
}