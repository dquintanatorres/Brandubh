/**
 * Clases de utilidad empleadas por las clases del resto de paquetes.
 * <p>
 * Contiene tipos enumerados, registros y clases de utilidad reutilizados en el resto de paquetes.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0
 */
package brandubh.util;

/**
 * Sentidos en los que se pueden realizar los movimientos.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public enum Sentido {

	/**
	 * Sentido en vertical hacia un menor número de fila.
	 */
	VERTICAL_N(-1, 0),

	/**
	 * Sentido en vertical hacia un mayor número de fila.
	 */
	VERTICAL_S(1, 0),

	/**
	 * Sentido en horizontal hacia un mayor número de columna.
	 */
	HORIZONTAL_E(0, 1),

	/**
	 * Sentido en horizontal hacia un menor número de columna.
	 */
	HORIZONTAL_O(0, -1);

	/**
	 * Entero con signo representando el desplazamiento en filas de la pieza: {@code 1} (desplazamiento hacia arriba), {@code -1} 
	 * (desplazamiento hacia abajo) o {@code 0} (sin desplazamiento).
	 */
	private final int desplazamientoEnFilas;

	/**
	 * Entero con signo representando el desplazamiento en columnas de la pieza: {@code 1} (desplazamiento hacia la derecha), 
	 * {@code -1} (desplazamiento hacia la izquierda) o {@code 0} (sin desplazamiento).
	 */
	private final int desplazamientoEnColumnas;

	/**
	 * Construye un objeto Sentido a partir de los valores de desplazamiento en filas y en columnas proporcionados.
	 *
	 * @param desplazamientoEnFilas Valor del desplazamiento en filas.
	 * @param desplazamientoEnColumnas Valor del desplazamiento en columnas.
	 */
	Sentido(int desplazamientoEnFilas, int desplazamientoEnColumnas) {
		this.desplazamientoEnFilas = desplazamientoEnFilas;
		this.desplazamientoEnColumnas = desplazamientoEnColumnas;
	}

	/**
	 * Devuelve el valor del desplazamiento en filas.
	 *
	 * @return Valor del desplazamiento en filas.
	 */
	public int consultarDesplazamientoEnFilas() {
		return this.desplazamientoEnFilas;
	}

	/**
	 * Devuelve el valor del desplazamiento en columnas.
	 *
	 * @return Valor del desplazamiento en columnas.
	 */
	public int consultarDesplazamientoEnColumnas() {
		return this.desplazamientoEnColumnas;
	}
}
