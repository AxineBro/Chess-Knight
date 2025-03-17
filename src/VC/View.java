package VC;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JPanel[][] chessBoard = new JPanel[8][8];
    private JList<String> moveList;
    private DefaultListModel<String> moveListModel;
    private JLabel knightLabel;
    private JLabel targetLabel;

    public View() {
        setTitle("Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel boardPanel = new JPanel(new GridLayout(8, 8, 0, 0)) {
            @Override
            public Dimension getPreferredSize() {
                int size = Math.min(getParent().getWidth() - 220, getParent().getHeight() - 40);
                return new Dimension(size, size);
            }
        };

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel(new BorderLayout());
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(new Color(139, 69, 19));
                }
                chessBoard[row][col] = square;
                boardPanel.add(square);
            }
        }

        knightLabel = new JLabel(new ImageIcon(getClass().getResource("/images/Knight.png")));
        targetLabel = new JLabel("●");
        targetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        targetLabel.setForeground(Color.GREEN);
        targetLabel.setFont(new Font("Arial", Font.BOLD, 30));

        moveListModel = new DefaultListModel<>();
        moveList = new JList<>(moveListModel);
        JScrollPane scrollPane = new JScrollPane(moveList);
        scrollPane.setPreferredSize(new Dimension(200, 0));

        add(boardPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(650, 450));
    }

    public JPanel[][] getChessBoard() {
        return chessBoard;
    }

    public JLabel getKnightLabel() {
        return knightLabel;
    }

    public JLabel getTargetLabel() {
        return targetLabel;
    }

    public void addMove(String move) {
        moveListModel.addElement(move);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            new Controller(view);
            view.setVisible(true);
        });
    }
}