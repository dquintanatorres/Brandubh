/**
 * Clase de tipo registro para representar los movimientos de las piezas en el tablero.
 * <p>
 * El movimiento de las piezas en el tablero vendrá defininido por la celda de origen y la celda de destino del movimiento.
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0
 */
package brandubh.modelo;

import brandubh.util.Sentido;

/**
 * Crea una nueva instancia de la clase de tipo registro {@code Jugada}.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 * @param origen Celda de origen de la pieza.
 * @param destino Celda de destino de la pieza.
 */
public record Jugada(Celda origen, Celda destino) {

	/**
	 * Devuelve un objeto de tipo {@code Sentido} con la orientación del movimiento.
	 * 
	 * @return Sentido del movimiento en la jugada. Puede ser uno de cuatro posibles: vertical-sur {@code Sentido.VERTICAL_S}, 
	 * vertical-norte {@code Sentido.VERTICAL_N}, horizontal-este ({@code Sentido.HORIZONTAL_E}) u 
	 * horizontal-oeste {@code Sentido.HORIZONTAL_O}.
	 */
	public Sentido consultarSentido() {

		if ((origen.consultarCoordenada().fila() != destino.consultarCoordenada().fila()) &&
				(origen.consultarCoordenada().columna() != destino.consultarCoordenada().columna())) {

			return null;

		} else if (origen.consultarCoordenada().fila() != destino.consultarCoordenada().fila()) {

			if (origen.consultarCoordenada().fila() < destino.consultarCoordenada().fila()) {

				return Sentido.VERTICAL_S;
			}

			return Sentido.VERTICAL_N;

		} else {

			if (origen.consultarCoordenada().columna() < destino.consultarCoordenada().columna()) {

				return Sentido.HORIZONTAL_E;

			}

			return Sentido.HORIZONTAL_O;
		}
	}

	/**
	 * Comprueba si el movimiento es horizontal o vertical.
	 *
	 * @return Valor de tipo {@code boolean}: {@code true} si el movimiento es horizontal/vertical y {@code false} en caso contrario.
	 */
	public boolean esMovimientoHorizontalOVertical() {

		if (consultarSentido() != null) {
			return true;
		}

		return false;
	}

}
