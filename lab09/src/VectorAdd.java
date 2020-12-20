import java.lang.Thread;
import java.util.ArrayList;
import java.util.Scanner;

public class VectorAdd implements Runnable {
	private ArrayList<Integer> aList;
	private int index;
	
	public VectorAdd(ArrayList<Integer> aList, int index) {
		this.aList = aList;
		this.index = index;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		System.out.print("nr. of elements = ");
		
		int nrOfElems = s.nextInt();
		Thread[] threads = new Thread[nrOfElems];
		
		for (int i = 0; i < nrOfElems; i++) {
			list.add(s.nextInt());
		}
		
		for (int i = 0; i < nrOfElems; i++) {
			threads[i] = new Thread(new VectorAdd(list, i));
		}
		
		for (int i = 0; i < nrOfElems; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < nrOfElems; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.print("list:");
		System.out.println(list);
		
	}

	@Override
	public void run() {
		aList.set(index, aList.get(index) + 1);
	}
}
