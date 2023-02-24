public class MyThreads2 {
	private static int NUMTHREADS = 12;
	private static long MAXNUMBER = 1000000000L;//Long.MAX_VALUE; 
	private static long INTERVAL;
	
	public static void main(String args[]){	
		if (args.length > 0)
			NUMTHREADS = Integer.parseInt(args[0]);
		if (args.length > 1)
			MAXNUMBER = Long.parseLong(args[1]);		
		
		INTERVAL = MAXNUMBER / NUMTHREADS;
		
		System.out.println(Runtime.getRuntime().availableProcessors() + " processor available to JVM");
		System.out.printf("Using %d Threads%nMAXNUMBER: %d , INTERVAL: %d%n",NUMTHREADS,MAXNUMBER, INTERVAL);
		long startTime = System.currentTimeMillis();
		long endTime;
		counterThread ct;
		
		Thread[] th = new Thread[NUMTHREADS];
		
	    counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");

		for (int k=0; k < NUMTHREADS; k++){
			if (k != NUMTHREADS-1)
				ct= new counterThread(k*INTERVAL+1, (k+1)*INTERVAL,1);
			else
				ct= new counterThread(k*INTERVAL+1,MAXNUMBER,1); 
			th[k] = new Thread(ct);
			th[k].start();
		}


		counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");
		
		try{
			for (int k=0; k < NUMTHREADS; k++)
  			   th[k].join();

  			long endTimeT = System.currentTimeMillis();
  			System.out.printf("Suma via Threads: %d toma: %d miliscd\n", counterThread.getTotalGeneral(), endTimeT - startTime);
		}
			catch (InterruptedException e){
			  counterThread.threadMessage(e.getMessage());
		}
		
		startTime = System.currentTimeMillis();
		long total = 0;
		for (long k = 1 ; k <= MAXNUMBER ; k += 1)
				total += k;
		endTime = System.currentTimeMillis();
		System.out.printf("\nSuma Secuencial: %d toma: %d miliscd\n",total,
		 endTime - startTime);	
	System.out.printf("\nThreads - seq=: %d \n",counterThread.getTotalGeneral()-total);	
	}

 


	







}
