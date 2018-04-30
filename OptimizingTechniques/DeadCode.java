class DeadCode {
	
	static int sum(int n) {
		int s = 0;
		for(int i=0;i<n;i++) {
			s = s + i;
		}
		return s;
	}
	
	static int prod(int n) {
		int p = 1;
		for(int i=0;i<n;i++) {
			p = p * i;
		}
		return p;
	}
	
	public static void main(String ar[]) {
		long startTime,endTime;
		startTime = System.nanoTime();
		for (int x = 0; x < 10000; x++) {
			int s = sum(x);
			int p = prod(x);
			if(true)
				System.out.println(s);
		}
		endTime = System.nanoTime();
		long t1 = endTime - startTime;
		startTime = System.nanoTime();
		for (int x = 0; x < 10000; x++) {
			int s = sum(x);
			if(true)
				System.out.println(s);
		}
		endTime = System.nanoTime();
		System.out.println("Un-Optimized: " + t1);
		System.out.println("Optimized: " + (endTime-startTime));
	}
}