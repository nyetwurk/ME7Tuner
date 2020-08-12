package model.kfzw;

public class Kfzw {

    public static Double[] getStockXAxis() {
        return new Double[]{9.75d, 18d, 27d, 39d, 50.25d, 62.25d, 74.25d, 97.50d, 120.75d, 144.75d, 168d, 191.25d};
    }

    public static Double[] getStockYAxis() {
        return new Double[]{440d, 720d, 1000d, 1240d, 1520d, 1760d, 2000d, 2520d, 3000d, 3520d, 4000d, 4520d, 5000d, 5520d, 6000d, 7000d};
    }

    public static Double[][] getStockMap() {
        return new Double[][]{
                {25.5, 25.5, 19.5, 15d, 12.75, 7.5, 0d, 0d, -10.5, -10.5, -10.5, -10.5},
                {28.5, 28.5, 23.25, 18d, 15d, 9d, 3.75, 0.75, -7.5, -10.5, -10.5, -10.5},
                {30.00,	30.00, 27.00, 21.75, 18.00,	15.00, 8.25, 4.50, -4.50, -9.00, -12.00, -12.75},
                {29.25, 29.25, 28.5, 24d, 20.25, 16.5, 14.25, 7.5, -0.75, -7.5, -12d, -13.5},
                {29.25, 29.25, 28.5, 25.5, 21.75, 20.25, 17.25, 11.25, 3.75, -6d, -9.75, -13.5},
                {32.25, 32.25, 28.5, 25.5, 22.5, 21d, 18d, 12d, 6d, -0.75, -6.75, -12d},
                {32.25, 32.25, 28.5, 27d, 24.75, 22.5, 19.5, 12.75, 7.5, 3.75, -2.25, -8.25},
                {40.5, 39.75, 33.75, 30d, 27.75, 25.5, 22.5, 16.5, 9d, 5.25, -1.5, -7.5},
                {42.25, 41.25, 36.75, 33d, 29.25, 26.25, 23.25, 18d, 9.75, 7.5, 0d, -6d},
                {41.25, 40.5, 36.75, 34.5, 31.5, 26.25, 23.25, 17.25, 10.5, 7.5, 0d, -5.25},
                {41.25, 40.5, 34.5, 31.5, 30d, 27d, 23.25, 15.75, 12d, 8.25, 0.75, -5.25},
                {41.25, 39d, 33.75, 32.25, 30.75, 25.5, 21.75, 15.75, 12d, 10.5, 1.5, -5.25},
                {41.25, 41.25, 38.25, 34.5, 31.5, 24d, 20.25, 15d, 11.25, 10.5, 0.75, -6d},
                {41.25, 41.25, 39d, 34.5, 31.5, 24.75, 20.25, 16.5, 15d, 12d, 0.75, -6d},
                {41.25, 41.25, 39d, 36.75, 32.25, 29.25, 24.75, 18.75, 16.5, 12d, 1.5, -5.25},
                {41.25, 41.25, 39.75, 36.75, 33.75, 32.25, 27.75, 24d, 18.75, 14.25, 2.25, -1.5}
        };

    }
}
