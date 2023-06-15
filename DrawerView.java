import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawerView extends JPanel implements MouseListener, MouseMotionListener{

    static int MAX = 100;
    public static int DRAW_POINT = 1;
    public static int DRAW_BOX = 2;
    public static int DRAW_LINE = 3;
    public static int DRAW_CIRCLE = 4;

    public static int NOTHING = 0;
    public static int DRAWING = 1;
    public static int MOVING = 2;
    
    private int actionMode;
    private int whatToDraw;
    private Figure selectedFigure;
    private Color selectedColor;
    private ArrayList<Figure> figures = new ArrayList<Figure>();

    private int currentX;
    private int currentY;

    Popup mainPopup;
    Popup pointPopup;
    Popup boxPopup;
    Popup linePopup;
    Popup circlePopup;

    DrawerView() {
        mainPopup = new MainPopup(this);
        pointPopup = new FigurePopup(this,"Point",false);
        boxPopup = new FigurePopup(this,"Box",true);
        linePopup = new FigurePopup(this,"Line",false);
        circlePopup = new FigurePopup(this, "Circle", true);
        actionMode = NOTHING;
        whatToDraw = DRAW_BOX;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    Popup PointPopup() {
        return pointPopup;
    }
    Popup boxPopup() {
        return boxPopup;
    }
    Popup linePopup(){
        return linePopup;
    }
    Popup circlePopup(){
        return circlePopup;
    }
    void setWhatToDraw(int figureType){
        whatToDraw = figureType;
    }
    // hook function
    // paint event(화면이 갱신되어야 할 때 호출되는 함수) 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //for each 문
        for(int i =0; i< figures.size(); i++){
            for(Figure pfigures : figures){
            pfigures.draw(g);
           }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
         Graphics g = getGraphics();
        g.setXORMode(getBackground());// 겹치는 색을 덧칠하지 않음
        if (actionMode == DRAWING){
            selectedFigure.drawing(g,x, y);
        }else if (actionMode == MOVING){
            selectedFigure.move(g,x-currentX,y-currentY);
            currentX = x;
            currentY = y;
        }
    }
    private Figure find(int x, int y){
        for(int i = 0; i< figures.size(); i++){
            Figure pFigure = figures.get(i);
            if (pFigure.contains(x,y)){
                return pFigure;
            }
        }
        return null;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        selectedFigure = find(x,y);
        if (selectedFigure != null){
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else{
            setCursor(Cursor.getDefaultCursor());
        }
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        if (e.getButton() == MouseEvent.BUTTON3){//우클릭 = BUTTON3
            actionMode = NOTHING;
            return;
        }
        selectedFigure = find(x,y);
        if(selectedFigure != null){
            actionMode = MOVING;
            currentX = x;
            currentY = y;
            figures.remove(selectedFigure);
            return;
        }
        if (whatToDraw == DRAW_POINT){
            selectedFigure = new Point(selectedColor,x,y);
            selectedFigure.setPopup(pointPopup);
        }else if (whatToDraw == DRAW_BOX){
            selectedFigure = new Box(selectedColor,x,y);
            selectedFigure.setPopup(boxPopup);
        }else if(whatToDraw == DRAW_LINE){
            selectedFigure = new Line(selectedColor,x,y);
            selectedFigure.setPopup(linePopup);
        }else if(whatToDraw == DRAW_CIRCLE){
            selectedFigure = new Circle(selectedColor,x,y);
            selectedFigure.setPopup(circlePopup);
        }
        actionMode = DRAWING;
        //polymorphic collection object
        //polymorphic container
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Graphics g = getGraphics(); 
        if (e.isPopupTrigger()){// 우클릭
            selectedFigure = find(x, y);
            if (selectedFigure == null){
                mainPopup.popup(this,x,y);
            } else {
                selectedFigure.popup(this,x,y);
            }
            return;
        }
        if (actionMode == DRAWING){
            selectedFigure.setXY2(x,y);
        }else if (actionMode == MOVING){
        }
        selectedFigure.draw(g);
        addFigure(selectedFigure);
        selectedFigure = null;
    }
    public void addFigure(Figure newFigure){
        newFigure.makeRegion();
        figures.add(newFigure);
        repaint();
    }
    public void delectFigure(){
        if (selectedFigure ==null) return;
        figures.remove(selectedFigure);
        selectedFigure = null;
        repaint();
    }
    public void fillFigure(){
        if (selectedFigure ==null) return;
            selectedFigure.setFill();
        
        repaint();
    }
    /* RTTI
    public void fillFigure(){
        if (selectedFigure ==null) return;
        if (selectedFigure instanceof Box){
            //downCasting
            Box pBox = (Box)selectedFigure;
            pBox.setFill();
        }
        repaint();
    */
    public void copyFigure(){
        if (selectedFigure ==null) return;
        Figure newFigure = selectedFigure.copy();
        addFigure(newFigure);
        selectedFigure = newFigure;
    }
    void setColorForSelectedFigure(Color color){
        if (selectedFigure == null) return;
        selectedFigure.setColor(color);
        repaint();
    }
    public void setBlackColor(){
        Color color = Color.BLACK;
        setColorForSelectedFigure(color);
        selectedColor = color;
    }
    public void setRedColor(){
        Color color = Color.RED;
        setColorForSelectedFigure(color);
        selectedColor = color;
    }
    public void setGreenColor(){
        Color color = Color.GREEN;
        setColorForSelectedFigure(color);
        selectedColor = color;
    }
    public void setBlueColor(){
        Color color = Color.BLUE;
        setColorForSelectedFigure(color);
        selectedColor = color;
    }
    public void showColorChooser() {
        Color color = JColorChooser.showDialog(this, "ColorChooser", Color.black);
        setColorForSelectedFigure(color);
        selectedColor = color;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}