public  class counterThread implements Runnable {
		//Properties
	    private static long totalGeneral = 0; // debemos acumular un solo total ! 
		private long inicio, terminal ,paso;
		private long subTotal = 0;
		
		//constructor
		public counterThread(long init, long end, int step){
			inicio  = init;
			terminal = end;
			paso = step;
			subTotal = 0;
		}

		//functional method of the class Runnable
		public void run (){ // this is the method that will be executed by the thread
			threadMessage("--> Inicia: "+inicio+"->"+terminal);
			for (long k = inicio ; k <= terminal ; k += paso)
				subTotal += k;	
			acumulate();
			threadMessage("--> Termina , subtotal: " + subTotal );
		}
		

		private  synchronized  void acumulate(){    
			  totalGeneral += subTotal;
		}
		
		//Getters and Setters
		public long getSubTotal(){
			return subTotal;
		}
		public static long getTotalGeneral(){
			return totalGeneral;
		}
		
		//Prints Information 
        public static void threadMessage(String message) {
           String threadName = Thread.currentThread().getName(); //EL sistema asigna nombres a los threads
           System.out.format("%s: %s%n",threadName,message);
       } 
	}