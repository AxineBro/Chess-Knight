package VC;

import Module.AxineQueue.SolutionChessKnightAxine;
import Module.JavaQueue.SolutionChessKnightJava;
import basicFiles.Solution;
import basicFiles.TypeImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Controller {
    private final View view;
    private boolean isFirstClick = true;
    private int knightRow = -1;
    private int knightCol = -1;
    private TypeImplementation type;

    public Controller(View view, TypeImplementation type) {
        this.view = view;
        this.type = type;
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

            Solution solution = null;
            switch (type){
                case JAVA:
                    solution = new SolutionChessKnightJava((char) ('A' + knightCol), 8 - knightRow);
                    break;
                case AXINE:
                    solution = new SolutionChessKnightAxine((char) ('A' + knightCol), 8 - knightRow);
                    break;
            }
            solution.solveParams((char)('A' + col), (8 - row));

            List<?> answer = solution.getAnswer().getList();
            Object prefPosition = null;
            for (Object position : answer) {
                if(prefPosition == null) {
                    prefPosition = position;
                    continue;
                }
               view.addMove(String.valueOf(prefPosition) + "-" +String.valueOf(position));
                prefPosition = position;
            }
        }
        view.revalidate();
        view.repaint();
    }
}