import java.util.ArrayList;

public class LeafNode extends Node {
	
	protected ArrayList<DataNode> list = new ArrayList<DataNode>();
	protected LeafNode next = null;
	protected LeafNode prev = null;
	
	protected LeafNode() {
		this.isLeaf = true;
	}
	
	protected void sortedInsert(DataNode data){
		if(list.isEmpty()){
			list.add(data);
		}
		else{
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).key > data.key){
					list.add(i, data);
					return;
				}
			}
			list.add(data);
			
		
		}
	}
}
