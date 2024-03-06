package hexlet.code.dto.label;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class LabelDTO {
    private long id;
    private String name;
    private Instant crestedAt;
}
