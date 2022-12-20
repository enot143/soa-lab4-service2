package itmo.service2.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateDto implements Serializable {
    private Double x;
    @NotNull(message = "coordinate y can't be null")
    @Max(value = 271, message = "coordinate y must be <= 271")
    private Float y;
}
