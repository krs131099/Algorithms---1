import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {

   private final WeightedQuickUnionUF quf;
   private final WeightedQuickUnionUF quffull;

   private int topsite, bottomsite;
   private boolean[][] grid;
   private final int size; 
   private int count;

   public Percolation(int n) {
      if (n<= 0)
	 throw new IllegalArgumentException(" Blocks should be positive");
      
      size = n;
      grid = new boolean[n][n];
      count = 0;

      topsite = 0; //virtual top site
      bottomsite = n*n +1; // virtual bottom site

      quffull = new WeightedQuickUnionUF(n*n+1);
      quf = new WeightedQuickUnionUF(n*n+2);
   }

   public void open(int row, int col) {
      if ((row < 1 || row > size) || (col < 1 || col > size))
	 throw new IllegalArgumentException("Arguments are not valid");

      //open the site 
      if( !isOpen(row,col) ) {
	
	 grid[row-1][col-1] = true;
	 int index = getIndex(row,col);

	 if (row ==1) {
	    quf.union(index,topsite);
	    quffull.union(index,topsite);
	 }

	 if (row == size) quf.union(index,bottomsite);

	 // connect to the adjacent sites
	 connect_adjacent(index,row-1,col);
	 connect_adjacent(index,row+1,col);
	 connect_adjacent(index,row,col+1);
	 connect_adjacent(index,row,col-1);
	 count++;
      }
   }

   public boolean isOpen(int row,int col) {
      if ((row < 1 || row > size) || (col < 1 || col > size))  throw new IllegalArgumentException("Arguments are not valid");
      return grid[row-1][col-1];
   }

   public boolean isFull(int row, int col) {
      if ((row < 1 || row > size) || (col < 1 || col > size)) {throw new
	IllegalArgumentException("Arguments are not valid");}
      
      if (isOpen(row,col)) {
	 int index = getIndex(row,col);
	 return quffull.connected(index,topsite);
      } 
      else return false; 
   }

   public int numberOfOpenSites() {
      return count;
   }

   public boolean percolates() {
      return quf.connected(topsite,bottomsite);
   }

   private int getIndex(int row, int col) {
      return (row-1)*size + (col) ;
   }


   // new condtion is added to the connect adjacent to check both the input arguments are
   // valid.
   private void connect_adjacent(int index,int row, int col) {
      // test if the site is open.
      if ((row >=1 && row <=size) && (col >=1 && col <=size) ) {
      if (isOpen(row,col)) {
	 int place = getIndex(row,col);
	 quf.union(index,place);
	 quffull.union(index,place);
      }
      }
   }

   public static void main(String[] args) {
      // read from the command line
      int n = StdIn.readInt();
      Percolation per = new Percolation(n);

      while(!StdIn.isEmpty()) {
	 int row = StdIn.readInt();
	 int col = StdIn.readInt();

	 per.open(row,col);
	 //StdOut.println(row+" " + col);
	 if (per.percolates()) { 
	    StdOut.println("Percolated with " +
	       per.numberOfOpenSites() + " sites open");
	    break;
	 }
      }
      StdOut.println(per.numberOfOpenSites());
   }
}

