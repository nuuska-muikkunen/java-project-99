package hexlet.code.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class TaskCreateDTO {
    private Integer index;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String slug;
    @NotNull
    private Long assigneeId;
    private Set<Long> taskLabelIds;
}
