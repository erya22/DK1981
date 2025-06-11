package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trave {
	private static final Logger log = LoggerFactory.getLogger(Trave.class);
	int id;
	int posizione;
	
	public Trave(int id, int posizione) {
		this.id = id;
		this.posizione = posizione;
	}
	
	
	public static List<Trave> travi = List.of(
			new Trave(1, 0), 
			new Trave(2, -30),
			new Trave(4, -28),
			new Trave(6, -26),
			new Trave(8, -24),
			new Trave(10, -22),
			new Trave(12, -20),
			new Trave(14, -18),
			new Trave(16, -16),
			new Trave(18, -14),
			new Trave(20, -12),
			new Trave(22, -10),
			new Trave(24, -8),
			new Trave(26, -6),
			new Trave(28, -4),
			new Trave(30, -2)
			);

	public static Trave byID(int id) {
		for (Trave trave : travi) {
			if (trave.id == id) return trave;
		}
		
		return null;
	}
}
