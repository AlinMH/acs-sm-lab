import java.lang.Math;

public class CREW {
	int[][][] mult(int [][] A, int[][] B, int n) {
		int[][][] V = new int[n][n][n + 1];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = n - 1; k >= 0; k--) {
					V[i][j][k] = A[i][k] * B[k][j];
					for (int m = 0; m < Math.log(n)/ Math.log(2); m++) {
						if (k % (int)Math.pow(2, m + 1) == 0) {
							V[i][j][k] += V[i][j][(int)(k + Math.pow(2, m))];
						}
					}
				}
			}
		}
		
		return V;
	}
	
}
