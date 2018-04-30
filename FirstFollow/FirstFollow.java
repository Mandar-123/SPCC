import java.util.*;
class Variable {
	String var;
	int no_first = 0;
	int no_follow = 0;
	String[] FIRST = new String[10];
	String[] FOLLOW= new String[10];
}
class FirstFollow {
	static int n;
	static int not,nov;
	static String[] terminal = new String[10];
	static Variable variable[] = new Variable[10];
	static List<String> list = Arrays.asList(terminal);
	static String production[] = new String[10];
	
	static Variable findVar(String s) {
		for(int i = 0;i < nov;i++)
		{
			if(variable[i].var.equals(s))
				return variable[i];
		}
		return null;
	}
	
	static void findFirst(String s) {
		String handles = "";
		int pos = 0;
		Variable curr = findVar(s);
		if(curr.no_first>0)
			return;
		for(int j = 0;j < n;j++) 
			if(production[j].startsWith(s)) {
				handles = production[j].split("->")[1];
			}
		String handle[] = handles.split("\\|");
		for(String t : handle) {
			if (t.equals("eps"))
				curr.FIRST[curr.no_first++] = t;
			else if(list.contains(t.substring(0,1))) {
				curr.FIRST[curr.no_first++] = t.substring(0,1);
			}
			else {
				List<String> list2 = Arrays.asList(curr.FIRST);
				Variable add = null;
				boolean f = true;				
				String next = t.substring(0,1);
				int p = 0;
				while(f) {
					f = false;
					if(list.contains(next))
						if(!list2.contains(next)) {
							curr.FIRST[curr.no_first++] = next;
							break;
						}
					findFirst(next);
					add = findVar(next);
					for(int i = 0;i < add.no_first;i++)
					{
						if(!list2.contains(add.FIRST[i]) && !add.FIRST[i].equals("eps"))
							curr.FIRST[curr.no_first++] = add.FIRST[i];
						if(add.FIRST[i].equals("eps"))
						{
							if(p == t.length()-1)
							{
								f = false;
								if(!list2.contains("eps")) {
									curr.FIRST[curr.no_first++] = "eps";
								}
							}
							else {
								f = true;
								p++;
								next = t.substring(p,p+1);
							}
						}
					}
				}
			}
		}
	}
	
	
	static void findFollow(String s) {
		
		Variable curr = findVar(s);
		if(curr.no_follow>0)
			return;
		if(production[0].substring(0,1).equals(s))
			curr.FOLLOW[curr.no_follow++] = "$";
		for(int i = 0;i < n;i++)
		{
			Variable left = null;
			String handles = production[i].split("->")[1];
			String left_var = production[i].split("->")[0];
			String handle[] = handles.split("\\|");
			left = findVar(left_var);
			for(String t : handle) {
				if(t.equals("eps"))
					continue;
				List<String> list2 = Arrays.asList(curr.FOLLOW);
				if(t.endsWith(s)) {
					findFollow(left_var);
					for(int j = 0;j < left.no_follow; j++) {
						if(!list2.contains(left.FOLLOW[j]))
							curr.FOLLOW[curr.no_follow++] = left.FOLLOW[j];
					}
				}
				else if(t.contains(s)){
					int index = t.indexOf(s) + 1;
					String temp = t.substring(index,index+1);
					System.out.print("* " + temp + " *\n");
					if(list.contains(temp)) {
						if(!list2.contains(temp))
							curr.FOLLOW[curr.no_follow++] = temp;
					}
					else {
						Variable temp2 = null;
						temp2 = findVar(temp);
						for(int j = 0;j < temp2.no_first; j++) {
							if(!list2.contains(temp2.FIRST[j]) && !temp2.FIRST[j].equals("eps"))
								curr.FOLLOW[curr.no_follow++] = temp2.FIRST[j];
							if(temp2.FIRST[j].equals("eps")) {
								findFollow(left_var);
								for(int k = 0;k < left.no_follow; k++) {
									if(!list2.contains(left.FOLLOW[k]))
										curr.FOLLOW[curr.no_follow++] = left.FOLLOW[k];
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public static void main(String ar[])
	{
		int i,j;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of Terminals: ");
		not = sc.nextInt();
		for(i=0;i<not;i++) {
			terminal[i] = sc.next();
		}
		System.out.print("Enter number of variables: ");
		nov = sc.nextInt();
		for(i=0;i<nov;i++) {
			String temp = sc.next();
			variable[i] = new Variable();
			variable[i].var = temp;
		}		
		System.out.print("Enter number of production: ");
		n = sc.nextInt();
		sc.nextLine();
		for(i = 0;i < n;i++) {
			System.out.print("Enter production " + (i+1) + ": ");
			production[i] = sc.nextLine();
		}
		for(i = 0;i< nov;i++) {
			findFirst(variable[i].var);
		}
		for(i = 0;i< nov;i++) {
			findFollow(variable[i].var);
		}
		for(i = 0;i< nov;i++) {
			Variable tem = variable[i];
			System.out.print("FIRST(" + tem.var + ") = ");
			for(j=0;j<tem.no_first;j++) {
				System.out.print(tem.FIRST[j] + " ");
			}
			System.out.print("\nFOLLOW(" + tem.var + ") = ");
			for(j=0;j<tem.no_follow;j++) {
				System.out.print(tem.FOLLOW[j] + " ");
			}
			System.out.println();
		}
	}
}