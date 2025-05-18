import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameLogic {

    public static boolean checkWinner(JButton[][] board,Player currentPlayer,JLabel textLabel,int turn) {
        
        for(int i=0;i<3;i++) {
            if(board[i][0].getText().equals("")) continue;
            if(board[i][0].getText().equals(board[i][1].getText()) && board[i][1].getText().equals(board[i][2].getText())) {
                highlightWinner(board[i][0],board[i][1],board[i][2]);
                textLabel.setText(currentPlayer.getName()+" is the winner!");
                return true;
            }
        }

        for(int j=0;j<3;j++) {
            if(board[0][j].getText().equals("")) continue;
            if(board[0][j].getText().equals(board[1][j].getText()) && board[1][j].getText().equals(board[2][j].getText())) {
                highlightWinner(board[0][j],board[1][j],board[2][j]);
                textLabel.setText(currentPlayer.getName()+" is the winner!");
                return true;
            }
        }

        if(!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) &&
          board[1][1].getText().equals(board[2][2].getText())) {
            highlightWinner(board[0][0],board[1][1],board[2][2]);
            textLabel.setText(currentPlayer.getName()+" is the winner!");
            return true;
        }

        if(!board[0][2].getText().equals("") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText())) {
            highlightWinner(board[0][2],board[1][1],board[2][0]);
            textLabel.setText(currentPlayer.getName()+" is the winner!");
            return true;
        }

        return false;
    }

    public static void highlightWinner(JButton... tiles) {
        for(JButton tile:tiles) {
            tile.setForeground(Color.blue);
            tile.setBackground(Color.gray);
        }
    }

    public static void highlightTie(JButton[][] board) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                board[i][j].setForeground(Color.green);
                board[i][j].setBackground(Color.gray);
            }
        }
    }
}
