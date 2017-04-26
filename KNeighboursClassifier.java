public class KNeighboursClassifier {
	
	private int noOfTuples, noOfFeatures;
	//private int featureSpace[][] = new int[noOfTuples][noOfFeatures], labels[]= new int[noOfTuples];
	private int featureSpace[][];
	private int labels[];
	public KNeighboursClassifier(int no_of_tuples, int no_of_features)
	{
		noOfTuples = no_of_tuples;
		noOfFeatures = no_of_features;


		for(int i=0; i<= noOfTuples; i++)//LOOK AT '<='. I HAVE NO IDEA WHY.
		{
			for(int j=0; j<= noOfFeatures; j++)//LOOK AT '<='. I HAVE NO IDEA WHY.
				featureSpace = new int[i][j];
			labels = new int[i];
		}
				//System.out.println(featureSpace.length);
	}
	public KNeighboursClassifier()
	{
		noOfTuples = 0;
		noOfFeatures = 0;
		for(int i=0; i<= noOfTuples; i++)//LOOK AT '<='. I HAVE NO IDEA WHY.
		{
			for(int j=0; j<= noOfFeatures; j++)//LOOK AT '<='. I HAVE NO IDEA WHY.
				featureSpace = new int[i][j];
			labels = new int[i];
		}
	}
	public void fit(int featureSpaceT[][], int labelsT[])// Applicable for single valued features only.
	{
		//noOfTuples = featureSpaceT.length;
		//noOfFeatures = noOfFeaturesT;
		//featureSpace[][] = new int[noOfTuples][noOfFeatures];
		//labels[]= new int[noOfTuples];
		//noOfTuples = 10;
		//noOfFeatures = 20;
		//System.out.println(featureSpace.length);
		
		/*
		 * 
		 * featureSpace[][] is a 2D table with i labels and j features
		 * example:
		 * 
		 * i = 5, j = 2
		 * 
		 * featureSpace     |	Labels
		 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
		 * height   weight  |	Person1
		 *   2.1     1.4    |	Person2
		 *   4.1    1.6     |	Person3
		 *   1.54   12.7    |	Person4
		 *   13.66  12.12   |	Person5
		 *   2.8    3.87    | 	Person6
		 */
		
		//FILLING THE FEATURE SPACE.
		for(int i=0; i<noOfTuples; i++)
			for(int j=0; j<noOfFeatures; j++)
			{
				featureSpace[i][j] = featureSpaceT[i][j];
				labels[i] = labelsT[i];
			}
		//BEKAR KA DISPLAY.
		/*
		System.out.println("Training data:\nf f f f f f f f f f f f f f f f f f f f L");
		for(int i=0; i<noOfTuples; i++)
		{
			for(int j=0; j<noOfFeatures; j++)
				System.out.print(featureSpace[i][j] + " ");
			System.out.println(labels[i]);
		}
		*/		
	}
	
	public int predict(int testFeatures[]) throws ArrayIndexOutOfBoundsException
	{
		
		//It only works for k = 1 for now.//
		
		
		double distance[] = new double[noOfTuples], sum;
		int indices[] = new int[noOfTuples];
		for(int i=0; i< noOfTuples; i++)
			indices[i] = i;
		/*
		System.out.println("Testing data:");
		for(int i=0; i< noOfFeatures; i++)
			System.out.print(testFeatures[i] + " ");
		System.out.println();
		*/
		//FINDING DIFFERNCES
		for(int i=0; i< noOfTuples; i++)
		{
			sum = 0;
			for(int j=0; j< noOfFeatures; j++)
			{
				 sum+=((testFeatures[j] - featureSpace[i][j])*(testFeatures[j] - featureSpace[i][j]));
			}
			distance[i] = Math.sqrt(sum);
			//System.out.print(distance[i] + " ");
		}
		//System.out.println();
		
		//SORTING | It's currently bubble sort. I'll change to something more efficient like quick sort.
		for(int i=0; i< noOfTuples-1; i++)
			for(int j=i+1; j< noOfTuples; j++)
				if(distance[i] > distance[j])
				{
					distance[i] = distance[i] + distance[j];
					distance[j] = distance[i] - distance[j];
					distance[i] = distance[i] - distance[j];
					indices[i] = indices[i] + indices[j];
					indices[j] = indices[i] - indices[j];
					indices[i] = indices[i] - indices[j];
				}
		/*
		System.out.print("SORTED: ");
		for(int i=0 ;i<distance.length; i++)
			System.out.print(distance[i] + " ");
		System.out.println();
		*/
		//PREDICTING
		int prediction = labels[indices[0]];
		
		return prediction;
	}
	/*
	public static void main(String args[])
	{
		int features[][] = new int[10][20],//[noOfTuples][noOfFeatures] 
				labels[] = new int[10];//[Single Attribute]
		int test[] = new int[20];
		for(int i=0; i< 10; i++)
		{
			for(int j=0; j<20; j++)
			{
				features[i][j] = i;
				test[j] = 1;
			}
			labels[i] = 9-i;			
		}
		int noOfFeatures = 20;
		//System.out.println(features.length);
		
		KNeighboursClassifier KNC = new KNeighboursClassifier(features.length, noOfFeatures);
		KNC.fit(features, labels);
		System.out.println("The prediction is " + KNC.predict(test));
	}*/
}