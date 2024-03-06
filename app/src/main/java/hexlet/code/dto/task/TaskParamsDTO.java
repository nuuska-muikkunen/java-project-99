package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskParamsDTO {
    private String nameCont;
    private Long assigneeId;
    private String status;
    private Long labelId;
}
