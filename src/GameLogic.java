import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameLogic {

    public static boolean checkWinner(JButton[][] board) {
        
        for(int i=0;i<3;i++) {
            if(board[i][0].getText().equals("")) continue;
            if(board[i][0].getText().equals(board[i][1].getText()) && board[i][1].getText().equals(board[i][2].getText())) {

            return true;
            }
        }

        for(int j=0;j<3;j++) {
            if(board[0][j].getText().equals("")) continue;
            if(board[0][j].getText().equals(board[1][j].getText()) && board[1][j].getText().equals(board[2][j].getText())) {

             return true;
            }
        }

        if(!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) &&
          board[1][1].getText().equals(board[2][2].getText())) {
        
            return true;
        }

        if(!board[0][2].getText().equals("") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText())) {
            
            return true;
        }

        return false;
    }

}