package brandubh.modelo;

import java.util.Objects;

import brandubh.util.Color;
import brandubh.util.TipoPieza;

/**
 * Clase que representa una pieza en el juego. Cada pieza lleva asociado un tipo de pieza.
 * 
 * @author Diego Quintana Torres
 * @version 1.0
 * @since 1.0 
 */
public class Pieza {

	/**
	 * Referencia a objeto de tipo {@code TipoPieza} que registra el tipo de pieza: defensor, atacante o rey.
	 * 
	 * @see brandubh.util.TipoPieza
	 */
	private TipoPieza tipoPieza;

	/**
	 * Constructor para la clase {@code Pieza}. Inicializa la pieza con el tipo de pieza proporcionado.
	 *
	 * @param tipoPieza El tipo de pieza para esta pieza.
	 * @see brandubh.util.TipoPieza
	 */
	public Pieza(TipoPieza tipoPieza) {
		this.tipoPieza = tipoPieza;
	}

	/**
	 * Crea y devuelve una copia de esta pieza.
	 *
	 * @return Una copia de esta pieza.
	 */
	public Pieza clonar() {
		return new Pieza(this.tipoPieza);
	}

	/**
	 * Obtiene el color de esta pieza.
	 *
	 * @return El color de esta pieza.
	 */
	public Color consultarColor() {
		return tipoPieza.consultarColor();
	}

	/**
	 * Obtiene el tipo de pieza de esta pieza.
	 *
	 * @return El tipo de pieza de esta pieza.
	 */
	public TipoPieza consultarTipoPieza() {
		return tipoPieza;
	}

	/**
	 * Genera un código hash para esta pieza basado en su tipo de pieza.
	 *
	 * @return Un código hash para esta pieza.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(tipoPieza);
	}

	/**
	 * Compara este objeto con el objeto especificado para verificar si son iguales.
	 *
	 * @param obj El objeto con el que se debe comparar esta pieza.
	 * @return Valor de tipo {@code boolean}: {@code true} si esta celda es igual al objeto especificado 
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
		Pieza other = (Pieza) obj;
		return tipoPieza == other.tipoPieza;
	}

	/**
	 * Devuelve una representación de cadena de caracteres (<em>string</em>) de esta pieza, que incluye su tipo de pieza.
	 *
	 * @return Una representación de cadena de caracteres ({@code String}) de esta pieza.
	 */
	@Override
	public String toString() {
		return "Pieza [tipoPieza=" + tipoPieza + "]";
	}




}
