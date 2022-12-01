public class Bard extends Character {

    /**
     * the constructor for the bard class.
     */
    public Bard() {
        protection = 3;
        damage = 6;
        speed = 4.5;
        pointsPerLevel = 10;
        levelUpDamage = damage / 2;
        levelUpSpeed = 0.5;
        levelUpProtection = protection / 2;
    }

}