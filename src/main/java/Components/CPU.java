package Components;

import java.math.BigDecimal;

public class CPU implements Component {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private int cores;
    private BigDecimal clockSpeed;

    public CPU(int id, String name, String type, BigDecimal price, int cores, BigDecimal clockSpeed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.cores = cores;
        this.clockSpeed = clockSpeed;
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
    public int getCores() { return cores; }
    public void setCores(int cores) { this.cores = cores; }
    public BigDecimal getClockSpeed() { return clockSpeed; }
    public void setClockSpeed(BigDecimal clockSpeed) { this.clockSpeed = clockSpeed; }
}