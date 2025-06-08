package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.util.HashMap;
import java.util.List;

public class Trave {
	int id;
	int posizione;
	
	public Trave(int id, int posizione) {
		this.id = id;
		this.posizione = posizione;
	}
	
	
	public static List<Trave> travi = List.of(
			new Trave(1, 0), 
			new Trave(2, 2),
			new Trave(4, 4),
			new Trave(6, 6),
			new Trave(8, 8),
			new Trave(10, 10),
			new Trave(12, 12),
			new Trave(14, 14),
			new Trave(16, 16),
			new Trave(18, 18),
			new Trave(20, 20),
			new Trave(22, 22),
			new Trave(24, 24),
			new Trave(26, 26),
			new Trave(28, 28),
			new Trave(30, 30)
			);

	public static Trave byID(int id) {
		for (Trave trave : travi) {
			if (trave.id == id) return trave;
		}
		
		return null;
	}
}
