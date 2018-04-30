import java.util.*;
class operator {
	static int precedence(String s1,String s2) {
		if(s1.equals("i") && s2.equals("i"))
			return -1;
		else if(s1.equals("i"))
			return 1;
		else if (s2.equals("i"))
			return 0;
		else if (s1.equals("$"))
			return 0;
		else if(s2.equals("$"))
			return 1;
		else if (s1.equals(s2))
			return 1;
		else if(s1.equals("*") && s2.equals("+"))
			return 1;
		else if(s1.equals("+") && s2.equals("*"))
			return 0;
		else return -1;
	}
	public static void main(String ar[]) {
		int tos = 0;
		String input = "",stack = "$",next = "",stack_top = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Operator precedence parser for E->E+E|E*E|i");
		System.out.print("Enter input to be parsed: ");
		input = sc.next();
		input = input + "$";
		next = input.substring(0,1);
		input = input.substring(1);
		boolean isValid = false;
		System.out.println(String.format("%10s %10s",stack,next+input));
		while(true) {
			if(stack.equals("$E") && input.equals("")) {
				isValid = true;
				break;
			}
			stack_top = stack.substring(tos,tos+1);
			if(stack_top.equals("E"))
				stack_top = stack.substring(tos-1,tos);
			if(precedence(stack_top,next) == 0) {
				stack = stack + next;
				next = input.substring(0,1);
				input = input.substring(1);	
				tos++;
			}
			else if(precedence(stack_top,next) == 1)
			{
				if(stack.endsWith("i")) {
					stack = stack.substring(0,stack.length()-1) + "E";
				}
				else if(stack.endsWith("E+E") || stack.endsWith("E*E")) {
					stack = stack.substring(0,stack.length()-3) + "E";
					tos = tos - 2;
				}
				else break;
			}
			else break;
			System.out.println(String.format("%10s %10s",stack,next+input));
			
		}
		if(isValid == true)
			System.out.print("\nInput can be parsed!");
		else System.out.print("\nInput cannot be parsed!");
	}
}