package catalog.brands.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandListItemDto {
    private long id;
    private String name;
    private LocalDateTime creationAt;
}
