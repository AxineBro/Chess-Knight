package VC;

import Module.SolutionChessKnight;
import basicFiles.Position;
import basicFiles.Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class Controller {
    private final View view;
    private boolean isFirstClick = true;
    private int knightRow = -1;
    private int knightCol = -1;

    public Controller(View view) {
        this.view = view;
        initializeBoard();
    }

    private void initializeBoard() {
        JPanel[][] chessBoard = view.getChessBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = chessBoard[row][col];
                final int finalRow = row;
                final int finalCol = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(square, finalRow, finalCol);
                    }
                });
            }
        }
    }

    private void handleSquareClick(JPanel square, int row, int col) {
        if (isFirstClick) {
            if (knightRow != -1) {
                view.getChessBoard()[knightRow][knightCol].remove(view.getKnightLabel());
            }
            square.add(view.getKnightLabel(), BorderLayout.CENTER);
            knightRow = row;
            knightCol = col;
            isFirstClick = false;
        } else {
            for (JPanel[] rowPanels : view.getChessBoard()) {
                for (JPanel cell : rowPanels) {
                    cell.remove(view.getTargetLabel());
                }
            }
            square.add(view.getTargetLabel(), BorderLayout.CENTER);
            isFirstClick = true;
            view.addMove(String.format("Knight from %s%d to %s%d",
                    (char)('A' + knightCol), 8 - knightRow,
                    (char)('A' + col), 8 - row));

            Solution solution = new SolutionChessKnight((char) ('A' + knightCol), 8 - knightRow);
            solution.solve((char)('A' + col), (8 - row));

            List<?> answer = solution.getAnswer().getAnswer();
            if (answer != null && !answer.isEmpty()) {
                for (Object position : answer) {
                    view.addMove(String.valueOf(position));
                }
            } else {
                view.addMove("No path found!");
            }
        }
        view.revalidate();
        view.repaint();
    }
}