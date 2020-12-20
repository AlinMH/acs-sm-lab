import java.util.Scanner;

public class CrossBar {
	private int k;
	private String intrare;
	private String iesire;

	public CrossBar(int k, String intrare, String iesire) {
		this.k = k;
		this.intrare = intrare;
		this.iesire = iesire;
		crossBarLogic();
	}

	private void crossBarLogic() {
		Integer result = Integer.parseInt(intrare, 2) ^ Integer.parseInt(iesire, 2);
		String binaryString = Integer.toBinaryString(result);

		if (binaryString.length() < k) {
			String s = "";

			for (int i = 0; i < k - binaryString.length(); i++) {
				s += "0";
			}
			s += binaryString;
			binaryString = s;
		}

		for (int i = 0; i < k; i++) {
			System.out.println(binaryString.charAt(i) == '0' ? "cross" : "bar");
		}
	}

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);

		int k = myScanner.nextInt();
		String intrare = myScanner.next();
		String iesire = myScanner.next();

		CrossBar crossBar = new CrossBar(k, intrare, iesire);
	}
}
