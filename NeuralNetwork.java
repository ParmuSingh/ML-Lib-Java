import javax.sql.rowset.spi.SyncFactory;

public class NeuralNetwork {
	
//	private int noOfExamples, noOfFeatures;
//	private int featureSpace[][];
//	private int labels[];
	private double synapticWeights[][];

	public NeuralNetwork(int numberOfFeatures)
	{
		for(int i=0; i< numberOfFeatures; i++)
		{
			for(int j=0; j<=1; j++)
				synapticWeights = new double[i][j];
			//System.out.println(i + ": " + synapticWeights[i][0]);
//			synapticWeights[i][0] = 1.0;
		}
		
		
	}
	
	
	
	private double[][] addMatrices(double x[][], double y[][])
	{
		double z[][] = null;
		for(int i=0; i<=x.length; i++)
			for(int j=0; j<=x[0].length; j++)
				z = new double[i][j];
		
		for(int i=0; i<z.length; i++)
			for(int j=0; j<z[0].length; j++)
				z[i][j] = x[i][j] + y[i][j];
		return z;
	}
	
	private double[][] subtractMatrices(double x[][], double y[][])
	{
		double z[][] = null;
		for(int i=0; i<=x.length; i++)
			for(int j=0; j<=x[0].length; j++)
				z = new double[i][j];
		
		for(int i=0; i<z.length; i++)
			for(int j=0; j<z[0].length; j++)
				z[i][j] = x[i][j] - y[i][j];
		System.out.println("subtracted inputLabels - output");
		print(z);
		return z;
	}
	
	private double[][] transpose(double x[][])
	{
		double z[][] = null;
		for(int i=0; i<=x[0].length; i++)
			for(int j=0; j<=x.length; j++)
				z = new double[i][j];
//		print(z);
//		System.out.println(x.length);
//		System.out.println(x[0].length);
		for(int i=0; i<x.length; i++)
			for(int j=0; j<x[0].length; j++)
				z[j][i] = x[i][j];
		return z;
	}
	
	private double[][] dotProduct(double x[][], double y[][])
	{
		double z[][] = null;
		for(int i=0; i<=x.length; i++)
			for(int j=0; j<=y[0].length; j++)
				z = new double[i][j];
		
//		print(x);
//		print(y);
//		System.out.println(x[0].length);
//		System.out.println(y.length);
//		System.out.println(synapticWeights.length);
//		System.out.println(synapticWeights[0].length);
//		System.out.println(z.length);
//		System.out.println(x.length);
		
		double sum = 0.0;
		for (int i = 0 ; i < x.length ; i++ )
        {
           for ( int j=0; j < y[0].length; j++)
           {   
              for (int k=0; k < x[0].length; k++ )
                 sum += x[i][k]*y[k][j];
              z[i][j] = sum;
              sum = 0;
           }
        }
//		print(z);
		
		return z;
	}
	
	private double[][] sigmoid(double z[][])
	{
		for(int i=0; i<z.length; i++)
			for(int j=0; j<z[0].length; j++)
				z[i][j] = (1/(1 + Math.exp(-z[i][j])));
//		System.out.println(z);
//		print(z);
		return z;
	}
	
	void print(double x[][])
	{
		for(int i=0; i<x.length; i++)
		{
			for(int j=0; j<x[0].length; j++)
				System.out.print(x[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void train(double inputFeatures[][], double inputLabels[][], int iterations)
	{
//		print(inputFeatures);
//		print(inputLabels);
//		System.out.print(inputFeatures[0].length);
		for(int i=0; i< inputFeatures[0].length; i++)
		{
//			synapticWeights = new double[i][0];
			for(int j=0; j<1; j++)
				synapticWeights[i][j] = 1.0;
		}
		synapticWeights[0][0] = -0.16595599;
		synapticWeights[1][0] = 0.44064899;
		synapticWeights[2][0] = -0.99977125;
		
		double output[][] = null;
		double error[][] = null;
		double adjustments[][] = null;
		System.out.println("Initial Synaptic weights:");
		print(synapticWeights);
		
		for(int i=0; i<iterations; i++)
		{
			output = predict(inputFeatures);
			System.out.println("output:");
			print(output);
			error = subtractMatrices(inputLabels, output);
			System.out.println("Error @ i = " + i + ": ");
			print(error);
			
			adjustments = dotProduct(transpose(inputFeatures), error);
			System.out.println("Adjusts:");
			print(adjustments);
			synapticWeights = addMatrices(synapticWeights, adjustments);
			print(synapticWeights);
			
		}
		
	}
	
	public double[][] predict(double inputs[][])
	{		
		return (sigmoid(dotProduct(inputs, synapticWeights)));
	}

	public void printWeights()
	{
		System.out.println("New synaptic weights:");
		print(synapticWeights);
	}
	
}
