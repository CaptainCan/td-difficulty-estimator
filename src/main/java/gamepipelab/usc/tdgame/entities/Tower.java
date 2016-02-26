package gamepipelab.usc.tdgame.entities;

public class Tower
{
	private String name;
	private float damage;
	private float HP;
	private float armor;
	private float range;
	private float rateOfFire;
	private float cost;
	private float armorPenetration;
	private float rebootTime;
	private boolean isAirUnit;
	
	public Tower(String name, float damage, float HP, float armor, float range,
			float rateOfFire, float cost, float armorPenetration, float rebootTime,
			boolean isAirUnit)
	{
		this.name = name;
		this.damage = damage;
		this.HP = HP;
		this.armor = armor;
		this.range = range;
		this.rateOfFire = rateOfFire;
		this.cost = cost;
		this.armorPenetration = armorPenetration;
		this.rebootTime = rebootTime;
		this.isAirUnit = isAirUnit;
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

	public float getCost()
	{
		return cost;
	}

	public void setCost(float cost)
	{
		this.cost = cost;
	}

	public float getArmorPenetration()
	{
		return armorPenetration;
	}

	public void setArmorPenetration(float armorPenetration)
	{
		this.armorPenetration = armorPenetration;
	}

	public float getRebootTime()
	{
		return rebootTime;
	}

	public void setRebootTime(float rebootTime)
	{
		this.rebootTime = rebootTime;
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
	public String toString()
	{
		String str = name+": "+"damage: "+damage+" hp: "+HP+" armor: "+armor+" range: "+range+" rateOfFire: "+rateOfFire+" cost: "+cost+" armorPenetration: "+
				     armorPenetration+" rebootTime: "+rebootTime+" isAirUnit?: "+isAirUnit;
		return str;
	}
}
