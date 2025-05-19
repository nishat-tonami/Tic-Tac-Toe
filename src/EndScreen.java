import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndScreen extends JPanel implements KeyListener {

    public interface EndListener {
        void onRestart();
    }

    private Image backgroundImage;
    private EndListener listener;
    private String winnerMsg="";
    private final String instruction="Press 'SPACEBAR' to Restart!";

    public EndScreen(String imagePath,EndListener listener) {
        this.listener=listener;
        backgroundImage=new ImageIcon(getClass().getResource(imagePath)).getImage();
        setFocusable(true);
        addKeyListener(this);
    }
    
    public void setWinnerMessage(String msg) {
        this.winnerMsg=msg;
        repaint();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFocusable(true);
        requestFocusInWindow();
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,37));
        FontMetrics fm=g.getFontMetrics();
        int x1=(getWidth()-fm.stringWidth(winnerMsg))/2;
        int y1=getHeight()-150;
        g.drawString(winnerMsg,x1,y1);
        int x2=(getWidth()-fm.stringWidth(instruction))/2;
        int y2=getHeight()-100;
        g.drawString(instruction,x2,y2);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            if(listener!=null) {
                listener.onRestart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
