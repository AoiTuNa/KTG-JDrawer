import javax.swing.*;

public class FigurePopup extends Popup {
    FigurePopup(DrawerView view,String title,boolean fillFlag) {
         super(title);

         JMenuItem delectItem = new JMenuItem("Delect");
         delectItem.addActionListener((evt)->{
            view.delectFigure();
         });
         popupPter.add(delectItem);

         JMenuItem copyItem = new JMenuItem("Copy");
         copyItem.addActionListener((evt) ->{
            view.copyFigure();
         });
         popupPter.add(copyItem);

         JMenu colorMenu = new JMenu("Colors");
         popupPter.add(colorMenu);

         JMenuItem blackItem = new JMenuItem("Black");
         blackItem.addActionListener((evt) ->{
            view.setBlackColor();
         });
         colorMenu.add(blackItem);

         JMenuItem redItem = new JMenuItem("Red");
         redItem.addActionListener((evt) ->{
            view.setRedColor();
         });
         colorMenu.add(redItem);

         JMenuItem greenItem = new JMenuItem("Green");
         greenItem.addActionListener((evt) ->{
            view.setGreenColor();
         });
         colorMenu.add(greenItem);

         JMenuItem blueItem = new JMenuItem("Blue");
         blueItem.addActionListener((evt) ->{
            view.setBlueColor();
         });
         colorMenu.add(blueItem);

        JMenuItem chooserItem = new JMenuItem("Chooser");
        chooserItem.addActionListener((evt) ->{
            view.showColorChooser();
        });
        colorMenu.add(chooserItem);

        if (fillFlag == true){
         JMenuItem fillITem = new JMenuItem("fill");
         fillITem.addActionListener((evt) ->{
            view.fillFigure();
        });
        popupPter.add(fillITem);
        }

    }   
       
}
