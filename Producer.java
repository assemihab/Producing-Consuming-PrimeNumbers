

class Producer extends Thread {
	Buffer buf;
	private int number;
	private Semaphore mutix;
	
	public Producer(Semaphore semp) {
	this.mutix=semp;
	}
	public void setBufferAndNumber(Buffer buuf,int nuum)
	{
		this.buf=buuf;
		this.number=nuum;
	}
   static boolean isPrime(int n){

        if(n==1||n==0)return false;
  

        for(int i=2; i<n; i++){

              if(n%i==0)return false;
        }
        return true;
   }
   
	public void run() {
		int i=1;
		while(i<=number)
		{
				mutix.P();
				while(i<=number&&!(buf.isFull()))
				{
					if(isPrime(i))
					{
						System.out.println(i+" producer "+buf.elements.value);
						buf.produce(i);
					}
					i++;
				}
				mutix.V();
		}

		buf.produce(-10);
		
	}
}
