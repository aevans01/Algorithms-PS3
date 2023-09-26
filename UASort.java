import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class UASort {
		public static String [] A;
		static long startTime;
		static long endTime;
		static long elapsedTime;
		String[] files;
		int size = 0;
		
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
				sort.A = new String[100];
				sort.traverseDirectory(args[0]);
				switch(numOrAlph) {
				case "numeric":
					if(ascOrDesc.equals("ascending")) {
						int pivot = sort.selectPivot(A, 0, A.length-1);
						sort.quicksort(A, 0, pivot);
						sort.quicksort(A, pivot+1, A.length-1);
					}else {
						
					}
				case "alphabetic":
					if(ascOrDesc.equals("ascending")) {
						
					}else {
						
					}
				}
				sort.writeFiles(inputDirectory, outputDirectory);
			}
		}
		public int selectPivot(String[] A, int p, int r) {
			Random rand = new Random();
			return Math.floorDiv((rand.nextInt(r) + rand.nextInt(r) + rand.nextInt(r)),3);
		}
		
		public int partition(String [] A,int p, int r) {
			String x = A[r];
			int i = p-1;
			String temp = "";
			for(int j = p; j < r; j++) {
				if(A[j].compareTo(x) > 0) {
					i = i+1;
					temp = A[j];
					A[j] = A[i];
					A[i] = temp;
				}
				temp = A[r];
				A[i+1] = A[r];
				A[r] = temp;
			}
			return i+1;
		}
		public void quicksort(String A[],int p,int r) {
			if(p < r) {
				int q = partition(A,p,r);
				quicksort(A,p,q-1);
				quicksort(A,q+1,r);
			}
		}
		void traverseDirectory(String startPath) throws IOException {
			File location = new File(startPath);
			System.out.println(location);
			if (location.listFiles().length == 0) {
				System.out.println("No Files In The Specified Directory");
				System.exit(100);
			}else {
				files = new String[location.listFiles().length];
			}
			for (File file : location.listFiles()) {
				
				if (file.isFile() && file.getName().endsWith(".txt")) {
					readFileWords(file.getPath());
				}
			}
		}
		void readFileWords(String file) throws IOException {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			String[] temp = new String[100];
			while ((line = br.readLine()) != null) {
				temp[size] = (line);
				size++;
			}
			fillArray(temp);
			br.close();
		}
		
		void fillArray(String [] temp) {
			A = new String[size];
			for(int i = 0; i<size;i++) {
				A[i] = temp[i];
			}
		}
		void writeFiles(String file, String locationDir) throws IOException {
			PrintWriter pw;
			for(int i = 0; i<files.length; i++) {
				System.out.println("Files[i]: " + files[i]);
				System.out.println(locationDir);
				pw = new PrintWriter(locationDir+files[i]);		
				for (int j = 0; j < size; j++) {
					pw.append((A[j] + ""));
					pw.append("\n");
				}
				pw.close();
			}
		}
}
