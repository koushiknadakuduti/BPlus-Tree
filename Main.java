import java.io.*;

public class Main {
public static int m;
	
	public static void main(String[] args) throws Exception {
		
		String line =null;
		String inputFileName = args[0];                   //InputFile
		File file = new File(inputFileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		m = Integer.valueOf(br.readLine().trim());
		
		BPlusTree tree = new BPlusTree();                  //Initialize
		File output = new File("output_file.txt");
		if(!output.exists()){                         
			output.createNewFile();
		}
		FileWriter fw = new FileWriter(output.getAbsoluteFile());
		PrintWriter out = new PrintWriter(fw);
		
		while((line = br.readLine()) != null){         //Input & Output Formatting
			if(line.contains("Insert")){
				double key = Double.valueOf(line.split("Insert\\(")[1].split(",")[0]);
				String value =  line.split(",")[1].split("\\)")[0];
				tree.Insert(key,value);                               //Insert
			}
			else{
				if(line.contains(",")){        // Range Search
					double key1 = Double.valueOf(line.split("Search\\(")[1].split(",")[0].trim());
					double key2 = Double.valueOf(line.split("Search\\(")[1].split(",")[1].split("\\)")[0].trim()); 
					String result = tree.Search(key1,key2);        
							
					if( result.charAt(result.length() - 1) == ','){
						result = result.substring(0, result.length()-1);
					}
					out.println(result);
				}
				else{   //Normal Search
					double key = Double.valueOf(line.split("Search\\(")[1].split("\\)")[0].trim());
					String result = tree.Search(key);
					if( result.charAt(result.length() - 1) == ','){
						result = result.substring(0, result.length()-1);
					}
					out.println(result);
				}
			}
		}		
		fw.close();
		br.close();
		
	}

}
