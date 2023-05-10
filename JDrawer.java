import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 
class DrawrFrame extends JFrame{
    DrawrFrame() {
        setTitle("Drawer");
        setSize(700,500);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class Drawer{
    public static void main(String[] args){
        JFrame drawerFrame = new DrawrFrame();
        drawerFrame.setVisible(true);
    }
}