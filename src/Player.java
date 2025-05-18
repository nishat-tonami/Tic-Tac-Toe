import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player {
    private String name;
    private String symbol;
    private int score=0;

    public Player(String name,String symbol) {
        this.name=name;
        this.symbol=symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void resetScore() {
        score=0;
    }
}
