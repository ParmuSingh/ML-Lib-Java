
public class NeuralNetworksTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double features[][] = {{0, 0, 1},
				{1, 1, 1},
				{1, 0, 1},
				{0, 1, 1}};

		double labels[][] = {{0}, {1}, {1}, {0}};
		double wts[][] = {{1}, {1}, {1}, {1}};
		NeuralNetwork nn = new NeuralNetwork(4);
		
		nn.train(features, labels, 10000);
		
		double test[][] = {{1}, {0}, {0}};
		
		System.out.println(nn.predict(test)[0][0]);
		nn.printWeights();
	}

}
