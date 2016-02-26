package gamepipelab.usc.tdgame.entities;

public class Monster
{
	private String name;
	private float damage;
	private float HP;
	private float armor;
	private float range;
	private float rateOfFire;
	private float gain;
	private float speed;
	private float mana;
	private String reference;
	private boolean isAirUnit;
	
	public Monster(String name, float damage, float HP, float armor, float range,
			float rateOfFire, float gain, float speed, float mana, String reference,
			boolean isAirUnit)
	{
		super();
		this.name = name;
		this.damage = damage;
		this.HP = HP;
		this.armor = armor;
		this.range = range;
		this.rateOfFire = rateOfFire;
		this.gain = gain;
		this.speed = speed;
		this.mana = mana;
		this.reference = reference;
		this.isAirUnit = isAirUnit;
	}

	public Monster() {
		// TODO Auto-generated constructor stub
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getDamage()
	{
		return damage;
	}

	public void setDamage(float damage)
	{
		this.damage = damage;
	}

	public float getHP()
	{
		return HP;
	}

	public void setHP(float hP)
	{
		HP = hP;
	}

	public float getArmor()
	{
		return armor;
	}

	public void setArmor(float armor)
	{
		this.armor = armor;
	}

	public float getRange()
	{
		return range;
	}

	public void setRange(float range)
	{
		this.range = range;
	}

	public float getRateOfFire()
	{
		return rateOfFire;
	}

	public void setRateOfFire(float rateOfFire)
	{
		this.rateOfFire = rateOfFire;
	}

	public float getGain()
	{
		return gain;
	}

	public void setGain(float gain)
	{
		this.gain = gain;
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public float getMana()
	{
		return mana;
	}

	public void setMana(float mana)
	{
		this.mana = mana;
	}

	public String getReference()
	{
		return reference;
	}

	public void setReference(String reference)
	{
		this.reference = reference;
	}

	public boolean isAirUnit()
	{
		return isAirUnit;
	}

	public void setAirUnit(boolean isAirUnit)
	{
		this.isAirUnit = isAirUnit;
	}

	@Override
	public String toString() {
		return "Monster{" +
				"name='" + name + '\'' +
				", damage=" + damage +
				", HP=" + HP +
				", armor=" + armor +
				", range=" + range +
				", rateOfFire=" + rateOfFire +
				", gain=" + gain +
				", speed=" + speed +
				", mana=" + mana +
				", reference='" + reference + '\'' +
				", isAirUnit=" + isAirUnit +
				'}';
	}
}
