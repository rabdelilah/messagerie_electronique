package mailing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import DAO.Client;
import DAO.DataDAO4;
import DAO.MsgDAO;


class Renderer implements TableCellRenderer {

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
    	background=Color.yellow;
    	Mail.getJEditorPaneMsg().setText(table.getValueAt(row, 3).toString());
        v.add(value.toString());
    } else if(!v.contains(value)){
    	tag=false;
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