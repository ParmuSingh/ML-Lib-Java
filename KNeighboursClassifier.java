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
		
	}
	
	public int predict(int testFeatures[]) throws ArrayIndexOutOfBoundsException
	{
		
		//It only works for k = 1 for now.//
		
		
		double distance[] = new double[noOfTuples], sum;
		int indices[] = new int[noOfTuples];
		for(int i=0; i< noOfTuples; i++)
			indices[i] = i;
		
		
		//FINDING DIFFERNCES
		for(int i=0; i< noOfTuples; i++)
		{
			sum = 0;
			for(int j=0; j< noOfFeatures; j++)
			{
				 sum+=((testFeatures[j] - featureSpace[i][j])*(testFeatures[j] - featureSpace[i][j]));
			}
			distance[i] = Math.sqrt(sum);
		}
		
		
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
		
		//PREDICTING
		int prediction = labels[indices[0]];
		
		return prediction;
	}
}
