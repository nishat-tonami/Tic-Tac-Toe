import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;
    
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    
    boolean gameOver=false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                JButton tile = new JButton();
                board [i][j]=tile;
                boardPanel.add(tile);

                tile.setBackground(Color.white);
                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile=(JButton) e.getSource();
                        if(tile.getText()=="") {
                          tile.setText(currentPlayer);
                          checkWinner();
                          if(!gameOver) {
                          if(currentPlayer==playerX) currentPlayer=playerO;
                          else currentPlayer=playerX;
                          textLabel.setText(currentPlayer+"'s Turn.");
                          }
                        }
                    }
                });
            }
        }

    }
    void checkWinner() {
        for(int i=0;i<3;i++) {
            if(board[i][0].getText()=="") continue;
            if(board[i][0].getText()==board[i][1].getText() && board[i][1].getText()==board[i][2].getText()) {
                gameOver=true;
                return;
            }
        }
    }
}
