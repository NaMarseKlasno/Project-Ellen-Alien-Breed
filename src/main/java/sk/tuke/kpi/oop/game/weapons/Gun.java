package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm
{
    public Gun(int state, int max) {
        super(state, max);
    }


    @Override
    public Fireable createBullet() {
        return new Bullet();
    }
}
