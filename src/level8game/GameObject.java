package level8game;

public class GameObject {
    int x, y, width, height, speed;
    boolean isActive;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 0;
        this.isActive = true;
    }

    public void update() {

    }
}
