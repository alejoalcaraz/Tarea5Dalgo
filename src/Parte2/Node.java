/**
 * 
 */
package Parte2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Gabriel
 *
 */
public class Node {
	
	private int nombre;

	private boolean visitado;
	
	private ArrayList<Node> adyacentes;
	
	private int grupo;

	/**
	 * @param visitado
	 * @param adyacentes
	 * @param grupo
	 */
	public Node(int nombre) {
		this.nombre = nombre;
		adyacentes = new ArrayList<Node>();
		this.visitado = false;
		this.grupo = -1;
	}
	
	public boolean DFScycle (LinkedList<Node> orden) {
		boolean respuesta = false;
		orden.add(this);
		visitado = true;
		for (Node node : adyacentes) {
			if(!respuesta) {
				if(node.isVisitado()) {
					
					return true;
				}
				else {
					respuesta = node.DFScycle(orden);
					int a = 0;
				}
			}
		}
		return respuesta;
	}

	/**
	 * @return the nombre
	 */
	public int getNombre() {
		return nombre;
	}

	/**
	 * @return the visitado
	 */
	public boolean isVisitado() {
		return visitado;
	}

	/**
	 * @param visitado the visitado to set
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public void addAdyacente(Node adyacente) {
		adyacentes.add(adyacente);
	}

	/**
	 * @return the grupo
	 */
	public int getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	

	/**
	 * @return the adyacentes
	 */
	public ArrayList<Node> getAdyacentes() {
		return adyacentes;
	}

	public void SDoS(int degree) {
		visitado = true;
		int maxDegree = degree;
		int ady = degree + 1;
		for (Node node : adyacentes) {
			if(!node.isVisitado() && ady <= 6) {
				node.SDoS(ady);
			}
		}
	}

	
	
	
	
	
	
}
