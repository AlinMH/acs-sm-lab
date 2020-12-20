public class Main {
	public static void main(String[] args) {
		CRCW crcw = new CRCW();
		CREW crew = new CREW();

		int n = 3;
		int[][] A = new int[3][3];
		int[][] B = new int[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					A[i][j] = 1;
					B[i][j] = i + j;
				} else {
					A[i][j] = 0;
					B[i][j] = j - 3;
				}
			}
		}

		int[][] C = crcw.mult(A, B, n);
		int[][][] V = crew.mult(A, B, n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(V[i][j][0] + " ");
			}
			System.out.println();
		}

	}
}
