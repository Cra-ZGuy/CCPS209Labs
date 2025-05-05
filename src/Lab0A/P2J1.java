package Lab0A;

public class P2J1 {
    public static long fallingPower(int n, int k) {
        if (k == 0) return 1;

        return fallingPower(n - 1, k - 1) * n;
    }

    public static int[] everyOther(int[] arr) {
        // Define array of set size, half of array length rounded up.
        int[] evenNums = new int[arr.length / 2 + ((arr.length & 1) == 1 ? 1 : 0)];

        for (int i = 0; i < arr.length; i++) {
            if ((i & 1) == 1) continue;

            evenNums[i / 2] = arr[i];
        }

        return evenNums;
    }

    public static int[][] createZigZag(int rows, int cols, int start) {
        int[][] zigZag = new int[rows][cols];

        for (int rowCount = 0; rowCount < rows; rowCount++) {
            boolean isOdd = (rowCount & 1) == 1;

            for (int colCount = 0; colCount < cols; colCount++) {
                // Fill each cell with correct value in increasing order.
                // Reverse column index for odd-numbered rows to create zigzag pattern.
                zigZag[rowCount][colCount] = start + rowCount * cols + (isOdd ? cols - colCount - 1 : colCount);
            }
        }

        return zigZag;
    }

    public static int countInversions(int[] arr) {
        int inversions = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) inversions++;
            }
        }

        return inversions;
    }
}
