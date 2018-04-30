class CodeMotion{
	public static void main(String ar[]){
		int a[] = new int[100000000];
		long tStart,tEnd;
		int x,y=99999999,z=99999999;
		
		tStart = System.nanoTime();
		x = y + z; //code motion
		int t1 = x * x * x * x * x * x; //code motion
		for (int i = 0; i < 99999999; i++) {	
				a[i] = 5421*i + t1;
		}
		tEnd = System.nanoTime();
		
		System.out.println("optimized: " + (tEnd-tStart) + " ns");
		
		tStart = System.nanoTime();
		for (int i = 0; i < 99999999; i++) {
			a[i] = 5421*i + x * x * x * x * x * x;
		}
		tEnd = System.nanoTime();
		
		System.out.println("unoptimized: "+ (tEnd-tStart) + " ns");
	}
}