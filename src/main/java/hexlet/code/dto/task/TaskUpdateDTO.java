package hexlet.code.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Setter
@Getter
public class TaskUpdateDTO {
    @NotNull
    private JsonNullable<Long> id;
    @NotBlank
    private JsonNullable<String> title;
    @NotBlank
    private JsonNullable<String> content;
    @NotBlank
    private JsonNullable<String> status;
    @NotNull
    private JsonNullable<Long> assigneeId;
    @NotNull
    private JsonNullable<Set<Long>> taskLabelIds;
}
