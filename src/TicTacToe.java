import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    final int boardWidth=600;
    final int boardHeight=650;
    
    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel=new JLabel();
    JLabel scoreLabel=new JLabel();
    Board gameBoard=new Board();

    Player player1=new Player1("Player X");
    Player player2=new Player2("Player O");
    Player currentPlayer=player1; 
    Player lastWinner=null;
    
    int rounds=0;
    int consecutiveWins=0;
    
    boolean gameOver=false;
    int turn=0;

    StartScreen startScreen;
    boolean gameStarted=false;

    EndScreen endScreen;

    JPanel textPanel=new JPanel(new GridLayout(2,1));

    TicTacToe() {

        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        scoreLabel.setBackground(Color.gray);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Courier New",Font.BOLD,20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setOpaque(true);

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Courier New",Font.BOLD,40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.add(textLabel);
        textPanel.add(scoreLabel);

        startScreen=new StartScreen("startscreen.png",new StartScreen.StartListener() {
        @Override
        public void onStart() {
           startGame();
          }
        });

        frame.add(startScreen,BorderLayout.CENTER);
        startScreen.requestFocusInWindow();

        endScreen=new EndScreen("endscreen.jpeg",new EndScreen.EndListener() { 
        @Override
        public void onRestart() {
        resetMatch();
        frame.remove(endScreen);
        frame.add(gameBoard.getPanel(),BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
        gameBoard.getPanel().requestFocusInWindow();
    }
});

        JButton[][] board=gameBoard.getTiles();

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                final JButton tile=board[i][j];

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        if(tile.getText().equals("")) {
                          tile.setText(currentPlayer.getSymbol());
                          turn++;
                          gameOver=GameLogic.checkWinner(board);
                          if(gameOver) {
                                rounds++;
                                currentPlayer.incrementScore();
                                if(lastWinner==currentPlayer) consecutiveWins++;
                                else {
                                    consecutiveWins=1;
                                    lastWinner=currentPlayer;
                                }
                                updateScoreLabel();
                                if(currentPlayer.getScore()==2 || consecutiveWins==2) {
                                    showMatchWinner(currentPlayer);
                                } else if(rounds==3) {
                                    int p1=player1.getScore();
                                    int p2=player2.getScore();

                                    if(p1>p2) showMatchWinner(player1);
                                    else if(p2>p1) showMatchWinner(player2);
                                    else showMatchDraw();
                            }
                                else {
                                    showRoundOverMessage(currentPlayer.getName()+" wins the round!");
                                }
                            }   else if(turn==9) {
                                rounds++;
                                consecutiveWins=0;
                                lastWinner=null;
                                updateScoreLabel();
                               
                                if(rounds==3) {
                                int p1=player1.getScore();
                                int p2=player2.getScore();

                                if(p1>p2) showMatchWinner(player1);
                                else if(p2>p1) showMatchWinner(player2);
                                else showMatchDraw();
                                }

                                else showRoundOverMessage("It's a Tie!");
                            } 
                              else {
                                if(currentPlayer==player1) currentPlayer=player2;
                                else currentPlayer=player1;
                                textLabel.setText(currentPlayer.getName()+"'s Turn.");
                          }
                        }
                    }
                });
            }
        }
        updateScoreLabel();
        frame.setVisible(true);
    }
    
    void resetGame() {
        gameBoard.resetBoard();
        currentPlayer=player1;
        textLabel.setText(currentPlayer.getName()+"'s Turn.");
        gameOver=false;
        turn=0;
    }

    void resetMatch() {
        resetGame();
        player1.resetScore();
        player2.resetScore();
        rounds=0;
        consecutiveWins=0;
        lastWinner=null;
        updateScoreLabel();
        textLabel.setText(currentPlayer.getName()+"'s Turn.");
    }

    void startGame() {
       if(gameStarted) return;
       gameStarted=true;
       frame.remove(startScreen);
       frame.add(gameBoard.getPanel(),BorderLayout.CENTER);
       frame.add(textPanel,BorderLayout.NORTH);
       frame.revalidate();
       frame.repaint();
       gameBoard.getPanel().requestFocusInWindow();
       textLabel.setText(currentPlayer.getName()+"'s Turn.");
    }
    
    void updateScoreLabel() {
        scoreLabel.setText(player1.getName()+"'s Score : "+player1.getScore()+" | "+player2.getName()+"'s Score : "+player2.getScore());
    }
    
    void showRoundOverMessage(String msg) {

    JLayeredPane layeredPane=frame.getLayeredPane();

    String imagePath="popup.jpeg";
    Color msgClr=Color.BLACK;
    int vtOffset=60;

    RoundOverScreen overlay=new RoundOverScreen(imagePath,msg,msgClr,vtOffset);

    overlay.setBounds(0,0,frame.getWidth(),frame.getHeight());
    overlay.setVisible(true);

    layeredPane.add(overlay,JLayeredPane.POPUP_LAYER);
    layeredPane.setLayer(overlay,JLayeredPane.POPUP_LAYER);

    frame.revalidate();
    frame.repaint();

    Timer timer=new Timer(2000,new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            layeredPane.remove(overlay);
            layeredPane.repaint();
            resetGame();
        }
    });

    timer.setRepeats(false);
    timer.start();
}
   void showMatchWinner(Player winner) {
        frame.remove(gameBoard.getPanel());
        frame.remove(textPanel);
        frame.add(endScreen,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        endScreen.setWinnerMessage(winner.getName()+" wins the match!!");
        SwingUtilities.invokeLater(()->endScreen.requestFocusInWindow());
    }
    void showMatchDraw() {
        frame.remove(gameBoard.getPanel());
        frame.remove(textPanel);
        frame.add(endScreen,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        endScreen.setWinnerMessage("Match ends in a draw!");
        SwingUtilities.invokeLater(()->endScreen.requestFocusInWindow());
    }
}