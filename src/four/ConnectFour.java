package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectFour extends JFrame implements ActionListener {
    static final int rowCount = 6;
    List<String> column = Arrays.asList("A","B","C","D","E","F","G");
    static boolean gameOver = false;
    static Cell[][] cellArray = new Cell[6][7];
    static ColumnTracker columnTracker = new ColumnTracker();

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        JPanel piecePanel = new JPanel();
        piecePanel.setLayout(new GridLayout(rowCount,column.size(),0,0));

        cellArray = generateCellList();
        JButton buttonReset = new JButton("Reset");
        buttonReset.setName("ButtonReset");

        buttonReset.addActionListener(this);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < column.size(); j++) {
                piecePanel.add(cellArray[i][j]);
            }
        }
        add(piecePanel, BorderLayout.CENTER);
        add(buttonReset, BorderLayout.SOUTH);

        setVisible(true);
        setTitle("Connect Four");
    }

    private Cell[][] generateCellList() {
        List<Cell> cellList = new ArrayList<>();
        for (int i = rowCount ; i > 0; i--) {
            for (String letter: column) {
                cellArray[rowCount - i][ColumnTracker.letterSequence.get(letter)] = new Cell( letter + i);
            }
        }
        return cellArray;
    }
    public void actionPerformed(ActionEvent e) {
        //reset all cells
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < column.size(); j++) {
                cellArray[i][j].setText(" ");
                cellArray[i][j].setBackground(Color.lightGray);
            }
        }
        //reset column tracker and click counter
        columnTracker.resetColumnTracker();
        ClickCounter.count = 0;
        gameOver = false;
    }
}