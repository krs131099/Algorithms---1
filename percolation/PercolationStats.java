/*
 * @author : K R S Nandhan
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

   private final double mean;
   private final double stddev;
   private final double confhi;
   private final double conflo;

   public PercolationStats(int n, int trails) {
      if (n<=0)
	 throw new IllegalArgumentException("size must greater than one");
      if (trails <=0) 
	 throw new IllegalArgumentException("experiments must be positive");

      double[] threshholds = new double[trails];
      for (int i = 0; i< trails; i++) {
	 Percolation per = new Percolation(n);
	 int run = 0;
	 while(!per.percolates()) {
	    int row = StdRandom.uniform(1,n+1);
	    int col = StdRandom.uniform(1,n+1);
	    per.open(row,col);
	 }
	 threshholds[i] = per.numberOfOpenSites()/(double)(n*n);
   }
   mean = StdStats.mean(threshholds);
   stddev = StdStats.stddev(threshholds);
   double confidencef = (1.96 * stddev())/ Math.sqrt(trails);
   conflo = mean - confidencef;
   confhi = mean + confidencef;
   }

   public double mean() {
      return mean;
   }

   public double stddev() {
      return stddev;
   }

   public double confidenceLo() {
      return conflo;
   }

   public double confidenceHi() {
      return confhi;
   }

   public static void main(String[] args) {

      int n = Integer.parseInt(args[0]);
      int trails = Integer.parseInt(args[1]);

      PercolationStats ps = new PercolationStats(n,trails);

      StdOut.println("mean                     = " +ps.mean());
      StdOut.println("stddev                   = " +ps.stddev());
      StdOut.println("95% confidence interval  = [" +ps.confidenceLo()+", " +
	    ps.confidenceHi() +"]");

      /*for (int i = 1; i <= trails;  i++) {
	 Percolation per = new Percolation(n);

	 while(!per.percolates()) {
	    int rand1 = StdRandom.uniform(1,n+1);
	    int rand2 = StdRandom.uniform(1,n+1);

	    // check for open site
	    per.Open(rand1,rand2);
	 }
	 ps.total += ((double) per.numberOfOpenSites())/n*n ;
	 ps.ratios[i] = 
      }
      StdOut.println("mean : "+ ps.mean()); 
      StdOut.println("Stddev : "+ps.Stddev());
      StdOut.println("confidence : ( "+ps.confidenceLo() +", "+ps.confidenceHi()+" ) ");
      */

   }
}


