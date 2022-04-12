public enum Location{

    North(1,50), East(2,40),South(3,50), West(4,80);
    private int boothNo;            //the booth number
    private int maxCapacity;        //the maximum capacity of the booth
    private MedicalStaff medStaff;  //the medical staff who in-charge of this booth

    private Location(int bN,int mC){ //Constructor of enum by passing the booth number and max capacity
        boothNo=bN;
        maxCapacity=mC;
    }

    public int getBoothNo(){ 		//getter method to get the booth number
        return boothNo;
    }

    public int getMaxCapacity(){	//getter method to get the maximum capacity of the booth
        return maxCapacity;
    }

    public void assignMedStaff(MedicalStaff m){  //setter method to assign the medical staff to the booth by passing the object of class MedicalStaff
		medStaff=m;
	}

	public MedicalStaff getMedStaff(){   		 //getter method to get the object of class Medical Staff which in-charge his booth
		return medStaff;
	}

}