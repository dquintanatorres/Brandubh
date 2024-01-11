/**
 * Descripción general, frase breve del paquete.
 * <p>
 * Descripción más desarrollada sobre la funcionalidad u objetivo del paquete.
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0
 */
package brandubh.modelo;

import java.util.Arrays;

import brandubh.util.Coordenada;
import brandubh.util.TipoCelda;
import brandubh.util.TipoPieza;

/**
 * Clase que define el tablero de Brandubh y su comportamiento.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public class Tablero {

	/**
	 * Array de celdas (objetos de tipo {@code Celda}) con dimensiones 7x7 por defecto.
	 * 
	 * @see brandubh.modelo.Celda
	 */
	private Celda[][] matriz = new Celda[7][7];

	/**
	 * Constructor para la clase {@code Tablero}.
	 * <p>
	 * Genera un tablero de 7x7 celdas con celdas de tipo provincia en las esquinas, el trono en la celda central y celdas normales
	 * para el resto de coordenadas.
	 *
	 * @see brandubh.modelo.Celda
	 * @see brandubh.util.TipoCelda
	 */
	public Tablero() {

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 3 && j == 3) {
					matriz[3][3] = new Celda(new Coordenada(3, 3), TipoCelda.TRONO);
				} else if ((i == 0 && j == 0) || (i == 0 && j == 6) || (i == 6 && j == 0) || (i == 6 && j == 6)) {
					matriz[i][j] = new Celda(new Coordenada(i, j), TipoCelda.PROVINCIA);
				} else {
					matriz[i][j] = new Celda(new Coordenada(i, j));
				}
			}
		}

	}


	/**
	 * Comprueba si la coordenada especificada corresponda a la de una celda del tablero.
	 *
	 * @param coordenada Coordenada a comprobar.
	 * @return Valor de tipo {@code boolean}: {@code true} si la coordenada está en el tablero 
	 * y {@code false} en caso contrario.
	 * @see brandubh.util.Coordenada
	 */
	private boolean estaTablero(Coordenada coordenada) {

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if ((fila < 0) || (fila > 6) || (columna < 0) || (columna > 6)) {
			return false;
		}

		return true;

	}


	/**
	 * Devuelve una representación de cadena de caracteres (<em>string</em>) del tablero.
	 * <p>
	 * Las celdas vacías se indican con un guión ({@code -}), los atacantes con la letra {@code A}, el rey con la letra {@code R} y 
	 * los defensores con la letra {@code D}.
	 *
	 * @return Una representación de cadena de caracteres ({@code String}) del tablero y su composición.
	 */
	public String aTexto() {

		String pantalla = "";

		for (int i = 0; i < 7; i++) {

			pantalla += "\n" + (7 - i) + " ";

			for (int j = 0; j < 7; j++) {

				if (matriz[i][j].estaVacia()) {
					pantalla = pantalla + "- ";
				} else {
					TipoPieza tipoPieza = matriz[i][j].consultarPieza().consultarTipoPieza();
					pantalla += Character.toString(tipoPieza.toChar());
					pantalla += " ";
				}

			}
		}
		pantalla += "\n  a b c d e f g";

		return pantalla;
	}

	/**
	 * Devuelve un clon en profundidad de este tablero.
	 * 
	 * @return Copia en profundidad del tablero y su composición.
	 */
	public Tablero clonar() {

		Tablero tablero = new Tablero();

		Celda[] celdas = this.consultarCeldas();
		int i = 0;

		for (int columna = 0; columna < consultarNumeroColumnas(); columna++) {
			for (int fila = 0; fila < consultarNumeroFilas(); fila++) {
				Celda nuevaCelda = celdas[i];
				tablero.colocar(nuevaCelda.consultarPieza(), nuevaCelda.consultarCoordenada());
				i++;
			}
		}

		return tablero;

	}

	/**
	 * Coloca la pieza dada en la coordenada especificada.
	 * 
	 * @param pieza Referencia a objeto de tipo {@code Pieza} para colocar en el tablero.
	 * @param coordenada Referencia a objeto de tipo {@code Coordenada} en la que se sitúa la celda especificada.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.modelo.Pieza
	 */
	public void colocar(Pieza pieza, Coordenada coordenada) {

		if ((pieza != null) && (coordenada != null) && estaTablero(coordenada)) {
			matriz[coordenada.fila()][coordenada.columna()].colocar(pieza);
		}

	}

	/**
	 * Devuelve un clon en profundidad de la celda con las coordenadas indicadas.
	 * 
	 * @param coordenada Referencia a objeto de tipo {@code Coordenada} en la que se sitúa la celda especificada.
	 * @return Clon en profundidad de la celda correspondiente a las coordenadas especificadas.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.modelo.Celda
	 */
	public Celda consultarCelda(Coordenada coordenada) {

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (!estaTablero(coordenada)) {
			return null;
		}

		return matriz[fila][columna].clonar();

	}

	/**
	 * Devuelve un array de una dimensión con clones en profundidad de todas las celdas del tablero.
	 * <p>
	 * El orden de las celdas devueltas resulta de recorrer el tablero de arriba hacia abajo y de izquierda a
	 * derecha.
	 * 
	 * @return Array de objetos de tipo {@code Celda} con clones en profundidad de todas las celdas del tablero.
	 * @see brandubh.modelo.Celda
	 */
	public Celda[] consultarCeldas() {

		Celda[] array = new Celda[49];
		int i = 0;

		for (int columna = 0; columna < matriz[0].length; columna++) {
			for (int fila = 0; fila < matriz.length; fila++) {
				array[i] = matriz[fila][columna].clonar();
				i++;
			}
		}

		return array;

	}

	/**
	 * Devuelve un array de una dimensión con clones en profundidad de todas las celdas contiguas a la coordenada dada.
	 * 
	 * @param coordenada Coordena de la celda de la que se devuelven los clones en profundidad de las celdas contiguas.
	 * @return Array de objetos de tipo {@code Celda} con clones en profundidad de todas las celdas contiguas a la coordenada dada.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.modelo.Celda
	 */
	public Celda[] consultarCeldasContiguas(Coordenada coordenada) {

		Celda[] celdasContiguasEnHorizontal = consultarCeldasContiguasEnHorizontal(coordenada);
		Celda[] celdasContiguasEnVertical = consultarCeldasContiguasEnVertical(coordenada);

		int numeroCeldasContiguasEnHorizontal = celdasContiguasEnHorizontal.length;
		int numeroCeldasContiguasEnVertical = celdasContiguasEnVertical.length;

		int numeroCeldasContiguas = numeroCeldasContiguasEnHorizontal + numeroCeldasContiguasEnVertical;

		Celda[] array = new Celda[numeroCeldasContiguas];

		for (int i = 0; i < numeroCeldasContiguasEnHorizontal; i++) {
			array[i] = celdasContiguasEnHorizontal[i];
		}

		for (int i = 0; i < numeroCeldasContiguasEnVertical; i++) {
			array[i + numeroCeldasContiguasEnHorizontal] = celdasContiguasEnVertical[i];
		}

		return array;

	}

	/**
	 * Devuelve un array de una dimensión con clones en profundidad de todas las celdas contiguas en horizontal a la coordenada dada.
	 * 
	 * @param coordenada Coordena de la celda de la que se devuelven los clones en profundidad de las celdas contiguas en horizontal.
	 * @return Array de objetos de tipo {@code Celda} con clones en profundidad de todas las celdas contiguas en horizontal 
	 * a la coordenada dada.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.modelo.Celda
	 */
	public Celda[] consultarCeldasContiguasEnHorizontal(Coordenada coordenada) {

		Celda[] array;
		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (!estaTablero(coordenada)) {
			array = new Celda[0];
			return array;
		}

		if (columna == 0) {
			array = new Celda[] {matriz[fila][columna + 1].clonar()};
		} else if (columna == 6) {
			array = new Celda[] {matriz[fila][columna - 1].clonar()};
		} else {
			array = new Celda[] {matriz[fila][columna - 1].clonar(), matriz[fila][columna + 1].clonar()};
		}

		return array;
	}

	/**
	 * Devuelve un array de una dimensión con clones en profundidad de todas las celdas contiguas en vertical a la coordenada dada.
	 * 
	 * @param coordenada Coordena de la celda de la que se devuelven los clones en profundidad de las celdas contiguas en vertical.
	 * @return Array de objetos de tipo {@code Celda} con clones en profundidad de todas las celdas contiguas en vertical 
	 * a la coordenada dada.
	 * @see brandubh.util.Coordenada
	 * @see brandubh.modelo.Celda
	 */
	public Celda[] consultarCeldasContiguasEnVertical(Coordenada coordenada) {

		Celda[] array;
		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (!estaTablero(coordenada)) {
			array = new Celda[0];
			return array;
		}

		if (fila == 0) {
			array = new Celda[] {matriz[fila + 1][columna].clonar()};
		} else if (fila == 6) {
			array = new Celda[] {matriz[fila - 1][columna].clonar()};
		} else {
			array = new Celda[] {matriz[fila - 1][columna].clonar(), matriz[fila + 1][columna].clonar()};
		}

		return array;
	}

	/**
	 * Devuelve el número de columnas del tablero.
	 * 
	 * @return Número entero correspondiente al número de columnas del tablero.
	 */
	public int consultarNumeroColumnas() {
		return matriz[0].length;
	}

	/**
	 * Devuelve el número de filas del tablero.
	 * 
	 * @return Número entero correspondiente al número de filas del tablero.
	 */
	public int consultarNumeroFilas() {
		return matriz.length;
	}

	/**
	 * Devuelve el número de piezas en el tablero del tipo indicado.
	 * 
	 * @param tipoPieza Tipo de las piezas que queremos contabilizar en el tablero.
	 * @return Número entero correspondiente al número de piezas en el tablero del tipo indicado.
	 */
	public int consultarNumeroPiezas(TipoPieza tipoPieza) {

		int recuento = 0;

		for (int i = 0; i < consultarNumeroFilas(); i++) {
			for (int j = 0; j < consultarNumeroColumnas(); j++) {

				Celda celda = matriz[i][j];
				Pieza pieza = celda.consultarPieza();

				if (!celda.estaVacia() && (pieza.consultarTipoPieza() == tipoPieza)) {
					recuento++;
				}
			}
		}

		return recuento;

	}

	/**
	 * Elimina la pieza en la celda con la coordenada indicada.
	 * <p>
	 * Si la coordenada no está en el tablero no se realiza ninguna acción.
	 * 
	 * @param coordenada Coordenada de la celda cuya pieza queremos eliminar.
	 */
	public void eliminarPieza(Coordenada coordenada) {

		if (coordenada != null && estaTablero(coordenada)) {
			int fila = coordenada.fila();
			int columna = coordenada.columna();

			Celda celda = matriz[fila][columna];

			celda.eliminarPieza();

		}

	}

	/**
	 * Devuelve la referencia a la celda con la coordenada indicada.
	 * <p>
	 * Si la coordenada no está en el tablero devuelve un valor nulo.
	 * 
	 * @param coordenada Coordenada de la celda.
	 * @return Referencia a la celda correspondiente a las coordenadas proporcionadas.
	 */
	public Celda obtenerCelda(Coordenada coordenada) {

		int fila = coordenada.fila();
		int columna = coordenada.columna();

		if (!estaTablero(coordenada)) {
			return null;
		}

		Celda celda = matriz[fila][columna];

		return celda;

	}

	/**
	 * Genera un código hash para este tablero basado en su tipo de pieza.
	 * <p>
	 * Esta implementación utiliza el método {@code Arrays.deepHashCode} para calcular el código hash para 
	 * el array {@code matriz} interno.
	 *
	 * @return Un código hash para este tablero.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
		return result;
	}

	/**
	 * Compara este objeto con el objeto especificado para verificar si son iguales.
	 *
	 * @param obj El objeto con el que se debe comparar este tablero.
	 * @return Valor de tipo {@code boolean}: {@code true} si este tablero es igual al objeto especificado 
	 * y {@code false} en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Arrays.deepEquals(matriz, other.matriz);
	}

	/**
	 * Devuelve una representación de cadena de caracteres (<em>string</em>) de este tablero.
	 *
	 * @return Una representación de cadena de caracteres ({@code String}) de este tablero.
	 */
	@Override
	public String toString() {
		return "Tablero [matriz=" + Arrays.toString(matriz) + "]";
	}































}
