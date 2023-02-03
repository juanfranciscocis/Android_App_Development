public class MyThreads {
	private static final long MAXNUMBER = 1000000000;
	private static final long INTERVAL = MAXNUMBER / 4;
	
	public static void main(String args[]){		
		System.out.printf("%nUsing Threads%nMAXNUMBER: %d , INTERVAL: %d%n",MAXNUMBER, INTERVAL);
		long startTime = System.currentTimeMillis();
		long endTime;
		
		counterThread c1 = new counterThread(1,INTERVAL,1); 
		counterThread c2 = new counterThread(INTERVAL + 1,2*INTERVAL,1);
		counterThread c3 = new counterThread(2*INTERVAL+1,3*INTERVAL,1);
		counterThread c4 = new counterThread(3*INTERVAL+1,MAXNUMBER,1);

	    counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");

		Thread T1 = new Thread(c1);
		Thread T2 = new Thread(c2);
		Thread T3 = new Thread(c3);
		Thread T4 = new Thread(c4);
		T1.start();  
		T2.start();  
		T3.start();  
		T4.start();  
		
		counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");
		
		try{
  			T1.join();
  			T2.join();
  			T3.join();
  			T4.join();
  			long endTimeT = System.currentTimeMillis();
  			System.out.printf("Suma via Threads: %d toma: %d miliscd\n\n", counterThread.getTotalGeneral(), endTimeT - startTime);
		}
			catch (InterruptedException e){
			  counterThread.threadMessage(e.getMessage());
		}
		
		startTime = System.currentTimeMillis();
		long total = 0;
		for (int k = 1 ; k <= MAXNUMBER ; k += 1)
				total += k;
		endTime = System.currentTimeMillis();
		System.out.printf("\n\nSuma Secuencial: %d toma: %d miliscd\n",total,
		 endTime - startTime);

		
	}

}
