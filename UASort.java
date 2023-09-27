import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class UASort {
	public static String[] A;
	static long startTime;
	static long endTime;
	static long elapsedTime;
	static String[] files;
	static int size = 0;

	public static void main(String[] args) throws IOException {
		startTime = System.currentTimeMillis();
		if (args.length < 4) {
			System.out.println("Invalid syntax:   java UASort inputdir mackeyoutput numeric ascending");
			System.exit(100);
		} else {
			String inputDirectory = args[0];
			String outputDirectory = args[1];
			String numOrAlph = args[2];
			String ascOrDesc = args[3];
			UASort sort = new UASort();
			sort.traverseDirectory(args[0]);
			int i = 0;
			File location = new File(inputDirectory);
			for (File file : location.listFiles()) {
				size = sort.getArraySize(file.toString());
				System.out.println(file.toString());
 				A = new String[size];
				files[i] = file.getName();
				i++;
				if (file.isFile() && file.getName().endsWith(".txt")) {
					sort.readFileWords(file.getPath());
			switch (numOrAlph) {
			case "numeric":
				if (ascOrDesc.equals("ascending")) {
					int pivot = sort.selectPivot(A, 0, A.length - 1);
					System.out.println("Pivot = " + pivot);
					sort.quicksort(A, 0, pivot);
					sort.quicksort(A, pivot + 1, A.length - 1);
				} else {
					int pivot = sort.selectPivot(A, 0, A.length - 1);
					System.out.println("Pivot = " + pivot);
					sort.quicksortDesc(A, 0, pivot);
					sort.quicksortDesc(A, pivot + 1, A.length - 1);
				}
			case "alphabetic":
				if (ascOrDesc.equals("ascending")) {
					int pivot = sort.selectPivot(A, 0, A.length - 1);
					sort.quicksortWords(A, 0, pivot);
					sort.quicksortWords(A, pivot + 1, A.length - 1);
				} else {
					int pivot = sort.selectPivot(A, 0, A.length - 1);
					sort.quicksortWordsDesc(A, 0, pivot);
					sort.quicksortWordsDesc(A, pivot + 1, A.length - 1);
				}
			}
			sort.writeFiles(file.getName(), outputDirectory);
			}
		}
			System.out.println("Number of files sorted: " + files.length);
			System.out.println("Input Directory:        " + inputDirectory);
			System.out.println("Output Directory:       " + outputDirectory);
			System.out.println();
			System.out.println("Start Time:             " + startTime);
			endTime = System.currentTimeMillis();
			elapsedTime = (endTime - startTime) / 1000;
			System.out.println("End Time:               " + endTime);
			System.out.println("ElapsedTime:            " + elapsedTime);
	}
	}

	public int selectPivot(String[] A, int p, int r) {
		Random rand = new Random();
		return Math.floorDiv((rand.nextInt(r) + rand.nextInt(r) + rand.nextInt(r)), 3);
	}

	public int partition(String[] A, int p, int r) {
		int x = Integer.parseInt(A[r]);
		int i = p - 1;
		String temp = "";
		for (int j = p; j < r; j++) {
			if (Integer.parseInt(A[j]) <= x) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, r);
		return i + 1;
	}

	public int partitionDesc(String[] A, int p, int r) {
		int x = Integer.parseInt(A[r]);
		int i = p - 1;
		String temp = "";
		for (int j = p; j < r; j++) {
			if (Integer.parseInt(A[j]) > x) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, r);
		return i + 1;
	}

	public int partitionWords(String[] A, int p, int r) {
		String x = (A[r]);
		int i = p - 1;
		String temp = "";
		for (int j = p; j < r; j++) {
			if (A[j].compareTo(x) <= 0) {
				i++;
				swapWords(i, j);
			}
		}
		swapWords(i + 1, r);
		return i + 1;
	}

	public int partitionWordsDesc(String[] A, int p, int r) {
		int x = Integer.parseInt(A[r]);
		int i = p - 1;
		String temp = "";
		for (int j = p; j < r; j++) {
			if (Integer.parseInt(A[j]) > x) {
				i++;
				swapWords(i, j);
			}
		}
		swapWords(i + 1, r);
		return i + 1;
	}

	public void quicksort(String A[], int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quicksort(A, p, q - 1);
			quicksort(A, q + 1, r);
		}
	}

	public void quicksortDesc(String A[], int p, int r) {
		if (p < r) {
			int q = partitionDesc(A, p, r);
			quicksortDesc(A, p, q - 1);
			quicksortDesc(A, q + 1, r);
		}
	}

	public void quicksortWords(String A[], int p, int r) {
		if (p < r) {
			int q = partitionWords(A, p, r);
			quicksortWords(A, p, q - 1);
			quicksortWords(A, q + 1, r);
		}
	}

	public void quicksortWordsDesc(String A[], int p, int r) {
		if (p < r) {
			int q = partitionWordsDesc(A, p, r);
			quicksortWordsDesc(A, p, q - 1);
			quicksortWordsDesc(A, q + 1, r);
		}
	}

	void traverseDirectory(String startPath) throws IOException {
		File location = new File(startPath);
		if (location.listFiles().length == 0) {
			System.out.println("No Files In The Specified Directory");
			System.exit(100);
		} else {
			files = new String[location.listFiles().length];
		}
	}

	void readFileWords(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		int size = 0;
		while ((line = br.readLine()) != null) {
			A[size] = (line);
			size++;
		}
		br.close();
	}

	void writeFiles(String fileName, String locationDir) throws IOException {
		PrintWriter pw;
			pw = new PrintWriter(locationDir + "\\" + fileName);
			for (int j = 0; j < size; j++) {
				pw.append((A[j] + "\n"));
			}
			pw.close();
	}

	void swap(int i, int j) {
		int temp = Integer.parseInt(A[i]);
		A[i] = A[j];
		A[j] = temp + "";
	}

	void swapWords(int i, int j) {
		String temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	int getArraySize(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		int size = 0;
		while ((line = br.readLine()) != null) {
			size++;
		}
		br.close();
		return size;
	}

}
