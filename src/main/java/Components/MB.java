package Components;

import java.math.BigDecimal;

public class MB implements Component {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private String formFactor;
    private String socketType;

    public MB(int id, String name, String type, BigDecimal price, String formFactor, String socketType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.formFactor = formFactor;
        this.socketType = socketType;
    }

    @Override
    public int getId() { return id; }
    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public String getType() { return type; }
    @Override
    public void setType(String type) { this.type = type; }
    @Override
    public BigDecimal getPrice() { return price; }
    @Override
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getFormFactor() { return formFactor; }
    public void setFormFactor(String formFactor) { this.formFactor = formFactor; }
    public String getSocketType() { return socketType; }
    public void setSocketType(String socketType) { this.socketType = socketType; }
}