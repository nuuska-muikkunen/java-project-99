package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class TaskDTO {
    private long id;
    private String title;
    private String content;
    private Integer index;
    private String status;
    private Long assigneeId;
    private Set<Long> taskLabelIds;
    private Instant createdAt;
}
