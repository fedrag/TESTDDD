package org.eternity.common;

public class DBManager {
	
	public static void startHsqlDb(){
		try{
			  org.hsqldb.Server.main(new String[]{"-database.0", "file:mydb"});
		} catch(Exception e){
		   // You know what to do here
		}
	}

}
