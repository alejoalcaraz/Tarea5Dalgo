/**
 * 
 */
package Parte2;

import java.util.ArrayList;

/**
 * @author Gabriel
 *
 */
public class Node {
	
	public int nombre;

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

	public void bfs(int i) {
		setVisitado(true);
		setGrupo(i);
		for (Node node : adyacentes) {
			if(!node.isVisitado()) {
				node.bfs(i);
			}
		}
		
	}
	
	
	
	
	
}
