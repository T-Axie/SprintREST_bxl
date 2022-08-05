package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DemoValidationConstraintForm {

    @Min(1)
    @Max(2)
    @Negative
    @Positive
    @NegativeOrZero
    @PositiveOrZero
    private int integer;

    // pareil que 'integer'
    private Integer nullableInteger;

    @NotBlank
    @NotEmpty
    @NotNull // sur tout type objet
    @Null
    @Pattern(regexp = "[0-9]{3,4}\\/[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}")
    @Size(min = 0, max = 200)
    private String numeroTel;

    @Future
    @Past
    @PastOrPresent
    @FutureOrPresent
    private LocalDateTime time;

    private boolean bool;

    @NotEmpty
    @Size(min = 1, max = 10)
    private List<Object> list;


}
