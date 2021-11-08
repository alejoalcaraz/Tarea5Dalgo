/**
 * 
 */
package Parte3;

import java.util.LinkedList;

import Parte2.Node;

/**
 * @author Gabriel
 *
 */
public class DFS {

	private boolean tieneCiclos;

	private LinkedList<Node> orden;

	private Node[] nodes;

	public DFS(int[][] matriz) {
		nodes = new Node[matriz.length];
		orden = new LinkedList<Node>();
		constuirGrafo(matriz);
		correrDFS();


	}

	private void correrDFS() {
		for (Node node : nodes) {
			if(!node.isVisitado() && !tieneCiclos) {
				tieneCiclos = node.DFScycle(orden);
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
	 * @return the tieneCiclos
	 */
	public boolean tieneCiclos() {
		return tieneCiclos;
	}

	/**
	 * @return the orden
	 */
	public String getOrden() {
		String respuesta = "{";
		for (Node node : orden) {
			respuesta += respuesta.equals("{") ? node.getNombre() : ", " + node.getNombre();
		}
		respuesta += "}";
		return respuesta;

	}
}
