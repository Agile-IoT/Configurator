package at.tugraz.ist.configurator.CSH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

public class Clustering {
	
	
public static void applyKMeans(){
		 	try {
		        /* Load a dataset */
		        Dataset data;
				
				data = FileHandler.loadDataset(new File("kmeans2/seda/inputs/"+TestCSH.modelsName+".data"), TestCSH.numberOfvars, ",");
				
		        /*
		         * Create a new instance of the KMeans algorithm, with no options
		         * specified. By default this will generate 4 clusters.
		         */
		        Clusterer km = new KMeans();
		        /*
		         * Cluster the data, it will be returned as an array of data sets, with
		         * each dataset representing a cluster
		         */
		        Dataset[] clusters = km.cluster(data);
		        System.out.println("Cluster count: " + clusters.length);
		        for(int i=0;i<clusters.length;i++){
		        	
		        	boolean dir = new File("kmeans2/seda/outputs/"+TestCSH.modelsName).mkdir();
		        	File file = new File("kmeans2/seda/outputs/"+TestCSH.modelsName+"/Cluster"+i+".txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

		        	FileHandler.exportDataset(clusters[i],file);
		        }
		        
		        
		 	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }

	
 public static void getClusters(){
		 
		 // "kmeans2/seda/outputs/"+modelsName+"/Cluster"+i+".txt"
	 	TestCSH.clusters = new int [TestCSH.numberOfclusters][];
		 
		 for (int i=0;i<TestCSH.numberOfclusters;i++){
			 List<Integer> indexes = new ArrayList<Integer>();
			 
			 try {
				 BufferedReader br = new BufferedReader(new FileReader("kmeans2/seda/outputs/"+TestCSH.modelsName+"/Cluster"+i+".txt"));
			     StringBuilder sb = new StringBuilder();
			     
			     String line = br.readLine();
	
			     while (line != null) {
			         sb.append(line);
			         sb.append(System.lineSeparator());
			         int val = Integer.valueOf(line.split("\t")[0]);
			         indexes.add(val);
			         
			         // read next string
			         line = br.readLine();
			     }
			     TestCSH.clusters[i]= new int[indexes.size()];
			     for(int m=0;m<indexes.size();m++){
			    	 TestCSH.clusters[i][m]=indexes.get(m);
			     }
			     String everything = sb.toString();
			     br.close();
			 }
			 catch(Exception e){
				 int z =0;
			 }
		 }
	 }


}
