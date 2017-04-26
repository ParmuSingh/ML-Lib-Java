/*
##################################
# Weight | Texture   | Label     #
# 150g   | Bumpy (0) | Orange (1)#
# 170g   | Bumpy (0) | Orange (1)#
# 140g   | Smooth (1)| Apple (0) #
# 130g   | smooth (1)| Apple (0) #
##################################
*/
import java.util.Scanner;
public class KNC_Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		
		//int features[][] = new int[4][2];
		int features[][] = {
				{150, 0},
				{170, 0},
				{140, 1},
				{130, 1}
		};
		int labels[] = {1, 1, 0, 0};
		
		int testFeatures[] = new int[2];
		
		System.out.print("Enter mass: ");
		testFeatures[0] = s.nextInt();
		System.out.print("Enter texture: ");
		String tex = s.next();
		if(tex.equals("bumpy"))
			testFeatures[1] = 0;
		else if(tex.equals("smooth"))
			testFeatures[1] = 1; 
		
		//It takes only two lines to train the classifier//
		KNeighboursClassifier clf = new KNeighboursClassifier(4, 2);
		clf.fit(features, labels);		
		//It takes only two lines to train the classifier//
		if(clf.predict(testFeatures) == 0)
			System.out.println("Its an orange.");
		else
			System.out.println("Its an apple.");	
	}

}
