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
 * Interconversión entre notación algebraica y coordenadas.
 * <p>
 * Clase de utilidad para la traducción de texto en notación algebra a coordenadas, y al revés,
 * de coordenadas a texto en notación algebraica. Se presupone un tamaño fijo de tablero de
 * 7x7 celdas.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public class Traductor {

	/**
	 * Array de arrays de objetos de tipo {@code String} organizado con nombres de las celdas del tablero.
	 * <p>
	 * Registra las filas en forma de arrays de las celdas del tablero. Cada array registra la disposición en columnas 
	 * de las celdas de cada fila.
	 */
	private static String[][] celdas = {{"a7", "b7", "c7", "d7", "e7", "f7", "g7"},
			{"a6", "b6", "c6", "d6", "e6", "f6", "g6"},
			{"a5", "b5", "c5", "d5", "e5", "f5", "g5"},
			{"a4", "b4", "c4", "d4", "e4", "f4", "g4"},
			{"a3", "b3", "c3", "d3", "e3", "f3", "g3"},
			{"a2", "b2", "c2", "d2", "e2", "f2", "g2"},
			{"a1", "b1", "c1", "d1", "e1", "f1", "g1"}};

	/**
	 * Dado un texto de entrada en notación algebraica devuelve la coordenada de la celda correspondiente al texto introducido.
	 * <p>
	 * Devuelve una coordenada si el texto introducido es de longitud 2 caracteres y el contenido es correcto 
	 * en formato relativo al tamaño del tablero. En caso contrario devuelve {@code NULL}.
	 *
	 * @param texto Texto en notación algebraica correspondiente a una celda.
	 * @return Coordenada si el texto introducido es válido y {@code NULL} en caso contrario.
	 * @see brandubh.util.Coordenada
	 */
	public static Coordenada consultarCoordenadaParaNotacionAlgebraica(String texto) {
		if (esTextoCorrectoParaCoordenada(texto)) {
			for (int i = 0; i < celdas.length; i++) {
				for (int j = 0; j < celdas[i].length; j++) {
					if (celdas[i][j].equals(texto)) {
						return new Coordenada(i, j);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Dada una coordenada de entrada devuelve el nombre en notación algebraica de la celda correspondiente.
	 * <p>
	 * Devuelve el texto de longitud 2 caracteres en notación algebraica si el contenido es correcto. Si no se puede 
	 * realizar la traducción por valor incorrecto en el texto devuelve {@code NULL}.
	 *
	 * @param coordenada Coordenada introducido correspondiente a una celda del tablero.
	 * @return Texto en notación algebraica si la coordenada introducida es válida y {@code NULL} en caso contrario.
	 * @see brandubh.util.Coordenada
	 */
	public static String consultarTextoEnNotacionAlgebraica(Coordenada coordenada) {
		if ((0 <= coordenada.fila() && coordenada.fila() <= 6) && (0 <= coordenada.columna() && coordenada.columna() <= 6)) {
			return celdas[coordenada.fila()][coordenada.columna()];
		}
		return null;
	}

	/**
	 * Comprueba la validez de un texto de entrada en notación algebraica.
	 * <p>
	 * Devuelve {@code true} para textos no nulos, de longitud 2 y que se corresponden en notación algebraica con una 
	 * coordenada válida del juego.
	 *
	 * @param texto Texto en notación algebraica correspondiente a una celda.
	 * @return Valor de tipo {@code boolean}: {@code true} si el texto en notación algebraica se corresponde con una celda 
	 * del tablero y {@code false} en caso contrario.
	 */
	public static boolean esTextoCorrectoParaCoordenada(String texto) {

		if (texto == null || texto.length() != 2) {

			return false;

		} else {

			char columna = texto.charAt(0);
			char fila = texto.charAt(1);

			return (columna >= 'a' && columna <= 'g') && (fila >= '1' && fila <= '7');

		}

	}

}
