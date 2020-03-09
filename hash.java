package lab5;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class hash<K,V>
{
	ArrayList<LinkedList<Couple>> Hash_table;
	int NumberOfElements;
	int NumberOfLists;
	double c;
	public hash()
	{
		Hash_table = new ArrayList<>(10);
		for(int i = 0; i < 10;i++)
			Hash_table.add(new LinkedList<>());
		NumberOfElements = 0;
		NumberOfLists = 10;
		c = 2.0;	
	}
	
	public void DeleteAll()
	{
		for(int i = 0; i < NumberOfLists; i++)
			Hash_table.set(i,(new LinkedList<>()));
		NumberOfLists = 0;
	}
	
	public void Add(K Key,V Value)
	{
		if(NumberOfLists == 0)
		{
			NumberOfLists = 10;
		}
		Couple couple = new Couple(Key,Value);
		int HashCode = Key.hashCode()%NumberOfLists;
		if(Hash_table.get(HashCode).isEmpty())
			Hash_table.get(HashCode).add(couple);
		else
		{
			for(Couple couple1 : Hash_table.get(HashCode))
					if(couple1.Key.equals(Key))
					{
						couple1.Value = Value;
						return;
					}
			Hash_table.get(HashCode).add(couple);
		}
		NumberOfElements++;
		if((double)NumberOfElements/(double)NumberOfLists > c)
			ReHash(2*NumberOfLists + 1);
	}
	
	public void ReHash(int size)
	{
		if(((double)NumberOfElements / 0.75*size) <= c)
			ReHash((int)0.75*size);
		else
		{	
			ArrayList<LinkedList<Couple>> Hash_Table_new = new ArrayList<> (size);
			for(int i = 0; i < size; i ++)
				Hash_Table_new.add(new LinkedList<>());
			for (LinkedList<Couple> list : Hash_table)
				for (Couple couple : list)
					Hash_Table_new.get(Math.abs(couple.Key.hashCode()) % size).add(couple);

		    this.Hash_table = Hash_Table_new;
		    this.NumberOfLists = size;
		}
	}
	
	public V GetElementByKey(K Key) throws NoSuchElementException
	{
		int HashCode = Key.hashCode()%NumberOfLists;
		for(Couple couple : Hash_table.get(HashCode))
			if(couple.Key.equals(Key))
				return couple.Value;
		throw new NoSuchElementException();
	}
	
	public void DeleteByKey(K Key)
	{
		int HashCode = Key.hashCode()%NumberOfLists;
		for(Couple couple : Hash_table.get(HashCode))
			if(couple.Key.equals(Key))
			{
				Hash_table.get(HashCode).remove(couple);
				NumberOfElements--;
				return;
			}
	}
	
	public int GetNumberOfElements()
	{
		return NumberOfElements;
	}
	
	public double getC()
	{
		return c;
	}
	
	public void setC(double newC)
	{
		c = newC;
		if((double)NumberOfElements/(double)NumberOfLists > c)
			ReHash(NumberOfLists);
	}
	
	public double getCurrentC()
	{
		return (double)NumberOfElements/(double)NumberOfLists;
	}
	
	public void print()
	{
		for (int i = 0; i < Hash_table.size(); i++)
        {
            System.out.print(i + ": ");
            for (Couple couple : Hash_table.get(i))
                System.out.print("key = " + couple.Key + " value = " + couple.Value);
            System.out.println();
        }
	}
	
	
	
	public class Couple
	{
		K Key;
		V Value;
		
		public Couple(K Key, V Value)
		{
			
			this.Key = Key;
			this.Value = Value;
		}
		
//		void set(K Key,V Value)
//		{
//			this.Key = Key;
//			this.Value = Value;
//		}
//		
//		K getKey()
//		{
//			return Key;
//		}
//		
//		V getValue()
//		{
//			return Value;
//		}
	}
	
}
