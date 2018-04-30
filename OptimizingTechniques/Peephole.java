class Peephole {
	
	static int func_unopt(int x) {
		int z,y=10;
		y=10;
		z=x+y;
		return z;
	}
	
	static int func_opt(int x) {
		return x + 10;
	}
	
	public static void main(String ar[]) {
		long startTime,endTime;
		startTime = System.nanoTime();
		func_unopt(10000);
		endTime = System.nanoTime();
		long t1 = endTime - startTime;
		startTime = System.nanoTime();
		func_opt(10000);
		endTime = System.nanoTime();
		System.out.println("Un-Optimized: " + t1);
		System.out.println("Optimized: " + (endTime-startTime));
	}
}