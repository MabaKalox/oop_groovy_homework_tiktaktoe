package org.example

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TikTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel t_panel = new JPanel();
    JPanel bt_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] bton = new JButton[9];
    int chance_flag = 0;
    Random random = new Random();
    boolean pl1_chance;

    // Creating class constructor for creating grid
    TikTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setTitle("Tic Tac Toe Game in Swing");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(0, 0, 0));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Serif", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic Tac Toe Game in Swing");
        textfield.setOpaque(true);

        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 800, 100);

        bt_panel.setLayout(new GridLayout(3, 3));
        bt_panel.setBackground(new Color(0, 0, 0));

        9.times {
            bton[it] = new JButton();
            bt_panel.add(bton[it]);
            bton[it].setFont(new Font("Serif", Font.BOLD, 120));
            bton[it].setFocusable(false);
            bton[it].addActionListener(this);
            bton[it].setBackground(Color.darkGray);
        }

        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);

        startGame();
    }

    // Creating method to start the game and decide the chance
    void startGame() {
        int chance = random.nextInt(100);
        pl1_chance = chance % 2 == 0;
        textfield.setText("Player ${pl1_chance ? "X" : "0"} turn");
    }

    void gameOver(String s) {
        chance_flag = 0;
        Object[] option = ["Restart", "Exit"];

        int n = JOptionPane.showOptionDialog(frame, "Game Over\n${s}", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if (n == 0) {
            frame.dispose();
            new TikTacToe();
        } else {
            frame.dispose();
        }

    }

    // Check if won
    void matchCheck() {
        int[] win_combo = [0, 0, 0];
        Boolean is_draw = false;
        if ((bton[0].getText() == "X") && (bton[1].getText() == "X") && (bton[2].getText() == "X")) {
            win_combo = [0, 1, 2];
        } else if ((bton[0].getText() == "X") && (bton[4].getText() == "X") && (bton[8].getText() == "X")) {
            win_combo = [0, 4, 8];
        } else if ((bton[0].getText() == "X") && (bton[3].getText() == "X") && (bton[6].getText() == "X")) {
            win_combo = [0, 3, 6];
        } else if ((bton[1].getText() == "X") && (bton[4].getText() == "X") && (bton[7].getText() == "X")) {
            win_combo = [1, 4, 7];
        } else if ((bton[2].getText() == "X") && (bton[4].getText() == "X") && (bton[6].getText() == "X")) {
            win_combo = [2, 4, 6];
        } else if ((bton[2].getText() == "X") && (bton[5].getText() == "X") && (bton[8].getText() == "X")) {
            win_combo = [2, 5, 8];
        } else if ((bton[3].getText() == "X") && (bton[4].getText() == "X") && (bton[5].getText() == "X")) {
            win_combo = [3, 4, 5];
        } else if ((bton[6].getText() == "X") && (bton[7].getText() == "X") && (bton[8].getText() == "X")) {
            win_combo = [6, 7, 8];
        } else if ((bton[0].getText() == "O") && (bton[1].getText() == "O") && (bton[2].getText() == "O")) {
            win_combo = [0, 1, 2];
        } else if ((bton[0].getText() == "O") && (bton[3].getText() == "O") && (bton[6].getText() == "O")) {
            win_combo = [0, 3, 6];
        } else if ((bton[0].getText() == "O") && (bton[4].getText() == "O") && (bton[8].getText() == "O")) {
            win_combo = [0, 4, 8];
        } else if ((bton[1].getText() == "O") && (bton[4].getText() == "O") && (bton[7].getText() == "O")) {
            win_combo = [1, 4, 7];
        } else if ((bton[2].getText() == "O") && (bton[4].getText() == "O") && (bton[6].getText() == "O")) {
            win_combo = [2, 4, 6];
        } else if ((bton[2].getText() == "O") && (bton[5].getText() == "O") && (bton[8].getText() == "O")) {
            win_combo = [2, 5, 8];
        } else if ((bton[3].getText() == "O") && (bton[4].getText() == "O") && (bton[5].getText() == "O")) {
            win_combo = [3, 4, 5];
        } else if ((bton[6].getText() == "O") && (bton[7].getText() == "O") && (bton[8].getText() == "O")) {
            win_combo = [6, 7, 8];
        } else if (chance_flag == 9) {
            textfield.setText("Game Draw!!");
            gameOver("Game Draw!!");
        }

        if (win_combo != [0, 0, 0]) {
            win(win_combo[0], win_combo[1], win_combo[2], bton[win_combo[0]].getText());
        }
    }

    void win(int x1, int x2, int x3, String winner) {
        bton[x1].setBackground(Color.WHITE);
        bton[x2].setBackground(Color.WHITE);
        bton[x3].setBackground(Color.WHITE);
        bton[x1].setForeground(Color.BLACK);
        bton[x2].setForeground(Color.BLACK);
        bton[x3].setForeground(Color.BLACK);

        9.times {
            bton[it].setEnabled(false);
        }

        String text = "Player ${winner} Wins";
        textfield.setText(text);
        gameOver(text);
    }

    // Method for performing action after every turn
    @Override
    void actionPerformed(ActionEvent e) {
        9.times {
            if (e.getSource() == bton[it]) {
                if (bton[it].getText() == "") {
                    bton[it].setForeground(new Color(255, 255, 255));
                    pl1_chance = !pl1_chance;
                    if (pl1_chance) {
                        bton[it].setText("X");
                        textfield.setText("O turn");
                    } else {
                        bton[it].setText("O");
                        textfield.setText("X turn");
                    }
                    chance_flag++;
                    matchCheck();
                }
            }
        }
    }
}

// Driver code
class Main {
    static void main(String[] args) throws Exception {
        new TikTacToe();
    }
}
