package Components;

import java.math.BigDecimal;

public interface Component {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getType();
    void setType(String type);
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}