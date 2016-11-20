import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.*;

public class HttpCon {
	public static String responseBody;
	static Squad Sq;//INITIATING SQUAD PROJECT ----------------------
	protected String keyTest() {
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();

		HttpGet httpGet = new HttpGet("http://cricapi.com/api/cricket");
		// HttpGet httpGet = new
		// HttpGet("http://cricapi.com/api/cricketScore?unique_id=1050229");
		// HttpGet httpGet = new
		// HttpGet("http://cricapi.com/api/cricketCommentary?unique_id=1050229");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return responseBody;
	}

	protected String MatchScore() {
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();

		// HttpGet httpGet = new HttpGet("http://cricapi.com/api/cricket");
		HttpGet httpGet = new HttpGet("http://cricapi.com/api/cricketScore?unique_id=1050229");
		// HttpGet httpGet = new
		// HttpGet("http://cricapi.com/api/cricketCommentary?unique_id=1050229");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return responseBody;
	}

	protected String PlayerNames() {
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(  
				//1050229
				"http://cricapi.com/api/fantasySquad?unique_id=667729&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			System.out.println("GAYA INTERNET\n");
			return e.getLocalizedMessage();
		}

		return responseBody;
	}

	public static void parsingNames(String response_, Player p, String id) throws JSONException {
		String response2_;
		// response_='{'+response_;
		final JSONObject obj1 = new JSONObject(response_);
		final JSONArray geodata1 = obj1.getJSONArray("squad"); // getting squad
		//System.out.println(geodata1+"\n\n\n");
		final JSONObject team1=geodata1.getJSONObject(0);
		final JSONObject team2=geodata1.getJSONObject(1);
		//System.out.println(team1);
		//System.out.println(team2);
		//Squad Sq= new Squad();//INITIATING SQUAD PROJECT ----------------------
		Sq.get_squad(team1,team2);
		
		//System.out.println(obj1);
	/*	final int n1 = geodata1.length();
		for (int i = 0; i < n1; ++i) {
			final JSONObject person = geodata1.getJSONObject(i);
			final JSONArray names_ = person.getJSONArray("players"); // player
																		// array
			final int n2 = names_.length();
			for (int j = 0; j < n2; ++j) {
				final JSONObject person2 = names_.getJSONObject(j);
				p.name = person2.getString("name");
				//System.out.println(p.name);// individual names
				//id = person2.getString("pid");
				//response2_ = Stats(id);
				//parseStats(response2_, p);

			}
*/		}
	

	private static String fantasyScores() throws JSONException {
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();

		// HttpGet httpGet = new HttpGet("http://cricapi.com/api/cricket");
		HttpGet httpGet = new HttpGet(
				"http://cricapi.com/api/fantasySummary?unique_id=667729&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
		// HttpGet httpGet = new
		// HttpGet("http://cricapi.com/api/cricketCommentary?unique_id=1050229");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		parseFantasyScores(responseBody);
		return responseBody;
	}

	private static void parseFantasyScores(String responseBody) throws JSONException {
		
		final JSONObject obj2 = new JSONObject(responseBody);
		final JSONObject data = obj2.getJSONObject("data");
		//System.out.println(data);

		//-------------------------BATTING-----------------------------------------------------------
		
		final JSONArray geodata1 = data.getJSONArray("batting"); // getting
		Player[] P1= new Player[16];
																	// squad
																	// array
		final int n1 = geodata1.length();
		for (int i = 0; i < n1; i++) {
			final JSONObject person = geodata1.getJSONObject(i);
			//System.out.println(i+" --i-->"+person);
			String team_name=person.getString("title");
			int r=i;
			final JSONArray names_ = person.getJSONArray("scores"); // player
			System.out.println("####                  "+team_name);
			Boolean inning_Check=Sq.CheckTeam(team_name);// INNING CHECK HORAHI HE K KON SI TEAM HE
			//
			
			System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
			
			if(inning_Check==true){	// SQUAD GET HORAHA HE
				P1=Sq.return_squad(true);
				//Sq.Display_Squad(P1);
			}
			
			else{
				
				P1=Sq.return_squad(false);
				//Sq.Display_Squad(P1);
			}
			final int n2 = names_.length();
			for (int j = 0; j < n2; j++) {
				final JSONObject person2 = names_.getJSONObject(j);
				//System.out.println(j+" ->>  "+person2);
				//CHeck for batsman "EXTRAS" "TOTAL"
				String batsman_NAME=person2.getString("batsman");
				if(batsman_NAME.equals("Total")){
					String runs = person2.getString("R");
					
					//System.out.println("Total Score " + runs);// TOTAL SCORE
				}
				else if(batsman_NAME.equals("Extras")){
					String runs = person2.getString("R");
					//System.out.println("Extras: " + runs);// TOTAL EXTRAS IN THE INNING
					}
				else{
					batsman_NAME=batsman_NAME.substring(3);
					//System.out.println(j+"--> "+batsman_NAME);
					Player P=new Player();
					int k;
					for(k=0;k<16;k++){
						
							P=P1[k]; //PLAYERS K NAAM COMPARE K RAHA HE
							if(batsman_NAME.contains("†")) {
								//System.out.println("wiccket keeper");
								int index= batsman_NAME.indexOf('†');
								batsman_NAME=batsman_NAME.replace('†',' ');
								batsman_NAME=batsman_NAME.trim();
							}
							if(batsman_NAME.contains("*")) {
								//System.out.println("captain");
								int index= batsman_NAME.indexOf('*');
								batsman_NAME=batsman_NAME.replace('*',' ');
								batsman_NAME=batsman_NAME.trim();
							}
							if(P.name.contains(batsman_NAME)){
								break;
							}
							
							else{	P=P1[3];
								//break;
							}
						
						}
				
					String runs = person2.getString("R");
					P.runs=Integer.parseInt(runs); //PARTICULAR PLAYER K RUNS WAGERA 
					System.out.println("Runs " + runs);// individual runs
					//System.out.println("New Runs "+ P.runs);
					String Balls = person2.getString("B");
					//System.out.println("Balls " + Balls);
					
					
					//System.out.println("\n");
					String SR = person2.getString("SR");
					P.SR=Double.parseDouble(SR); //PARTICULAR PLAYER Ka SR 
					
					//System.out.println("SR " + SR);
					//System.out.println("\n");
					P1[k]=P;
					//System.out.println("economy " + eco);
					}
			}

			Sq.set_squad(P1, inning_Check); // UPDATES SQUAD WITH RUNS OF THE EACH PLAYER 
		}
	
		
		
		//-------------------------BOWLING-----------------------------------------------------------
		System.out.println("-------------------------BOWLING-----------------------------------------------------------");
			final JSONArray geodata2 = data.getJSONArray("bowling"); // getting
			
			final int n2 = geodata2.length();
			for(int i=0;i<n2;i++){
					final JSONObject bowl1= geodata2.getJSONObject(i);
					
					//System.out.println(bowl1);
					String team_name=bowl1.getString("title");
					System.out.println("####                  "+team_name);
					Boolean inning_Check=Sq.CheckTeam(team_name);// INNING CHECK HORAHI HE K KON SI TEAM HE
					//
					
					System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
					
					if(inning_Check!=true){	// SQUAD GET HORAHA HE
						P1=Sq.return_squad(true);
						//Sq.Display_Squad(P1);
					}
					
					else{
						
						P1=Sq.return_squad(false);
						//Sq.Display_Squad(P1);
					}
					final JSONArray inning=bowl1.getJSONArray("scores");
					
				
					//System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
					final int inning_size = inning.length();
					for (int j = 0; j < inning_size; ++j) {
						final JSONObject bowler = inning.getJSONObject(j);
						//System.out.println(j+" ->>  "+bowler);
						String bowler_NAME=bowler.getString("bowler");
						bowler_NAME=bowler_NAME.substring(3);
						//System.out.println(j+"--> "+bowler_NAME);
						Player P=new Player();
						int k;
						for(k=0;k<16;k++){
							
								P=P1[k]; //PLAYERS K NAAM COMPARE K RAHA HE
								if(bowler_NAME.contains("†")) {
									//System.out.println("Captain");
									bowler_NAME=bowler_NAME.replace('†',' ');
									bowler_NAME=bowler_NAME.trim();
								}
								if(bowler_NAME.contains("*")) {
									//System.out.println("wicket keeper");
									int index= bowler_NAME.indexOf('*');
									bowler_NAME=bowler_NAME.replace('*',' ');
									bowler_NAME=bowler_NAME.trim();
								}
								if(P.name.contains(bowler_NAME)){
									//System.out.println(k+"--> "+P.name);
									break;
								}
								
								else{	P=P1[3];
									//break;
								}
							
							}
							//System.out.println(j+"--> "+bowler_NAME);	
							String runs = bowler.getString("R");
							P.runs=Integer.parseInt(runs);
							//System.out.println("Runs " + runs);// individual runs
							String wickets = bowler.getString("W");
							P.wickets=Integer.parseInt(wickets);
							//p[j].wickets=p[j].wickets+Integer.parseInt(wickets);
							//System.out.println("Wickets " + wickets);//individual wickets
							String Econ=bowler.getString("Econ");
							P.economy=Double.parseDouble(Econ);
							//	System.out.println("Economy " + Econ);//individual economy
							P1[k]=P;
							System.out.println("\n");
							
						
							}
					Sq.set_squad(P1, !inning_Check);
			}
					
		
		//-----------------------------FIElDING-------------------------------------------
			System.out.println("-----------------------------FIElDING-------------------------------------------");
			final JSONArray geodata3 = data.getJSONArray("fielding"); // getting
				final int n3 = geodata3.length();
				for(int i=0;i<n3;i++){
					final JSONObject field1= geodata3.getJSONObject(i);
					System.out.println(field1+"\n");
					final JSONArray inning=field1.getJSONArray("scores");
					String team_name=field1.getString("title");
					System.out.println("####                  "+team_name);
					Boolean inning_Check=Sq.CheckTeam(team_name);// INNING CHECK HORAHI HE K KON SI TEAM HE
					//
					
					System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
					
					if(inning_Check!=true){	// SQUAD GET HORAHA HE
						P1=Sq.return_squad(true);
						//Sq.Display_Squad(P1);
					}
					
					else{
						
						P1=Sq.return_squad(false);
						//Sq.Display_Squad(P1);
					}
					System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
					final int inning_size = inning.length();
					
					for (int j = 0; j < inning_size; ++j) {
						final JSONObject fielder = inning.getJSONObject(j);
						
						//System.out.println(j+" ->>  "+fielder);
						String fielder_NAME=fielder.getString("name");
						fielder_NAME=fielder_NAME.substring(3);
						//System.out.println(j+"--> "+fielder_NAME);
						Player P=new Player();
						int k;
						for(k=0;k<16;k++){
							
								P=P1[k]; //PLAYERS K NAAM COMPARE K RAHA HE
								if(fielder_NAME.contains("†")) {
									//System.out.println("Captain");
									fielder_NAME=fielder_NAME.replace('†',' ');
									fielder_NAME=fielder_NAME.trim();
								}
								if(fielder_NAME.contains("*")) {
									//System.out.println("wicket keeper");
									int index= fielder_NAME.indexOf('*');
									fielder_NAME=fielder_NAME.replace('*',' ');
									fielder_NAME=fielder_NAME.trim();
								}
								if(P.name.contains(fielder_NAME)){
									//System.out.println(k+"--> "+P.name);
									break;
								}
								
								else{	P=P1[3];
									//break;
								}
							
							}
					
					//	System.out.println(j+" ->>  "+fielder_NAME);
						int catch_=fielder.getInt("catch");
						//System.out.println("catches "+catch_);
						P.catches=catch_;
						P1[k]=P;
					}
					
					Sq.set_squad(P1, !inning_Check);
					Sq.Display_Squad(P1);
				}
			// -----------------------GETTING TEAM--------------------------
				//final JSONArray team = data.getJSONArray("team"); // getting
				//final int team_size = team.length();
				
				
	
	}
	
	private static void calculatebattingTest(String runs_, String balls_,
			Player p) {
		
		int runs,balls,points;
		runs=Integer.parseInt(runs_);
		balls=Integer.parseInt(balls_);
		p.setPoints(runs);
	}

	public void parseStats(String response2_, Player p) throws JSONException {
		Double []BattingAvg=new Double[3];
		 Double []BowlingAvg=new Double[3];
		  final JSONObject obj1 = new JSONObject(response2_);
		  final JSONObject data= obj1.getJSONObject("data");
		 // System.out.println(data);
		  // GETTING Batting AVERAGES
		  final JSONObject batting= data.getJSONObject("batting");
		  final JSONObject ListA= batting.getJSONObject("listA");
		  final JSONObject FC= batting.getJSONObject("firstClass");
		//  final JSONObject tests= batting.getJSONObject("tests");
		//GETTING BOWLING AVERAGE NOW
		  final JSONObject bowling= data.getJSONObject("bowling");
		  final JSONObject ListA_= bowling.getJSONObject("listA");
		  final JSONObject FC_= bowling.getJSONObject("firstClass");
		//  final JSONObject tests_= bowling.getJSONObject("tests");
		  String[] batAverages=new String[3];
		  String[] bowlAverages=new String[3];
		  
		  //SOME INPUT HAVE "-" instead of numbers...  
		  batAverages[0]=(String) FC.get("Ave");
		  batAverages[1]=(String) ListA.get("Ave");
		 // batAverages[2]=(String) tests.get("Ave");
		  bowlAverages[0]=(String) FC_.get("Ave");
		  bowlAverages[1]=(String) ListA_.get("Ave");
		//  bowlAverages[2]= (String) tests_.get("Ave");
		  
		  for(int i=0; i<2;i++){
			 // System.out.println(batAverages[i]);
			  if(batAverages[i].equals("-")){
				  BattingAvg[i]=0.0;
			  }
			  else {
				  BattingAvg[i]=(Double.parseDouble(batAverages[i]));
			  }
		  }
		  
		  //System.out.println(a+"DDDDDDDDDDDDDDDDDDD");
		  for(int i=0; i<2;i++){
			  //System.out.println(bowlAverages[i]);
			  if(bowlAverages[i].equals("-")){
			//	  System.out.println("DDDDDDDDDDDDDDDDDDD");
				  BowlingAvg[i]=0.0;
			  }
			  else {
				  BowlingAvg[i]=(Double.parseDouble(bowlAverages[i]));
			  }
		  }
		 // System.out.println("\n ----------- Checking--\n");
		  p.setcost("", BattingAvg, BowlingAvg);
		  double a=p.printCost();
		 
	 }
	static String Stats(String id) {
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://cricapi.com/api/playerStats?pid=" + id);
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		// System.out.println(responseBody);
		return responseBody;
	}
	private static String Squad()  throws JSONException{
		HttpClient httpClient = new DefaultHttpClient();
		BasicHttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(
				"http://cricapi.com/api/fantasySummary?unique_id=667729&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			// text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		parseSquad(responseBody);
		return responseBody;
	}
	
	private static void parseSquad(String responseBody) throws JSONException {
		final JSONObject obj2 = new JSONObject(responseBody);
		final JSONObject data = obj2.getJSONObject("data");
		//System.out.println(data);
		
		final JSONArray team = data.getJSONArray("team");
		System.out.println(team);
		final JSONObject team1=team.getJSONObject(0);
		final JSONObject team2=team.getJSONObject(1);
		//System.out.println(team1);
		//System.out.println(team2);
		//Sq = new Squad();
		//Sq.get_squad(team1,team2);
		
		
		
		
		
		
		
	}
	public static void main(String args[]) throws UnsupportedEncodingException, JSONException {
		Player p = new Player();
		String id = null;
		HttpCon t = new HttpCon();
		t.Sq= new Squad();//INITIATING SQUAD PROJECT ----------------------
		String response_;

		
		  System.out.println(
		  "\n\n API Key that Provides Available squad of a team: ");
		 response_=t.PlayerNames(); parsingNames(response_,p,id);
		 
		
		
		/*System.out.println("\n\n API Key that Provides Fantasy summary of a team: ");
		response_ = t.fantasyScores();
		// parsingNames(response_,p,id);
		 * */
		
		System.out.println("\n\n API Key --------- GETTING SQUAD -------------- ");
		//response_ = t.Squad();
		
		 fantasyScores();
	

	}

} // System.out.println(response_);
