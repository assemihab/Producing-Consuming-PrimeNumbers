


public class Buffer {
	
	public Buffer()
	{

	}
	public void setter(int soize, Semaphore semph,Object Storey[])
	{
		this.size=soize;
		this.store= Storey;
		this.spaces=semph;
		
	}
	private int size; // the buffer bound
	private Object store[]; /*= new Object[size];*/
	Semaphore spaces; /*= new Semaphore(size);*/
	private int inptr = 0;
	private int outptr = 0;
	public Semaphore elements = new Semaphore(0);
	
	public void produce(Object value) {
	
		spaces.P();
		store[inptr] = value;
		inptr = (inptr + 1) % size;
		elements.V();
	}
	public Object consume() {
		Object value;
		elements.P();
		value = store[outptr];
		outptr = (outptr + 1) % size;
		spaces.V();
		return value;
	}
//	trace this code later//
	public boolean isFull()
	{
		if(elements.value==size)
			return true;
		else return false;
	}
	public boolean isEmpty()
	{
		if(elements.value<=0)
			return true;
		else return false;
	}
}
