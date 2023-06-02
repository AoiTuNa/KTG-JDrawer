import javax.swing.JDialog;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class FigureDialog extends JDialog {
    static class DialogPanel extends JPanel implements ActionListener{
        static int TOP_GAP = 30;
        static int LEFT_GAP = 40;
        static int LABEL_WIDTH = 40;
        static int HEIGHT = 30;
        static int FIELD_WIDTH = 80;
        static int CENTER_GAP = 20;
        static int BOX_WIDTH = 180;
        static int BUTTON_WIDTH = 90;
        static int RIGHT_GAP = LEFT_GAP + 20;
        static int BOTTOM_GAP = TOP_GAP + 10;
        static int X_LABEL_POS = LEFT_GAP;
        static int FIRST_ROW = TOP_GAP;
        static int SECOND_ROW = FIRST_ROW*2 + HEIGHT;
        static int THIRD_ROW = FIRST_ROW*3 + HEIGHT*2;
        static int FINAL_ROW = FIRST_ROW*4 + HEIGHT*3 + HEIGHT/2;
        static int X_FIELD_POS = X_LABEL_POS + LABEL_WIDTH;
        static int Y_LABEL_POS = X_FIELD_POS + FIELD_WIDTH + CENTER_GAP;
        static int Y_FIELD_POS = Y_LABEL_POS + LABEL_WIDTH;
        static int PANEL_WIDTH = Y_FIELD_POS + FIELD_WIDTH + RIGHT_GAP;
        static int BOX_POS = (PANEL_WIDTH - BOX_WIDTH) /2;
        static int OK_BUTTON_POS = (PANEL_WIDTH - BUTTON_WIDTH*2)/3;
        static int CANCEL_BUTTON_POS = OK_BUTTON_POS*2 + BUTTON_WIDTH;
        static int PANEL_HEIGHT = FINAL_ROW + 2*HEIGHT + BOTTOM_GAP;

        JTextField x1TextField;
        JTextField x2TextField;
        JTextField y1TextField;
        JTextField y2TextField;
        String[] figures = {"Box","Line"};
        JComboBox<String> box;
        JDialog dialog;
        DrawerView view;


        DialogPanel(JDialog dialog,DrawerView view){
            this.view = view;
            this.dialog = dialog;
            this.setLayout(null); //
            // Flowlayput(순서대로.Default) Borderlayout(동서남북) Gridlayout(2차원배열해석)
            JLabel x1Lable = new JLabel("x1: ");
            x1Lable.setFont(new Font("Courier New",Font.BOLD,16));
            x1Lable.setBounds(X_LABEL_POS,FIRST_ROW,LABEL_WIDTH,HEIGHT);
            add(x1Lable);

            JLabel x2Lable = new JLabel("x2: ");
            x2Lable.setFont(new Font("Courier New",Font.BOLD,16));
            x2Lable.setBounds(X_LABEL_POS,SECOND_ROW,LABEL_WIDTH,HEIGHT);
            add(x2Lable);

            JLabel y1Lable = new JLabel("y1: ");
            y1Lable.setFont(new Font("Courier New",Font.BOLD,16));
            y1Lable.setBounds(Y_LABEL_POS,FIRST_ROW,LABEL_WIDTH,HEIGHT);
            add(y1Lable);

            JLabel y2Lable = new JLabel("y2: ");
            y2Lable.setFont(new Font("Courier New",Font.BOLD,16));
            y2Lable.setBounds(Y_LABEL_POS,SECOND_ROW,LABEL_WIDTH,HEIGHT);
            add(y2Lable);

            x1TextField = new JTextField("0");
            x1TextField.setBounds(X_FIELD_POS, FIRST_ROW, FIELD_WIDTH, HEIGHT);
            x1TextField.setHorizontalAlignment(JTextField.RIGHT);
            add(x1TextField);

            x2TextField = new JTextField("0");
            x2TextField.setBounds(X_FIELD_POS, SECOND_ROW, FIELD_WIDTH, HEIGHT);
            x2TextField.setHorizontalAlignment(JTextField.RIGHT);
            add(x2TextField);

            y1TextField = new JTextField("0");
            y1TextField.setBounds(Y_FIELD_POS, FIRST_ROW, FIELD_WIDTH, HEIGHT);
            y1TextField.setHorizontalAlignment(JTextField.RIGHT);
            add(y1TextField);

            y2TextField = new JTextField("0");
            y2TextField.setBounds(Y_FIELD_POS, SECOND_ROW, FIELD_WIDTH, HEIGHT);
            y2TextField.setHorizontalAlignment(JTextField.RIGHT);
            add(y2TextField);

            box = new JComboBox<String>(figures);
            box.setBounds(BOX_POS,THIRD_ROW,BOX_WIDTH,HEIGHT);
            add(box);

            JButton okButton = new JButton("OK");
            okButton.setFont(new Font("Courier New",Font.BOLD,16));
            okButton.setBounds(OK_BUTTON_POS, FINAL_ROW, BUTTON_WIDTH, HEIGHT);
            okButton.addActionListener(this);
            add(okButton);

            JButton cancelButton = new JButton("cancel");
            cancelButton.setFont(new Font("Courier New",Font.BOLD,14));
            cancelButton.setBounds(CANCEL_BUTTON_POS, FINAL_ROW, BUTTON_WIDTH, HEIGHT);
            cancelButton.addActionListener(this);
            add(cancelButton);
        }
        private void onOK(){
            int x1,x2,y1,y2;
            String selection = (String)box.getSelectedItem();
            try{
                x1 = Integer.parseInt(x1TextField.getText());
                x2 = Integer.parseInt(x1TextField.getText());
                y1 = Integer.parseInt(x1TextField.getText());
                y2 = Integer.parseInt(x1TextField.getText());
            }
            catch (Exception ex){
                System.out.println("Invalid text filed! Try Again!");
                return;
            }
            Figure newFigure = null;
            if (selection.equals("Box")){
                newFigure = new Box(x1,x2,y1,y2);
            }else if(selection.equals("Line")){
                newFigure = new Line(x1,x2,y1,y2);
            }
            view.addFigure(newFigure);
            x1TextField.setText("0");
            x2TextField.setText("0");
            y1TextField.setText("0");
            y2TextField.setText("0");
        }
        private void onCancel(){
            dialog.setVisible(false);
        }
        public void actionPerformed(ActionEvent event){
            String name = event.getActionCommand();
            if (name.equals("OK")){
                onOK();
            }else if (name.equals("cancel")){
                onCancel();
            }
        }
        public Dimension getSize() {
            return new Dimension(PANEL_WIDTH,PANEL_HEIGHT);
        }
    
    }
    FigureDialog(String title,DrawerView view){
        super((JFrame)null,title);
        setLocation(200,300);
        Container container = getContentPane();
        JPanel panel = new DialogPanel(this,view);
        container.add(panel);
        setSize(panel.getSize());
    } 
}
