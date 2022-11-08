import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxGiven {

	
	GamePlay game;

    @Before
    public void setUp() throws Exception {
    	game = new GamePlay();
    }

    @After
    public void tearDown() throws Exception {
    }

    // simple attack test with test of experience after attack
    @Test
    public void equalDP() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.attack(wiz1, wiz2);

        assertEquals(wiz1.experience, 7);
        assertEquals(wiz2.experience, 7);
    }
    
    // node test 1, edge test 1
    @Test
    public void play() {
    	Character bar1 = new Barbarian();
        Character bar2 = new Barbarian();
        Character wiz = new Wizard();

        wiz.health = 5;
        wiz.pointsPerLevel = 20;
        
        GamePlay game1 = new GamePlay(bar1, bar2);
        game1.addOpponent(wiz);
        
        
        
        game1.play();

        assertEquals(0, wiz.health);
        assertEquals(100, bar1.health);
        assertEquals(100, bar2.health);
    }
    
    //test that player goes first when player speed >= opponent speed
    @Test
    public void playPlayerGoesFirst() {
    	Character bar1 = new Barbarian();
        Character bar2 = new Barbarian();

        bar2.health = 5;
        bar2.damage = 5;
        bar1.damage = 15;
        
        
        GamePlay game1 = new GamePlay(bar1, bar2);
        
        game1.play();

        assertEquals(100, bar1.health);
        assertEquals(0, bar2.health);
    }
    
    //test that player goes first when player speed >= opponent speed
    @Test
    public void playOpponentGoesFirst() {
    	Character bar1 = new Barbarian();
        Character bar2 = new Barbarian();

        bar2.health = 5;
        bar2.speed = 3;
        
        bar1.health = 5;
        
        
        GamePlay game1 = new GamePlay(bar1, bar2);
        
        game1.play();

        assertEquals(0, bar1.health);
        assertEquals(100, bar2.health);
    }
    
    //Play a character
    @Test
    public void playCharacter() {
    	Character cha = new Character();
        Character bar = new Barbarian();

        cha.pointsPerLevel = 10;
        
        
        GamePlay game1 = new GamePlay(cha, bar);
        
        game1.play();
        game1.play();


        assertEquals(100, cha.health);
        assertEquals(106, bar.health);
    }
    
    //test that opponents are removed
    @Test
    public void playRemovesImaginaryOpponents() {
    	Character bar1 = new Barbarian();
        Character bar2 = new Barbarian();

        bar2.health = 5;
        bar1.damage = 15;
        
        
        GamePlay game1 = new GamePlay(bar1, bar2);
        
        game1.play();

        assertEquals(0, game1.opponents.size());
    }
    
    
    //test that opponents are removed
    @Test
    public void removesImaginaryOpponents() {
    	Character bar1 = new Barbarian();
        Character bar2 = new Barbarian();
        Wizard wiz = new Wizard();

        GamePlay game1 = new GamePlay(bar1, bar2);
        

        assertEquals(false, game1.removeOpponent(wiz));
    }
    
 // normal experience when healthy
    @Test
    public void dealtDamageNormalExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        game.dealDamage(wiz);
        assertEquals(5, wiz.experience);

        game.dealDamage(bar);
        assertEquals(10, bar.experience);

        game.dealDamage(bard);
        assertEquals(6, bard.experience);

        game.dealDamage(dru);
        assertEquals(7, dru.experience);

        game.dealDamage(ran);
        assertEquals(8, ran.experience);

        game.dealDamage(ro);
        assertEquals(5, ro.experience);
        
        
    }
    
    //The names of the functions correspond to the function names in the document
    
 // normal damage when healthy
    @Test
    public void dealDamageHealthyDamage() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        assertEquals(5, game.dealDamage(wiz));

        bar.health = 100;
        assertEquals(10, game.dealDamage(bar));

        bard.health = 100;
        assertEquals(6, game.dealDamage(bard));

        dru.health = 100;
        assertEquals(7, game.dealDamage(dru));

        ran.health = 100;
        assertEquals(8, game.dealDamage(ran));

        ro.health = 100;
        assertEquals(5, game.dealDamage(ro));   
        
    }
    
    //damage is 0 when health is 0
    @Test
    public void dealDamageZeroHealthDamage() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 0;
        assertEquals(0, game.dealDamage(wiz));

        bar.health = 0;
        assertEquals(0, game.dealDamage(bar));

        bard.health = 0;
        assertEquals(0, game.dealDamage(bard));

        dru.health = 0;
        assertEquals(0, game.dealDamage(dru));

        ran.health = 0;
        assertEquals(0, game.dealDamage(ran));

        ro.health = 0;
        assertEquals(0, game.dealDamage(ro));   
        
    }
    
    @Test
    public void dealDamageLowHealthDamage() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 9;
        assertEquals(10, game.dealDamage(wiz));

        bar.health = 9;
        assertEquals(20, game.dealDamage(bar));

        bard.health = 9;
        assertEquals(12, game.dealDamage(bard));

        dru.health = 9;
        assertEquals(14, game.dealDamage(dru));

        ran.health = 9;
        assertEquals(16, game.dealDamage(ran));

        ro.health = 9;
        assertEquals(10, game.dealDamage(ro));   
        
    }
    
    //damage should be normal at 10 health
    @Test
    public void dealDamageAt10HealthDamage() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 10;
        assertEquals(5, game.dealDamage(wiz));

        bar.health = 10;
        assertEquals(10, game.dealDamage(bar));

        bard.health = 10;
        assertEquals(6, game.dealDamage(bard));

        dru.health = 10;
        assertEquals(7, game.dealDamage(dru));

        ran.health = 10;
        assertEquals(8, game.dealDamage(ran));

        ro.health = 10;
        assertEquals(5, game.dealDamage(ro));   
        
    }
    
    // zero health gives zero experience
    @Test
    public void dealDamageZeroHealthExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 0;
        game.dealDamage(wiz);
        assertEquals(0, wiz.experience);

        bar.health = 0;
        game.dealDamage(bar);
        assertEquals(0, bar.experience);

        bard.health = 0;
        game.dealDamage(bard);
        assertEquals(0, bard.experience);

        dru.health = 0;
        game.dealDamage(dru);
        assertEquals(0, dru.experience);

        ran.health = 0;
        game.dealDamage(ran);
        assertEquals(0, ran.experience);

        ro.health = 0;
        game.dealDamage(ro);
        assertEquals(0, ro.experience);   
        
    }
    
    
    //Health = initialHealth-(damage-protection)
    @Test
    public void takeDamageOverProHealth() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        game.takeDamage(wiz,2);
        assertEquals(99, wiz.health);

        bar.health = 100;
        game.takeDamage(bar,11);
        assertEquals(99, bar.health);

        bard.health = 100;
        game.takeDamage(bard,4);
        assertEquals(99, bard.health);

        dru.health = 100;
        game.takeDamage(dru,5);
        assertEquals(99, dru.health);

        ran.health = 100;
        game.takeDamage(ran,9);
        assertEquals(99, ran.health);

        ro.health = 100;
        game.takeDamage(ro,7);
        assertEquals(99, ro.health);   
        
    }
    
 // no damage should be taken
    @Test
    public void takeDamageAtProHealth() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        game.takeDamage(wiz,1);
        assertEquals(100, wiz.health);

        bar.health = 100;
        game.takeDamage(bar,10);
        assertEquals(100, bar.health);

        bard.health = 100;
        game.takeDamage(bard,3);
        assertEquals(100, bard.health);

        dru.health = 100;
        game.takeDamage(dru,4);
        assertEquals(100, dru.health);

        ran.health = 100;
        game.takeDamage(ran,8);
        assertEquals(100, ran.health);

        ro.health = 100;
        game.takeDamage(ro,6);
        assertEquals(100, ro.health);   
        
    }
    
 // should heal by half and 0.5 should be floored
    @Test
    public void takeDamageBelowProOddHealth() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        wiz.protection = 10;
        game.takeDamage(wiz,5);
        assertEquals(102, wiz.health);

        bar.health = 100;
        bar.protection = 10;
        game.takeDamage(bar,5);
        assertEquals(102, bar.health);

        bard.health = 100;
        bard.protection = 10;
        game.takeDamage(bard,5);
        assertEquals(102, bard.health);

        dru.health = 100;
        dru.protection = 10;
        game.takeDamage(dru,5);
        assertEquals(102, dru.health);

        ran.health = 100;
        ran.protection = 10;
        game.takeDamage(ran,5);
        assertEquals(102, ran.health);

        ro.health = 100;
        ro.protection = 10;
        game.takeDamage(ro,5);
        assertEquals(102, ro.health);   
        
    }
    
    //health should not go below 0
    @Test
    public void takeDamageOverkillHealth() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 10;
        game.takeDamage(wiz,12);
        assertEquals(0, wiz.health);

        bar.health = 10;
        game.takeDamage(bar,21);
        assertEquals(0, bar.health);

        bard.health = 10;
        game.takeDamage(bard,14);
        assertEquals(0, bard.health);

        dru.health = 10;
        game.takeDamage(dru,15);
        assertEquals(0, dru.health);

        ran.health = 10;
        game.takeDamage(ran,19);
        assertEquals(0, ran.health);

        ro.health = 10;
        game.takeDamage(ro,17);
        assertEquals(0, ro.health);   
        
    }
    
  //experience=pro-dam
    @Test
    public void takeDamageBelowProExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        wiz.protection = 10;
        game.takeDamage(wiz,5);
        assertEquals(5, wiz.experience);

        bar.health = 100;
        bar.protection = 10;
        game.takeDamage(bar,5);
        assertEquals(5, bar.experience);

        bard.health = 100;
        bard.protection = 10;
        game.takeDamage(bard,5);
        assertEquals(5, bard.experience);

        dru.health = 100;
        dru.protection = 10;
        game.takeDamage(dru,5);
        assertEquals(5, dru.experience);

        ran.health = 100;
        ran.protection = 10;
        game.takeDamage(ran,5);
        assertEquals(5, ran.experience);

        ro.health = 100;
        ro.protection = 10;
        game.takeDamage(ro,5);
        assertEquals(5, ro.experience);   
        
    }
    
  //experience should be 0 when pro=dam
    @Test
    public void takeDamageAtProExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        game.takeDamage(wiz,1);
        assertEquals(0, wiz.experience);

        bar.health = 100;
        game.takeDamage(bar,10);
        assertEquals(0, bar.experience);

        bard.health = 100;
        game.takeDamage(bard,3);
        assertEquals(0, bard.experience);

        dru.health = 100;
        game.takeDamage(dru,4);
        assertEquals(0, dru.experience);

        ran.health = 100;
        game.takeDamage(ran,8);
        assertEquals(0, ran.experience);

        ro.health = 100;
        game.takeDamage(ro,6);
        assertEquals(0, ro.experience);   
        
    }
    
  //experience = (damage-protection)/2 and 0.5 is floored
    @Test
    public void takeDamageAboveProOddExperience() {
        Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        wiz.health = 100;
        game.takeDamage(wiz,2);
        assertEquals(0, wiz.experience);

        bar.health = 100;
        game.takeDamage(bar,11);
        assertEquals(0, bar.experience);

        bard.health = 100;
        game.takeDamage(bard,4);
        assertEquals(0, bard.experience);

        dru.health = 100;
        game.takeDamage(dru,5);
        assertEquals(0, dru.experience);

        ran.health = 100;
        game.takeDamage(ran,9);
        assertEquals(0, ran.experience);

        ro.health = 100;
        game.takeDamage(ro,7);
        assertEquals(0, ro.experience);   
        
    }
    
  //attack does not happen when char health =0
    @Test
    public void attackCharZeroHealth() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        wiz.health = 0;
        game.attack(wiz, ro);
        assertEquals(0, wiz.health);
        assertEquals(100, ro.health);
        
        bar.health = 0;
        game.attack(bar, ro);
        assertEquals(0, bar.health);
        assertEquals(100, ro.health);
        
        bard.health = 0;
        game.attack(bard, ro);
        assertEquals(0, bard.health);
        assertEquals(100, ro.health);
        
        dru.health = 0;
        game.attack(dru, ro);
        assertEquals(0, dru.health);
        assertEquals(100, ro.health);
        
        ran.health = 0;
        game.attack(ran, ro);
        assertEquals(0, ran.health);
        assertEquals(100, ro.health);
        
        ro.health = 0;
        wiz.health =100;
        game.attack(ro, wiz);
        assertEquals(0, ro.health);
        assertEquals(100, wiz.health);
    }
    
    //attack should not happen when opp health=0
    @Test
    public void attackOppZeroHealth() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        ro.health = 0;
        game.attack(wiz, ro);
        assertEquals(100, wiz.health);
        assertEquals(0, ro.health);
        
        ro.health = 0;
        game.attack(bar, ro);
        assertEquals(100, bar.health);
        assertEquals(0, ro.health);
        
        ro.health = 0;
        game.attack(bard, ro);
        assertEquals(100, bard.health);
        assertEquals(0, ro.health);
        
        ro.health = 0;
        game.attack(dru, ro);
        assertEquals(100, dru.health);
        assertEquals(0, ro.health);
        
        ro.health = 0;
        game.attack(ran, ro);
        assertEquals(100, ran.health);
        assertEquals(0, ro.health);
        
        wiz.health = 0;
        ro.health =100;
        game.attack(ro, wiz);
        assertEquals(100, ro.health);
        assertEquals(0, wiz.health);
    }
    
    //attack should not happen at 0 health
    @Test
    public void attackBothZeroHealth() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        wiz.health = 0;
        ro.health = 0;
        game.attack(wiz, ro);
        assertEquals(0, wiz.experience);
        assertEquals(0, ro.experience);
        
        bar.health = 0;
        ro.health = 0;
        game.attack(bar, ro);
        assertEquals(0, bar.experience);
        assertEquals(0, ro.experience);
        
        bard.health = 0;
        ro.health = 0;
        game.attack(bard, ro);
        assertEquals(0, bard.experience);
        assertEquals(0, ro.experience);
        
        dru.health = 0;
        ro.health = 0;
        game.attack(dru, ro);
        assertEquals(0, dru.experience);
        assertEquals(0, ro.experience);
        
        ran.health = 0;
        ro.health = 0;
        game.attack(ran, ro);
        assertEquals(0, ran.experience);
        assertEquals(0, ro.experience);
        
        wiz.health = 0;
        ro.health = 0;
        game.attack(ro, wiz);
        assertEquals(0, ro.experience);
        assertEquals(0, wiz.experience);
    }
    
  //char should attack first so if they kill opp chqar should take no damage
    @Test
    public void attackCharKillsHealth() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        wiz.damage = 16;
        ro.health = 10;
        ro.experience=0;
        ro.level=1;
        game.attack(wiz, ro);
        assertEquals(100, wiz.health);
        assertEquals(0, ro.health);
        
        bar.damage = 16;
        ro.health = 10;
        ro.experience=0;
        ro.level=1;
        game.attack(bar, ro);
        assertEquals(100, bar.health);
        assertEquals(0, ro.health);
        
        bard.damage = 16;
        ro.health = 10;
        ro.experience=0;
        ro.level=1;
        game.attack(bard, ro);
        assertEquals(100, bard.health);
        assertEquals(0, ro.health);
        
        dru.damage = 16;
        ro.health = 10;
        ro.experience=0;
        ro.level=1;
        game.attack(dru, ro);
        assertEquals(100, dru.health);
        assertEquals(0, ro.health);
        
        ran.damage = 16;
        ro.health = 10;
        ro.experience=0;
        ro.level=1;
        game.attack(ran, ro);
        assertEquals(100, ran.health);
        assertEquals(0, ro.health);
        
        ro.damage = 11;
        ro.health= 100;
        wiz.experience=0;
        wiz.level=1;
        wiz.protection = 1;
        wiz.health = 10;
        game.attack(ro, wiz);
        assertEquals(100, ro.health);
        assertEquals(0, wiz.health);
    }
    
    //level up called on char
    @Test
    public void attackLevelUpCharHealthy() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        wiz.damage = 10;
        ro.damage = 1;
        ro.health = 100;
        ro.level=1;
        ro.experience=0;
        game.attack(wiz, ro);
        assertEquals(2, wiz.level);
        assertEquals(1, ro.level);
        
        bar.damage = 20;
        ro.damage = 10;
        ro.health = 100;
        ro.level=1;
        ro.experience=0;
        game.attack(bar, ro);
        assertEquals(2, bar.level);
        assertEquals(1, ro.level);
        
        bard.damage = 10;
        ro.damage = 3;
        ro.health = 100;
        ro.level=1;
        ro.experience=0;
        game.attack(bard, ro);
        assertEquals(2, bard.level);
        assertEquals(1, ro.level);
        
        dru.damage = 15;
        ro.damage = 4;
        ro.health = 100;
        ro.level=1;
        ro.experience=0;
        game.attack(dru, ro);
        assertEquals(2, dru.level);
        assertEquals(1, ro.level);
        
        ran.damage = 15;
        ro.damage = 8;
        ro.health = 100;
        ro.level=1;
        ro.experience=0;
        game.attack(ran, ro);
        assertEquals(2, ran.level);
        assertEquals(1, ro.level);
        
        ro.damage = 20;
        ro.health= 100;
        ro.level = 1;
        ro.experience= 0;
        wiz.level=1;
        wiz.experience=0;
        wiz.damage = 6;
        wiz.health = 100;
        game.attack(ro, wiz);
        assertEquals(2, ro.level);
        assertEquals(1, wiz.level);
    }
    
    @Test
    public void attackLevelupOppHealthy() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        wiz.damage = 10;
        ro.health = 100;
        ro.damage = 1;
        ro.level = 1;
        ro.experience = 0;
        game.attack(ro, wiz);
        assertEquals(2, wiz.level);
        assertEquals(1, ro.level);
        
        bar.damage = 20;
        ro.health = 100;
        ro.damage = 10;
        ro.level = 1;
        ro.experience = 0;
        game.attack(ro, bar);
        assertEquals(2, bar.level);
        assertEquals(1, ro.level);
        
        bard.damage = 10;
        ro.health = 100;
        ro.damage = 3;
        ro.level = 1;
        ro.experience = 0;
        game.attack(ro, bard);
        assertEquals(2, bard.level);
        assertEquals(1, ro.level);
        
        dru.damage = 15;
        ro.health = 100;
        ro.damage = 4;
        ro.level = 1;
        ro.experience = 0;
        game.attack(ro, dru);
        assertEquals(2, dru.level);
        assertEquals(1, ro.level);
        
        ran.damage = 15;
        ro.health = 100;
        ro.damage = 8;
        ro.level = 1;
        ro.experience = 0;
        game.attack(ro, ran);
        assertEquals(2, ran.level);
        assertEquals(1, ro.level);
        
        ro.damage = 20;
        ro.health= 100;
        wiz.health = 100;
        wiz.level = 1;
        wiz.damage = 6;
        wiz.experience = 0;
        game.attack(wiz, ro);
        assertEquals(2, ro.level);
        assertEquals(1, wiz.level);
    }
    
    @Test
    public void attackLevelupZeroHealth() {
    	Wizard wiz = new Wizard();
        Barbarian bar = new Barbarian();
        Bard bard = new Bard();
        Druid dru = new Druid();
        Ranger ran = new Ranger();
        Rogue ro = new Rogue();
        
        
        ro.damage = 2;
        ro.health = 100;
        wiz.health = 1;
        game.attack(ro, wiz);
        assertEquals(1, wiz.level);
        
        ro.damage = 11;
        ro.health = 100;
        bar.health = 1;
        game.attack(ro, bar);
        assertEquals(1, bar.level);
        
        ro.damage = 4;
        ro.health = 100;
        bard.health = 1;
        game.attack(ro, bard);
        assertEquals(1, bard.level);
        
        ro.damage = 5;
        ro.health = 100;
        dru.health = 1;
        game.attack(ro, dru);
        assertEquals(1, dru.level);
        
        ro.damage = 9;
        ro.health = 100;
        ran.health = 1;
        game.attack(ro,ran);
        assertEquals(1, ran.level);
        
        wiz.damage = 7;
        wiz.health= 100;
        ro.health = 1;
        ro.level = 1;
        ro.experience = 0;
        game.attack(wiz, ro);
        assertEquals(1, ro.level);
    }
}