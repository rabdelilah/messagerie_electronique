package mailing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import DAO.Client;
import DAO.DataDAO4;
import beans.Message;
import beans.demande;

class EvenOddRenderer implements TableCellRenderer {

  public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
  Vector<Object> v=new Vector<Object>();
  boolean tag=false;
  public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    ((JLabel) renderer).setOpaque(true);
    Color foreground = Color.black, background = Color.white;
    int fond=0,size=12;
    
    try {
		if (isSelected) {
    	if(table.getValueAt(row, 4).equals("Non Lu") && !tag){
    		java.util.Date d=new java.util.Date(table.getValueAt(row, 1).toString());
    		Message ancienM=new Message(table.getValueAt(row, 0).toString(),Client.email,new Timestamp(d.getTime()),table.getValueAt(row, 2).toString(),table.getValueAt(row, 3).toString(),(short)0);
    		Message m=new Message(table.getValueAt(row, 0).toString(),Client.email,new Timestamp(d.getTime()),table.getValueAt(row, 2).toString(),table.getValueAt(row, 3).toString(),(short)1);
    		Client.Lire(new demande("MessageLu",m));
    		table.setValueAt("Lu", row, 4);
    		Client.Lire(new demande("identification",Client.utilisateur.getMail()));
    		tag=true;
    	}
    	background=Color.yellow;
    	Mail.getJEditorPaneMsg().setText(table.getValueAt(row, 3).toString());
      v.add(value.toString());
    } else if(!v.contains(value) && table.getValueAt(row, 4).equals("Non Lu")){
    	tag=false;
        fond=Font.BOLD;
        size=13;
    }
    renderer.setForeground(foreground);
    renderer.setBackground(background);
    renderer.setFont(new Font("Dialog", fond, size));
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    return renderer;
  }
}