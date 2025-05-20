import javax.swing.*;
import java.awt.*;

public class RoundOverScreen extends JPanel {

    private final Image backgroundImage;
    private String msg;
    private Color txtClr;
    private int vtOffset;

    public RoundOverScreen(String imagePath,String msg,Color txtClr,int vtOffset) {

        this.backgroundImage=new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.msg=msg;
        this.txtClr=txtClr;
        this.vtOffset=vtOffset;
        setFocusable(false);
        setOpaque(false);
        setBounds(0,0,600,650);
    }

    public void updateMessage(String msg,Color clr) {
        this.msg=msg;
        this.txtClr=clr;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);

        if(msg!=null && !msg.isEmpty()) {
            g.setColor(txtClr);
            g.setFont(new Font("Comic Sans MS",Font.BOLD,42));
            FontMetrics fm=g.getFontMetrics();
            int x=(getWidth()-fm.stringWidth(msg))/2;
            int y=getHeight()/2+vtOffset;
            g.drawString(msg,x,y);
        }
    }
}