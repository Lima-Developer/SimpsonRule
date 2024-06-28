package Profit;

public class CalculatedProfit {
    static final double LOAD = 142.8;
    static final double SUPPLY = 151.7;
    static final int LOWERBOUND = 0;
    static final int UPPERBOUND = 23;
    static final double N = 24;

    public static double loadFunction(int t) {
        double cos1 = Math.cos(2 * Math.PI * t / 24);
        double sin1 = Math.sin(2 * Math.PI * t / 24);
        double cos2 = Math.cos(4 * Math.PI * t / 24);
        double sin2 = Math.sin(4 * Math.PI * t / 24);
        double cos3 = Math.cos(6 * Math.PI * t / 24);
        double sin3 = Math.sin(6 * Math.PI * t / 24);

        double function = LOAD + (-19.39 * cos1) + (-17.03 * sin1) + (4.56 * cos2) + (-3.80 * sin2) + (5.15 * cos3) + (-0.93 * sin3);

        return function;
    }

    public static double supplyFunction(int t) {
        if (t < 5) {
            return 0;
        } else if (t > 18) {
            return 0;
        } else {
            double cos1 = Math.cos(2 * Math.PI * t / 24);
            double sin1 = Math.sin(2 * Math.PI * t / 24);
            double cos2 = Math.cos(4 * Math.PI * t / 24);
            double sin2 = Math.sin(4 * Math.PI * t / 24);
            double cos3 = Math.cos(6 * Math.PI * t / 24);
            double sin3 = Math.sin(6 * Math.PI * t / 24);

            double function = SUPPLY + (-212.8 * cos1) + 31.45 * sin1 + 57.48 * cos2 + 8.61 * sin2 + 36.5 * cos3 + (-0.89 * sin3);

            return function;
        }
    }

    public static double complexSimpson(int a, int b) {
        double h = (b - a) / N;
        double loadSum = 0;
        double supplySum = 0;

        // Somar os valores de LOAD e SUPPLY
        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) { // Se i é par
                loadSum += 2 * loadFunction(i);
                supplySum += 2 * supplyFunction(i);
            } else { // Se i é ímpar
                loadSum += 4 * loadFunction(i);
                supplySum += 4 * supplyFunction(i);
            }
        }

        // Calcular a diferença
        double difference = loadSum - supplySum;
        System.out.printf("Load Sum: %.2f\n", loadSum);
        System.out.printf("Supply Sum: %.2f\n", supplySum);
        if (difference < 0) {
            double gain = (0.5 * difference) * -1;
            System.out.printf("Gain: $ %.2f\n", gain);
        } else if (difference > 0) {
            double surplus = 0.18 * difference;
            System.out.printf("Surplus: $ %.2f\n", surplus);
        } else {
            double surplus = 0;
            double gain = 0;
        }

        return (h / 3) * difference;
    }


    public static void main(String[] args) {
        double result = complexSimpson(LOWERBOUND, UPPERBOUND);
        System.out.printf("Integral´s Result: %.2f\n", result);
    }
}
