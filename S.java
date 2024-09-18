import java.util.HashMap;
import java.util.Map;

public class ShamirSecretSharing {

    public static void main(String[] args) {
        // Sample JSON-like data, parsed manually
        int n = 4;  // Number of roots
        int k = 3;  // k = m + 1
        int[][] roots = {
            {1, decodeValue("4", 10)},   // (x = 1, y = 4)
            {2, decodeValue("111", 2)},  // (x = 2, y = 7)
            {3, decodeValue("12", 10)},  // (x = 3, y = 12)
            {6, decodeValue("213", 4)}   // (x = 6, y = 43)
        };

        // Finding c using Lagrange Interpolation
        double c = computeConstantTerm(roots, k);
        
        // Print the output
        System.out.println("The constant term (c) of the polynomial is: " + c);
    }

    // Method to decode a string value from a given base
    private static int decodeValue(String value, int base) {
        return Integer.parseInt(value, base);
    }

    // Method to compute the constant term using Lagrange Interpolation
    private static double computeConstantTerm(int[][] roots, int k) {
        double c = 0;

        // Apply Lagrange Interpolation to compute f(0), which gives us the constant term
        for (int i = 0; i < k; i++) {
            int xi = roots[i][0];
            int yi = roots[i][1];

            // Calculate the Lagrange basis polynomial for each i
            double term = yi;
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    int xj = roots[j][0];
                    term *= (0.0 - xj) / (xi - xj);  // Lagrange basis polynomial formula
                }
            }
            c += term;
        }

        return c;
    }
}
