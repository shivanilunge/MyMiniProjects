package textBasedRolePlayGamePackage;

import java.util.Scanner;
import java.util.Random;

class Entity {
	private String name;
	private int health;
	private int attack;

	public Entity(String name, int health, int attack) {
		this.name = name;
		this.health = health;
		this.attack = attack;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void damageTaken(int damage) {
		health = health - damage;
	}

	int limit = 0;

	public boolean isAlive() {
		return health > limit;
	}

}
//----------------------------------------------------------------------------------------

class Enemy extends Entity {
	public Enemy(String name, int health, int attack) {
		super(name, health, attack);
	}

}
//----------------------------------------------------------------------------------------

class Player extends Entity {
	private int exp;

	public Player(String name) {
		super(name, 100, 10);
		this.exp = 0;
	}

	public int getExp() {
		return exp;
	}

	public void gainExp(int points) {
		exp = exp + points;
	}
}
//----------------------------------------------------------------------------------------

public class TextBasedRolePlayGame {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.print("Enter player name : ");
		String playerName = sc.nextLine();

		Player p1 = new Player(playerName); // got 100 health

		System.out.println();
		System.out.println("   |=========================================================|");
		System.out.println("   | 	   " + p1.getName() + "... welcome to battle ground !");
		System.out.println("   |=========================================================|");
		System.out.println();
		System.out.println(">>  Press Enter to start");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		sc.nextLine();
		
		System.out.println("	>> " + p1.getName() + " Health : " + p1.getHealth());
		System.out.println("	>> " + p1.getName() + " Attack Power : " + p1.getAttack());

		System.out.println();
		System.out.println(">>  Press Enter to know your enemy details");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sc.nextLine();
		
		int limit = 0;
		while(p1.isAlive()) {
			Enemy e1 = createRandomEnemy();
			System.out.println("	>> Your Enemy : " + e1.getName());
			System.out.println("    	>> " + e1.getName() + "'s Health : " + e1.getHealth());

			while (e1.isAlive()) {
				if(p1.getHealth() > 0)
				{
				System.out.println();
				System.out.println(">>  Press 'Enter' to attack");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				sc.nextLine();

				int playerAttacked = p1.getAttack();
				e1.damageTaken(playerAttacked);

				System.out.println("    >>>>> " + p1.getName() + " attacked " + e1.getName() + " with '" + playerAttacked
						+ "' damage points");
				}
				else
				{
						System.out.println("   |=================|");
						System.out.println("   |>>  Game Over! <<|");
						System.out.println("   |=================|");
						System.out.println();
						System.out.println("    >> You have been defeated by " + e1.getName());
						System.exit(0);
				}

				if (!e1.isAlive()) {
					int expGain = new Random().nextInt(50) + 50;
					p1.gainExp(expGain);

					System.out.println();
					System.out.println("    >> " + p1.getName() + " Health : " + p1.getHealth());
					System.out.println("    >> " + e1.getName() + " Health : " + e1.getHealth());
					System.out.println();
					System.out.println("    >> " + p1.getName() + " defeated " + e1.getName() + " <<|");
					System.out.println();
					System.out.println("   |=================|");
					System.out.println("   |>>  Game Over! <<|");
					System.out.println("   |=================|");
					System.out.println();
					System.out.println("    >> Congrats " + p1.getName() + "! You have gained " + expGain + " exp points");
					System.out.println();

					System.exit(0);
				} else {
					int attack = new Random().nextInt(10) + 5;
					e1.setAttack(attack);
					int enemyAttacked = e1.getAttack();
					p1.damageTaken(enemyAttacked);

					System.out.println();
					System.out.println("    <<<<< " + e1.getName() + " attacked " + p1.getName() + " with '"
							+ enemyAttacked + "' damage points");
					System.out.println();
					System.out.println("    >> " + p1.getName() + " Health : " + p1.getHealth());
					System.out.println("    >> " + e1.getName() + " Health : " + e1.getHealth());
					System.out.println();
				}
			}
		}
	}

	private static Enemy createRandomEnemy() {
		String enemyList[] = { "THANOS", "LOKI", "GOBLIN", "GALACTUS", "ULTRON" };

		Random r = new Random();

		String name = enemyList[r.nextInt(enemyList.length)];
		int health = r.nextInt(80) + 50;
		int attack = r.nextInt(20) + 10;

		return new Enemy(name, health, attack);
	}

}
