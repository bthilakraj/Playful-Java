package neuralnetworks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class neuralnetworktesting {
	static int counter=0;
	static double vi1;
	static double vi2;
	static double yi1;
	static double yi2;
	static double[] y1= new double[800];
	 static double[][] wh1= new double[800][800];
	 static double[][] wh2= new double[800][800];
	 static double[][] wh3= new double[800][800];
	 static double[][] delta_wh3= new double[800][800];
	 static double[][] delta_wh2= new double[800][800];
	 static double[][] delta_wh1= new double[800][800];
	 static double[] vh1= new double[800];
	 static double[] vh2= new double[800];
	 static double[]vh3= new double[800];
	 static double[] yh1= new double[800];
	 static double[] yh2= new double[800];
	 static double[] yh3= new double[800];
	 static int classno=1;
	 static double c1d[] = {1,0,0,0};
	 static double c2d[] = {0,1,0,0};
	 static double c3d[] = {0,0,1,0};
	 static double c4d[] = {0,0,0,1};
	 static double e1=0,e2=0,e3=0,e0=0;
	 static double og[]=new double[800];
	 static double h2_gradient[]=new double[800];
	 static double h1_gradient[]=new double[800];
	 static double h2_gradient_sum[]=new double[800];
	 static double h1_gradient_sum[]=new double[800];
	 static double delta_wb3=0,wb3=0,delta_wb2=0,wb2=0,delta_wb1=0,wb1=0;
	 static double eta=0.4;
	private static int c1e=0;
	private static int c2e=0;
	private static int c3e=0;
	private static int c4e=0;
	

public static void inputlayer_testing(double x1,double x2,double x0,double wx1,double wx2,double wx0, int layer1nodes, int layer2nodes, int i){

	{
		vi1=x1*wx1+(x0*wx0);
		vi2=x2*wx2+(x0*wx0);
		y1[0]=(Math.tanh(vi1));
		y1[1]=(Math.tanh(vi2));
		input_hidden1_testing(y1[0],y1[1],1,layer1nodes,layer2nodes,i);			
	}
}
public static void input_hidden1_testing(double yi1,double yi2,double bias,int layer1nodes, int layer2nodes, int i2){
	
	int k=0;
	if(i2==0){
		wb1=Math.random();
for(int i=0;i<layer1nodes;i++){
for(int j=0;j<2;j++){
	wh1[i][j]=Math.random();
	System.out.println("random weight wh1"+wh1[i][j]);
}
}}
while(k<layer1nodes){
	int l=0;
	vh1[k]=(yi1*wh1[k][l])+(yi2*wh1[k][l+1]);
	vh1[k]=vh1[k]+(1*wb1);
	yh1[k]=(Math.tanh(vh1[k]));
	k++;
}	
hidden1_hidden2_testing(yh1,1,layer2nodes,layer1nodes,i2);
}	

public static void hidden1_hidden2_testing(double[] yh1,double bias,int layer2nodes,int layer1nodes, int i2){
	
	if(i2==0){
		 wb2=Math.random();
for(int i=0;i<layer2nodes;i++){
for(int j=0;j<layer1nodes;j++){
	wh2[i][j]=Math.random();
	System.out.println("random weight wh2"+wh2[i][j]);
}
}
}
for(int i=0;i<layer2nodes;i++){
	double s=0;
	for(int j=0;j<layer1nodes;j++){
		s=(yh1[i]*wh1[i][j])+s;
	}
	vh2[i]=s+(1*wb2);
	yh2[i]=(Math.tanh(vh2[i]));
}
hidden2_output_testing(yh2,1,layer2nodes,layer1nodes,i2);
}

public static void hidden2_output_testing(double[] yh2,double bias,int layer2nodes,int layer1nodes, int i2){
	if(i2==0){
		 wb3=Math.random();
for(int i=0;i<layer2nodes;i++){
for(int j=0;j<4;j++){
	wh3[i][j]=Math.random();
	//System.out.println("random weight wh3"+wh3[i][j]);
}
}
}
for(int i=0;i<layer2nodes;i++){
	double s=0;
	for(int j=0;j<4;j++){
		s=(yh2[i]*wh3[i][j])+s;
	}
	vh3[i]=s+(1*wb3);
	yh3[i]=(Math.tanh(vh3[i]));
}

if(classno==1){
	if(c1d[0]==yh3[0] && c1d[1]==yh3[1] && c1d[2]==yh3[2] && c1d[3]==yh3[3]){
	}
	else{

		c1e++;
		
	}
	}
if(classno==2){
	if(c2d[0]==yh3[0] && c2d[1]==yh3[1] && c2d[2]==yh3[2] && c2d[3]==yh3[3]){
	}
	else{
	
		c2e++;
	}
	}
if(classno==3){
	if(c3d[0]==yh3[0] && c3d[1]==yh3[1] && c3d[2]==yh3[2] && c3d[3]==yh3[3]){
	}
	else{
		
		c3e++;
	}
	}
if(classno==4){
	if(c4d[0]==yh3[0] && c4d[1]==yh3[1] && c4d[2]==yh3[2] && c4d[3]==yh3[3]){
	}
	else{
	
		c4e++;
	}
	}
}
public static void main(String[] args) throws IOException{
	
	double[] c1x1;
	double[] c1x2;
	double[] c2x1 ;
	double[] c2x2 ;
	double[] c3x1 ;
	double[] c3x2 ;
	double[] c4x1 ;
	double[] c4x2 ;
	ArrayList<Double> arr = new ArrayList<Double>();
	ArrayList<Double> arr2 = new ArrayList<Double>();
	ArrayList<Double> arr3 = new ArrayList<Double>();
	ArrayList<Double> arr4 = new ArrayList<Double>();
	ArrayList<Double> yarr = new ArrayList<Double>();
	ArrayList<Double> yarr2 = new ArrayList<Double>();
	ArrayList<Double> yarr3 = new ArrayList<Double>();
	ArrayList<Double> yarr4 = new ArrayList<Double>();
	BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Thilak\\Desktop\\fall2014-unm\\neural\\TestingData.txt"));
	String line;
	while((line=br1.readLine())!= null){
		String[] values=StringUtils.split(line.toString()," ");	
		if(Integer.parseInt(values[0])==1)
		{
			classno=1;
			arr.add(Double.parseDouble(values[2]));
			yarr.add(Double.parseDouble(values[3]));
			
		}
		if(Integer.parseInt(values[0])==2)
		{
			classno=2;
			arr2.add(Double.parseDouble(values[2]));
			yarr2.add(Double.parseDouble(values[3]));
		}
		if(Integer.parseInt(values[0])==3)
		{
			classno=3;	
			arr3.add(Double.parseDouble(values[2]));
			yarr3.add(Double.parseDouble(values[3]));
		}
		if(Integer.parseInt(values[0])==4)
		{
			classno=4;	
			arr4.add(Double.parseDouble(values[2]));
			yarr4.add(Double.parseDouble(values[3]));
		}
		
		//x1.(Double.parseDouble(values[4].toString());
	}
	br1.close();
	

c1x1=ArrayUtils.toPrimitive(arr.toArray(new Double[arr.size()]));	
c1x2=ArrayUtils.toPrimitive(yarr.toArray(new Double[yarr.size()]));
c2x1=ArrayUtils.toPrimitive(arr2.toArray(new Double[arr2.size()]));	
c2x2=ArrayUtils.toPrimitive(yarr2.toArray(new Double[yarr2.size()]));
c3x1=ArrayUtils.toPrimitive(arr2.toArray(new Double[arr3.size()]));	
c3x2=ArrayUtils.toPrimitive(yarr2.toArray(new Double[yarr3.size()]));
c4x1=ArrayUtils.toPrimitive(arr2.toArray(new Double[arr4.size()]));	
c4x2=ArrayUtils.toPrimitive(yarr2.toArray(new Double[yarr4.size()]));


double x0=1;
double wx1 = 0.5,wx2 = 0.5,wx0 = 0.3;

System.out.println("Enter the number of neuron nodes in hidden layer 1");
BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
String s = bufferRead.readLine();
int layer1nodes=Integer.parseInt(s);
System.out.println("Enter the number of neuron nodes in hidden layer 2");
BufferedReader bufferRead1 = new BufferedReader(new InputStreamReader(System.in));
String s1 = bufferRead1.readLine();
int layer2nodes=Integer.parseInt(s1);
System.out.println("learning rate");
BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
String s2 = bufferRead2.readLine();
eta=Double.parseDouble(s2);
System.out.println("TESTING DATA CLASS 1");
for(int i=0;i<200;i++){
	classno=1;
	inputlayer_testing(c1x1[i],c1x2[i],x0,wx1,wx2,wx0,layer1nodes,layer2nodes,i);
}
System.out.println("TESTING DATA CLASS 2");
for(int i=0;i<200;i++){
	classno=2;
	inputlayer_testing(c2x1[i],c2x2[i],x0,wx1,wx2,wx0,layer1nodes,layer2nodes,1);
}
System.out.println("TESTING DATA CLASS 3");
for(int i=0;i<200;i++){
	classno=3;
	inputlayer_testing(c3x1[i],c3x2[i],x0,wx1,wx2,wx0,layer1nodes,layer2nodes,1);
System.out.println("TESTING DATA CLASS 4");}
for(int i=0;i<200;i++){
	classno=4;
	inputlayer_testing(c4x1[i],c4x2[i],x0,wx1,wx2,wx0,layer1nodes,layer2nodes,1);


}

System.out.println("error couunt class1:"+c1e);
System.out.println("error couunt class3:"+c3e);
System.out.println("error couunt class2:"+c2e);
System.out.println("error couunt class4:"+c4e);
}
}
