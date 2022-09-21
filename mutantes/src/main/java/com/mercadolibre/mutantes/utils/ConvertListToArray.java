package com.mercadolibre.mutantes.utils;

import java.util.List;

public class ConvertListToArray {

    /**
     * Get a list and convert it in a char matrix, futhermore check if the array is NxN.
     *
     * @param list
     * @return A char matrix
     */
    public char[][] convertList(List<String> list) {
        int length = list.size();
        char[][] matrix = new char[length][length];

        for (int row = 0; row < length; row++) {
            if (list.get(row).length() != length) {
                return null;
            }
            for (int column = 0; column < length; column++) {
                matrix[row][column] = list.get(row).charAt(column);
            }
        }

        return matrix;
    }
}
