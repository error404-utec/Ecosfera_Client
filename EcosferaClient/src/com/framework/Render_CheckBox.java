package com.framework;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Render_CheckBox extends JCheckBox implements TableCellRenderer {
 

	private static final long serialVersionUID = 1L;
	private JComponent component = new JCheckBox();
 
    public Render_CheckBox() {
        setOpaque(true);
    }
 
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      ( (JCheckBox) component).setBackground( new Color(255,255,255) );
      boolean b = ((Boolean) value).booleanValue();
      ( (JCheckBox) component).setSelected( b );
      return ( (JCheckBox) component);
  }
 
}