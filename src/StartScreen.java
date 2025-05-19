import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JPanel implements KeyListener {

    public interface StartListener {
        void onStart();
    }

    private Image backgroundImage;
    private StartListener listener;
    private final String instruction="Press 'S' to Start!";

    public StartScreen(String imagePath,StartListener listener) {
        this.listener=listener;
        backgroundImage=new ImageIcon(getClass().getResource(imagePath)).getImage();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,44));
        FontMetrics fm=g.getFontMetrics();
        int x=(getWidth()-fm.stringWidth(instruction))/2;
        int y=getHeight()-100;
        g.drawString(instruction,x,y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='s' || e.getKeyChar()=='S') {
            if(listener!=null) {
                listener.onStart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
