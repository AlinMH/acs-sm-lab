import java.util.ArrayList;
import java.util.Scanner;

public class LRU {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		
		System.out.print("nr of elem=");
		int nrOfElements = s.nextInt();
		
		
		for (int i = 0; i < nrOfElements; i++) {
			arrayList.add(s.nextInt());
		}
		
		
		while(true) {
			int elem = s.nextInt();
			
			for (int i = 0; i < arrayList.size(); i++) {
				if (arrayList.get(i) == elem) {
					arrayList.add(0, arrayList.remove(i));
					break;
				}
			}
			
			System.out.print("list:");
			System.out.println(arrayList);	
		}
	}
	
}
