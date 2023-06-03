import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainPopup extends Popup {
    MainPopup(DrawerView view){
        super("그림");
        JMenuItem boxItem = new JMenuItem("box (B)");
        popupPter.add(boxItem);
        boxItem.addActionListener( (event) -> view.setWhatToDraw(DrawerView.DRAW_BOX));
        JMenuItem LineItem = new JMenuItem("Line (L)");
        popupPter.add(LineItem);
        LineItem.addActionListener( (event) -> view.setWhatToDraw(DrawerView.DRAW_LINE));
    }
    
            
}
