import javax.sql.rowset.spi.SyncFactory;

public class NeuralNetwork {
	
	private double synapticWeights[][];

	public NeuralNetwork(int numberOfFeatures)
	{
		for(int i=0; i< numberOfFeatures; i++)
			for(int j=0; j<=1; j++)
				synapticWeights = new double[i][j];		
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
		return z;
	}
	
	private double[][] transpose(double x[][])
	{
		double z[][] = null;
		for(int i=0; i<=x[0].length; i++)
			for(int j=0; j<=x.length; j++)
				z = new double[i][j];

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
		return z;
	}
	
	private double[][] sigmoid(double z[][])
	{
		for(int i=0; i<z.length; i++)
			for(int j=0; j<z[0].length; j++)
				z[i][j] = (1/(1 + Math.exp(-z[i][j])));
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
		
		for(int i=0; i< inputFeatures[0].length; i++)
			for(int j=0; j<1; j++)
				synapticWeights[i][j] = 1.0;
// 		synapticWeights[0][0] = -0.16595599;
// 		synapticWeights[1][0] = 0.44064899;
// 		synapticWeights[2][0] = -0.99977125;
		
		double output[][] = null;
		double error[][] = null;
		double adjustments[][] = null;
		
		for(int i=0; i<iterations; i++)
		{
			output = predict(inputFeatures);
			
			error = subtractMatrices(inputLabels, output);
	
			adjustments = dotProduct(transpose(inputFeatures), error);
			
			synapticWeights = addMatrices(synapticWeights, adjustments);
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
