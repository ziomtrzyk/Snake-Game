
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyPanel extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    int x = 0, y = 0, run = 50, vectorx = run, vectory = 0, longSnake = 3;
    final int MAX_WIDTH = 600, MAX_HEIGHT = 600;
    List<Pair> list = new ArrayList<>();
    Pair gold;
    public MyPanel() {
        this.setPreferredSize(new Dimension(MAX_WIDTH,MAX_HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(250, this);
        timer.start();
        list.add(new Pair(0,0));
        gold = new Pair();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        teleport();



        g2d.setColor(Color.BLUE);
        for(int i = 1; i < list.size(); i++) {
            g2d.fillRect(list.get(i).x, list.get(i).y, 50, 50);// wypelnia recznie kwadrat
        }

        g2d.setColor(Color.RED);
        g2d.fillRect(list.get(0).x, list.get(0).y, 50, 50);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(gold.x, gold.y, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        list.add(0,new Pair(list.get(0).x+vectorx, list.get(0).y+vectory));// add new pair x,y with vector
        if(list.get(0).x==gold.x && list.get(0).y==gold.y) {
            gold = new Pair();
            longSnake++;
        }
        if(list.size()>longSnake)
            list.remove(list.size()-1);
        System.out.println("List size: " + list.size());

        repaint();
    }
    public void teleport(){
        if(list.get(0).x < 0)
            list.get(0).setX(MAX_WIDTH-run);
        if(list.get(0).x > MAX_WIDTH)
            list.get(0).setX(0);
        if(list.get(0).y < 0)
            list.get(0).setY(MAX_HEIGHT-run);
        if(list.get(0).y > MAX_HEIGHT)
            list.get(0).setY(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                System.out.println("a");
                vectorx=-run;
                vectory=0;
                break;
            case 'd':
                System.out.println("d");
                vectorx=run;
                vectory=0;
                break;
            case 'w':
                System.out.println("w");
                vectorx=0;
                vectory=-run;
                break;
            case 's':
                System.out.println("s");
                vectorx=0;
                vectory=run;
                break;
            default:
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
    class Pair{
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Pair(){
            Random r = new Random();
            x = r.nextInt(MAX_WIDTH/run);
            x*=run;
            y=r.nextInt(MAX_HEIGHT/run);
            y*=run;
            System.out.println("x, y: " + x + ", " + y);
        }

        public void setX(int x){
            this.x = x;
        }
        public void setY(int y){
            this.y = y;
        }
    }
}
