import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board {
    private JButton[][] tiles=new JButton[3][3];
    private JPanel panel;

    public Board() {
        panel=new JPanel(new GridLayout(3,3));
        panel.setBackground(Color.darkGray);
        initializeTiles();
    }

    private void initializeTiles() {
        for(int i= 0;i<3;i++) {
            for (int j=0;j<3;j++) {
                JButton tile=new JButton();
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setBackground(Color.white);
                tile.setForeground(Color.darkGray);
                tile.setFocusable(false);
                tiles[i][j]=tile;
                panel.add(tile);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton[][] getTiles() {
        return tiles;
    }

    public void resetBoard() {
        for(int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                tiles[i][j].setText("");
                tiles[i][j].setBackground(Color.white);
                tiles[i][j].setForeground(Color.darkGray);
            }
        }
    }
}
