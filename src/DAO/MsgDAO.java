package DAO;

import java.text.SimpleDateFormat;
import java.util.Vector;
import beans.Message;

public class MsgDAO {
	public static int nbrNonLu=0;
	public static Vector<Message> MsgNonLu(){
		Vector<Message> nonLu=new Vector<Message>();
		Vector<Message> v=vectorMsgRecevoire();
		for(Message d:v){
			if(d.getEtat()==0){
				nonLu.add(d);
			}
		}
		return nonLu;
	}
	static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	public static String[][] listeMsgRecevoire(){
		nbrNonLu=0;
		int k=0;
		Vector<beans.Message> data=DataDAO4.data();
		
		String[][] liste=new String[data.size()][5];
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).getDest().equals(Client.email)){
				liste[k][0]=data.get(i).getSrc();
				liste[k][1]=sdf.format(data.get(i).getDate());
				liste[k][2]=data.get(i).getObject();
				liste[k][3]=data.get(i).getMsg();
				if(data.get(i).getEtat()==0 ){
					liste[k][4]="Non Lu";
					nbrNonLu+=1;
				}else{
					liste[k][4]="Lu";
				}
				k=k+1;
			}
		}
		return liste;
	}
	public static Vector<Message> vectorMsgRecevoire(){
		Vector<beans.Message> data=DataDAO4.data();
		Vector<beans.Message> liste=new Vector<beans.Message>();
		for (Message d:data) {
			if(d.getDest().equals(Client.email)){
				liste.add(d);
			}
		}
		return liste;
	}
	public static String[][] listeMsgEnvoyer(){
		int k=0;
		Vector<beans.Message> data=DataDAO4.dataEnvoie();
		String[][] liste=new String[data.size()][4];
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).getSrc().equals(Client.email)){
				liste[k][0]=data.get(i).getDest();
				liste[k][1]=sdf.format(data.get(i).getDate());
				liste[k][2]=data.get(i).getObject();
				liste[k][3]=data.get(i).getMsg();
				k=k+1;
			}
		}
		return liste;
	}
	public static String[] TitrelisteMsg(){
		String[] listeMsg={"E Mail","Date","Objet","Message"};
		return listeMsg;
	}
	public static String[] TitrelisteMsgRecevoir(){
		String[] listeMsg={"E Mail","Date","Objet","Message","Lu/Non Lu"};
		return listeMsg;
	}
	public static void ModifEtatMsg(){
		
	}
}
