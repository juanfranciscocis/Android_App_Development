import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MyThreadsExecutor {
	private static final long MAXNUMBER = 1000000000;
	private static final long INTERVAL = MAXNUMBER / 4;


	
	public static void main(String args[]){		
		System.out.printf("%nUsing Executor%nMAXNUMBER: %d , INTERVAL: %d%n",MAXNUMBER, INTERVAL);
		long startTime = System.currentTimeMillis();
		long endTime;
		
		counterThread c1 = new counterThread(1,INTERVAL,1); 
		counterThread c2 = new counterThread(INTERVAL + 1,2*INTERVAL,1);
		counterThread c3 = new counterThread(2*INTERVAL+1,3*INTERVAL,1);
		counterThread c4 = new counterThread(3*INTERVAL+1,MAXNUMBER,1);
	    counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");
		 
		ExecutorService executorService = Executors.newFixedThreadPool(4);//newFixedThreadPool(4); 
		
		
		executorService.execute(c4);
		executorService.execute(c3);
		executorService.execute(c2);
		executorService.execute(c1);
		counterThread.threadMessage("--> Existen " + Thread.activeCount() + " Threads");
		

		
		
		executorService.shutdown();

           // wait  for both writers to finish executing
        while(!executorService.isTerminated()){
			System.out.println("ESTOY EN PROCESO");

		}

          
	   	long endTimeT = System.currentTimeMillis();
	   	System.out.printf("Suma via Executor: %d toma: %d miliscd\n\n", counterThread.getTotalGeneral(), endTimeT - startTime);		
   

		
		// Calcula de manera secuencial
		startTime = System.currentTimeMillis();
		long total = 0;
		for (int k = 1 ; k <= MAXNUMBER ; k += 1)
				total += k;
		endTime = System.currentTimeMillis();
		System.out.printf("\n\nSuma Secuencial: %d toma: %d miliscd\n",total,
		 endTime - startTime);

		
	}
}
