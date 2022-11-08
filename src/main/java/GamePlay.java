import java.util.*;

public class GamePlay implements GamePlayInterface {

    public Character player;
    public List<Character> opponents;
    // SER316 TASK 2 SPOTBUGS FIX
    public static final String BARBARIAN_NAME = "Barbarian";
    public static final String BARD_NAME = "Bard";
    public static final String DRUID_NAME = "Druid";
    public static final String RANGER_NAME = "Ranger";
    public static final String ROGUE_NAME = "Rogue";
    public static final String WIZARD_NAME = "Wizard";

    /**
     * Default constructor for Game Play.
     */
    public GamePlay() {
        this(new Barbarian());
        
    }

    /**
     * Parameterized constructor for Game Play.
     * 
     * @param character type for player to use
     */
    public GamePlay(Character character) {
        this.player = character;
        this.opponents = new LinkedList<>();
        addOpponent(new Wizard());
        this.opponents.add(new Bard());
        this.opponents.add(new Druid());
        this.opponents.add(new Rogue());
        this.opponents.add(new Ranger());
        this.opponents.add(new Barbarian());
    }

    /**
     * Parameterized constructor for Game Play. Allows choice of opponent.
     *
     * @param character type for player to use
     * @param opponent  type for opponent
     */
    public GamePlay(Character character, Character opponent) {
        this.player = character;
        this.opponents = new LinkedList<>();
        this.opponents.add(opponent);
    }

    /**
     * Utility method to add an opponent to the list of opponents.
     *
     * @param opponent to add
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addOpponent(Character opponent) {
        if (this.opponents.add(opponent)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Utility method to remove an opponent from the list of opponents.
     *
     * @param opponent to remove
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeOpponent(Character opponent) {
        if (this.opponents.remove(opponent)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to add experience points for attacking character and returning the
     * damage the character tries to deal. Experience is only gained if the
     * character still has more than 0 health, damage is also only dealt when health
     * > 0. If the health of a character is less than 10 they deal double damage.
     *
     * @param character that is dealing damage
     * @return damage stat of character as int
     */
    @Override
    public int dealDamage(Character character) {
        int damage;

        if (character.health > 0) {
            if (character.health < 10) {
                // If the health of a character is less than 10 they deal double damage.
                damage = character.damage * 2;
            } else {
                damage = character.damage;
            }
        } else {
            // Experience is only gained if the character still has more than 0 health,
            // damage is also
            // only dealt when health > 0.
            damage = 0;
        }

        character.experience += damage;

        return damage;
    }

    /**
     * Function to add experience points for attacked character as well as update
     * their health based on the attack and their protection. If their protection is
     * higher than the blowDamage then the character will heal by half of that
     * difference they will also gain the full difference as experience
     * 
     * If their protection is lower or equal than the blowDamage then the character
     * will take half the difference as experience and the health will be reduced by
     * the full difference.
     * 
     * 
     * If the difference by half is 0.5 we floor it.
     * 
     * Health cannot go below 0
     * 
     * @param character  that is being attacked
     * @param blowDamage full force of attack without protection factored in
     * @return amount of damage actually taken by the character as int
     */
    @Override
    public int takeDamage(Character character, int blowDamage) {
        int finalDamage;
        int finalExperience;
        double damage;
        double experience;
        if (character.protection > blowDamage) {
            // SER316 TASK 2 SPOTBUGS FIX
            damage = ((double) character.protection - (double) blowDamage) / 2;
            // If the difference by half is 0.5 we floor it.
            finalDamage = -1 * (int) Math.floor(damage); 

            finalExperience = (character.protection - blowDamage);

        } else {
            finalDamage = (blowDamage - character.protection);

            // SER316 TASK 2 SPOTBUGS FIX
            experience = ((double) blowDamage - (double) character.protection) / 2;
            // If the difference by half is 0.5 we floor it.
            finalExperience = (int) Math.floor(experience); 
        }
        character.health -= finalDamage;
        character.experience += finalExperience;

        // Health cannot go below 0
        if (character.health < 0) {
            character.health = 0;
        }

        return finalDamage;
    }

    /**
     * Function to level up characters correctly, if they have enough points to do
     * so. You can assume that the new stats are what we want, so they are not
     * wrong. So this method is not wrong, it is the way we want it!
     * 
     * @param character to be leveled up
     * @return boolean true if character leveld up, false if they did not
     */
    @Override
    public boolean levelUp(Character character) {
        boolean test = false;
        if (character.experience >= character.pointsPerLevel) {
            if (character.experience == character.pointsPerLevel) {
                character.experience += 5;
            }

            character.level++;
            character.pointsPerLevel *= 2; // need more points to level up next time
            character.health = 100; // level up resets health
            
            // increase character stats by class levelUp amounts
            character.damage += character.levelUpDamage;
            character.speed += character.levelUpSpeed;
            character.protection += character.levelUpProtection;
            

            levelUp(character);
            test = true;
        }
        return test;
    }

    /**
     * Function that facilitates the attacker dealing damage to their opponent and
     * then the opposite.
     * 
     * A character can only attack if both still have health greater than 0, this
     * needs to be true for both attacks happening here
     * 
     * You do NOT have the implemented methods for this but just 5 implemented
     * versions in the .class files in the cls directory. So you need to Blackbox
     * test this method based on the description you get here. As you see the method
     * returns void, so no return type. You need to come up with a way to still test
     * if this method does what it is supposed to do. It is up to you to figure that
     * out.
     * 
     * This method uses dealDamage and takeDamage from above, which you should
     * BlackBox test first.
     * 
     * An attack only happens if health>0 for both characters The first character
     * attacks first, by using dealsDamage and the opponent takesDamage. Then the
     * characters level up (call levelUp on both) -- if health > 0
     * 
     * Then the other character attacks, same procedure as above
     * 
     * 
     *
     * @param character that is attacking
     * @param opponent  that is being attacked
     */
    @Override
    public void attack(Character character, Character opponent) {
        int damage;

        if (character.health > 0 && opponent.health > 0) {

            // character attacks
            damage = dealDamage(character);
            takeDamage(opponent, damage);
            if (opponent.health > 0) {
                levelUp(opponent);
                levelUp(character);
            } else {
                levelUp(character);
            }

            // opponent attacks
            if (character.health > 0 && opponent.health > 0) {
                damage = dealDamage(opponent);
                takeDamage(character, damage);
                if (character.health > 0) {
                    levelUp(character);
                    levelUp(opponent);
                } else {
                    levelUp(opponent);
                }
            }
        }
    }

    /**
     * This method returns the amount of experience points earned by the player
     * during one round of attacks. White box test me in assignment 3 (not in 2) !
     * What this method does: - The player will attack each opponent once, and each
     * opponent will attack the player once. - The player will just iterate through
     * the list of opponents in the order they are in - The attack from an opponent
     * and the attack on the same opponent happen right after one another. The order
     * in which the attacks happen are based on the speed of the opponent and
     * player. The faster of the two attacks first, then the slower. If equal the
     * player attacks first. The character that attacks first is awarded the
     * difference of the two speeds rounded up to the next integer in experience
     * points. Your player then moves onto the next opponent. - The attack and
     * levelUp are in separate methods (for whitebox testing in assignment 3 you can
     * assume that these methods work correctly and it just shows up as "node" in
     * your graph)
     *
     * @return the amount of experience points that the play acquired during play as
     *         int
     */
    @Override
    public int play() {
        int startingExperience = player.experience;
        for (Character opponent : opponents) {
            // determine order of attack and give experience points for attacking first
            Character[] orderOfAttack = new Character[2];
            //changed > to >= "If equal the player attacks first."
            if (player.speed >= opponent.speed) {  
                orderOfAttack[0] = player;
                orderOfAttack[1] = opponent;
                player.experience += Math.ceil(player.speed - opponent.speed);
            } else {
                orderOfAttack[0] = opponent; // changed [1] to [0]
                // changed [0] to [1] if an opponents speed is higher it should go first
                orderOfAttack[1] = player; 
                opponent.experience += Math.ceil(opponent.speed - player.speed);
            }

            // attack in order
            attack(orderOfAttack[0], orderOfAttack[1]);

        }

        // remove opponents that have <= 0 health
        for (int o = 0; o < opponents.size(); o++) {
            if (opponents.get(o).health <= 0) {
                System.out.println(opponents.get(o).getClass().getName() + " removed\n");
                removeOpponent(opponents.get(o));
                o--;
            }
        }

        return player.experience - startingExperience;
    }

}