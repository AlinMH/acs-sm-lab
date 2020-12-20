import java.util.ArrayList;
import java.util.Scanner;

public class Benes {
	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private static final int N = 8;
	private static final int E = 5;

	public Benes(ArrayList<Integer> inputs, ArrayList<Integer> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
		doRouting();
	}

	private void doRouting() {
		for (int j = 0; j < inputs.size(); j++) {
			int aux_in = inputs.get(j);
			int aux_out = outputs.get(j);
			int[] in_rev = new int[E];
			int[] out_rev = new int[E];
			int[] path = new int[E];
			
			if (aux_in >= 4) {
				aux_in = 7 - aux_in;
				aux_out = 7 - aux_out;
			}
			
			for (int i = E - 1; i >= 0; i--) {
				in_rev[i] = aux_in % 2;
				aux_in /= 2;

				if (aux_in == 0)
					break;
			}

			for (int i = E - 1; i >= 0; i--) {
				out_rev[i] = aux_out % 2;
				aux_out /= 2;

				if (aux_out == 0)
					break;
			}

			for (int i = 0; i < E; i++) {
				if (i == 0 || i == 1) {
					if (in_rev[E - 1] == 0 && in_rev[E - 2] == 0)
						path[i] = 0;

					if (in_rev[E - 1] == 1 && in_rev[E - 2] == 1)
						path[i] = 1;

					if (in_rev[E - 1] == 0 && in_rev[E - 2] == 1)
						path[i] = in_rev[E + i - 2] ^ 1;

					if ((in_rev[E - 1] == 1) && (in_rev[E - 2] == 0)) {
						path[i] = in_rev[E + i - 2] ^ 1;
					}
				} else {
					if (out_rev[i] == 1)
						path[i] = 1;
					else
						path[i] = 0;
				}
			}

			System.out.print("Path for " + inputs.get(j) + "->" + outputs.get(j) + ": ");
			for (int i = 0; i < E; i++) {
				if (path[i] == 0) {
					System.out.print("Straight ");
				} else {
					System.out.print("Cross ");
				}
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		ArrayList<Integer> outputs = new ArrayList<Integer>();

		Scanner s = new Scanner(System.in);
		System.out.println("Numarul de intrari");
		int n = s.nextInt();

		System.out.print("Intrari: ");
		for (int i = 0; i < n; i++) {
			inputs.add(s.nextInt());
		}

		System.out.print("Iesiri: ");
		for (int i = 0; i < n; i++) {
			outputs.add(s.nextInt());
		}

		Benes benes = new Benes(inputs, outputs);
	}
}
