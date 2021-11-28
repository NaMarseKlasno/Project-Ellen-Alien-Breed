package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm
{
    private int value;
    private int maxValue;

    public Firearm(int state, int max) {
        this.value = state;
        this.maxValue = max;
    }
    public Firearm(int state) {
        this.value = state;
        this.maxValue = state;
    }

    public int getAmmo() {
        return this.value;
    }

    public int getAmmoMax() {
        return this.maxValue;
    }

    public void reload(int newAmmo) {
        if (this.value+newAmmo < this.maxValue) this.value += newAmmo;
        else this.value = this.maxValue;
    }

    public Fireable fire()
    {
        if (this.value < 1) return null;
        --this.value;
        return createBullet();
    }

    public abstract Fireable createBullet();
}
