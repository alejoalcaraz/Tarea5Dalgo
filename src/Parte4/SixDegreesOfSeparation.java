package Parte4;

import java.util.LinkedList;

import Parte2.Node;

public class SixDegreesOfSeparation {

	private Node[] nodes;
	
	private boolean cumple = true ;
	
	public SixDegreesOfSeparation(int[][] matriz) {
		nodes = new Node[matriz.length];
		constuirGrafo(matriz);
		correrSDoS();
		


	}
	
	private void correrSDoS() {
		int cont = 0;
		int maxDegree = 0;
		for (Node node : nodes) {
			if(!node.isVisitado() && cumple) {
				cont ++;
				cumple = cont > 1 ? false: true;
				node.SDoS(0);
			}
		}
		
		
	}
	
	private void constuirGrafo(int[][] matriz) {
		// TODO Auto-generated method stub
		for (int i = 0; i < matriz.length; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] > 0) nodes[i].addAdyacente(nodes[j]);
			}
		}	

	}

	/**
	 * @return the cumple
	 */
	public boolean cumple() {
		return cumple;
	}
}
