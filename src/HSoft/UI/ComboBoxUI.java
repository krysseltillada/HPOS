package HSoft.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;
 
public class ComboBoxUI extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;
     
    public ComboBoxUI() {
         
        label.setOpaque(false);
        label.setFont(new Font("C:\\Users\\Gina\\Desktop\\cs 202 project\\POS\\src\\fonts\\roboto\\Roboto-Regular.ttf", Font.PLAIN, 11));
        label.setForeground(new Color (235, 235, 235));
         
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        panel.setBackground(new Color (51,51,51));
     
    }
     
    public Component getEditorComponent() {
        return this.panel;
    }
     
    public Object getItem() {
        return  this.selectedItem.toString() ;
    }
     
    public void setItem(Object item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }
     
}