import java.io.*;
import java.util.*;
class Mneumonics {
	static boolean contains(String mne[],String s) {
		for(String t : mne)
			if(t.equals(s))
				return true;
		return false;
	}
	public static void main(String ar[]) throws Exception{
		File file1 = new File("mot.txt");
		File file2 = new File("pot.txt");
		File file3 = new File("program.txt");
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		BufferedReader br3 = new BufferedReader(new FileReader(file3));
		String mne[]=new String[18];
		String found[] = new String[100];
		String line = br1.readLine().split(" ",2)[0];
		int nom = 0,n = 0;
		while(line!=null) {
			mne[nom++] = line;
			line = br1.readLine();
			if(line != null)
				line = line.split(" ",2)[0];
		}
		line = br2.readLine();
		while(line!=null) {
			mne[nom++] = line;
			line = br2.readLine();
		}
		line = br3.readLine();
		while(line!=null) {
			String temp[] = line.split(" ");
			if(contains(mne,temp[0]))
				found[n++] = temp[0];
			else found[n++] = temp[1];
			line = br3.readLine();
		}
		for(int i = 0;i < n;i++)
			System.out.println("Line " + (i+1) + ": " + found[i]);
	}
}