package agh.oop.project1;

public record Vector2d(int x, int y) {

    @Override
    public String toString(){
        return "(%d,%d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2d other){
        return ((this.x <= other.x) && (this.y <= other.y));
    }

    public boolean follows(Vector2d other){
        return ((this.x >= other.x) && (this.y >= other.y));
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Integer.max(this.x, other.x), Integer.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Integer.min(this.x, other.x), Integer.min(this.y, other.y));
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }
}
