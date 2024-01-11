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
 * Tipo de pieza de entre los tres tipos posibles (defensor, atacante o rey).
 * <p>
 * Cada tipo de pieza tien una representación en caracter y un color asociados.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public enum TipoPieza {
	/**
	 * Pieza de tipo defensor.
	 */
	DEFENSOR('D', Color.BLANCO),

	/**
	 * Pieza de tipo atacante.
	 */
	ATACANTE('A', Color.NEGRO),

	/**
	 * Pieza de tipo rey.
	 */
	REY('R', Color.BLANCO);

	/**
	 * Representación en forma de caracter del tipo de pieza.
	 */
	private final char caracter;

	/**
	 * Color de la pieza.
	 * 
	 * @see brandubh.util.Color
	 */
	private final Color color;

	/**
	 * Constructor para el tipo de pieza.
	 *
	 * @param caracter La representación en caracter de la pieza.
	 * @param color El color de la pieza.
	 */
	TipoPieza(char caracter, Color color) {
		this.caracter = caracter;
		this.color = color;
	}

	/**
	 * Obtiene el color de la pieza.
	 *
	 * @return El color de la pieza.
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Obtiene la representación en caracter de la pieza.
	 *
	 * @return La representación en caracter de la pieza.
	 */
	public char toChar() {
		return caracter;
	}

}
