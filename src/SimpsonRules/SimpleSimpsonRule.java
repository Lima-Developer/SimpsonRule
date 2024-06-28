package SimpsonRules;

import FunctionInterface.Function;

import java.util.Scanner;

public class SimpleSimpsonRule {
    static final Function f = new Function() {
        @Override
        public double value(double value) {
            return Math.pow(value, 2) / 2;
        }
    };

    public static double simpleSimpsonRule(double lowerBound, double upperBound) {
        double h = (upperBound - lowerBound) / 2;
        double x0 = lowerBound;
        double x1 = lowerBound + h;
        double x2 = upperBound;

        return (h / 3) * (f.value(x0) + (4 * f.value(x1)) + f.value(x2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o limite inferior: ");
        double lowerBound = scanner.nextDouble();

        System.out.println("Digite o limite superior: ");
        double upperBound = scanner.nextDouble();

        System.out.println("Regra de Simpson Simples: " + simpleSimpsonRule(lowerBound, upperBound));
    }
}
