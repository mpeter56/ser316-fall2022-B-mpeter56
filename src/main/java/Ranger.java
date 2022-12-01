public class Ranger extends Character {

    /**
     * the constructor for the ranger class.
     */
    public Ranger() {
        protection = 8;
        damage = 8;
        speed = 2.5;
        pointsPerLevel = 15;
        levelUpDamage = damage % 10;
        levelUpSpeed = 0.5;
        levelUpProtection = protection % 5;
    }

}