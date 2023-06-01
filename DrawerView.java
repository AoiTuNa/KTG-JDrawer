import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawerView extends JPanel implements MouseListener, MouseMotionListener{

    static int MAX = 100;
    public static int DRAW_BOX = 1;
    public static int DRAW_LINE = 2;
    
    private int whatToDraw;
    private Figure cuurrentFigure;
    private ArrayList<Figure> figures = new ArrayList<Figure>();

    DrawerView() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    void setWhatToDraw(int figureType){
        whatToDraw = figureType;
    }
    // hook function
    // paint event(화면이 갱신되어야 할 때 호출되는 함수) 
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i =0; i< figures.size(); i++){
           //for each 문
            for(Figure pfigures : figures){
            pfigures.draw(g);
           }
            // Demeter's Law
            /*Box p =  boxes.get(i);
            p.draw(g);*/
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
         Graphics g = getGraphics();
        g.setXORMode(getBackground());// 겹치는 색을 덧칠하지 않음
        cuurrentFigure.drawing(g,e.getX(), e.getY());
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (whatToDraw == DRAW_BOX){
            cuurrentFigure = new Box(e.getX(),e.getY());
        }else if(whatToDraw == DRAW_LINE){
            cuurrentFigure = new Line(e.getX(),e.getY());
        }
        figures.add(cuurrentFigure);
        //polymorphic collection object
        //polymorphic container
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics g = getGraphics(); 
        cuurrentFigure.setXY2(e.getX(),e.getY());
        cuurrentFigure.draw(g);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}