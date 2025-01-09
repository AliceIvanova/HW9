import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class JacksonTest {

  ObjectMapper objectMapper = new ObjectMapper();
  @Test
  void jsonSnowboardTest() throws Exception {
    File file = new File("src/test/resources/snowboard.json");

    Info info = objectMapper.readValue(file, Info.class);
    assertThat(info.getBrand()).isEqualTo("Never Summer");
    assertThat(info.getModel()).isEqualTo("2024-2025");
    assertThat(info.getType()).isEqualTo("snowboard");
    assertThat(info.getCharacteristics().getPurpose()).isEqualTo("freeride");
    assertThat(info.getCharacteristics().getHardness()).isEqualTo("hard");
    assertThat(info.getCharacteristics().getHardnessLevel()).isEqualTo(8);
    assertThat(info.getLevel()).isEqualTo(List.of("advance", "progressive", "expert"));
  }
}
