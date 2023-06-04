import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawerFrame extends JFrame{
    DrawerView canvus;
    DrawerFrame() {
        setTitle("Drawer");
        Toolkit tk = Toolkit.getDefaultToolkit();// 스크린의 크기를 알아옴
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3,screenHeight*2/3);
        setLocation(screenWidth*1/6,screenHeight*1/6);
        Image img = tk.getImage("DoaGa.png");
        setIconImage(img);

        Container  container = this.getContentPane();
        canvus = new DrawerView();
        container.add(canvus);

        JMenuBar menus = new JMenuBar();
        setJMenuBar(menus);
   
        JMenu fileMenu = new JMenu("파일 (F)");
        menus.add(fileMenu);
        
    

        JMenuItem newFile = new JMenuItem("새 파일(N)");
        fileMenu.add(newFile);
        newFile.setIcon(new ImageIcon("new.gif"));
        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
        newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("새 파일(N)");
            }
        });

        JMenuItem openFile = new JMenuItem("열기(O)");
        fileMenu.add(openFile);
        openFile.setIcon(new ImageIcon("open.gif"));
        openFile.setMnemonic('O');
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK));
        openFile.addActionListener( (e) ->
                System.out.println("열기(O)") 
        );//lambda expression

        JMenuItem saveFile = new JMenuItem("저장(S)");
        fileMenu.add(saveFile);
        saveFile.setIcon(new ImageIcon("save.gif"));
        saveFile.setMnemonic('S');
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));

        JMenuItem anotherSaveFile = new JMenuItem("다른 이름으로 저장(A)");
        fileMenu.add(anotherSaveFile);
        
        fileMenu.addSeparator();
        JMenuItem Exit = new JMenuItem("종료(X)");
        fileMenu.add(Exit);
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        JMenu figureMenu = new JMenu("그림 (F)");
        menus.add(figureMenu);

        JMenuItem figurePoint = new JMenuItem("Point (P)");
        figureMenu.add(figurePoint);
        figurePoint.addActionListener( (e) -> canvus.setWhatToDraw(DrawerView.DRAW_POINT));

        JMenuItem figureBox = new JMenuItem("Box (B)");
        figureMenu.add(figureBox);
        figureBox.addActionListener( (e) -> canvus.setWhatToDraw(DrawerView.DRAW_BOX));

        JMenuItem figureLine = new JMenuItem("Line (L)");
        figureMenu.add(figureLine);
        figureLine.addActionListener( (e) -> canvus.setWhatToDraw(DrawerView.DRAW_LINE));

        JMenuItem figureCircle = new JMenuItem("Circle (C)");
        figureMenu.add(figureCircle);
        figureLine.addActionListener( (e) -> canvus.setWhatToDraw(DrawerView.DRAW_CIRCLE));

        JMenu toolMenu = new JMenu("도구 (T)");
        menus.add(toolMenu);

        JMenuItem modalTool = new JMenuItem("Modal (M)");
        toolMenu.add(modalTool);
        modalTool.addActionListener(  (e) ->  {
                    FigureDialog dialog = new FigureDialog("Figure Dialog",canvus);
                    dialog.setModal(true);
                    dialog.setVisible(true);
        });
        JMenuItem modalessTool = new JMenuItem("Modaless (S)");
        toolMenu.add(modalessTool);
        modalessTool.addActionListener(  (e) ->  {
                    FigureDialog dialog = new FigureDialog("Figure Dialog",canvus);
                    dialog.setModal(false);
                    dialog.setVisible(true);
            
        });


        JMenu helpMenu = new JMenu("도움말 (H)");
        menus.add(helpMenu);

        JMenuItem infoHelp = new JMenuItem("Drawer 정보(I)");
        helpMenu.add(infoHelp);
        infoHelp.addActionListener((e) ->
                            { JOptionPane.showMessageDialog(null,"Author: Hong Chung-Pyo\r\nCompany: BUFS",
                            "Drawer 정보",JOptionPane.INFORMATION_MESSAGE);}
        );                     

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
} 
// Toolkit : 사용하는 컴퓨터의 환경 PATH 등 고정된 한정된 정보를 알아 오는 클래스
// Dimension : 너비와 높이를 캡슐화 하는 클래스 
// JMenuBar : JFrame에 장착해서 사용하는 메뉴바 (사용 함수 적을 것)
// Jmenu : JmenuBar에 메뉴를 추가하는  클래스
// JmenuItem : Jmenu 하위에 Item을 추가하는 것