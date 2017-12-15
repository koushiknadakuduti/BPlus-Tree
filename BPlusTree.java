import java.util.ArrayList;

public class BPlusTree {
	
	public static Node root = null;
	
	public static int m = Main.m;
	
	protected void Insert(double key, String value){
		DataNode data = new DataNode(key, value);
		if(root == null){
			root = new LeafNode();
			((LeafNode)root).sortedInsert(new DataNode(key, value));
		}
		else{
			root = Helper(root, data);
		}
		
	}
	protected static Node Helper(Node root, DataNode data){
		
		if(!root.isLeaf){
			root.isSplit = false;
			ArrayList<Double> rootList = new ArrayList<Double>();
			rootList = ((IndexNode)root).keys;
			
			int i = 0;
			while(i < rootList.size()){
				
				if(rootList.get(i) > data.key || i == rootList.size()-1) 
				{
					int position = rootList.get(i) > data.key ? i : i + 1;  
					Node temp = new Node();
					temp = Helper(((IndexNode)root).children.get(position), data);
					if(temp.isSplit)
					{
					 ((IndexNode)root).sortedInsert(((IndexNode)temp).keys.get(0), ((IndexNode)temp).children.get(0),
								((IndexNode)temp).children.get(1));
						if(((IndexNode)root).keys.size() == m)
						{
							int pos = ((int) Math.ceil(((double)m)/2)) - 1;
							IndexNode idx = new IndexNode();
							IndexNode child = new IndexNode();
							idx.sortedInsert(((IndexNode)root).keys.get(pos), root, child);
							((IndexNode)root).keys.remove(pos);
							for(int k = pos, j = pos; k < m-1; k++)
							{
								child.keys.add(((IndexNode)root).keys.remove(j));
							}
							for(int k = pos+1, j = pos+1; k <= m; k++)
							{
								child.children.add(((IndexNode)root).children.remove(j));
							}
							idx.isSplit = true;
							return idx;
						}
						return root;
					}
					else
						return root;
					}
				i++;
			}
		}
		else{
			((LeafNode)root).sortedInsert(data);
			if(((LeafNode)root).list.size() == m){
				int pos = m/2;
				LeafNode leaf = new LeafNode();
				for(int i = pos, j = pos; i < m; i++){
					leaf.sortedInsert(((LeafNode)root).list.remove(j));
				}
				
				leaf.next = ((LeafNode)root).next;
				((LeafNode)root).next = leaf;
				leaf.prev = ((LeafNode)root);
				if(leaf.next != null)
					leaf.next.prev = leaf;
				IndexNode splitPointer = new IndexNode();
				splitPointer.keys.add(leaf.list.get(0).key);
				splitPointer.isSplit = true;
				splitPointer.children.add((LeafNode)root);
				splitPointer.children.add(leaf);
				return splitPointer;
			}
			else
				return root;
		}
		
		return root;
	}
	
	
	
	protected String Search(double key){
		LeafNode leaf = new LeafNode();
		leaf = findLeafWithKey(key,root);
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		while(leaf != null && flag){
			for(int i=0; i < leaf.list.size(); i++){
				if(leaf.list.get(i).key==key){
					sb.append(leaf.list.get(i).val+", ");
				}
				else if( leaf.list.get(i).key > key){
					flag = false;
					break;
				}
			}
			leaf = leaf.next;
		}
		return sb.toString().equals("")? "Null": sb.toString().substring(0,sb.length()-1);
	}
	
	protected static StringBuilder stringHelper(StringBuilder sb, LeafNode node, Double key1, Double key2){
		ArrayList<DataNode> leafList = new ArrayList<DataNode>();
		leafList = node.list;
		for(int i = 0; i < leafList.size(); i++)
		    {
			if(leafList.get(i).key >= key1 && leafList.get(i).key <= key2 )
			{
				sb.append("("+ leafList.get(i).key+","+ leafList.get(i).val+"), ");
			}
			if(leafList.get(i).key > key2)
			{
				return sb;
			}
		}
		
		return sb;
	}
	
	protected String Search(double key1, double key2){
		LeafNode leaf = new LeafNode();
		leaf = findLeafWithKey(key1, root);
		StringBuilder sb = new StringBuilder();
		sb = stringHelper(sb, leaf, key1, key2);
		
		while(leaf.next != null){
			if(leaf.next.list.get(0).key > key2){
				break;
			}
			else{
				sb = stringHelper(sb, leaf.next, key1, key2);
			}
			leaf = leaf.next;
		}
		
		return sb.toString().equals("")? "Null": sb.toString().substring(0, sb.length()-1);
		
	}
	
     protected static LeafNode findLeafWithKey(double key1,Node node) {
    	Node temp = new Node();
 		temp = node;
 		while(!temp.isLeaf)
 		{
 			int index = 0;
 			while(index < ((IndexNode)temp).keys.size() && ((IndexNode)temp).keys.get(index) < key1)
 			{
 				index++;
 			}
 			temp = ((IndexNode)temp).children.get(index);
 		}
 		
        return (LeafNode)temp;
     }
}
