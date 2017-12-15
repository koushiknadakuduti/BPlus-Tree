import java.util.ArrayList;

public class IndexNode extends Node {
	
	protected ArrayList<Double> keys = new ArrayList<Double>();
	protected ArrayList<Node> children = new ArrayList<Node>();
	
	protected void sortedInsert(double key, Node child1, Node child2){
		if(this.keys.isEmpty()){
			this.keys.add(key);
			this.children.add(child1);
			this.children.add(child2);
		}
		else
		{
			for(int i=0;i < this.keys.size();i++){
				if(this.keys.get(i) > key) {
					this.keys.add(i, key);
					this.children.add(i + 1, child2);
					return;
				}
				
			}
			this.keys.add(key);
			this.children.add(child2);
		}
		return;
	}
}
