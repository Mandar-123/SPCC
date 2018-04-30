import java.io.*;
import java.util.*;

public class prog
{
  public static boolean isNumeric(String str)
  {
	for (char c : str.toCharArray())
		if (!Character.isDigit(c)) return false;
    return true;
  }
  public static void main(String[] args)throws Exception
  {
	Scanner sc=new Scanner(System.in);
	File file1 = new File("operators.txt");
	File file2 = new File("keywords.txt");
	File file3 = new File("program.txt");
	BufferedReader br1 = new BufferedReader(new FileReader(file1));
	BufferedReader br2 = new BufferedReader(new FileReader(file2));
	BufferedReader br3 = new BufferedReader(new FileReader(file3));
	String st,codeline;
	String operators[]=new String[35];
	String keywords[] = new String[32];
	String deli="",oper="",cons="",vari="",keyw="",temp="",tempop="";
	int i=0,j=0,k=0;
	st = br1.readLine();
	StringTokenizer strtok = new StringTokenizer(st," ");
	while (strtok.hasMoreTokens())
		operators[i++]=strtok.nextToken();
	i=0;
	st = br2.readLine();
	strtok = new StringTokenizer(st," ");
	while (strtok.hasMoreTokens())
		keywords[i++]=strtok.nextToken();
	while ((st = br3.readLine()) != null)
	{
		k++;
		i=0;
		System.out.println("Line "+k+": ");
		int len=st.length();
		char str[]=st.toCharArray();
		while(i!=len)
		{
			if(Character.isDigit(str[i])==false && Character.isLetter(str[i])==false && str[i]!='_')
			{
				if(temp.length()!=0)
				{
					for(j=0;j<keywords.length;j++)
						if(temp.equals(keywords[j]))
						{
							keyw=keyw+" "+temp;
							temp="";
							break;
						}
					if(j==keywords.length)
					{
						if(isNumeric(temp))
						{
							cons=cons+" "+temp;
							temp="";
						}
						else
						{
							vari=vari+" "+temp;
							temp="";
						}
					}
				}

				if(str[i]==')' || str[i]==' ')
					i++;
				else if(str[i]==';' || str[i]=='"' || str[i]=='\'' || str[i]=='{' || str[i]==',' || str[i]=='}')
				{
					if(str[i]==';')
						deli=deli+"; ";
					if(str[i]==',')
						deli=deli+", ";
					else if(str[i]=='"')
					{
						deli=deli+"\"\" ";
						j=i+1;
						while(j<str.length && str[j]!='"')
							j++;
						i=j;
					}
					else if(str[i]=='\'')
						deli=deli+"'' ";
					else if(str[i]=='{')
						deli=deli+"{ ";
					else if(str[i]=='}')
						deli=deli+"} ";
					i++;
				}
				else if(Character.isDigit(str[i])==false && Character.isLetter(str[i])==false && str[i]!='_')
				{
					while(i<str.length && Character.isDigit(str[i])==false && Character.isLetter(str[i])==false && str[i]!='_' && str[i]!=';' && str[i]!=' ' && str[i]!=')')
					{
						if(str[i]=='(' && tempop.length()!=0)	
							break;
						tempop=tempop+str[i++];
						if(tempop.equals("("))
							break;
					}
					for(j=0;j<operators.length;j++)
						if(tempop.equals(operators[j]))
						{
							if(tempop.equals("("))
								oper=oper+" ()";
							else 
								oper=oper+" "+tempop;
							tempop="";
							break;
						}
				}
				}
				else
				{
					temp=temp+str[i];	
					i++;
				};
		}
		if(deli!="")
			System.out.println("	delimiters: "+deli);
		if(keyw!="")
			System.out.println("	keywords: "+keyw);
		if(vari!="")
			System.out.println("	identifiers/variables: "+vari);
		if(cons!="")
			System.out.println("	constants: "+cons);
		if(oper!="")
			System.out.println("	Operators: "+oper);
		deli=keyw=vari=cons=oper="";
	}
  }
}
