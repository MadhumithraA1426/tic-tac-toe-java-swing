package ui;

import model.GameLogic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JButton[][] buttons;
    private JLabel statusLabel;
    private JLabel scoreLabel;

    private GameLogic gameLogic;

    private String currentPlayer = "X";

    private int xScore = 0;
    private int oScore = 0;

    public GameFrame() {

        gameLogic = new GameLogic();

        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Status Label
        statusLabel = new JLabel(
                "Player X Turn",
                SwingConstants.CENTER
        );

        statusLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        add(statusLabel, BorderLayout.NORTH);

        // Board Panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                JButton button = new JButton();

                button.setFont(
                        new Font("Arial", Font.BOLD, 50)
                );

                int r = row;
                int c = col;

                button.addActionListener(e ->
                        handleMove(r, c));

                buttons[row][col] = button;

                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(
                new GridLayout(2, 1)
        );

        scoreLabel = new JLabel(
                "X : 0   O : 0",
                SwingConstants.CENTER
        );

        JButton restartButton =
                new JButton("Restart Game");

        restartButton.addActionListener(e ->
                resetGame());

        bottomPanel.add(scoreLabel);
        bottomPanel.add(restartButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleMove(int row, int col) {

        boolean success =
                gameLogic.makeMove(
                        row,
                        col,
                        currentPlayer
                );

        if (!success) {
            return;
        }

        buttons[row][col].setText(currentPlayer);

        if (gameLogic.checkWinner(currentPlayer)) {

            JOptionPane.showMessageDialog(
                    this,
                    currentPlayer + " Wins!"
            );

            if (currentPlayer.equals("X")) {
                xScore++;
            } else {
                oScore++;
            }

            updateScore();
            resetGame();

            return;
        }

        if (gameLogic.isDraw()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Match Draw!"
            );

            resetGame();
            return;
        }

        currentPlayer =
                currentPlayer.equals("X")
                        ? "O"
                        : "X";

        statusLabel.setText(
                "Player " +
                        currentPlayer +
                        " Turn"
        );
    }

    private void updateScore() {

        scoreLabel.setText(
                "X : " +
                        xScore +
                        "   O : " +
                        oScore
        );
    }

    private void resetGame() {

        gameLogic.resetBoard();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                buttons[i][j].setText("");
            }
        }

        currentPlayer = "X";

        statusLabel.setText(
                "Player X Turn"
        );
    }
}