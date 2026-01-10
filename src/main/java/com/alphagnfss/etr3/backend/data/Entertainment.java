package com.alphagnfss.etr3.backend.data;


import lombok.Builder;

import java.time.LocalDate;

// New format
@Builder
public record Entertainment(int id, String name, EntertainmentType type, LocalDate date, EntertainmentStatus status,
							boolean isSpecial, boolean isPilot, boolean isFavorite, String[] tags,
							int seasonId, int episodeNum, int duration) {
}