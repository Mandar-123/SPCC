import java.util.*;

class lrlf {
	
	static int n;
	
	static String[] removelr(String s[]) {		
		String answer[] = new String[10];
		for(int i = 0;i < n;i++) {
			String curr = s[i];
			String[] spl = curr.split("->");
			String ter = spl[0];
			String handles = spl[1];
			String handle[] = handles.split("\\|");
			String old_prod = "",new_prod = "";
			for(String t : handle) {
				if(t.startsWith(ter)) {
					String alpha = t.substring(1,t.length());
					new_prod = ter + "'->" + alpha + ter + "'|eps";
					old_prod = ter + "->";
					for(String t1 : handle) {
						if(!t1.equals(t)) {
							old_prod = old_prod + t1 + ter + "'|";
						}
					}
					old_prod = old_prod.substring(0,old_prod.length()-1);
					for(int k = n-1;k>i;k--)
						s[k+1] = s[k];
					s[i] = old_prod;
					s[i+1] = new_prod;
					n++;
					break;
				}
			}
		}	
		return s;
	}
	
	
	static String[] removelf(String s[]) {
		String answer[] = new String[10];
		for(int i = 0;i < n;i++) {
			String curr = s[i];
			String[] spl = curr.split("->");
			String ter = spl[0];
			String handles = spl[1];
			String handle[] = handles.split("\\|");
			String old_prod = "",new_prod = "";
			int isChanged = 0;
			int isAll = 1;
			String sym = "";
			int pos = 0;
			for(int j=handle[0].length();j > 0;j--) {
				isAll = 1;
				sym = handle[0].substring(0,j);
				for(String t : handle) {
					if(!t.startsWith(sym)) {
						isAll = 0;
						break;
					}
				}
				if(isAll == 1){
					pos = j;
					break;
				}
			}
			if(isAll == 1 && handle.length>1) {
				old_prod = ter + "->" + sym + ter + "'";
				new_prod = ter + "'->";
				for(String t : handle) {
					String abc = t.substring(pos,t.length()); // Yeh change kia
					if(abc.equals(""))
						abc = "eps";
					new_prod = new_prod + abc + "|";
				}
				new_prod = new_prod.substring(0,new_prod.length()-1);
				for(int k = n-1;k>i;k--)
						s[k+1] = s[k];
				s[i] = old_prod;
				s[i+1] = new_prod;
				n++;
				i--;
			}
		}	
		return s;
	}
	
	
	public static void main(String ar[])
	{
		int i;
		Scanner sc = new Scanner(System.in);
		String production[] = new String[10];
		System.out.print("Enter number of production: ");
		n = sc.nextInt();
		sc.nextLine();
		for(i = 0;i < n;i++) {
			System.out.print("Enter production " + (i+1) + ": ");
			production[i] = sc.nextLine();
		}
		String s[] = removelr(production);
		System.out.println("After removing left recurrsion:");
		for(i = 0;i < n;i++) {
			System.out.print(s[i] + "\n");
		}
		String s2[] = removelf(s);
		System.out.println("After removing left factoring:");
		for(i = 0;i < n;i++) {
			System.out.print(s2[i] + "\n");
		}
	}
}