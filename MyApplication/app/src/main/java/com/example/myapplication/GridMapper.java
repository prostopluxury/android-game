package com.example.myapplication;

import android.support.v7.widget.helper.ItemTouchHelper;

public class GridMapper {

    private static int columns;

    private static int rows;

    private static int currentRow;

    private static int currentColumn;

    public static final int GRIDSIZE = 3;

    static {
        columns = GRIDSIZE;
        rows = GRIDSIZE;
    }

    private static void toGrid(int index) {
        currentColumn = index % columns;
        currentRow = index / rows;
    }

    public static int isNeighbour(int nonEmptyCellIndex, int emptyCellIndex) {
        toGrid(nonEmptyCellIndex);
        int column1 = currentColumn;
        int row1 = currentRow;

        toGrid(emptyCellIndex);
        int column2 = currentColumn;
        int row2 = currentRow;

        if (row1 == row2 && Math.abs(column1 - column2) == 1) {
            if (column1 - column2 > 0)
                return ItemTouchHelper.LEFT;

            return ItemTouchHelper.RIGHT;
        }

        if (column1 == column2 && Math.abs(row1 - row2) == 1) {
            if (row1 - row2 > 0)
                return ItemTouchHelper.UP;

            return ItemTouchHelper.DOWN;
        }

        return 0;
    }
}