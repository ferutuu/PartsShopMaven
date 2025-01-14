package Components;

import java.math.BigDecimal;

public class RAM implements Component {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private int size;
    private int speed;

    // Getters and setters for all fields
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
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
}