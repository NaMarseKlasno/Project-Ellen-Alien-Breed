package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien
{
    private Health HEALTH;

    public AlienMother() {
        setAnimation(new Animation("sprites/mother.png", 112, 162, .2f, Animation.PlayMode.LOOP_PINGPONG));
        this.HEALTH = new Health(200);
    }
}
