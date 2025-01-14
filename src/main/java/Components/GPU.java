package Components;

import java.math.BigDecimal;

public class GPU implements Component {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private int memorySize;
    private BigDecimal clockSpeed;

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
    public int getMemorySize() { return memorySize; }
    public void setMemorySize(int memorySize) { this.memorySize = memorySize; }
    public BigDecimal getClockSpeed() { return clockSpeed; }
    public void setClockSpeed(BigDecimal clockSpeed) { this.clockSpeed = clockSpeed; }
}