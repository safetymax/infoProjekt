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
    int maxdist;
    SoundHandler es = new SoundHandler();
    Melee ml;
    VomitGun vg;
    public Enemy(int x, int y, int type) {
        super(x, y, x, y, x + 100, y + 100, type);
        // 3.141
        speed = 1.5;
        lastPos = new float[2];
        lastPos[0] = 10;
        lastPos[1] = 10;
        this.type = type;
        currentPos = new int[2];
        damage = new float[] { 5, 5, 5 };
        if (type == 3) {
            hitbox = 40;
            health = 15;
            ml = new Melee(150);
            maxdist =64;
        } else if (type == 4) {

            hitbox = 40;
            health = 10;
            vg = new VomitGun(150);
            maxdist = 280;
        } else if (type == 11) {
            hitbox= 40;
            health =120;
            vg = new VomitGun(150);
            maxdist = 350;
        }
    }

    public void move(Boundary[] sprites, Player player, int[][] map, double dist) {
        
            
        if (dist > maxdist) {
            
            posX += Math.cos(alpha) * speed;
            posY += Math.sin(alpha) * speed;
        }
        
        float distX = posX - lastPos[0];
        float distY = posY - lastPos[1];

        if (map[(int) posY / 64][(int) posX / 64] == 1 || map[(int) posY / 64][(int) posX / 64] == 2) {
            // slide on walls / ignore one axis
            if (map[(int) currentPos[1] / 64][(int) posX / 64] == 0) {
                posY = currentPos[1];
            } else if (map[(int) posY / 64][(int) currentPos[0] / 64] == 0) {
                posX = currentPos[0];
            } else {
                posX = currentPos[0];
                posY = currentPos[1];
            }
        }
        if (map[(int) posY / 64][(int) posX / 64] == 0) {
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
        alpha = Math.atan2((posY - player.posY), (posX - player.posX)) + Math.PI;
        if(dist<400){
        move(sprites, player, collisions,dist);
        takeDamage(sprites, player);
        
        if(type ==3){
            ml.updateWeaponsEnemy(this,player, sprites, collisions);
            if(dist<100){
                ml.shoot(sprites, -1);


            }
        } else if(type ==4){
            
            vg.updateWeaponsEnemy(this,player, sprites, collisions);
            if(dist<300){
                vg.shoot(sprites, 12);
            }

        } else if(type ==11){
            vg.updateWeaponsEnemy(this, player, sprites, collisions);
            if(dist<300){

                vg.shoot(sprites, 13);

            }


        }
    }
    }

    
    public void draw(Graphics2D g2d) {
    }

    public void takeDamage(Boundary[] sprites, Player player) {

        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] != null) {

                if (sprites[i].type == 2 || sprites[i].type == 9) {
                    int disttoBullet = (int) Math.sqrt(
                            Math.pow(posX - sprites[i].posX, 2) + Math.pow(posY - sprites[i].posY, 2));

                    if (disttoBullet < hitbox) {
                        if (sprites[i].type == 2) {
                            health -= damage[0];
                            sprites[i] = null;
                        } else if (sprites[i].type == 9) {
                            health -= damage[1];
                            sprites[i] = null;
                        }

                    }

                }
            }
        }

        if (health <= 0) {
            for (int i = 0; i < sprites.length; i++) {
                if (sprites[i] == this) {
                    sprites[i] = null;
                    /*if(this.type == 4){
                    for(int j = 0;j < sprites.length; j++){
                        if(sprites[j] != null && sprites[j] != this){
                            if(sprites[j].type == 4){
                                for(int k = 0; k<sprites.length; k++){
                                    Enemy ally = (Enemy) sprites[k];
                                    if(this.vg.b[k] ==  null){
                                        for(int l = 0; l<sprites.length; l++){
                                            VomitGun allyvg = ally.vg;
                                            if(allyvg.b[l] == null){
                                                ally.vg.b[l] = this.vg.b[k];
                                                this.vg.b[k] = null;
                                                break;


                                            }

                                    }
                                    


                                }
                            }
                        }

                    }
                    }
                    

                }*/

            }

        }
        boolean enemiesDead = true;
        for(int i = 0;i<sprites.length;i++){
            if(sprites[i] != null){
                if(sprites[i].type == 3|| sprites[i].type == 4 || sprites[i].type == 11){
                    enemiesDead = false;
                }
            }
            if(type == 3){
                for(int j = 0;j<ml.b.length;j++){
                    if(sprites[i] == ml.b[j]){
                        sprites[i] = null;
                    }
                }
            }
            else if(type == 4){
                for(int j = 0;j<vg.b.length;j++){
                    if(sprites[i] == vg.b[j]){
                        sprites[i] = null;
                    }
                }
            }
        }
        if(enemiesDead){
            player.isFinished = true;
        }
    }
    }

    // called when a Ray hits the wall
    public void isHitByRay(int x, int y) {
    }

    public void updatePosition(int x1, int y1, int x2, int y2) {
    }
}
