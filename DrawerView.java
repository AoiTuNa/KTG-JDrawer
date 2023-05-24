import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawerView extends JPanel implements MouseListener, MouseMotionListener{

    int startX;
    int startY;

    DrawerView() {
        startX = 0;
        startY = 0;

        addMouseListener(this);
        addMouseMotionListener(this);
    }
    // hook function
    // paint event(화면이 갱신되어야 할 때 호출되는 함수) 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();

        int minX = Math.min(startX, endX);
        int minY = Math.min(startY, endY);
        int width = Math.abs(endX-startX);
        int height = Math.abs(endY-startY);

        Graphics g = getGraphics();
        g.drawRect(minX, minY, width, height);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();

        int minX = Math.min(startX, endX);
        int minY = Math.min(startY, endY);
        int width = Math.abs(endX-startX);
        int height = Math.abs(endY-startY);

        Graphics g = getGraphics();
        g.drawRect(minX, minY, width, height);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
