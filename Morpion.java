import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morpion extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public Morpion() {
        setTitle("Jeu de Morpion");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            if (checkForWin()) {
                JOptionPane.showMessageDialog(this, "Le joueur " + currentPlayer + " a gagné !");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "Match nul !");
                resetGame();
            } else {
                togglePlayer();
            }
        }
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        // Check diagonals
        return checkDiagonals();
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
               buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
               buttons[row][2].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
               buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
               buttons[2][col].getText().equals(String.valueOf(currentPlayer));
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
               (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new Morpion();
    }
}
