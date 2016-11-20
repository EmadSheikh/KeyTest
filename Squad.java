import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Squad {
	HttpCon h= new HttpCon();
	Player p= new Player();
	public Player[] squad_team1;
	String team1;
	public Player[] squad_team2;
	String team2;
	public int series_id;
	
	
	public Squad(){
		squad_team1=new Player[18];
		squad_team2=new Player[18];
		for(int i=0;i<18;i++){
			squad_team1[i]=new Player();
			squad_team2[i]=new Player();
		}
		//squad_team2=null;
	}
	
	public void get_squad(final JSONObject team1_,final JSONObject team2_) throws JSONException,NullPointerException{
		team1=team1_.getString("name");
		team2=team2_.getString("name");
		//CheckTeam("Pakistan 1st Innings");
		// Country names----------------
		System.out.println(team1+"-------------\n");
		
		
		//Putting Players in Array---------------
		final JSONArray players1=team1_.getJSONArray("players");
		final JSONArray players2=team2_.getJSONArray("players");
		//System.out.println(players1);
		//System.out.println(players2);
		
		int n1=players1.length();
		for(int i=0;i<n1;i++){	//Team1 k players initiate horahe hen
			final JSONObject player=players1.getJSONObject(i);
		     squad_team1[i].setName(player.getString("name"));
		     p=squad_team1[i];
		     String id = player.getString("pid");
		     String response=h.Stats(id);//players k cost or type
		     h.parseStats(response,p);
		     //String a=squad_team1[i].getType();
		     //if (a.equals("batter"))
		    	 //System.out.println(i+" Batters : "+squad_team1[i].name);
			
		}
		//Display_Squad(squad_team1);
		
		System.out.println(team2+"-------------\n");
		
		
		int n2=players2.length();
		for(int i=0;i<n2;i++){	//Team2 k players initiate horahe hen
			final JSONObject player=players2.getJSONObject(i);
			squad_team2[i].setName(player.getString("name"));
			 p=squad_team2[i];
		     String id = player.getString("pid");
		     String response=h.Stats(id);
		     h.parseStats(response,p);//players k cost or type 
		     //String a=squad_team1[i].getType();
		     //if (a.equals("batter"))
		    	// System.out.println(i+"  "+squad_team2[i].name);
		    	 
		}
		//Display_Squad(squad_team1);//displaying the squad
		//Display_Squad(squad_team2);
		
	}
	
	
public void Display_Squad(Player[] P){
	int n1=P.length;
	for(int i=0;i<n1;i++){
		System.out.println(i+" --");
		P[i].Display_Player();//har player ki info print hogii!!
	}
	
	System.out.println("\n\n\n");
}

public Boolean CheckTeam(String str){
	System.out.println("Team Playing innings:  "+ str);
	System.out.println("Name of Squad:  "+ team1);
	
	if(str.contains(team1)){
		System.out.println(team1+" - returning false \n\n");
		return false;
	}
	else{
		System.out.println(team2+" -   returning true \n\n");
		return true;
	}
	
	
}

public Player[] return_squad( Boolean t){
	if(t==true)
		return squad_team2;
	else {
		System.out.println("england aagaya");
		//Display_Squad(squad_team1);
		return squad_team1;
}
}
public void set_squad(Player[]P, Boolean B){
	if(B==true){
		squad_team2=P;
		//System.out.println("!!!!!!!!!!!!!!!!!!!!INDIAAAA!!!!!!!!!!!!!!!!!");
		//Display_Squad(squad_team2);
	}
	else{ 
		squad_team1=P;
		//System.out.println("this is england");
		//Display_Squad(squad_team1);
		
	}

}
	
	
}
