import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Info {
  private String type;
  private String brand;
  private String model;
  private List<String> level;
  private Characteristics characteristics;

  public Characteristics getCharacteristics() {
    return characteristics;
  }

  public static class Characteristics{
    @JsonProperty("Purpose")
    private String purpose;
    @JsonProperty("Hardness")
      private String hardness;
    @JsonProperty("HardnessLevel")
      private int hardnessLevel;

      public String getPurpose() {
        return purpose;
      }

      public String getHardness() {
        return hardness;
      }

      public int getHardnessLevel() {
        return hardnessLevel;
      }
    }

  public String getType() {
    return type;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public List<String> getLevel() {
    return level;
  }
}
