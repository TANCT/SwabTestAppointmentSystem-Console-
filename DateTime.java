public class DateTime
{
    private String date;    //date in format "dd-mm-yyyy"
    private String time;    //time in format "h:mm tt"


    public DateTime(String d, String t){ //Constructor by passing string of date and time
        date=d;
        time=t;
    }

    public void setDate(String d){   //function to set the date by passing string of date
        date=d;
    }

    public void setTime(String t) {  //function to set the time by passing string of time
        time=t;
    }

    public String getDate(){     //function to get the string of date
        return date;
    }

    public String getTime(){     //function to get the string of time
        return time;
    }
}