/*
 *  @author : K R S Nandhan
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;


public class Permutation {

   public static void main(String[] args) {
      
      RandomizedQueue<String> rq = new RandomizedQueue<String>() ;

      int max = Integer.parseInt(args[0]);
      int index = 0; // index for reservoir.

      if (max > 0) {

      while(!StdIn.isEmpty()) {

	 String s = StdIn.readString();

	 // store first k values.
	 if (index < max) {
	    rq.enqueue(s);
	    index++;
	 }
	 else {
	    int rand = StdRandom.uniform(index);
	    
	    // reservoir sampling - to gain better memory efficiency by storing minimum
	    // values.
	    if (rand < max) {
	       rq.dequeue();
	       rq.enqueue(s);
	       index++;
	    }
	 }
      } 
      
      for (String s : rq) {
	 StdOut.println(s);
      }
      }

   }
}
