package model.kfwdkmsn;

public class Kfwdkmsn {
    public static Double[][] getMap() {
        Double[][] map = new Double[][]{
                {0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
                {1.801, 1.766, 1.668, 1.668, 1.833, 1.833},
                {3.072, 3.028, 2.983, 2.936, 3.055, 3.055},
                {6.141, 5.959, 6.032, 6.011, 5.959, 5.959},
                {9.473, 8.881, 8.883, 8.945, 9.093, 9.095},
                {12.385, 12.258, 11.781, 11.631, 11.778, 11.831},
                {15.085, 15.657, 15.097, 14.563, 14.665, 14.807},
                {18.001, 18.910, 18.304, 17.525, 17.245, 17.563},
                {22.823, 21.121, 20.372, 20.001, 19.423, 19.617},
                {29.530, 26.722, 25.676, 25.741, 25.042, 24.796},
                {36.836, 35.467, 35.176, 35.190, 34.503, 33.807},
                {41.062, 39.792, 40.012, 40.280, 39.824, 39.110},
                {44.787, 43.750, 43.761, 44.136, 44.042, 44.042},
                {50.797, 50.004, 50.004, 50.004, 50.004, 50.004},
                {70.419, 69.221, 69.221, 69.221, 70.323, 70.323},
                {89.681, 89.240, 89.240, 89.240, 90.010, 90.010},
        };

        return map;
    }

    public static Double[] getXAxis() {
        return new Double[]{1000d, 2000d, 3000d, 4000d, 5000d, 6000d};
    }

    public static Double[] getYAxis() {
        return new Double[]{0d, 10d, 17.8d, 39.20d, 66d, 95d, 130d, 165d, 200d, 300d, 485d, 625d, 780d, 1000d, 1490d, 1760d};
    }
}
