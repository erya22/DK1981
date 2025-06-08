package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

import java.util.List;

public class Scala {
	int id;
	int inizio;
	int fine;
	
	public Scala(int id, int inizio, int fine) {
		this.id = id;
		this.inizio = inizio;
		this.fine = fine;
	}
	
	public static List<Scala> scale = List.of(
		new Scala(32, 0, 32),
		new Scala(33, 0,  2),
		new Scala(34, 2, 32),
		new Scala(35, 0,  4),
		new Scala(36, 4, 32),
		new Scala(37, 0,  6),
		new Scala(38, 6, 32),
		new Scala(39, 0,  8),
		new Scala(40, 8, 32),
		new Scala(41, 0, 10),
		new Scala(42, 10, 32),
		new Scala(43, 0, 12),
		new Scala(44, 12, 32),
		new Scala(45, 0, 14),
		new Scala(46, 14, 32),
		new Scala(47, 0, 16),
		new Scala(48, 16, 32),
		new Scala(49, 0, 18),
		new Scala(50, 18, 32),
		new Scala(51, 0, 20),
		new Scala(52, 20, 32),
		new Scala(53, 0, 22),
		new Scala(54, 22, 32),
		new Scala(55, 0, 24),
		new Scala(56, 24, 32),
		new Scala(57, 0, 26),
		new Scala(58, 26, 32),
		new Scala(59, 0, 28),
		new Scala(60, 28, 32),
		new Scala(61, 32, 30)
	);
	
	public static Scala byID(int id) {
		for (Scala scala : scale) {
			if (scala.id == id) return scala;
		}
		
		return null;
	}
	
}
