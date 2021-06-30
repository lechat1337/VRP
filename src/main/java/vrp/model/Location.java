package vrp.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Location {
    public BigDecimal x;
    public BigDecimal y;

    public Location(){}

    public Location(BigDecimal x, BigDecimal y){
        this.x = x;
        this.y = y;
    }

    public BigDecimal getDist(Location a){
        return this.x.subtract(a.getX()).pow(2).add(this.y.subtract(a.getY()).pow(2)).sqrt(new MathContext(7));
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
