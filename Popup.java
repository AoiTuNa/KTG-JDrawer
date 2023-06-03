import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Popup {
    JPopupMenu popupPter;
    Popup(String title){
        popupPter = new JPopupMenu();
        popupPter.add(title);
        popupPter.addSeparator();
    }
    public void popup(JPanel view,int x, int y){
        popupPter.show(view,x,y);
    }

    
}
