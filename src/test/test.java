package test;

import server.Server;

public class test{
public static void main(String[] args) throws Exception {
	//new Server().MessageLu(new Timestamp(new java.util.Date("02/08/2011 12:00:00").getTime()), "hasnaa@rs.com");
	int a=new Server().listeRelation("hasnaa@rs.com").size();
	System.out.println(a);
}
}