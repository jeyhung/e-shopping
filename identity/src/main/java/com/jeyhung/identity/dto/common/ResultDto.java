package com.jeyhung.identity.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResultDto<T> {
    private List<T> items;
    private long itemsCount;
}
