package syntax;

import java.util.Arrays;

public class MathOps {

    public static void mathBasics() {
        System.out.println("------------------------------------------");
        System.out.println("MATH BASICS");
        System.out.println("---------------------");

        // Basic math operations
        int a = 10, b = 3;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b)); // Integer division
        System.out.println("a % b = " + (a % b)); // Modulo
        System.out.println("a ^ b = " + (a ^ b)); // XOR
        System.out.println("a & b = " + (a & b)); // AND
        System.out.println("a | b = " + (a | b)); // OR
        System.out.println("~a = " + (~a)); // NOT
        System.out.println("a << 1 = " + (a << 1)); // Left shift
        System.out.println("a >> 1 = " + (a >> 1)); // Right shift
        System.out.println("a >>> 1 = " + (a >>> 1)); // Unsigned right shift

        // Math class functions
        System.out.println("Math.abs(-5) = " + Math.abs(-5));
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
        System.out.println("Math.min(10, 20) = " + Math.min(10, 20));
        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3));
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.ceil(3.2) = " + Math.ceil(3.2));
        System.out.println("Math.floor(3.8) = " + Math.floor(3.8));
        System.out.println("Math.round(3.6) = " + Math.round(3.6));
    }

    public static void numberTheory() {
        System.out.println("------------------------------------------");
        System.out.println("NUMBER THEORY");
        System.out.println("---------------------");

        // GCD and LCM
        int num1 = 48, num2 = 18;
        System.out.println("GCD of " + num1 + " and " + num2 + ": " + gcd(num1, num2));
        System.out.println("LCM of " + num1 + " and " + num2 + ": " + lcm(num1, num2));

        // Prime numbers
        int n = 20;
        System.out.println("Is " + n + " prime? " + isPrime(n));
        System.out.println("Prime factors of " + n + ": " + Arrays.toString(getPrimeFactors(n)));

        // Sieve of Eratosthenes
        int limit = 30;
        boolean[] primes = sieveOfEratosthenes(limit);
        System.out.println("Primes up to " + limit + ":");
        for (int i = 2; i <= limit; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        // Fibonacci
        System.out.println("Fibonacci sequence (first 10):");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }

    public static void bitManipulation() {
        System.out.println("------------------------------------------");
        System.out.println("BIT MANIPULATION");
        System.out.println("---------------------");

        int num = 42;
        System.out.println("Number: " + num + " (Binary: " + Integer.toBinaryString(num) + ")");

        // Count set bits
        System.out.println("Number of set bits: " + countSetBits(num));

        // Check if power of 2
        System.out.println("Is power of 2? " + isPowerOfTwo(num));

        // Get lowest set bit
        System.out.println("Lowest set bit: " + getLowestSetBit(num));

        // Clear lowest set bit
        System.out.println("After clearing lowest set bit: " + clearLowestSetBit(num));

        // Get position of rightmost set bit
        System.out.println("Position of rightmost set bit: " + getRightmostSetBitPosition(num));

        // Check if even/odd
        System.out.println("Is even? " + isEven(num));
        System.out.println("Is odd? " + isOdd(num));

        // Swap without temp
        int x = 5, y = 10;
        System.out.println("Before swap: x = " + x + ", y = " + y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("After swap: x = " + x + ", y = " + y);
    }

    public static void combinatorics() {
        System.out.println("------------------------------------------");
        System.out.println("COMBINATORICS");
        System.out.println("---------------------");

        int n = 5, r = 3;
        System.out.println("n = " + n + ", r = " + r);

        // Permutation
        System.out.println("P(" + n + "," + r + ") = " + permutation(n, r));

        // Combination
        System.out.println("C(" + n + "," + r + ") = " + combination(n, r));

        // Factorial
        System.out.println(n + "! = " + factorial(n));

        // Catalan numbers
        System.out.println("Catalan numbers (first 8):");
        for (int i = 0; i < 8; i++) {
            System.out.print(catalanNumber(i) + " ");
        }
        System.out.println();
    }

    public static void matrixOperations() {
        System.out.println("------------------------------------------");
        System.out.println("MATRIX OPERATIONS");
        System.out.println("---------------------");

        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        System.out.println("Matrix 1:");
        printMatrix(matrix1);

        System.out.println("Matrix 2:");
        printMatrix(matrix2);

        // Transpose
        System.out.println("Transpose of Matrix 1:");
        printMatrix(transpose(matrix1));

        // Matrix multiplication
        System.out.println("Matrix 1 * Matrix 2:");
        printMatrix(multiplyMatrix(matrix1, matrix2));

        // Rotate matrix 90 degrees
        System.out.println("Matrix 1 rotated 90 degrees:");
        printMatrix(rotateMatrix90(matrix1));
    }

    // Helper methods for Number Theory
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] getPrimeFactors(int n) {
        int[] factors = new int[0];
        int temp = n;
        
        for (int i = 2; i * i <= temp; i++) {
            while (temp % i == 0) {
                factors = Arrays.copyOf(factors, factors.length + 1);
                factors[factors.length - 1] = i;
                temp /= i;
            }
        }
        
        if (temp > 1) {
            factors = Arrays.copyOf(factors, factors.length + 1);
            factors[factors.length - 1] = temp;
        }
        
        return factors;
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        return isPrime;
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Helper methods for Bit Manipulation
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static int getLowestSetBit(int n) {
        return n & (-n);
    }

    public static int clearLowestSetBit(int n) {
        return n & (n - 1);
    }

    public static int getRightmostSetBitPosition(int n) {
        if (n == 0) return -1;
        return (int) (Math.log(n & (-n)) / Math.log(2));
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    // Helper methods for Combinatorics
    public static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static long permutation(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    public static long combination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public static long catalanNumber(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 0; i < n; i++) {
            result += catalanNumber(i) * catalanNumber(n - 1 - i);
        }
        return result;
    }

    // Helper methods for Matrix Operations
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }

    public static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        
        int[][] result = new int[rowsA][colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return result;
    }

    public static int[][] rotateMatrix90(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        mathBasics();
        numberTheory();
        bitManipulation();
        combinatorics();
        matrixOperations();
    }
} 