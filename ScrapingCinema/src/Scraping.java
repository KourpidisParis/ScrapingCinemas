import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraping {

    public static void main(String[] args) throws Exception {
    	Scanner in = new Scanner(System.in);

    	while(true) {
	    	System.out.println("1 Movies playing on Cineplexx Plateia Odeon");
	        System.out.println("2 Movies playing on Village Cosmos Cinema");
	
	
	        System.out.println("Please enter your choice(1 or 2):");        
	        
	        String choice=in.nextLine();//Check for correct answer
	        while(!choice.equals("1") && !choice.equals("2"))
	        {
	        	System.out.println("Wrong!Please enter 1 or 2: ");
	            choice=in.nextLine();
	        } 
	        
	        
	        switch (choice) {
	            case "1": Cineplexx(); 
		                break;
	            case "2": VillageCosmos();
	                    break;
	            default: System.out.println("Invalid choice");
	        }
	        
	        System.out.println("Do you want to continue (YES/NO): ");
	       
	        String answer = in.nextLine();
	        String ch = answer.toUpperCase();	        
	        while(!ch.equals("YES")  && !ch.equals("NO"))
	        {
	        	System.out.println("Wrong!Please enter yes or no: ");
	            ch=in.nextLine();
	        }
	        if (ch.equals("NO")) 
	        	break;
	        

	            
    	}
    	System.out.println("END");

    }
    
    
       
public static void Cineplexx() throws IOException {
	
	final Document document = Jsoup.connect("https://www.cineplexx.gr/tainies/").get();
	//System.out.println(document.outerHtml());   	
	Elements details = document.select("div.detailview-element");
	Elements rows = details.select(".row");
	
	    	
	for(Element r :rows) {
		
	    Elements spans =  r.select(".span6");
	    		
   		Element span1 = spans.get(0);
   		String imgUrl = span1.select("img").attr("data-original");
	    	    	
	    String url = span1.select("h2 a").attr("href");
	    String title = span1.select("h2 a").text();    	
	                    
	    MovieFromCineplexx movie = new MovieFromCineplexx(title,url,imgUrl);
	            
	    Element span2 = spans.get(1);
	   	Elements subSpans = span2.select(".span3"); 		
	    for(Element s : subSpans){
	    	
	        String hall = s.select("p.room-desc").text();
	   		String time =  s.select("p.time-desc").text();
     		String mode = s.select("p.mode-desc").text();
	 		
	       	Date date = new Date(hall,time,mode);
	        movie.addDate(date);
	    	}
        System.out.println(movie.getTitle());
        System.out.println(movie.getUrl());
	    System.out.println(movie.getUrlImage());
	
     	for(Date d : movie.getDates()){
		
     		System.out.print(d.getHall() + " ==> ");
	    	System.out.print(d.getTime() +" ");
     	    System.out.println(d.getMode());
	    }
	            
	    System.out.println("=============================");
  }
	    		
}
    
    
   
public static void VillageCosmos() throws IOException{
	
	String date = null;
    String time = null;
	String hallName = null;
    String movie;
   	Theater t=null;
   	ArrayList<MovieFromVillage> movies = new ArrayList<>();
		 

   	final Document document = Jsoup.connect("https://www.villagecinemas.gr/el/kinimatografoi/cosmos-11-cinemas/").get();
    //System.out.println(document.outerHtml());
    ArrayList<Element> title = new ArrayList<>();
        
    	
   	title = document.select("div.view");
    //System.out.println(title.get(0));

    for (Element row: title){
        // title movies
       	movie  = row.select("div.cinema.FloatLeft h3").text();
       	MovieFromVillage m = new MovieFromVillage(movie);
       	//System.out.println(m.getMovie());
    		
    	Elements views = row.select("div.view_details");
		for(Element view : views)
    		{
    			hallName = view.select("div.hall_title").text();
    		    // System.out.println(hallName);
    		    
    		    Elements hall_row = view.select("div.hall_row.row");
    		    for(Element h : hall_row) {
    		    	 date = h.select("div.date.FloatLeft").text();
    		    	 time = h.select("div.hour.FloatLeft").text();
    				 t = new Theater (hallName,date,time);
    				 m.addTheater(t);
    		    	 //System.out.println(t.getDate() + t.getTimes());
    		    }   
    		}
			
			movies.add(m);
       }
    	
    for(MovieFromVillage mv : movies)
    	{
    		System.out.println(mv.getMovie());
    		ArrayList<Theater> th = mv.getTheater();
    		for(Theater t1 : th) {
    			System.out.print(t1.getTheater()+" ");
		    	System.out.println(t1.getDate() + t1.getTimes());
    		}
    	
    		System.out.println("=========================");
    	}
    }
    
}
