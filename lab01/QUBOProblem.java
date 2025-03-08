import java.util.Arrays;

/**
 * Klasa reprezentująca instancję problemu QUBO.
 * Problem QUBO polega na znalezieniu binarnego wektora minimalizujacego wyrazenie x^T Q x (elementy wektora x to 0 i 1),
 * Q to macierz wspolczynnikow .
 */

public class QUBOProblem {
    
    private double[][] couplingMatrix;
    private int[] optimalState;
    private double optimalStateValue;

    //* Kod implementacji */

    public QUBOProblem() {
        couplingMatrix = new double[3][3];
        optimalState = new int[couplingMatrix.length];
        optimalStateValue = 100;
    }

    public QUBOProblem(double[][] matrix) {
        this.couplingMatrix = matrix;
        this.optimalState = new int[matrix.length];
        this.optimalStateValue = 100;
    }

    public QUBOProblem(double[][] matrix, int[] optState) {
        this.couplingMatrix = matrix;
        this.optimalState = optState;
        this.optimalStateValue = 100;
    }
    
    public QUBOProblem(double[][] matrix, int[] optState, double optStateVal) {
        this.couplingMatrix = matrix;
        this.optimalState = optState;
        this.optimalStateValue = optStateVal;
    }
    
    private void tryNewState(int[] vector) {
        double value = 0;
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                value += this.couplingMatrix[i][j] * vector[i] * vector[j];
            }
        }
        if (value < this.optimalStateValue) {
            this.optimalState = vector;
            this.optimalStateValue = value;
        }
    }

    private void compareObjects(QUBOProblem qubo) {
        boolean result = true;
        if (this.couplingMatrix == qubo.couplingMatrix 
        && this.optimalStateValue == qubo.optimalStateValue) {
            for (int i = 0; i < this.couplingMatrix.length; i++) {
                for (int j = 0; j < this.couplingMatrix.length; j ++) {
                    if (this.couplingMatrix[i] != qubo.couplingMatrix[i]) {
                        result = false;
                    }
                }
            }
            for (int i = 0; i < this.optimalState.length; i++) {
                if (this.optimalState[i] != qubo.optimalState[i]) {
                    result = false;
                }
            }
        } else {
            result = false;
        }
        if (result == true) {
            System.out.println("Macierze są równe");
        } else {
            System.out.println("Macierze nie są równe");
        }
    }
    
    // Metody pomocnicze

    private void validateCouplingMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Coupling matrix cannot be null");
        }
        
        double n = matrix.length;
        
        // Check for square matrix
        for (double[] row : matrix) {
            if (row.length != n) {
                throw new IllegalArgumentException("Coupling matrix must be square");
            }
        }
    }
    
    private double[][] deepCopy(double[][] matrix) {
        double[][] copy = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    private double returnValue() {
        return this.optimalStateValue;
    }

    private int[] returnState() {
        return this.optimalState;
    }

    private double[][] returnMatrix() {
        return deepCopy(this.couplingMatrix);
    }

    
    public static void main(String[] args) {
        double[][] matrix = {{0.6, 1.2, -0.4}, {0.7, 1, 0}, {-2.0, 0.6, 0.1}};
        QUBOProblem qubo1 = new QUBOProblem(matrix);
        QUBOProblem qubo2 = new QUBOProblem(matrix, new int[]{0, 1, 1});
        QUBOProblem qubo3 = new QUBOProblem(matrix, new int[]{1, 1, 1}, -0.1);
        qubo1.validateCouplingMatrix(qubo1.couplingMatrix);
        qubo1.tryNewState(new int[]{0, 0, 1});
        System.out.println("Optymalny stan: ");
        System.out.println(qubo1.returnValue());
        int[] bestState = qubo1.returnState(); 
        for (int i = 0; i < bestState.length; i++) {
            System.out.println(bestState[i]);
        }
        double[][] q1Matrix = qubo1.returnMatrix(); 
        for (int i = 0; i < q1Matrix.length; i++) {
            for (int j = 0; j < q1Matrix.length; j++) {
                System.out.println(q1Matrix[i][j]);
            }
        }
        System.out.println("Wartość stanu optymalnego: ");
        System.out.println(qubo2.optimalStateValue);
        qubo2.validateCouplingMatrix(qubo2.couplingMatrix);
        qubo2.tryNewState(new int[]{0, 0, 1});
        System.out.println("Optymalny stan: ");
        for (int i = 0; i < qubo2.optimalState.length; i++) {
            System.out.println(qubo2.optimalState[i]);
        }
        System.out.println("Wartość stanu optymalnego: ");
        System.out.println(qubo3.optimalStateValue);
        qubo3.validateCouplingMatrix(qubo3.couplingMatrix);
        qubo3.tryNewState(new int[]{0, 0, 1});
        System.out.println("Optymalny stan: ");
        for (int i = 0; i < qubo3.optimalState.length; i++) {
            System.out.println(qubo3.optimalState[i]);
        }
        System.out.println("Wartość stanu optymalnego: ");
        System.out.println(qubo3.optimalStateValue);
        qubo1.compareObjects(qubo1);
    }
    
}