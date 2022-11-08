public class Rogue extends Character {

    public Rogue() {
        protection = 6;
        damage = 5;
        speed = 3.5;
        pointsPerLevel = 20;
        levelUpDamage = damage / 3;
        levelUpSpeed = 1.25;
        levelUpProtection = 3;
    }

}