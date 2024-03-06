package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class TaskDTO {
    private long id;
    private String name;
    private String description;
    private Integer index;
    private String slug;
    private Long assigneeId;
    private Set<Long> taskLabelIds;
    private Instant crestedAt;
}
