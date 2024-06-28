package SimpsonRules;

import FunctionInterface.Function;

public class ComposedSimpsonRule {
    static final Function firstPartLoad = new Function() {
        @Override
        public double value(double time) {
            double[] firstPartValues = {128.67, 33.4125, 62.20, 28.7575, 54.61, 27.445, 58.6, 32.345};
            return firstPartValues[(int) time];
        }
    };

    static final Function secondPartLoad = new Function() {
        @Override
        public double value(double time) {
            double[] secondPartValues = {71.365, 38.48, 80.665, 41.38, 84.185, 42.9025, 87.795, 44.75};
            return secondPartValues[(int) time - 8]; // Ajuste para o índice correto
        }
    };

    static final Function thirdPartLoad = new Function() {
        @Override
        public double value(double time) {
            double[] thirdPartValues = {89.88, 44.1075, 84.63, 40.07, 76.115, 36.76, 72.345, 143.30};
            return thirdPartValues[(int) time - 16]; // Ajuste para o índice correto
        }
    };

    static final Function firstPartSupply = new Function() {
        @Override
        public double value(double time) {
            double[] firstPartValues = {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 14.645, 22.585};
            return firstPartValues[(int) time];
        }
    };

    static final Function secondPartSupply = new Function() {
        @Override
        public double value(double time) {
            double[] secondPartValues = {126.00, 73.175, 184.57, 47.355, 86.915, 80.8925, 219.545, 86.165};
            return secondPartValues[(int) time - 8]; // Ajuste para o índice correto
        }
    };

    static final Function thirdPartSupply = new Function() {
        @Override
        public double value(double time) {
            double[] thirdPartValues = {139.64, 67.74, 78.86, 14.4325, 1.25, 0.00, 0.00, 0.00};
            return thirdPartValues[(int) time - 16]; // Ajuste para o índice correto
        }
    };

    public static double composedSimpson(Function f, double a, double b, double deltaX) {
        double n = 12 * deltaX;
        double h = (b - a) / n;
        double somaTotal = 0;
        if (a == 0){
            somaTotal = f.value(a);
            for (int i=1; i <= 7; i++){
                System.out.print(somaTotal + " ");
                if (i % 2 == 0){
                    somaTotal += 2 * f.value(i);
                } else {
                    somaTotal += 4 * f.value(i);
                }
            }
        } else if (b == 23){
            somaTotal = f.value(b);
            for (int i = 16; i < 23; i++){
                System.out.print(somaTotal + " ");
                if (i % 2 == 0)
                    somaTotal += 2 * f.value(i);
                else
                    somaTotal += 4 * f.value(i);
            }
        } else {
            somaTotal = 2* f.value(a);
            for (int i = 9; i <= 15; i++){
                System.out.print(somaTotal + " ");
                if (i % 2 == 0)
                    somaTotal += 2 * f.value(i);
                else
                    somaTotal += 4 * f.value(i);
            }
        }

        return somaTotal;
    }

    public static void main(String[] args) {
        System.out.println("---- LOAD FUNCTION ----\n");

        System.out.println("\nCálculo da Regra de Simpson (Load) - Primeira Parte do Dia: " + composedSimpson(firstPartLoad, 0, 7,2));
        double firstSumLoad = composedSimpson(firstPartLoad, 0, 7,2);
        System.out.println("\nCálculo da Regra de Simpson (Load) - Segunda Parte do Dia: " + composedSimpson(secondPartLoad, 8, 15,2));
        double secondSumLoad = composedSimpson(secondPartLoad, 8, 15,2);
        System.out.println("\nCálculo da Regra de Simpson (Load) - Terceira Parte do Dia: " + composedSimpson(thirdPartLoad, 16, 23,2));
        double thirdSumLoad = composedSimpson(thirdPartLoad, 16, 23,2);

        double soma = firstSumLoad + secondSumLoad + thirdSumLoad;
        System.out.println();
        System.out.println("\n---- SUM LOAD FUNCTION ----");
        System.out.println(soma);

        System.out.println("\n---- SOLAR FUNCTION ----\n");

        System.out.println("\nCálculo da Regra de Simpson (Supply) - Primeira Parte do Dia: " + composedSimpson(firstPartSupply, 0, 7,2) + "\n");
        double firstSumSupply = composedSimpson(firstPartSupply, 0, 7,2);
        System.out.println("\nCálculo da Regra de Simpson (Supply) - Segunda Parte do Dia: " + composedSimpson(secondPartSupply, 8, 15,2) + "\n");
        double secondSumSupply = composedSimpson(secondPartSupply, 8, 15,2);
        System.out.println("\nCálculo da Regra de Simpson (Supply) - Terceira Parte do Dia: " + composedSimpson(thirdPartSupply, 16, 23,2) + "\n");
        double thirdSumSupply = composedSimpson(thirdPartSupply, 16, 23,2);

        soma = firstSumSupply + secondSumSupply + thirdSumSupply;
        System.out.println();
        System.out.println("\n---- SUM SOLAR FUNCTION ----");
        System.out.println(soma);

        System.out.println("\n---- DIFFERENCE IN THREE PARTS OF THE DAY ----");
        double firstDifference = firstSumLoad - firstSumSupply;
        double secondDifference = secondSumLoad - secondSumSupply;
        double thirdDifference = thirdSumLoad - thirdSumSupply;

        double spent;
        double profit;
        double totalProfit = 0;
        double totalSpent = 0;

        //PRIMEIRA DIFERENÇA
        if (firstDifference > 0 ){
            System.out.println("\nDiferença na primeira parte do dia: "+firstDifference+" KwH");
            System.out.println("Armazenamento de energia maior que fornecimento!");
            spent = 0.18 * firstDifference;
            System.out.printf("Despesa de : $%.2f%n", spent);
            totalSpent += spent;
        } else {
            System.out.println("\nDiferença na primeira parte do dia: "+(-1)*firstDifference+" KwH");
            System.out.println("Fornecimento de Energia maior que o armazenamento!");
            profit = 0.50 * firstDifference;
            System.out.printf("Ganho de : $%.2f%n", profit);
            totalProfit += profit;
        }
        //SEGUNDA DIFERENÇA
        if (secondDifference > 0){
            System.out.println("\nDiferença na segunda parte do dia: "+secondDifference+" KwH");
            System.out.println("Armazenamento de energia maior que fornecimento!");
            spent = 0.18 * secondDifference;
            System.out.printf("Despesa de : $%.2f%n", spent);
            totalSpent += spent;
        } else {
            System.out.println("\nDiferença na segunda parte do dia: "+(-1)*secondDifference+" KwH");
            System.out.println("Fornecimento de Energia maior que o armazenamento!");
            profit = 0.50 * ((-1)*secondDifference);
            System.out.printf("Ganho de : $%.2f%n", profit);
            totalProfit += profit;
        }
        //TERCEIRA DIFERENÇA
        if (thirdDifference > 0){
            System.out.println("\nDiferença na terceira parte do dia: "+thirdDifference+" KwH");
            System.out.println("Armazenamento de energia maior que fornecimento!");
            spent = 0.18 * thirdDifference;
            System.out.printf("Despesa de : $%.2f%n", spent);
            totalSpent += spent;
        } else {
            System.out.println("\nDiferença na terceira parte do dia: "+(-1)*thirdDifference+" KwH");
            System.out.println("Fornecimento de Energia maior que o armazenamento!");
            profit = 0.50 * thirdDifference;
            System.out.printf("Ganho de : $%.2f%n", profit);
            totalProfit += profit;
        }

        System.out.println("\n---- RESULTADOS ----");

        System.out.println("\nGasto total: $"+totalSpent);
        System.out.println("Ganho total: $"+totalProfit);

        double profitOrNot = totalProfit - totalSpent;
        if (profitOrNot > 0){
            System.out.println("\nObteve-se um lucro de: $"+profitOrNot);
        } else
            System.out.println("\nObteve-se um prejuízo de $"+profitOrNot);

    }
}