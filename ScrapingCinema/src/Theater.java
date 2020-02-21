public class Theater {
	public String theater;
	private String date;
	private String times ;
	
	public Theater(String theater,String date, String times)
	{
		this.theater = theater;
		this.date = date;
		this.times = times;
	}

	
	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	




}
