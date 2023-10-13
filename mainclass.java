public class mainclass {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int number =1000;
		Semaphore mut=new Semaphore(1);
		Buffer buf=new Buffer();
		Producer prod=new Producer(mut);
		Consumer cons=new Consumer(mut,start);
		
		GUI GUI1=new GUI(prod,cons,buf);
		GUI1.createFrame(prod,cons,buf);
		cons.GUIsetter(GUI1);
		


	}

}
