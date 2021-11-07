/**
 * 
 */
package Parte2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Gabriel
 *
 */
public class BFS {
	private Node[] nodes;
	int maxGrupo = 0;
	String grupos;

	public BFS(int[][] matriz) {
		nodes = new Node[matriz.length];
		constuirGrafo(matriz);
		correrBFS();
		constuirRespuesta();
	}

	private void constuirRespuesta() {
		String[] respuesta = new String[maxGrupo];
		Arrays.fill(respuesta, "{");
		for (int i = 0; i < nodes.length; i++) {
			Node nodo = nodes[i];
			int grupo = nodo.getGrupo();
			if(respuesta[grupo].equals("{")) {
				respuesta[grupo] += i;
			}
			else {
				
				respuesta[grupo] += ", " + i;
			}
			
		}
		for (int i = 0; i < respuesta.length; i++) {
			respuesta[i] += "}";
		}
		unificar(respuesta);
		
	}

	private void unificar(String[] respuesta) {
		String unificada = "{";
		for (String string : respuesta) {
			unificada += unificada.equals("{") ? string : ", " + string;
		}
		unificada += "}";
		this.grupos = unificada;
	}

	private void correrBFS() {
		int identificador = 0;
		for (Node node : nodes) {
			if(!node.isVisitado()) {
				node.bfs(identificador++);
			}
		}
		maxGrupo = identificador;
		
	}

	private void constuirGrafo(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			nodes[i] = new Node();
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
