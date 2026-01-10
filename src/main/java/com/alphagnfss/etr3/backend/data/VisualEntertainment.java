package com.alphagnfss.etr3.backend.data;

import java.time.LocalDate;

public record VisualEntertainment(int id, String name, EntertainmentType type, EntertainmentStatus status, boolean isFavorite) {
	public VisualEntertainment(Entertainment entertainment) {
		this(entertainment.id(), entertainment.name(), entertainment.type(), entertainment.status(), entertainment.isFavorite());
	}
}
