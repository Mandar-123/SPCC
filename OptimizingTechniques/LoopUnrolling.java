class LoopUnrolling {
	static void sum(int n) {
		int s = 0;
		for(int i=0;i<n;i++) {
			s = s + i;
		}
	}
	
	public static void main(String ar[]) throws Exception{
		long startTime,endTime;
		startTime = System.nanoTime();
		for (int x = 0; x < 100000; x++) {
			sum(x);
		}
		endTime = System.nanoTime();
		System.out.println("Un-Optimized: " + endTime-startTime);
		startTime = System.nanoTime();
		for (int x = 0; x < 100000; x = x + 5) {
			sum(x);
			sum(x+1);
			sum(x+2);
			sum(x+3);
			sum(x+4);
		}
		endTime = System.nanoTime();
		System.out.println("Optimized: " + endTime-startTime);
	}
}