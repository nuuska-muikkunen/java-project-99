package hexlet.code.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class TaskCreateDTO {
    @NotNull
    private String title;

    @NotNull
    private String content;

    private Integer index;

    @NotNull
    private String status;

    @JsonProperty("assignee_id")
    @NotNull
    private Long assigneeId;

    private Set<Long> taskLabelIds;
}
