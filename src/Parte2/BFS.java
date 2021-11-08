/**
 * 
 */
package Parte2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Gabriel
 *
 */
public class BFS {
	private Node[] nodes;
	int maxGrupo = 0;
	String grupos = "{";

	public BFS(int[][] matriz) {
		nodes = new Node[matriz.length];
		constuirGrafo(matriz);
		correrBFS();
		
	}
	private void correrBFS() {
		LinkedList<Node> queue = new LinkedList<Node>();
		for (Node node: nodes) {
			if(!node.isVisitado()) {
				node.setVisitado(true);
				queue.add(node);
				String grupo = "{";
				while (queue.size() != 0) {
					int s = queue.poll().getNombre();
					grupo += grupo.equals("{") ? s : ", " + s;

					ArrayList<Node> adyacentes = nodes[s].getAdyacentes();
					for (Node node2 : adyacentes) {
						if(!node2.isVisitado()) {
							node2.setVisitado(true);
							queue.add(node2);
						}
					}
				}
				grupo += "}";
				grupos += grupos.equals("{") ? grupo : ", " + grupo;
			}

		}
		grupos += "}";
	}

	private void constuirGrafo(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == 1) nodes[i].addAdyacente(nodes[j]);
			}
		}	
	}

	/**
	 * @return the grupos
	 */
	public String getGrupos() {
		return grupos;
	}




}
