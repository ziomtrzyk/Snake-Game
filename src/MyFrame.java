import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame  {

    MyPanel panel;

    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyPanel();
        panel.setLayout(null);

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

}
