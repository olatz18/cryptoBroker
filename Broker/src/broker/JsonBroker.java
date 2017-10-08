package broker;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
//import org.json.*;
import com.google.gson.*;



public class JsonBroker {
	private static JsonBroker nireJson;

	
		public static JsonBroker getNireJson() {
			if (nireJson == null) {
				nireJson = new JsonBroker();
			}
			return nireJson;
		}
	
	private JsonBroker(){
		
	}
	public static void main(String[] args){
		
		JsonBroker j = JsonBroker.getNireJson();
		j.irakurri();
	
	}
	
	 public void irakurri(){
		  int erositakoTxanponakBtc=0;
		  int saldutakoTxanponakBtc=0;
		  int erositakoTxanponakEth=0;
		  int saldutakoTxanponakEth=0;
		  String kostuaBtc="0";
		  String kostuaEth="0";
		  String btc = "BTC-EUR";
		  String eth = "ETH-EUR";
		  String fitxategi ="mezuak.json";
		  try {
		   Scanner sc = new Scanner(new File(fitxategi));
		   while (sc.hasNext()){
		    //System.out.println("while");
		    String mezua = sc.next();
		    //System.out.println(mezua);
		    String eragiketa = eragiketaBueltatu(mezua);
		    if(eragiketa.equals("received")){
		     //System.out.println("recived");
		     if(txanponMota(mezua).equals(btc)){
		      //System.out.println("txanpon mota");
		      if(erosi(mezua)){
		      // System.out.println("erosi");
		      erositakoTxanponakBtc++;
		      //System.out.println(erositakoTxanponakBtc);
		      }
		      else{
		      saldutakoTxanponakBtc++;
		      //System.out.println(saldutakoTxanponakEth);
		     }
		     }
		     else{
		      //System.out.println("eth");
		      if(erosi(mezua)){
		       //System.out.println("erosi");
		       erositakoTxanponakEth++;
		       //System.out.println(erositakoTxanponakEth);
		      } 
		      else{
		       saldutakoTxanponakEth++;
		       //System.out.println(saldutakoTxanponakEth);
		      }
		      
		     }
		    }
		     if(eragiketa.equals("match")){
		      if(txanponMota(mezua).equals(btc)){
		       kostuaBtc= kostuaBueltatu(mezua);
		       }
		      else{
		       kostuaEth= kostuaBueltatu(mezua);
		       }
		      }
		     }
		   //}
		   
		   System.out.println("ETH erosteko egindako agindu kopurua"+ erositakoTxanponakEth);
		   System.out.println("ETH saltzeko egindako agindu kopurua"+ saldutakoTxanponakEth);
		   
		   System.out.println("Azken prezioa: " + kostuaBtc);
		   
		   System.out.println("BTC erosteko egindako agindu kopurua"+ erositakoTxanponakBtc);
		   System.out.println("BTC saltzeko egindako agindu kopurua"+ saldutakoTxanponakBtc);
		   
		   System.out.println("Azken prezioa: " + kostuaEth );
		   
		   
		  }
		   catch (FileNotFoundException e) {
		    System.out.println("catch-ean sartu da");
		   e.printStackTrace();
		  }
		 }
		 
		 
		 
		 
		 public String txanponMota(String mezua){
		  JsonParser parser = new JsonParser();
		     JsonObject o = parser.parse(mezua).getAsJsonObject();
		     String txanponMota = o.get("product_id").getAsString();
		     return txanponMota;
		 }
		 public String eragiketaBueltatu(String mezua){
		  JsonParser parser = new JsonParser();
		     JsonObject o = parser.parse(mezua).getAsJsonObject();
		     String eragiketa = o.get("type").getAsString();
		     return eragiketa;
		 }
		 public boolean erosi(String mezua){
		  JsonParser parser = new JsonParser();
		     JsonObject o = parser.parse(mezua).getAsJsonObject();
		     String eragiketa = o.get("side").getAsString();
		     boolean erosi = false;
		     if(eragiketa.equals("buy")){
		      erosi=true;
		     }
		     return erosi;
		     
		 }
		 public String kostuaBueltatu(String mezua){
		  JsonParser parser = new JsonParser();
		     JsonObject o = parser.parse(mezua).getAsJsonObject();
		     String kostua = o.get("price").getAsString();
		     return kostua;
		 }
		 
		
}
