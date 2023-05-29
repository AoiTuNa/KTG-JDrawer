import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawerView extends JPanel implements MouseListener, MouseMotionListener{

    static int MAX = 100;
    private Box pBox;
    private Box boxes[];
    private int nBox;
    DrawerView() {
        pBox = null;
        boxes = new Box[MAX];
        int nBox = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    // hook function
    // paint event(화면이 갱신되어야 할 때 호출되는 함수) 
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i =0; i< nBox; i++){
            boxes[i].draw(g);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
         Graphics g = getGraphics();
        g.setXORMode(getBackground());// 겹치는 색을 덧칠하지 않음
        
        pBox.drawing(g,e.getX(), e.getY());
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        pBox = new Box(e.getX(),e.getY());
        boxes[nBox++] = pBox;
       
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        pBox.setXY2(e.getX(),e.getY());
        Graphics g = getGraphics(); 
        pBox.draw(g);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
