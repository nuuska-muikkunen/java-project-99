package hexlet.code.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class TaskCreateDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;
    private Integer index;
    @NotNull
    private String slug;
    @NotNull
    private Long assigneeId;
    private Set<Long> taskLabelIds;
}
