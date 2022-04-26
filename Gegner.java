public abstract class Gegner {
    public float posX;
    public float posY;
    public double direction;

    public float speed;

    public Gegner(float x, float y, double d, float s) {   
        posX = x;
        posY = y;
        direction = d;
        speed = s;
    }
}