import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;
    
    JFrame frame = new JFrame();

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
    }
}
