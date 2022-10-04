package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell extends JButton {
    private final int rowIndex;
    private final int columnIndex;
    private final int MAX_ROW = 6;
    private final int MAX_COLUMN = 7;

    public Cell(String name) {
        this.setFocusPainted(false);
        this.setName("Button" + name);
        this.setText(" ");

        this.rowIndex=ConnectFour.rowCount - Integer.parseInt(name.substring(1));
        this.columnIndex=ConnectFour.columnTracker.letterSequence.get(name.substring(0,1));

        //this.setText(rowIndex + "," + columnIndex);
        this.addActionListener((x) -> setTxt());
        this.setBackground(Color.lightGray);
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }
    public void resetCell(){
        this.setText(" ");
    }

    private void setTxt() {
        Integer bottomIndex = ConnectFour.columnTracker.columnBottom.get(columnIndex);
        String targetMark = ClickCounter.count % 2 == 0 ? "X":"O";

        if( bottomIndex >= 0 && ! ConnectFour.gameOver) {
            ConnectFour.cellArray[bottomIndex][columnIndex].setText(targetMark);
            ClickCounter.count++;

            int winCellCount = 0;
            List<Cell> winCell = new ArrayList<>();
            //check if one player wins.

                //horizontal check
            for (int i = 0; i < MAX_COLUMN; i++) {
                if (ConnectFour.cellArray[bottomIndex][i].getText().equals(targetMark)){
                    winCellCount++;
                    winCell.add(ConnectFour.cellArray[bottomIndex][i]);
                    if(winCellCount >=4) break;
                }
                else {
                    winCellCount = 0;
                    winCell.clear();
                }
            }
            if (winCellCount >=4) {

                winCell.forEach((x)->x.setBackground(Color.CYAN));
                ConnectFour.gameOver = true;
                return;
            }
                //vertical check
            winCellCount = 0;
            winCell.clear();
            for (int i = 0; i < MAX_ROW; i++) {
                if (ConnectFour.cellArray[i][columnIndex].getText().equals(targetMark)){
                    winCellCount++;
                    winCell.add(ConnectFour.cellArray[i][columnIndex]);
                    if(winCellCount >=4) break;
                }
                else {
                    winCellCount = 0;
                    winCell.clear();
                }
            }
            if (winCellCount >=4) {

                winCell.forEach((x)->x.setBackground(Color.CYAN));
                ConnectFour.gameOver = true;
                return;
            }

            //forward diagonal check, sum of index and column is identical.
            winCellCount = 0;
            winCell.clear();
            int sumRowColumn = bottomIndex + columnIndex;
            //ignore top left cells and bottom right cells
            if (sumRowColumn >= 6 && sumRowColumn < 9) {// deal with right bottom part of cells
                for (int i = 5, j = sumRowColumn - 5; j < MAX_COLUMN; i--, j++) {
                    if (ConnectFour.cellArray[i][j].getText().equals(targetMark)) {
                        winCellCount++;
                        winCell.add(ConnectFour.cellArray[i][j]);
                        if (winCellCount >= 4) break;
                    } else {
                        winCellCount = 0;
                        winCell.clear();
                    }
                }
                if (winCellCount >= 4) {

                    winCell.forEach((x) -> x.setBackground(Color.CYAN));
                    ConnectFour.gameOver = true;
                    return;
                }
            } else if (sumRowColumn < 6 && sumRowColumn > 2) { //deal with left up part of cells
                for (int i = 0, j = sumRowColumn; j >= 0; i++, j--) {
                    if (ConnectFour.cellArray[i][j].getText().equals(targetMark)) {
                        winCellCount++;
                        winCell.add(ConnectFour.cellArray[i][j]);
                        if (winCellCount >= 4) break;
                    } else {
                        winCellCount = 0;
                        winCell.clear();
                    }
                }
                if (winCellCount >= 4) {

                    winCell.forEach((x) -> x.setBackground(Color.CYAN));
                    ConnectFour.gameOver = true;
                    return;
                }
            }

            //backward diagonal check, difference of index and column is identical.
            winCellCount = 0;
            winCell.clear();
            int difference = bottomIndex - columnIndex;
            //only check the diagonal that current cell resides, no need to check all diagonal lines.
            if(difference >=0 && difference <=2 ) { //deal with bottom left part, ignore corner
                for (int i = 5, j = columnIndex + MAX_ROW - 1 - bottomIndex; j >=0 ; i--, j--) {
                    if (ConnectFour.cellArray[i][j].getText().equals(targetMark)){
                        winCellCount++;
                        winCell.add(ConnectFour.cellArray[i][j]);
                        if(winCellCount >=4) break;
                    }
                    else {
                        winCellCount = 0;
                        winCell.clear();
                    }
                }
                if (winCellCount >=4) {

                    winCell.forEach((x)->x.setBackground(Color.CYAN));
                    ConnectFour.gameOver = true;
                    return;
                }
            }
            else if (difference >= -3 && difference <= -1) { //deal with upper right part, ignore corner
                for (int i = 0, j = columnIndex - bottomIndex; j <= 6; i++, j++) {
                    if (ConnectFour.cellArray[i][j].getText().equals(targetMark)){
                        winCellCount++;
                        winCell.add(ConnectFour.cellArray[i][j]);
                        if(winCellCount >=4) break;
                    }
                    else {
                        winCellCount = 0;
                        winCell.clear();
                    }
                }
                if (winCellCount >=4) {

                    winCell.forEach((x)->x.setBackground(Color.CYAN));
                    ConnectFour.gameOver = true;
                    return;
                }
            }

            ConnectFour.columnTracker.columnBottom.put(columnIndex, --bottomIndex);

        } else {
            System.out.println("This column is full or game over!");
        }
    }
}
