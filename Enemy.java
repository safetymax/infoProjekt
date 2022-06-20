import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Enemy extends Boundary {

    public int type;// 1 = horizontal wall, 2 = vertical wall
    public double speed;
    int[] currentPos;
    float[] lastPos;
    int hitbox;
    float health;
    float[] damage;
    double alpha;
    SoundHandler es = new SoundHandler();
    Melee ml;

    public Enemy(int x, int y, int type) {
        super(x, y, x, y, x + 100, y + 100, type);
        // 3.141
        speed = 3.141;
        lastPos = new float[2];
        lastPos[0] = 10;
        lastPos[1] = 10;
        this.type = type;
        currentPos = new int[2];
        damage = new float[]{5,5,5};
        if (type == 3) {
            hitbox = 32;
            health = 15;
            ml = new Melee(150);

        } else if (type == 4) {
            
            hitbox = 32;
            health = 10;

        }

    }

    public void move(Boundary[] sprites, Player player, int[][] map, double dist) {
        
        

        
        if (dist > 64) {
            alpha = Math.atan2((posY - player.posY), (posX - player.posX)) + Math.PI;
            posX += Math.cos(alpha) * speed;
            posY += Math.sin(alpha) * speed;
        }

        float distX = posX - lastPos[0];
        float distY = posY - lastPos[1];

        if(map[(int)posY/64][(int)posX/64] == 1 || map[(int)posY/64][(int)posX/64] == 2){
            //slide on walls / ignore one axis
            if(map[(int)currentPos[1]/64][(int)posX/64] == 0) {
                posY = currentPos[1];
            }
            else if(map[(int)posY/64][(int)currentPos[0]/64] == 0) {
                posX = currentPos[0];
            }
            else {
                posX = currentPos[0];
                posY = currentPos[1];
            }
        }
        if(map[(int)posY/64][(int)posX/64] == 0) {
            currentPos[0] = posX;
            currentPos[1] = posY;
        }
        
        
        
        
        if (Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) > 50) {
            lastPos[0] = posX;
            lastPos[1] = posY;
            if (this.type == 3) {
                es.playSound("alien1Walking", (int) dist);
            } else if (this.type == 4) {
                es.playSound("alien2Walking", (int) dist);

            }

        }

    }

    public void update(Boundary[] sprites, Player player, int[][] collisions) {
        double dist = Math.sqrt(Math.pow(posY - player.posY, 2) + Math.pow(posX - player.posX, 2));
        if(dist<500){
        move(sprites, player, collisions,dist);
        takeDamage(sprites, player);
        
        if(type ==3){
        ml.updateWeaponsEnemy(this,player, sprites, collisions);
        if(dist<100){
            ml.shoot(sprites);


        }
        }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // g2d.setPaint(new Color(60f/255f,60f/255f,60f/255f));
        g2d.setPaint(Color.BLUE);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public Boundary takeDamage(Boundary[] sprites, Player player) {
        
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] != null) {
                //System.out.println("es is nich null");
                
                if (sprites[i].type == 2 || sprites[i].type == 9) {
                    int disttoBullet = (int) Math.sqrt(
                            Math.pow(posX - sprites[i].posX, 2) + Math.pow(posY - sprites[i].posY, 2));
                    System.out.println(disttoBullet);
                    if (disttoBullet < hitbox) {
                        System.out.println("ligma " + sprites[i].type);
                        if(sprites[i].type == 2){
                            health -= damage[0];
                            sprites[i] = null;
                        }
                        else if(sprites[i].type == 9){
                            health -= damage[1];
                            sprites[i] = null;
                        }
                        
                        

                    }
                    
                        

                    }
                } 
            }
            
            if (health <= 0) {
                for(int i = 0; i<sprites.length; i++){
                    if(sprites[i] == this){
                        sprites[i] = null;

                    }


                }

            } 
            return this;
       
        }
       

    
    
    // called when a Ray hits the wall
    public void isHitByRay(int x, int y) {
    }

    public void updatePosition(int x1, int y1, int x2, int y2) {
    }
}

