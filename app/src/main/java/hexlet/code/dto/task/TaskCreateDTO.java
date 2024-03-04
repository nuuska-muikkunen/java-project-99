package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class TaskCreateDTO {
    private String title;
    private String content;
    private String slug;
    private Long assigneeId;
    private Set<Long> taskLabels;
}
