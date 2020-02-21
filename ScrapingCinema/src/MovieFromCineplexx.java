import java.util.ArrayList;

public class MovieFromCineplexx {
	private String title;
	private String url;
	private String urlImage;
	private ArrayList<Date> dates = new ArrayList<>();
	
	public MovieFromCineplexx(String title, String url, String urlImage)
	{
		this.title = title;
		this.url = url;
		this.urlImage =urlImage;
	}

	
	public void addDate(Date t)
	{
		dates.add(t);
	}
	
	
	public ArrayList<Date> getDates() {
		return dates;
	}



	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	
	

}
