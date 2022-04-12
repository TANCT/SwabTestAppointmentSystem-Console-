import java.util.*;
import java.io.*;

public class SwabTestSystem{		//main class

	public static void main (String [] args)throws IOException{

		Scanner kin=new Scanner(System.in);
		Scanner in=new Scanner(new File("Staff.txt"));				//create Scanner object for input of file Staff.txt
		String name, id, phone, email, booth=null, ic, address, gender, position, date, time, appID, status;
		Applicant applicant;
		Appointment app;
		MedicalStaff medStaff;
		Location location;
		DateTime datetime;
		Registrar registrar;
		int choice=0, role=0, value, no, boothno=0, selection;
		boolean con,end;
		boolean correct;
		boolean found=false;


		ArrayList<Staff> staffList=new ArrayList<Staff>();						//create ArrayList of Staff to store arrays of Staff object
		ArrayList<Applicant> applicantList=new ArrayList<Applicant>();			//create ArrayList of Applicant to store arrays of Applicant object
		ArrayList<Appointment> appList=new ArrayList<Appointment>();			//create ArrayList of Appointment to store arrays of Appointment object
		while(in.hasNext()){                                                    //input staff data from file Staff.txt
			name=in.nextLine();
			id=in.nextLine();
			phone=in.nextLine();
			email=in.nextLine();

			if(id.substring(0,1).equals("M".trim())){							//if it is a medical staff ("M")
				booth=in.nextLine();											//read name of booth
				location=Location.valueOf(booth);								//get enum constant of Location using booth name
				medStaff=new MedicalStaff(name, id, phone, email,location);		//create Medical Staff object
				staffList.add(medStaff);										//add the newly created Medical Staff object into Staff ArrayList
				location.assignMedStaff(medStaff);								//assign the medical staff to the location given
			}
			else{																//else if it is a registrar
				staffList.add(new Registrar(name, id, phone, email));			//add the created Registrar object into Staff ArrayList
			}
			if(in.hasNext())
				in.nextLine();													//read the blank line
		}
		in.close();																//close the file
		Scanner input=new Scanner(new File ("Applicant.txt"));					//create Scanner object for input of file Applicant.txt

		while(input.hasNext()){                                                  //input applicant data from file Applicant.txt
			appID=input.nextLine();
			ic=input.nextLine();
			name=input.nextLine();
			email=input.nextLine();
			address=input.nextLine();
			gender=input.nextLine();
			phone=input.nextLine();
			position=input.nextLine();
			date=input.nextLine();
			time=input.nextLine();
			status=input.nextLine();
			booth=null;
			if(input.hasNext())
			{
				booth=input.nextLine();
			}

			datetime=new DateTime(date, time);															//create Datetime object
			applicant=new Applicant(ic, name, email, address, gender, phone, position, new Result());	//create Applicant object
			app=new Appointment(appID, datetime,applicant);												//create Appointment object
			if(booth!=null&&!booth.trim().isEmpty()){					//if booth is not empty
				location=Location.valueOf(booth);						//get enum constant of Location using booth constant name
				app.setLocation(location);								//set the location for appointment
				app.setPIC(location.getMedStaff());						//set the pic for appointment
				location.getMedStaff().addAppointment(app);				//add the appointment for medical staff
				if(input.hasNext())
				{
					input.nextLine();									//read blank line
				}

				if(!status.equals("Pending")){							//if status read is not Pending
					applicant.setResult(status);						//set the result for applicant
				}
			}
			applicant.makeAppointment(app);				//applicant make the appointment
			appList.add(app);							//add app into the appList which is Appointment ArrayList
			applicantList.add(applicant);				//add applicant into the applicantList which is Applicant ArrayList
		}
		input.close();			//close the file
		do{
			end=false;
			System.out.println("~Swab Test System~\n");
			System.out.println("1 Medical Staff\n2 Registrar\n3 Applicant");
			System.out.print("Enter your role (1/2/3): ");
			correct=false;
			while(!correct){
				try{								//use try..catch block to handle the InputMismatch Exception
					role=kin.nextInt();				//read the integer that represents the role of user
					while(role<1 ||role>3){
						System.out.print("Wrong Input! Please enter the role(1/2/3): ");
						role=kin.nextInt();
					}
					correct=true;
				}
				catch(InputMismatchException e){			//catch and handle the Exception when user enters String for integer variable
					System.out.print("Wrong Input! Please enter the role(1/2/3): ");
				}
			}
			kin.nextLine();
			switch(role){
				case 1:                                                                          //Take the role of Medical staff
				System.out.print("Enter your Staff ID:");                                        //Enter staff ID
				id=kin.nextLine();

				while(!staffExist(id, staffList)||!id.substring(0,1).equals("M".trim())){		//while the staff ID entered is incorrect
					System.out.print("Incorrect Staff ID! Please enter correct Staff ID: ");
					id=kin.nextLine();
				}

				medStaff=(MedicalStaff)searchStaff(id, staffList);			//search and get the Medical Staff object
				do{
					con=true;
					medStaffMenu();                                                                   //display Medical Staff Menu
					correct=false;
					while(!correct){
						try{									//try..catch block to handle InputMismatch Exception
							choice=kin.nextInt();				//read choice for Medical Staff
							while(choice<1 ||choice>4)
							{
								System.out.print("Wrong Input! Please enter your choice(1 - 4): ");
								choice=kin.nextInt();
							}
							correct=true;
						}
						catch(InputMismatchException e){
							System.out.print("Wrong Input! Please enter your choice(1 - 4): ");
						}
					}
					kin.nextLine();
					switch(choice){
						case 1:                                                                      //Can only display information of appointment assigned to him based on the specific datetime entered
						System.out.print("Enter date (eg. 02-12-2021): ");
						date=kin.nextLine();									//read date
						while(!validateDate(date)){								//validate date entered
							System.out.println("The format of date entered is wrong as not following dd-mm-yyyy.");
							System.out.print("Please reenter the date for swab test (dd-mm-yyyy): ");
							date=kin.nextLine();
						}
						System.out.print("Enter time (eg. 2:00 PM): ");
						time=kin.nextLine();									//read time
						while(!validateTime(time)){								//validate time entered
							System.out.println("The format of time entered is wrong as not following h:mm tt.");
							System.out.print("Please reenter the time for swab test (h:mm tt): ");
							time=kin.nextLine();
						}
						medStaff.checkAppointment(date, time);					//check and get apppointment details if any
						break;

						case 2:                                                                      //Can set the result of any applicant
						System.out.print("Enter applicant IC: ");				//read applicant IC
						ic=kin.nextLine();
						if(!applicantExist(ic, applicantList)){					//if no such applicant exists
							System.out.println("No record found!");
							break;
						}
						applicant=searchApplicant(ic, applicantList);			//get applicant object from the Applicant ArrayList
						System.out.print("Enter result (Postive/Negative): ");
						status=kin.nextLine();									//enter status
						medStaff.assignResult(applicant, status);				//assign the status to the applicant
						break;

						case 3:                                                                      //Can display information of any applicant
						System.out.print("Enter applicant IC: ");
						ic=kin.nextLine();										//read applicant IC
						if(!applicantExist(ic, applicantList)){					//if no such applicant exists
							System.out.println("No record found!");
							break;
						}
						applicant=searchApplicant(ic, applicantList);			//get applicant object from the Applicant ArrayList
						applicant.displayApplicantInfo();						//use it to display the applicant's personal information
						break;

						case 4:                                                                      //Exit the Medical Staff operation
						con=false;
						break;
					}
				}while(con);
				break;
				case 2:                                                                         //Take the role of registrar
				System.out.print("Enter your Staff ID:");                                       //Enter staff id
				id=kin.nextLine();
				while(!staffExist(id, staffList)||id.charAt(0)!='R'){							//while the staffID does not exist or the ID is not for registrar
					System.out.print("Incorrect Staff ID! Please enter correct Staff ID: ");
					id=kin.nextLine();
				}
				registrar=(Registrar)searchStaff(id, staffList);					//get registrar object from the Staff ArrayList
				do{
					con=true;
					registrarMenu();                                                                //display registrar menu
					correct=false;
					while(!correct){
						try{									//try..catch block to handle InputMismatch Exception
							choice=kin.nextInt();				//read choice for Registrar
							while(choice<1 ||choice>6)
							{
								System.out.print("Wrong Input! Please enter your choice(1 - 6): ");
								choice=kin.nextInt();
							}
							correct=true;
						}
						catch(InputMismatchException e){
							System.out.print("Wrong Input! Please enter your choice(1 - 6): ");
						}
					}
					kin.nextLine();

					switch(choice){
						case 1:                                                                    //check all appointment of certain date, time and booth
						System.out.print("Enter date (eg. 02-12-2021): ");
						date=kin.nextLine();
						while(!validateDate(date)){
							System.out.println("The format of date entered is wrong as not following dd-mm-yyyy.");
							System.out.print("Please reenter the date for swab test (dd-mm-yyyy): ");
							date=kin.nextLine();
						}
						System.out.print("Enter time (eg. 2:00 PM): ");
						time=kin.nextLine();
						while(!validateTime(time)){
							System.out.println("The format of time entered is wrong as not following h:mm tt.");
							System.out.print("Please reenter the time for swab test (h:mm tt): ");
							time=kin.nextLine();
						}
						System.out.println("Booth No  Booth Name");
						for(Location i: Location.values()){
							System.out.printf("%4d%12s%n", i.ordinal()+1,i.toString());
						}

						System.out.print("Enter booth number: ");
						correct=false;
						while(!correct){
							try{								//try..catch block to handle InputMismatch Exception
								boothno=kin.nextInt();			//read booth number
								while(boothno<1 ||boothno>4)	//while enter invalid booth number
								{
									System.out.print("Wrong Input! Please enter booth number (1 - 4): ");
									boothno=kin.nextInt();
								}
								correct=true;
							}
							catch(InputMismatchException e){
								System.out.print("Wrong Input! Please enter booth number (1 - 4): ");

							}
						}
						kin.nextLine();
						registrar.checkAppointment(date, time,Location.values()[boothno-1],appList);		//display appointment details if any
						break;
						case 2:                                                                              //show medical staff with their respective booth
							showMedStaffSchedule(staffList);
						break;
						case 3:                                                                               //show staff info
						System.out.printf("%-3s%-10s%-20s%-15s%-20s%-10s%s%n","No", "ID", "Name", "Phone", "Email", "Location", "Position");
						for(int i=0; i<staffList.size(); i++){				//display staff information for Staff ArrayList
							System.out.printf("%-3d",i+1);
							staffList.get(i).displayStaff();
							if(staffList.get(i) instanceof MedicalStaff){
								System.out.println("Medical Staff");
							}
							else{
								System.out.println("Registrar");
							}
						}
						System.out.println();
						break;
						case 4:                                             //show applicant info based on IC entered
						System.out.print("Enter applicant IC: ");
						ic=kin.nextLine();
						if(!applicantExist(ic, applicantList)){
							System.out.println("No record found!");
							break;
						}
						applicant=searchApplicant(ic, applicantList);		//search and get applicant object
						applicant.displayApplicantInfo();
						break;
						case 5:                                             //assign location to appointment
						System.out.print("Enter appointment ID: ");
						id=kin.nextLine();
						if(!appExist(id, appList)){
							System.out.println("No record found!");
							break;
						}
						app=searchAppointment(id, appList);					//get appointment object from the Appointment ArrayList
						System.out.println("Booth No  Booth Name");
						for(Location i: Location.values()){
							System.out.printf("%4d%12s%n", i.ordinal()+1,i.toString());
						}

						System.out.print("Enter booth number: ");
						correct=false;
						while(!correct){
							try{						//try..catch block to handle InputMismatch Exception
								boothno=kin.nextInt();	//read booth number
								while(boothno<1 ||boothno>4)		//while read invalid booth number
								{
									System.out.print("Wrong Input! Please enter booth number (1 - 4): ");
									boothno=kin.nextInt();
								}
								correct=true;
							}
							catch(InputMismatchException e){
								System.out.print("Wrong Input! Please enter booth number (1 - 4): ");
							}
						}
						kin.nextLine();
						location=null;
						for(Location i:Location.values()){
							if(i.getBoothNo()==boothno){
								location=i;
								break;
							}
						}
						registrar.assignLocation(app, location);		//registrar assign location to appointment
						System.out.println("\nThe medical staff and location are assigned successfully!");
						System.out.println("Location     : "+app.getLocation().toString());
						System.out.println("Medical staff: "+app.getPIC().getID()+" - "+app.getPIC().getName());
						break;
						case 6:	                                                                                        //exit registrar menu
						con=false;
						break;
						}
				}while(con);
				break;
				case 3:                                                                                             //Take the role of applicant
				do{
					con=true;
					applicantMenu();                                                                                    //display applicant menu
					correct=false;
					while(!correct){
						try{				//try..catch block to handle InputMismatch Exception
							choice=kin.nextInt();			//read choice for applicant
							while(choice<1 ||choice>4)		//while read invalid choice
							{
								System.out.print("Wrong Input! Please enter your choice(1 - 4): ");
								choice=kin.nextInt();
							}
							correct=true;

						}catch(InputMismatchException e){
							System.out.print("Wrong Input! Please enter your choice(1 - 4): ");

						}
					}
					kin.nextLine();

					switch(choice){
					case 1:                                                                                            //make appointment
					System.out.print("Enter your IC: ");
					ic=kin.nextLine();
					if(applicantExist(ic, applicantList)){
						System.out.println("You have already registered!");
						break;
					}
					System.out.print("Enter your name: ");
					name=kin.nextLine();
					System.out.print("Enter your email: ");
					email=kin.nextLine();
					System.out.print("Enter your gender (Male or Female): ");
					gender=kin.nextLine();
					while(!gender.toUpperCase().equals("MALE") &&!gender.toUpperCase().equals("FEMALE"))
					{
						System.out.print("Invalid Input. Please enter your gender (Male or Female): ");
						gender=kin.nextLine();
					}
					System.out.print("Enter your phone: ");
					phone=kin.nextLine();
					System.out.print("Enter your address: ");
					address=kin.nextLine();
					System.out.print("Enter your position(Staff/ Student): ");
					position=kin.nextLine();
					while(!position.toUpperCase().equals("STAFF")&&!position.toUpperCase().equals("STUDENT")){
						System.out.print("Invalid Input. Please enter your position(Staff/ Student): ");
						position=kin.nextLine();
					}
					System.out.print("Enter a date for swab test (eg. 02-03-2021): ");
					date=kin.nextLine();
					while(!validateDate(date)){
						System.out.println("The format of date entered is wrong as not following dd-mm-yyyy.");
						System.out.print("Please reenter the date for swab test (dd-mm-yyyy): ");
						date=kin.nextLine();
					}
					System.out.print("Enter a time for swab test (eg. 2:00 PM): ");
					time=kin.nextLine();
					while(!validateTime(time)){
						System.out.println("The format of time entered is wrong as not following h:mm tt.");
						System.out.print("Please reenter the time for swab test (h:mm tt): ");
						time=kin.nextLine();
					}
					applicant =new Applicant(ic,name, email,address, gender, phone, position, new Result());
					value=appList.size()+1;
					no=1;
					while(value/10>0){
						value/=10;
						no++;
					}
					appID="A";
					for(int i=0; i<(4-no); i++){
						appID+="0";
					}
					appID+=String.valueOf(value);

					app=new Appointment(appID, new DateTime(date, time), applicant);
					appList.add(app);								//add appointment into Appointment ArrayList
					applicantList.add(applicant);					//add applicant into Applicant ArrayList
					applicant.makeAppointment(app);					//applicant make the appointment
					break;
					case 2:                                                                                               //get appointment details
					System.out.print("Enter your IC: ");
					ic=kin.nextLine();
					if(!applicantExist(ic, applicantList)){
						System.out.println("Record Not Found! You have not been registered yet.");
						break;
					}
					applicant=searchApplicant(ic, applicantList);				//get the applicant object if search found
					applicant.displayApplicantInfo();							//display applicant's information
					applicant.displayResult();									//display applicant's result
					break;
					case 3:                                                                                                //check result
					System.out.print("Enter your IC: ");
					ic=kin.nextLine();
					if(!applicantExist(ic, applicantList)){
						System.out.println("Record Not Found! You have not been registered yet.");
						break;
					}
					applicant=searchApplicant(ic, applicantList);
					applicant.displayAppointment();
					break;
					case 4:                                                                                                //exit the applicant menu
					con=false;
					break;
				    }
			}while(con);
			break;
			}
			System.out.println("End the system? (Enter 1 to leave or 0 to stay)");                                    //exit the whole system
			end=(kin.nextInt()==1)?true:false;
			kin.nextLine();
		}while(!end);

		PrintWriter out=new PrintWriter("Applicant.txt");		//create PrintWriter object for output file Applicant.txt

		for(int i=0; i<applicantList.size(); i++){                                                                    //print applicant data into file
			out.println(applicantList.get(i).getAppointment().getAppID());
			out.println(applicantList.get(i).getIC());
			out.println(applicantList.get(i).getName());
			out.println(applicantList.get(i).getEmail());
			out.println(applicantList.get(i).getAddress());
			out.println(applicantList.get(i).getGender());
			out.println(applicantList.get(i).getPhone());
			out.println(applicantList.get(i).getPosition());
			out.println(applicantList.get(i).getAppointment().getDate());
			out.println(applicantList.get(i).getAppointment().getTime());
			out.println(applicantList.get(i).getStatus());
			if(applicantList.get(i).getAppointment().getLocation()!=null){
				out.println(applicantList.get(i).getAppointment().getLocation().toString());
			}
			out.println();
		}
		out.close();
	}

	public static void medStaffMenu(){							//Medical Staff Menu
		System.out.println();
		System.out.println("Medical Staff Menu");
		System.out.println("1 Check My Appointment");
		System.out.println("2 Assign Result");
		System.out.println("3 Display Applicant's Info");
		System.out.println("4 Exit\n");
		System.out.println("Enter your choice (1 - 4): ");
	}

	public static void registrarMenu(){							//Registrar Menu
		System.out.println();
		System.out.println("Registrar Menu");
		System.out.println("1 Check Appointment");
		System.out.println("2 Display Medical Staff's Schedule");
		System.out.println("3 Display Staff's Info");
		System.out.println("4 Display Applicant's Info");
		System.out.println("5 Assign Medical Staff and Location");
		System.out.println("6 Exit\n");
		System.out.println("Enter your choice (1 - 6): ");

	}
	public static void applicantMenu(){							//Appicant Menu
		System.out.println();
		System.out.println("Applicant Menu");
		System.out.println("1 Make Appointment");
		System.out.println("2 Check Result");
		System.out.println("3 Get Appointment Detail");
		System.out.println("4 Exit");
		System.out.println("Enter your choice (1 - 4): ");

	}
	public static Appointment searchAppointment(String appID, ArrayList<Appointment>appList){              //return appointment with app id given
		Appointment app=null;
		for(int i=0; i<appList.size(); i++){
			if(appList.get(i).getAppID().equals(appID)){
				app=appList.get(i);
				break;
			}
		}
		return app;
	}
	public static boolean appExist(String appID, ArrayList<Appointment>appList){                            //return true if appointment exist and vice versa
			boolean exist=false;
			for(int i=0; i<appList.size(); i++){
				if(appList.get(i).getAppID().equals(appID)){
					exist=true;
					break;
				}
			}
			return exist;
	}
	public static Staff searchStaff(String staffID, ArrayList<Staff> staffList){                            //return staff with staff ID given
			Staff staff=null;
			for(int i=0; i<staffList.size(); i++){
				if(staffList.get(i).getID().equals(staffID)){
					staff=staffList.get(i);
					break;
				}
			}
			return staff;
	}

	public static boolean staffExist(String staffID, ArrayList<Staff> staffList){                          //return true if staff exists and vice versa
		boolean exist=false;
		for(int i=0; i<staffList.size(); i++){
			if(staffList.get(i).getID().equals(staffID)){
				exist=true;
				break;
			}
		}
		return exist;
	}
	public static Applicant searchApplicant(String ic, ArrayList<Applicant> applicantList){               //return applicant with ic given
			Applicant applicant=null;
			for(int i=0; i<applicantList.size(); i++){
				if(applicantList.get(i).getIC().equals(ic)){
					applicant=applicantList.get(i);
					break;
				}
			}
			return applicant;
	}
	public static boolean applicantExist(String ic, ArrayList<Applicant> applicantList){                 //return true if applicant exists
			boolean exist=false;
			for(int i=0; i<applicantList.size(); i++){
				if(applicantList.get(i).getIC().equals(ic)){
					exist=true;
					break;
				}
			}
			return exist;
	}
	public static void showMedStaffSchedule(ArrayList<Staff> staffList){                                //show medical staff with their booth assigned to them
		System.out.println("Medical Staff Schedule");
		for(int i=0; i<staffList.size(); i++){
			if(staffList.get(i) instanceof MedicalStaff){
				System.out.printf("%-3d%-30s%-3s%s%n",(i+1),staffList.get(i).getName()," - ",((MedicalStaff)staffList.get(i)).getLocation().toString());
			}
		}
	}
	public static boolean validateDate(String d){								   //validate the date entered,return true for date in (dd-mm-yyyy) format and year from 2021 until 2029, else return false
		String date = "^(0?[1-9]|[12][0-9]|3[01])[-](0?[1-9]|1[012])[-]202[1-9]$";
		if(d.matches(date)){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean validateTime(String t){								//validate the time entered,return true for time in (h:mm tt) format, else return false
		String time = "^(1[0-2]|0?[1-9]):[0-5][0-9]\040(AM|am|PM|pm)$";
		if(t.matches(time)){
			return true;
		}
		else{
			return false;
		}
	}
}






































