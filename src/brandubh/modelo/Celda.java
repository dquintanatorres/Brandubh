/**
 * Clase que define las celdas de un tablero de Brandubh y su comportamiento.
 * <p>
 * Un conjunto de celdas constituye el tablero de Brandubh. Estas celdas pueden ser de tres tipos distintos:
 * trono, provincia o normal. Sobre estas celdas se pueden llevar a cabo acciones como colocar/quitar piezas, 
 * comprobar si una celda se encuentra vacía o de qué tipo es, y recuperar la pieza de una celda, entre otras.
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0
 */
package brandubh.modelo;

import java.util.Objects;

import brandubh.util.Color;
import brandubh.util.Coordenada;
import brandubh.util.TipoCelda;

/**
 * Clase que define las celdas de un tablero de Brandubh y su comportamiento.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public class Celda {

	/**
	 * Referencia a objeto de tipo {@code TipoCelda} que registra el tipo de celda: trono, provincia o normal.
	 * 
	 * @see brandubh.util.TipoCelda
	 */
	private TipoCelda tipoCelda;

	/**
	 * Referencia a objeto de tipo {@code Coordenada} que registra la localización de la celda en el tablero (fila y columna).
	 * 
	 * @see brandubh.util.Coordenada
	 */
	private Coordenada coordenada;

	/**
	 * Referencia a objeto de tipo {@code Pieza} que registra la pieza, si la hubiese, situada en la celda.
	 * 
	 * @see brandubh.modelo.Pieza
	 */
	private Pieza pieza;

	/**
	 * Constructor para la clase {@code Celda} a partir de una coordenada como entrada. Crea por defecto una celda de tipo normal.
	 *
	 * @param coordenada Referencia a objeto de tipo {@code Coordenada} en la que se sitúa la celda creada.
	 * @see brandubh.util.Coordenada
	 */
	public Celda(Coordenada coordenada) {
		this.coordenada = coordenada;
		this.tipoCelda = TipoCelda.NORMAL;
	}

	/**
	 * Constructor para la clase {@code Celda} a partir de una coordenada y un tipo de celda como entradas. Crea una celda del tipo 
	 * de celda especificado.
	 *
	 * @param coordenada Referencia a objeto de tipo {@code Coordenada} en la que se sitúa la celda creada.
	 * @param tipoCelda Referencia a objeto de tipo {@code TipoCelda} que indica el tipo (trono, provincia o normal) de la celda creada.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.util.TipoCelda
	 */
	public Celda(Coordenada coordenada, TipoCelda tipoCelda) {
		this.coordenada = coordenada;
		this.tipoCelda = tipoCelda;
	}

	/**
	 * Devuelve un clon en profundidad de la celda actual.
	 * 
	 * @return Copia de la celda con copia de los atributos de la celda actual.
	 */
	public Celda clonar() {
		Celda nuevaCelda = new Celda(this.coordenada, this.tipoCelda);
		if (!this.estaVacia()) {
			nuevaCelda.colocar(new Pieza(this.pieza.consultarTipoPieza()));
		}
		return nuevaCelda;	
	}

	/**
	 * Coloca sobre la celda actual la pieza pasada como entrada.
	 * 
	 * @param pieza Pieza a colocar sobre la celda actual.
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Devuelve el color de la pieza de la celda actual si no está vacía.
	 * 
	 * @return Color de la pieza de la celda actual si no está vacía y {@code null} en caso contrario.
	 * @see brandubh.util.Color
	 */
	public Color consultarColorDePieza() {
		if (!estaVacia()) {
			return pieza.consultarColor();
		}
		return null;
	}

	/**
	 * Devuelve las coordenadas de la celda actual.
	 * 
	 * @return Coordenada de la celda actual.
	 * @see brandubh.util.Coordenada
	 */
	public Coordenada consultarCoordenada() {
		return coordenada;
	}

	/**
	 * Devuelve la pieza de la celda actual.
	 * 
	 * @return Pieza de la celda actual si no está vacía y {@code null} en caso contrario.
	 * @see brandubh.modelo.Pieza
	 */
	public Pieza consultarPieza() {
		return pieza;
	}

	/**
	 * Devuelve el tipo de celda de la celda actual.
	 * 
	 * @return Tipo de celda de la celda actual: trono, provincia o normal.
	 * @see brandubh.util.TipoCelda
	 */
	public TipoCelda consultarTipoCelda() {
		return tipoCelda;
	}

	/**
	 * Elimina la pieza situada sobre la celda actual.
	 */
	public void eliminarPieza() {
		pieza = null;
	}

	/**
	 * Comprueba si la celda actual está vacía, esto es, si hay alguna pieza colocada sobre ella.
	 *
	 * @return Valor de tipo {@code boolean}: {@code true} si la celda no contiene ninguna pieza y {@code false} en caso contrario.
	 */
	public boolean estaVacia() {
		if (pieza == null) {
			return true;
		}
		return false;
	}

	/**
	 * Devuelve un hash code para esta celda.
	 * 
	 * El hash code se calcula usando las coordenadas, pieza y tipo de la celda actual.
	 *
	 * @return Un hash code para esta celda.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza, tipoCelda);
	}

	/**
	 * Comprueba la igualdad entre esta celda y el objeto especificado.
	 * 
	 * Dos objetos de tipo {@code Celda} son iguales si tienen las mismas coordenadas, la misma pieza asociada y son celdas del mismo tipo.
	 *
	 * @param obj El objeto con el que comparar.
	 * @return Valor de tipo {@code boolean}: {@code true} si esta celda es igual al objeto especificado y {@code false} en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza)
				&& tipoCelda == other.tipoCelda;
	}

	/**
	 * Devuelve una representación de cadena de caracteres (<em>string</em>) de esta celda.
	 *
	 * La representación de caracteres incluye el tipo de celda, las coordenadas y la pieza en caso de que la haya.
	 *
	 * @return Una representación de cadena de caracteres ({@code String}) de esta celda.
	 */
	@Override
	public String toString() {
		return "Celda [tipoCelda=" + tipoCelda + ", coordenada=" + coordenada + ", pieza=" + pieza + "]";
	}

}
