import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;
    
    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel=new JLabel();
    Board gameBoard=new Board();

    Player player1=new Player1("Player X");
    Player player2 = new Player2("Player O");
    Player currentPlayer=player1;  
    
    boolean gameOver=false;
    int turn=0;

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

        JPanel textPanel=new JPanel(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        frame.add(gameBoard.getPanel(),BorderLayout.CENTER);

        JButton[][] board = gameBoard.getTiles();

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                final JButton tile=board[i][j];

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        if(tile.getText().equals("")) {
                          tile.setText(currentPlayer.getSymbol());
                          turn++;
                          gameOver=GameLogic.checkWinner(board,currentPlayer,textLabel,turn);
                          if(!gameOver) {
                          if(currentPlayer==player1) currentPlayer=player2;
                          else currentPlayer=player1;
                          textLabel.setText(currentPlayer.getName()+"'s Turn.");
                          }
                        }
                    }
                });
            }
        }
        JButton resetButton=new JButton("Restart");
        resetButton.setFont(new Font("Arial",Font.BOLD,30));
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.darkGray);
        resetButton.setForeground(Color.white);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        frame.add(resetButton,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    void resetGame() {
        gameBoard.resetBoard();
        currentPlayer=player1;
        textLabel.setText("Tic-Tac-Toe");
        gameOver=false;
        turn=0;
    }
}
