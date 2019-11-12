package com.framework;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Celda_CheckBox extends DefaultCellEditor implements TableCellRenderer  {
 
	private static final long serialVersionUID = 1L;
	private JComponent component = new JCheckBox();
    private boolean value = false; 
 
    public Celda_CheckBox() {
        super( new JCheckBox() );
    }
 

    @Override
    public Object getCellEditorValue() {
        return ((JCheckBox)component).isSelected();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ( (JCheckBox) component).setBackground( new Color(200,200,0) );
        boolean b = ((Boolean) value).booleanValue();
        ( (JCheckBox) component).setSelected( b );
        return ( (JCheckBox) component);
    }
 

    @Override
    public boolean stopCellEditing() {
        value = ((Boolean)getCellEditorValue()).booleanValue() ;
        ((JCheckBox)component).setSelected( value );
        return super.stopCellEditing();
    }
 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;
         return ( (JCheckBox) component );
    }
 
}