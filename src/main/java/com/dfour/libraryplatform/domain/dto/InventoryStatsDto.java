package com.dfour.libraryplatform.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryStatsDto {
    public long uniqueItems;
}
