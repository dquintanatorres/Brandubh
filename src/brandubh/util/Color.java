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
 * Color de las piezas.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public enum Color {

	/**
	 * Color blanco.
	 */
	BLANCO('B'),

	/**
	 * Color negro.
	 */
	NEGRO('N');

	/**
	 * Valor del caracter asociado con el color.
	 */
	private final char letra;

	/**
	 * Construye un objeto Color con el valor del caracter proporcionado.
	 *
	 * @param letra El valor del caracter asociado con el color.
	 */
	Color(char letra) {
		this.letra = letra;
	}

	/**
	 * Devuelve el color de las piezas del oponente.
	 *
	 * @return Color del oponente. Si el color es BLANCO, devuelve NEGRO, y viceversa.
	 */
	public Color consultarContrario() {
		return this == BLANCO ? NEGRO : BLANCO;
	}

	/**
	 * Devuelve el caracter asociado con este color.
	 *
	 * @return Valor del caracter asociado con este color.
	 */
	public char toChar() {
		return this.letra;
	}
}
