public class Result{		//Result class
	private String status;

	public Result(){		//default constructor that will set the initial status as Pending
		status="Pending";
	}
	public void setStatus(String s){	//setter method
		status = s;
	}

	public String getStatus(){			//getter method
		return status;
	}
}

