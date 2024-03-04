package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskParamsDTO {
    private String titleCont;
    private Long assigneeId;
    private String slug;
    private Long labelId;
}
