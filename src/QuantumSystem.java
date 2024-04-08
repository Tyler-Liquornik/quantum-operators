import java.util.Arrays;

/**
 * Calculation of a sum of linear operators on a superposition quantum state for ECE2238
 *
 * @author Tyler Liquornik
 */
public class QuantumSystem {

    public static void main(String[] args) {
        // Define the complex coefficients corresponding to the state vector |ψ⟩
        // Define the complex coefficients corresponding to the state vector |ψ⟩
        Complex[] coefficients = {
                new Complex(0.0737, 0.1277),  // Coefficient for |1,12⟩
                new Complex(0.1966, -0.3405), // Coefficient for |1,15⟩
                new Complex(-0.2554, -0.1474), // Coefficient for |12,12⟩
                new Complex(-0.4178, 0.7236)  // Coefficient for |12,18⟩
        };

        // Define states with the quantum numbers for each state
        double[][] states = {
                {1, 12},
                {1, 15},
                {12, 12},
                {12, 18}
        };

        // Calculate the eigenvalues for each state
        double[][] eigenvalues = getEigenvalues(states);

        // Calculate the expectation value
        double expectationValue = calculateExpectationValue(coefficients, eigenvalues);

        // Output the result
        System.out.println("The expectation value is: " + expectationValue);
    }

    /**
     * @param coefficients The array of complex coefficients representing the state vector.
     * @param eigenvalues  The 2D array of eigenvalues for each state.
     * @return The calculated expectation value as a double.
     */
    public static double calculateExpectationValue(Complex[] coefficients, double[][] eigenvalues) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            double sumOfEigenvalues = 0;
            for (int j = 0; j < eigenvalues[i].length; j++) {
                sumOfEigenvalues += eigenvalues[i][j];
            }
            sum += coefficients[i].modulusSquared() * sumOfEigenvalues;
        }
        return sum;
    }

    /**
     * @param state Array of arrays of size n, where n is the number of "qubits" in a state
     * @return A new 2D array with each state transformed to its eigenvalue.
     */
    public static double[][] getEigenvalues(double[][] state) {
        return Arrays.stream(state)
                .map(QuantumSystem::operatorFunctionSet)
                .toArray(double[][]::new);
    }

    /**
     * @param array An array containing any number of operators in a sum of operators (array length 1 for a single operator)
     * @return A new array with the operation applied to each element of the input array.
     */
    public static double[] operatorFunctionSet(double[] array){
        return new double[] {Math.sqrt(array[0]), Math.sqrt(array[1])};
    }

    public static class Complex {
        private final double real;      ///< The real part of the complex number.
        private final double imaginary; ///< The imaginary part of the complex number.

        /**
         * @param real The real part of the complex number.
         * @param imaginary The imaginary part of the complex number.
         */
        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        /**
         * @return The modulus squared as a double.
         */
        public double modulusSquared() {
            return real * real + imaginary * imaginary;
        }
    }
}