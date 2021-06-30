package vrp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private static BigDecimal[][] matrix;

    public static void setMatrix(List<Location> locationList){
        matrix = new BigDecimal[locationList.size()][locationList.size()];
        for (int i = 0; i < locationList.size(); i++){
            for (int j = 0; j < locationList.size(); j++){
                matrix[i][j] = locationList.get(i).getDist(locationList.get(j));
            }
        }
    }

    public static BigDecimal getDist(int i, int j){
        if (i == -1) {i = matrix.length - 1;}
        if (j == -1) {j = matrix.length - 1;}
        return matrix[i][j];
    }

    public static void clear(){
        matrix = new BigDecimal[0][0];
    }
}
