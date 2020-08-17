/*
 * @ author : K R S Nandhan
 */


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

   private int n;
   private Item[] a;

   public RandomizedQueue() {
      n = 0;
      a = (Item[]) new Object[1];
   }

   public boolean isEmpty() {
      return n == 0;
   }

   public int size() {
      return n;
   }

   public void enqueue(Item item) {
      if(item == null) 
	 throw new IllegalArgumentException("Item must be positive");

      if(a.length == n) resize(2*a.length);
      a[n++] = item;
   }

   public Item dequeue() {
      if (n == 0)
	 throw new NoSuchElementException(" items are not present");

      // random number
      int rand = StdRandom.uniform(n);
      Item arand = a[rand];
      a[rand] = a[--n];
      a[n] = null; // prevent loitering.
      if( n > 0 && n == a.length/4 ) resize(a.length/2); //resize if no of elements are 1/4  of size.

      return arand;
   }

   public Iterator<Item> iterator() {
      return new RandomIterator(); 
   }

   private void resize(int length) {
      Item[] temp = (Item[]) new Object[length];
      
      for ( int i = 0; i < n; i++ ) 
	 temp[i] = a[i];
      a = temp;
   }

   public Item sample() {
      if ( n == 0 ) 
	 throw new NoSuchElementException("No elements in the array");
      return a[StdRandom.uniform(n)];
   }

   private class RandomIterator implements Iterator<Item> {
      // return random items

      private int i ;
      private int[] k;
      public RandomIterator() {
	 i = 0;
	 k = new int[n];
	 for ( int j = 0 ; j < n ; j++ ) 
	    k[j] = j;
	 StdRandom.shuffle(k);
      }
      
      //@override
      public boolean hasNext() { return i < n ; }

      //@override
      public Item next() {
	 if ( !hasNext())
	    throw new NoSuchElementException("No elements in array");
	 return a[k[i++]];

      }

      public void remove() {
	 throw new UnsupportedOperationException(" Not supported");
      }

   }

    public static void main(String[] args) {

	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
	 
	 for (int j = 1; j < 10; j++) 
	    rq.enqueue(j);

	 rq.dequeue();
	 rq.dequeue();
	 rq.dequeue();
	 StdOut.println("sample : " +rq.sample());

	 for (int j : rq)
	    StdOut.println(j);

      }
}



