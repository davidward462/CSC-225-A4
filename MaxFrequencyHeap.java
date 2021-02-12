// Name: David Ward
// Student number: V00920409

public class MaxFrequencyHeap implements PriorityQueue {
	
	private static final int DEFAULT_CAPACITY = 10;
	private Entry[] data;
	private int size;
	
	public MaxFrequencyHeap() {
		data = new Entry[DEFAULT_CAPACITY];
		size = 0;
	}
	
	public MaxFrequencyHeap(int size) {
		data = new Entry[size];
		size = 0;
	}
	//for testing
	public void print(String s)
	{
		System.out.println(s);
	}
	//for testing
	public void printInt(int i)
	{
		System.out.println(i);
	}
	/*
	//FOR TESTING
	public void printHeap()
	{
		if(size>0)
		{
			for(int i = 0; i< size; i++)
			{
				System.out.print(data[i]);
			}
			print("");
			return;
		}
		else
		{
			//print("Empty");
			return;
		}
	}*/
	
	//return index of lookingFor
	//returns -1 if it's not found
	public int getIndex(Entry lookingFor)//linear, change later
	{
		//go through list 
		for(int i = 0; i<size; i++)
		{
			if(lookingFor.equals(data[i]))//check entry
			{
				return i;
			}
		}
		return -1;
	}
	
	
	public void insert(Entry element) {
		// TODO: Complete this method
		//NOTE: So far it looks like insert works correctly.
		//It's probably removeMax that isn't working right.
		
		//full
		if(size==data.length && size!=0)
		{
			//print("FULL");
			return;
		}
		
		//if element is already in the heap
		int check = getIndex(element);
		if(check!=-1)
		{
			//update frequency
			data[check].addToFrequency();
			return;
		}
		
		data[size] = element;//add element to end of array
		int currIndex = size;
		size++;
		//Compare the added element with its parent; if they are in the correct order, stop.
		//if frequency < data.[floor((i-1)/2)], return (comparing frequency)
		while(element.getFrequency() > data[(int)(java.lang.Math.floor((currIndex-1)/2))].getFrequency())
		{
			//SWAP
			//print("SWAP");
			int parentIndex = (int)(java.lang.Math.floor((currIndex-1)/2));
			data[currIndex] = data[parentIndex];
			data[parentIndex] = element;
			currIndex = parentIndex;
		}
		//If not, swap the element with its parent and return to the previous step.
		return;
	}
	
	public Entry removeMax() { 
		// TODO: Complete this method
		Entry max = data[0];//hold max
		Entry min;
		if(size>0)
		{
			min = data[size-1];//hold min (last element)
		}
		else
		{
			min = data[0];
		}
		data[0] = min; //replace root with min
		size--;//delete last position
		boolean outOfOrder = true;
		int curIndex = 0;
		int L_child_freq;
		int R_child_freq;
		while(outOfOrder)//while things need to be swapped
		{
			//System.out.println("out of order");
			/*
			
			left: 2i+1
			right: 2i+2
			
			if left child greater do
				swap current and left
			else if right child greater do
				swap current and right
			else
				outOfOrder is false */
			//System.out.println("try");
			try//out of bounds
			{
				L_child_freq = data[(2*curIndex)+1].getFrequency();
				R_child_freq = data[(2*curIndex)+2].getFrequency();
			}
			catch(java.lang.NullPointerException e)
			{
				return max;
			}
			catch(java.lang.ArrayIndexOutOfBoundsException f)
			{
				return max;
			}
				
			if(L_child_freq > min.getFrequency())
			{
				//System.out.println("left");
				Entry temp = data[curIndex];
				data[curIndex] = data[(2*curIndex)+1];
				data[(2*curIndex)+1] = temp;
				curIndex = (2*curIndex)+1;	
				
			}//data[(int)(java.lang.Math.floor((currIndex-1)/2))]
			else if(R_child_freq > min.getFrequency() )
			{
				//System.out.println("right");
				Entry temp = data[curIndex];
				data[curIndex] = data[(2*curIndex)+2];
				data[(2*curIndex)+2] = temp;
				curIndex = (2*curIndex)+2;
			}
			else
			{
				outOfOrder = false;
			}
		}
		return max;
	}
	
	public boolean isEmpty() {
		// TODO: Complete this method
		if(size==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int size() {
		// TODO: Complete this method
		return size; // so it compiles
	}

}
 
