/**
 * Gestión de las reglas.
 * <p>
 * Definición de la lógica del juego y coordinación del resto de objetos, comprobando la legalidad de las jugadas, 
 * realizando los movimientos si son legales, completando las capturas, gestionando el cambio de turno, la 
 * comprobación de situaciones de finalización de la partida y retroceder el estado de una partida.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0
 */
package brandubh.control;

import brandubh.modelo.Celda;
import brandubh.modelo.Jugada;
import brandubh.modelo.Pieza;
import brandubh.modelo.Tablero;
import brandubh.util.Color;
import brandubh.util.Coordenada;
import brandubh.util.TipoCelda;
import brandubh.util.TipoPieza;

/**
 * Define parte de la lógica de negocio y coordinación del resto de objetos, comprobando la legalidad de las jugadas, 
 * realizando los movimientos si son legales, completando las capturas, gestionando el cambio de turno y la 
 * comprobación de situaciones de finalización de la partida.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public class Arbitro {

	/**
	 * Referencia a objeto de tipo {@code Tablero} en el que se organizan las piezas.
	 * 
	 * @see brandubh.modelo.Tablero
	 */
	private Tablero tablero;

	/**
	 * Valor numérico que registra el número de jugadas ejecutadas.
	 */
	private int contadorJugadas = 0;

	/**
	 * Referencia a objeto de tipo {@code Color} que registra el turno (color) actual.
	 * 
	 * @see brandubh.util.Color
	 */
	private Color turno;

	/**
	 * Referencia a objeto de tipo {@code Pieza} que registra la pieza del último movimiento.
	 * 
	 * @see brandubh.modelo.Pieza
	 */
	private Pieza piezaUltimoMovimiento;

	/**
	 * Referencia a objeto de tipo {@code Celda} que registra la celda de destino del último movimiento.
	 * 
	 * @see brandubh.modelo.Celda
	 */
	private Celda celdaUltimoMovimiento;


	/**
	 * Constructor para la clase {@code Arbitro}.
	 *
	 * @param tablero Referencia a objeto de tipo {@code Tablero} que se asigna al objeto {@code Arbitro} creado.
	 * @see brandubh.modelo.Tablero
	 */
	public Arbitro(Tablero tablero) {
		this.tablero = tablero;
	}

	/**
	 * Transfiere el turno al otro jugador.
	 */
	public void cambiarTurno() {

		if (turno == Color.BLANCO) {
			turno = Color.NEGRO;
		} else {
			turno = Color.BLANCO;
		}
	}

	/**
	 * Coloca las piezas proporcionadas en las coordenadas especificadas e inicializa el turno al jugador indicado. 
	 *
	 * @param tipoPiezas Array de objetos de tipo {@code TipoPieza}.
	 * @param coordenadas Array de coordenadas (tipo {@code Coordenada}), que vendrán dadas por arrays de una dimensión con dos elementos.
	 * @param turnoActual Objeto de tipo {@code Color} que indica el color del jugador al que se inicializa el turno actual.
	 * @see brandubh.util.TipoPieza
	 * @see brandubh.util.Coordenada
	 * @see brandubh.util.Color
	 */
	public void colocarPiezas(TipoPieza[] tipoPiezas, int[][] coordenadas, Color turnoActual) {

		for (int i = 0; i < coordenadas.length; i++) {

			this.tablero.colocar(new Pieza(tipoPiezas[i]), new Coordenada(coordenadas[i][0], coordenadas[i][1]));
			this.turno = turnoActual;

		}
	}

	/**
	 * Coloca las piezas en la configuración inicial para una partida de Brandubh. 
	 */
	public void colocarPiezasConfiguracionInicial() {

		this.turno = Color.NEGRO;

		// Piezas blancas
		tablero.colocar(new Pieza(TipoPieza.REY), new Coordenada(3, 3));
		tablero.colocar(new Pieza(TipoPieza.DEFENSOR), new Coordenada(2, 3));
		tablero.colocar(new Pieza(TipoPieza.DEFENSOR), new Coordenada(3, 2));
		tablero.colocar(new Pieza(TipoPieza.DEFENSOR), new Coordenada(3, 4));
		tablero.colocar(new Pieza(TipoPieza.DEFENSOR), new Coordenada(4, 3));

		// Piezas negras
		int[][] coordenadas = {{0, 3}, {1, 3}, {3, 0}, {3, 1}, {3, 5}, {3, 6}, {5, 3}, {6, 3}};

		for (int[] coord : coordenadas) {
			tablero.colocar(new Pieza(TipoPieza.ATACANTE), new Coordenada(coord[0], coord[1]));
		}


	}


	/**
	 * Devuelve el número de jugadas efectuadas. 
	 *
	 * @return Número de jugadas efectuadas.
	 */
	public int consultarNumeroJugada() {
		return contadorJugadas;
	}

	/**
	 * Devuelve un clon en profundidad del tablero actual. 
	 *
	 * @return Clon en profundidad de tipo {@code Tablero} del tablero actual.
	 * @see brandubh.modelo.Tablero
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}

	/**
	 * Devuelve el color del jugador con el turno actual. 
	 *
	 * @return Color del jugador con el turno actual.
	 * @see brandubh.util.Color
	 */
	public Color consultarTurno() {
		return turno;
	}

	/**
	 * Comprueba si una jugada lleva a cabo un movimiento legal.
	 *
	 * @param jugada Objeto de tipo {@code Jugada} a comprobar.
	 * @return Valor de tipo {@code boolean}: {@code true} si la jugada es legal y {@code false} en caso contrario.
	 * @see brandubh.modelo.Jugada
	 */
	public boolean esMovimientoLegal(Jugada jugada) {

		Coordenada coordenadaOrigen = jugada.origen().consultarCoordenada();
		Coordenada coordenadaDestino = jugada.destino().consultarCoordenada();

		Celda celdaOrigen = tablero.obtenerCelda(coordenadaOrigen);
		Celda celdaDestino = tablero.obtenerCelda(coordenadaDestino);

		if ((celdaOrigen == null) || (celdaDestino == null)) {
			return false;
		}

		if (celdaOrigen.estaVacia() || !celdaDestino.estaVacia()) {
			return false;
		}

		if (celdaOrigen.consultarColorDePieza() != turno) {
			return false;
		}

		if (!jugada.esMovimientoHorizontalOVertical()) {
			return false;
		}

		if (celdaDestino.consultarTipoCelda() == TipoCelda.PROVINCIA && celdaOrigen.consultarPieza().consultarTipoPieza() != TipoPieza.REY) {
			return false;

		}

		if (celdaDestino.consultarTipoCelda() == TipoCelda.TRONO && celdaOrigen.consultarPieza().consultarTipoPieza() != TipoPieza.REY) {
			return false;

		}

		if (!estanCeldasIntermediasVacias(jugada)) {
			return false;
		}

		return true;

	}

	/**
	 * Comprueba si las celdas intermedias de una jugada están vacías.
	 * <p>
	 * Para que un movimiento sea válido, entre las celdas de origen y de destino de un movimiento no debe haber situada 
	 * ninguna pieza.
	 *
	 * @param jugada Objeto de tipo {@code Jugada} a comprobar.
	 * @return Valor de tipo {@code boolean}: {@code true} si las celdas intermedias están vacías y {@code false} 
	 * en caso contrario.
	 * @see brandubh.modelo.Jugada
	 */
	private boolean estanCeldasIntermediasVacias(Jugada jugada) {

		Coordenada coordenadaOrigen = jugada.origen().consultarCoordenada();
		Coordenada coordenadaDestino = jugada.destino().consultarCoordenada();

		int filaOrigen = coordenadaOrigen.fila();
		int columnaOrigen = coordenadaOrigen.columna();
		int filaDestino = coordenadaDestino.fila();
		int columnaDestino = coordenadaDestino.columna();

		int desplazamientoEnFilas = jugada.consultarSentido().consultarDesplazamientoEnFilas();
		int desplazamientoEnColumnas = jugada.consultarSentido().consultarDesplazamientoEnColumnas();

		for (int fila = filaOrigen + desplazamientoEnFilas, columna = columnaOrigen + desplazamientoEnColumnas;
				fila != filaDestino || columna != columnaDestino;
				fila += desplazamientoEnFilas, columna += desplazamientoEnColumnas) {

			Celda celdaIntermedia = tablero.obtenerCelda(new Coordenada(fila, columna));

			if (!celdaIntermedia.estaVacia()) {
				return false;
			}
		}

		return true;
	}


	/**
	 * Comprueba si una celda dada se encuentra contigua al trono.
	 *
	 * @param celda Objeto de tipo {@code Celda} a comprobar.
	 * @return Valor de tipo {@code boolean}: {@code true} si la celda se encuentra contigua al trono y {@code false} en caso contrario.
	 * @see brandubh.modelo.Celda
	 */
	private boolean esCeldaColindanteATrono(Celda celda) {

		Celda[] celdasContiguas = tablero.consultarCeldasContiguas(celda.consultarCoordenada());

		for (Celda celdaContigua : celdasContiguas) {

			if (celdaContigua.consultarTipoCelda() == TipoCelda.TRONO) {
				return true;
			}

		}

		return false;

	}

	/**
	 * Comprueba si ha ganado el jugador atacante tras el último movimiento.
	 *
	 * @return Valor de tipo {@code boolean}: {@code true} si el atacante ha ganado y {@code false} en caso contrario.
	 */
	public boolean haGanadoAtacante() {

		if (piezaUltimoMovimiento.consultarColor() == Color.BLANCO) {
			return false;
		}

		for (Celda celda : tablero.consultarCeldas()) {

			Pieza pieza = celda.consultarPieza();
			Celda[] celdasContiguas = tablero.consultarCeldasContiguas(celda.consultarCoordenada());


			if (!celda.estaVacia() && pieza.consultarTipoPieza() == TipoPieza.REY) {

				// Rey en el trono
				if (celda.consultarTipoCelda() == TipoCelda.TRONO) {

					int contador = 0;

					for (Celda celdaContigua : celdasContiguas) {

						Color colorPiezaCeldaContigua = celdaContigua.consultarColorDePieza();


						if (!celdaContigua.estaVacia() && (colorPiezaCeldaContigua == Color.NEGRO)) {
							contador++;
						}



					}

					if (contador == 4) {

						return true;
					}

				}

				// Rey colindante al trono
				if (esCeldaColindanteATrono(celda)) {

					int contador = 0;

					for (Celda celdaContigua : celdasContiguas) {

						Color colorPiezaCeldaContigua = celdaContigua.consultarColorDePieza();

						if (!celdaContigua.estaVacia() && (colorPiezaCeldaContigua == Color.NEGRO)) {
							contador++;
						}

					}

					if (contador == 3) {

						return true;
					}

				}


				// Rey en el resto del tablero
				if (celda.consultarTipoCelda() != TipoCelda.TRONO) {

					Celda[] celdasContiguasEnHorizontal = tablero.consultarCeldasContiguasEnHorizontal(celda.consultarCoordenada());
					Celda[] celdasContiguasEnVertical = tablero.consultarCeldasContiguasEnVertical(celda.consultarCoordenada());
					Celda[][] celdasContiguasEnHorizontalOVertical = {celdasContiguasEnHorizontal, celdasContiguasEnVertical};

					for (Celda[] direccion : celdasContiguasEnHorizontalOVertical) {

						int contador = 0;
						boolean piezaContiguaMovidaUltimoMovimiento = false;


						for (Celda celdaContigua : direccion) {

							Color colorPiezaCeldaContigua = celdaContigua.consultarColorDePieza();
							TipoCelda tipoCeldaContigua = celdaContigua.consultarTipoCelda();
							Pieza piezaCeldaContigua = tablero.obtenerCelda(celdaContigua.consultarCoordenada()).consultarPieza();


							if ((!celdaContigua.estaVacia() && (colorPiezaCeldaContigua == Color.NEGRO)) || (tipoCeldaContigua == TipoCelda.PROVINCIA)) {
								contador++;
							}

							if (piezaCeldaContigua == piezaUltimoMovimiento) {

								piezaContiguaMovidaUltimoMovimiento = true;

							}

						}

						if (contador == 2 && piezaContiguaMovidaUltimoMovimiento) {

							return true;
						}

					}

				}

			}

		}

		return false;

	}

	/**
	 * Comprueba si ha ganado el jugador defensor (rey) tras el último movimiento.
	 *
	 * @return Valor de tipo {@code boolean}: {@code true} si el defensor (rey) ha ganado y {@code false} en caso contrario.
	 */
	public boolean haGanadoRey() {

		if (piezaUltimoMovimiento.consultarTipoPieza() == TipoPieza.REY && celdaUltimoMovimiento.consultarTipoCelda() == TipoCelda.PROVINCIA) {
			return true;
		} else {
			return false;
		}

	}


	/**
	 * Efectúa el movimiento correspondiente a la jugada indicada.
	 *
	 * @param jugada Objeto de tipo {@code Jugada} a realizar.
	 * @see brandubh.modelo.Jugada
	 */
	public void mover(Jugada jugada) {

		Coordenada coordenadaOrigen = jugada.origen().consultarCoordenada();
		Coordenada coordenadaDestino = jugada.destino().consultarCoordenada();

		Celda celdaOrigen = tablero.obtenerCelda(coordenadaOrigen);
		Celda celdaDestino = tablero.obtenerCelda(coordenadaDestino);

		piezaUltimoMovimiento = celdaOrigen.consultarPieza();

		celdaOrigen.eliminarPieza();
		celdaDestino.colocar(piezaUltimoMovimiento);
		celdaUltimoMovimiento = celdaDestino;

		contadorJugadas++;

	}

	/**
	 * Retira las piezas capturadas en el último movimiento.
	 *
	 */
	public void realizarCapturasTrasMover() {

		for (Celda celda : tablero.consultarCeldas()) {

			Color colorPieza = celda.consultarColorDePieza();
			Pieza pieza = tablero.obtenerCelda(celda.consultarCoordenada()).consultarPieza();


			if (!celda.estaVacia() && pieza != piezaUltimoMovimiento && pieza.consultarTipoPieza() != TipoPieza.REY) {

				Celda[] celdasContiguasEnHorizontal = tablero.consultarCeldasContiguasEnHorizontal(celda.consultarCoordenada());
				Celda[] celdasContiguasEnVertical = tablero.consultarCeldasContiguasEnVertical(celda.consultarCoordenada());
				Celda[][] celdasContiguasEnHorizontalOVertical = {celdasContiguasEnHorizontal, celdasContiguasEnVertical};

				for (Celda[] direccion : celdasContiguasEnHorizontalOVertical) {

					int contador = 0;
					boolean piezaContiguaMovidaUltimoMovimiento = false;

					for (Celda celdaContigua : direccion) {

						Pieza piezaCeldaContigua = tablero.obtenerCelda(celdaContigua.consultarCoordenada()).consultarPieza();
						Color colorPiezaCeldaContigua = celdaContigua.consultarColorDePieza();
						TipoCelda tipoCeldaContigua = celdaContigua.consultarTipoCelda();

						if (piezaCeldaContigua == piezaUltimoMovimiento) {

							piezaContiguaMovidaUltimoMovimiento = true;

						}


						if ((!celdaContigua.estaVacia() && (colorPiezaCeldaContigua != colorPieza)) ||
								((tipoCeldaContigua == TipoCelda.PROVINCIA) || (tipoCeldaContigua == TipoCelda.TRONO) && celdaContigua.estaVacia())) {
							contador++;
						}


					}


					if (contador == 2 && piezaContiguaMovidaUltimoMovimiento) {

						tablero.eliminarPieza(celda.consultarCoordenada());

					}

				}

			}

		}

	}

}

