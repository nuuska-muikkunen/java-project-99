package hexlet.code.dto.label;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class LabelDTO {
    private long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "UTC+3")
    private Instant createdAt;
}
