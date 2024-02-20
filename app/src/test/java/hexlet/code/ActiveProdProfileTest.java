package hexlet.code;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "production")
public class ActiveProdProfileTest {
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUserName;

    @Test
    void testProperties() {
        assertThat(dataSourceUrl).isEqualTo("jdbc:postgresql://localhost:5432/spring");
        assertThat(dataSourceUserName).isEqualTo("newcomer");
    }

}
