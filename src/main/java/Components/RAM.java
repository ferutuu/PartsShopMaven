package Components;

import java.math.BigDecimal;

public class RAM implements Component {
    private int id;
    private String name;
    private String type;
    private BigDecimal price;
    private int capacity;
    private int speed;
    private String generation;

    public RAM(int id, String name, String type, BigDecimal price, int capacity, int speed, String generation) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.speed = speed;
        this.generation = generation;
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
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public String getGeneration() { return generation; }
    public void setGeneration(String generation) { this.generation = generation; }
}