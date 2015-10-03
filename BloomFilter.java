import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
//Thilak Raj Balasubramanian-Bloom Filter Implementation  
public class BloomFilter {
	//main method for the bloom filter
	public static void main(String[] args) throws IOException{
	//Generate 1 million Random strings of Length 10 using random string utils package	
	PrintWriter out = new PrintWriter(new FileWriter("G:/webservices/workspace/assignment1/BloomFilter/src/TrainData/TrainData.txt"));	
	for(int i=1;i<=1000000;i++){
	String s=RandomStringUtils.random(10,new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','}'});
	//System.out.println(s);
	out.println(s);
	}
	out.close();
	//declaration of bloom filter array for different training data
	Integer[] bloomfilter_1_100 = new Integer[1000];
	Integer[] bloomfilter_2_100 = new Integer[1000];
	Integer[] bloomfilter_1_1000 = new Integer[1000];
	Integer[] bloomfilter_2_1000 = new Integer[1000];
	Integer[] bloomfilter_1_10000 = new Integer[1000];
	Integer[] bloomfilter_2_10000 = new Integer[1000];
	Integer[] bloomfilter_1_100000 = new Integer[1000];
	Integer[] bloomfilter_2_100000 = new Integer[1000];
	Integer[] bloomfilter_1_1000000 = new Integer[1000];
	Integer[] bloomfilter_2_1000000 = new Integer[1000];
	//initialize bloom array with 0
	for(int i=0;i<bloomfilter_1_100.length;i++){
	bloomfilter_1_100[i] = 0;
	bloomfilter_2_100[i] = 0;
	bloomfilter_1_1000[i] = 0;
	bloomfilter_2_1000[i] = 0;
	bloomfilter_1_10000[i] = 0;
	bloomfilter_2_10000[i] = 0;
	bloomfilter_1_100000[i] = 0;
	bloomfilter_2_100000[i] = 0;
	}
	//reading the random generated input from the stored text file and test data A.txt
	FileInputStream in = new FileInputStream("G:/webservices/workspace/assignment1/BloomFilter/src/TrainData/TrainData.txt");
	FileInputStream in1 = new FileInputStream("G:/webservices/workspace/assignment1/BloomFilter/src/TrainData/A.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
    List<String> A_list = new ArrayList<String>();
    String strLine;
	while (( strLine = br1.readLine()) != null) {
	A_list.add(strLine);	
    }
	String[] A_array = A_list.toArray(new String[0]);
	List<String> Train_list = new ArrayList<String>();
    String strLine1;
	while (( strLine1 = br.readLine()) != null) {
	Train_list.add(strLine1);	
    }
	String[] Train_array = Train_list.toArray(new String[0]);
	//System.out.println(A_array[2]);
    in1.close();
    in.close();
    //calling bloom filter generation
    bloomfilter_1_100=generateBloomFilter1(Train_array,100);
    bloomfilter_2_100=generateBloomFilter2(Train_array,100);
    bloomfilter_1_1000=generateBloomFilter1(Train_array,1000);
    bloomfilter_2_1000=generateBloomFilter2(Train_array,1000);
    bloomfilter_1_10000=generateBloomFilter1(Train_array,10000);
    bloomfilter_2_10000=generateBloomFilter2(Train_array,10000);
    bloomfilter_1_100000=generateBloomFilter1(Train_array,100000);
    bloomfilter_2_100000=generateBloomFilter2(Train_array,100000);
    bloomfilter_1_1000000=generateBloomFilter1(Train_array,100000);
    bloomfilter_2_1000000=generateBloomFilter2(Train_array,100000);
    int[][] hashtest_data_Hash1 = new int[1000000][8];
    int[][] hashtest_data1_Hash2 = new int[1000000][8];
    //test data hash value
    hashtest_data_Hash1=getHashTestDataFunction1(A_array);
    hashtest_data1_Hash2=getHashTestDataFunction2(A_array);
    //calculate the false positive values
    int count_100_1=calulateFalsePositive(bloomfilter_1_100,hashtest_data_Hash1);
    int count_100_2=calulateFalsePositive(bloomfilter_2_100,hashtest_data1_Hash2);
    int count_1000_1=calulateFalsePositive(bloomfilter_1_1000,hashtest_data_Hash1);
    int count_1000_2=calulateFalsePositive(bloomfilter_2_1000,hashtest_data1_Hash2);
    int count_10000_1=calulateFalsePositive(bloomfilter_2_10000,hashtest_data_Hash1);
    int count_10000_2=calulateFalsePositive(bloomfilter_2_10000,hashtest_data1_Hash2);
    int count_100000_1=calulateFalsePositive(bloomfilter_1_100000,hashtest_data_Hash1);
    int count_100000_2=calulateFalsePositive(bloomfilter_2_100000,hashtest_data1_Hash2);
    int count_1000000_1=calulateFalsePositive(bloomfilter_1_1000000,hashtest_data_Hash1);
    int count_1000000_2=calulateFalsePositive(bloomfilter_2_1000000,hashtest_data1_Hash2);
    System.out.println("FalsePositive for training 100 for 1 HashFunction:"+count_100_1);
    System.out.println("FalsePositive for training 100 for 2 HashFunction:"+count_100_2);
    System.out.println("FalsePositive for training 1000 for 1 HashFunction:"+count_1000_1);
    System.out.println("FalsePositive for training 1000 for 2 HashFunction:"+count_1000_2);
    System.out.println("FalsePositive for training 10000 for 1 HashFunction:"+count_10000_1);
    System.out.println("FalsePositive for training 10000 for 2 HashFunction:"+count_10000_2);
    System.out.println("FalsePositive for training 100000 for 1 HashFunction:"+count_100000_1);
    System.out.println("FalsePositive for training 100000 for 2 HashFunction:"+count_100000_2);
    System.out.println("FalsePositive for training 1000000 for 1 HashFunction:"+count_1000000_1);
    System.out.println("FalsePositive for training 1000000 for 2 HashFunction:"+count_1000000_2);
    
    
}


//calculate the false positive count with respect to the hast test data and trained bloom filter
	private static int calulateFalsePositive(Integer[] bloomfilter,
			int[][] hashtest_data_Hash1) {
		// TODO Auto-generated method stub
		int count=0;
		int false_positive=0;
	    for(int i =0;i<1000000;i++){
            for(int j=0;j<8;j++){   	
            int k=hashtest_data_Hash1[i][j];
                if(bloomfilter[k]==1){
                    count=count+1;
                }
            }
            if(count==8){
               false_positive=false_positive+1;
             }
            count=0;
            }
        return false_positive;
	}


//generate bloom filter with 8 different h values -->djb2  hash function 
	private static Integer[] generateBloomFilter1(String[] train_array, int i) {
		// TODO Auto-generated method stub
		//bloom filter of array 1000
		Integer[] bloomfilter_1 = new Integer[1000];
		for(int o=0;o<bloomfilter_1.length;o++){
			bloomfilter_1[o] = 0;
		}
		//choose random train data either 100,1000,10000,100000,1000000
		List<String> Train_data = new ArrayList<String>();
		for(int j=0;j<i;j++){
		int idx = new Random().nextInt(train_array.length);
		Train_data.add(train_array[idx]);
		}
		String[] Train_data_array = Train_data.toArray(new String[0]);
		//h different values
		int[] h={5634,2346,2177,1287,3890,9768,5679,6670};
		for(int k=0;k<i;k++){
			for (int l=0;l<h.length;l++){
				int hash1=h[l];
				for (int m=0;m<10;m++){
					//ascii value generation for each char at string
					int c=(int)Train_data_array[k].charAt(m);
					//djb2 implementation
					hash1=((hash1 << 5)+hash1)+c;
					//taking absolute value as sometimes hash produces negative values
					hash1=Math.abs(hash1);
				}
				hash1=((hash1%1000));
				//System.out.println(hash1);
				
				//assiging bloom filter bit from 0 to  1 
				if(bloomfilter_1[hash1]==0){
		            	bloomfilter_1[hash1]=1;
		            }
				
			}
			
		}
		return bloomfilter_1;
	}
	
	//generate bloom filter with 8 different h values -->sdbm  hash function 
	private static Integer[] generateBloomFilter2(String[] train_array, int i) {
		// TODO Auto-generated method stub
		//bloom filter of array 1000
		Integer[] bloomfilter_2 = new Integer[1000];
		for(int o=0;o<bloomfilter_2.length;o++){
			bloomfilter_2[o] = 0;
		}
		//choose random train data either 100,1000,10000,100000,1000000
		List<String> Train_data = new ArrayList<String>();
		for(int j=0;j<i;j++){
		int idx = new Random().nextInt(train_array.length);
		Train_data.add(train_array[idx]);
		}
		String[] Train_data_array = Train_data.toArray(new String[0]);
		for(int k=0;k<i;k++){
			//different constant values as k=8 we are changing the left shift values c1 and c2
			for (int l=0;l<8;l++){
				int hash2=0;
				int c1=0,c2=0;
				if(l==0){
				c1=5;
				c2=8;
				}
				if(l==1){
					c1=4;
					c2=10;
				}
				if(l==2){
					c1=5;
					c2=8;
					}
				if(l==3){
					c1=8;
					c2=10;
					}
				if(l==4){
					c1=5;
					c2=18;
					}
				if(l==5){
					c1=9;
					c2=10;
					}
				if(l==6){
					c1=7;
					c2=18;
					}
				if(l==7){
					c1=6;
					c2=16;
					}
				for (int m=0;m<10;m++){
					//ascii value generation for each char at string
					int c=(int)Train_data_array[k].charAt(m);
					//sdbm implemetation
					hash2=c+(hash2<<c1)+(hash2<<c2)-hash2;
					//taking absolute value as sometimes hash produces negative values
					hash2=Math.abs(hash2);
				}
				hash2=(hash2%1000);
				//assigining the bloom filter bit from 0 to 1
	            if(bloomfilter_2[hash2]==0){
	            	bloomfilter_2[hash2]=1;
	            }
			}
		}
		return bloomfilter_2;
	}
	//generation of hash data for test data function using djb1
	private static int[][] getHashTestDataFunction1(String[] a_array) {
		// TODO Auto-generated method stub
		//different 8 h values
		int[] h={5634,2346,2177,1287,3890,9768,5679,6670};
		
		   int[][] hashtest_data_Hash1 = new int[1000000][8];
		   for(int k=0;k<1000000;k++){
				for (int l=0;l<h.length;l++){
					int hash1=h[l];
					for (int m=0;m<10;m++){
						//ascii generation
						int c=(int)a_array[k].charAt(m);
						//djb implenetation
						hash1=((hash1 << 5)+hash1)+c;
						hash1=Math.abs(hash1);
					}
					hash1=(hash1%1000);
					hashtest_data_Hash1[k][l]=hash1;
				}
		   }
		    return hashtest_data_Hash1;
		   
	}
	//generation of hash data for test   datafunction using sdbm
	private static int[][] getHashTestDataFunction2(String[] a_array) {
		// TODO Auto-generated method stub
		int[][] hashtest_data_Hash2 = new int[1000000][8];
		   for(int k=0;k<1000000;k++){
			   for (int l=0;l<8;l++){
					int hash2=0;
					int c1=0,c2=0;
					if(l==0){
					c1=5;
					c2=8;
					}
					if(l==1){
						c1=4;
						c2=10;
					}
					if(l==2){
						c1=5;
						c2=8;
						}
					if(l==3){
						c1=8;
						c2=10;
						}
					if(l==4){
						c1=5;
						c2=18;
						}
					if(l==5){
						c1=9;
						c2=10;
						}
					if(l==6){
						c1=7;
						c2=18;
						}
					if(l==7){
						c1=6;
						c2=16;
						}
					for (int m=0;m<10;m++){
						//ASCII GENERATION
						int c=(int)a_array[k].charAt(m);
						//sdbm implementation
						hash2=c+(hash2<<c1)+(hash2<<c2)-hash2;
						hash2=Math.abs(hash2);
					}
						hash2=(hash2%1000);
						hashtest_data_Hash2[k][l]=hash2;
					}
			   }
			    return hashtest_data_Hash2;
	}
}
