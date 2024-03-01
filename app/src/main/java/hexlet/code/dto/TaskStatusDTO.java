package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class TaskStatusDTO {
    private Long id;
    private String name;
    private String slug;
    private Instant createdAt;
}
