

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Consumer extends Thread  {
	
	private int counter=0;
	private int stopCondition=0;
	private Buffer buf;
	private Semaphore mutix;
	private String filename;
	private int largest_number=0;
	private int number=0;
	GUI GUI1;
	private long startTime;
	private long endTime;
	private long elapsedTime;
	
	public Consumer(Semaphore sem,long start) {
			this.mutix=sem;
			this.startTime=start;

		}
	public void setBufAndFname(Buffer buuf,String fname)
	{
		this.buf=buuf;
		this.filename=fname;
	}
	public void GUIsetter(GUI guii)
	{
		this.GUI1=guii;
	}
	@Override
	public void run() 
	{
		try
		{
			
			File file=new File(filename);
			FileWriter fw=new FileWriter(file);
			PrintWriter pw=new PrintWriter(fw);
			while(number!=-10)
			{
				mutix.P();
				while (!(buf.isEmpty()))
				{
					number=Integer.parseInt(buf.consume().toString());
					System.out.println(number+" consumer "+buf.elements.value);
					if(number!=-10)
					{
						GUI1.setlargestprimenumber(number);
						pw.print(number+",");
						counter++;
						GUI1.setCounter(counter);
						endTime= System.currentTimeMillis();
						elapsedTime=endTime-startTime;
						GUI1.setTime(elapsedTime);
					}
					try
					{
						Thread.sleep(100);
					}
					catch (Exception e)
					{}
				}
				mutix.V();
			}
			pw.close();
		}
		catch(IOException e)
		{
			System.out.print("and error occurred. ");
		}
	}

}
