public class SplitPointer extends Node{
	  boolean isSplit = true;
	  double key;
	  Node pointer;
	
	public SplitPointer(double k, Node n) {
		this.key = k;
		this.pointer = n;
	}
}
