/*
 * @author : K R S Nadhan
 *
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

   private int n;
   private Node first;
   private Node last;

   private class Node {
      Item item;
      Node next;
      Node prev;
   }

   //constructor
   public Deque() {
      //first = null;
      n = 0;
   }

   public boolean isEmpty() {
      return n == 0;
   }

   public int size() {
      return n;
   }

   public void addFirst(Item item) {

      if (item == null)
	 throw new IllegalArgumentException("Argument must be positive");

      //add the item to the first
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      if(last == null) last = first;
      else first.next.prev = first;
      n++;
   }

   public void addLast(Item item) {

      if (item == null)
	 throw new IllegalArgumentException("Argument should not be null");

      // add the item to the last
      Node oldlast = last;
      last = new Node();
      last.item = item;
      last.prev = oldlast;
      if(first == null) first = last;
      else last.prev.next = last;
      n++;
   }

   public Item removeFirst() {

      if (isEmpty())
	 throw new NoSuchElementException("removing from empty list is prohibited");

      // rm first node
      Item item = first.item;
      n--;
      if(isEmpty()) {
	 first = null;
	 last = null;
      }
      else {
	 first = first.next;
	 first.prev = null;
      }
      return item;
   }

   public Item removeLast() {

      if(isEmpty())
	 throw new NoSuchElementException("Removing from empty is not allowed");

      // rm last node
      Item item = last.item;
      n--;
      if (isEmpty()) {
	 first=null;
	 last = null;
      }
      else {
	 last = last.prev;
	 last.next= null;
      }
      return item;
   }

   public Iterator<Item> iterator() {
      return new ListIterator();
   }

   private class ListIterator implements Iterator<Item> {

      Node current = first;

      public boolean hasNext() {
	 return current != null ;
      }

      public Item next() {
	 if (!hasNext())
	    throw new NoSuchElementException();

	 Item item = current.item;
	 current = current.next;
	 return item;
      }

      public void remove() {
	       throw new UnsupportedOperationException("not supported");
      }
   }

   public static void main(String[] args) {
     // sample client code
      Deque<String> dq = new Deque<String>();

      while (!StdIn.isEmpty()) {
	 String s =StdIn.readString();
	 dq.addLast(s);
      }

      dq.addFirst("First");
      dq.addLast("one");
      dq.removeLast();
      dq.removeLast();
      //dq.removeFirst();

      for(String s : dq)
	 StdOut.println( s + " ");
      StdOut.println(dq.size());
   }

}
