class FunctionInlining {
	static int pred(int x) {
		if (x == 0)
		   return 0;
		else
		   return x - 1;
	}
	
	static int f_unoptimized(int y) {
		return pred(y) + pred(0) + pred(y+1);
	}
	
	static int f_optimized(int y) {
		int temp;
		if (y == 0) 
			temp = 0; 
		else 
			temp = y - 1;
		if (0 == 0)
			temp += 0;
		else 
			temp += 0 - 1;
		if (y+1 == 0) 
			temp += 0; 
		else 
			temp += (y + 1) - 1;
		return temp;
	}
	public static void main(String ar[]) throws Exception{
		long startTime,endTime;
		startTime = System.nanoTime();
		for(int i=0;i<1000;i++)
			f_unoptimized(i);
		endTime = System.nanoTime();
		System.out.println("Un-Optimized: " + (endTime-startTime));
		startTime = System.nanoTime();
		for(int i=0;i<1000;i++)
			f_optimized(i);
		endTime = System.nanoTime();
		System.out.println("Optimized: " + (endTime-startTime));
	}
}