package PopSim;

import java.util.Random;
import javax.swing.*;

public abstract class Organism {
	private boolean isAlive;
	private Species speciesName;
	private int organismID;
	private int xCoordinate;
	private int yCoordinate;
	private JLabel label;
	private ImageIcon image;
	private int nutrients;
	private double energy;
	private int speed;
	private int camouflage;
	private int perception;
	private int strength;
	private int numberOfOffspring;
	private double energyToReproduce;
	
	private static double metabolismRate = 0.25;
	private static double mutationRate = 0.25;
	
	//Accessor Functions
	public Species getSpeciesName() {
		return this.speciesName;
	}
	public int getOrganismID() {
		return this.organismID;
	}
	public int getX() {
		return this.xCoordinate;
	}
	public int getY() {
		return this.yCoordinate;
	}
	public ImageIcon getImage() {
		return this.image;
	}
	public JLabel getLabel() {
		return this.label;
	}
	public boolean getIsAlive() {
		return this.isAlive;
	}
	public double getEnergy() {
		return this.energy;
	}
	public int getNutrients() {
		return this.nutrients;
	}
	public int getSpeed() {
		return this.speed;
	}
	public int getPerception() {
		return this.perception;
	}
	public int getCamouflage() {
		return this.camouflage;
	}
	public int getStrength() {
		return this.strength;
	}
	public double getEnergyToReproduce() {
		return this.energyToReproduce;
	}
	public int getNumOffspring() {
		return this.numberOfOffspring;
	}
	public double getMutationRate() {
		return this.mutationRate;
	}
	public double getMetabolismRate() {
		return this.metabolismRate;
	}
	
	//Mutator Functions
	public void setSpeciesName(Species species) {
		this.speciesName = species;
	}
	public void setIsAlive(boolean b) {
		this.isAlive = b;
	}
	public void setOrganismID() {
		this.organismID = this.speciesName.getPopulation().size();
	}
	public void setX(int x) {
		if (x >= 0 && x < this.getSpeciesName().getMap().getXBound()) {
			this.xCoordinate = x;
		}
	}
	public void setY(int y) {
		if (y >= 0 && y < this.getSpeciesName().getMap().getYBound()) {
			this.yCoordinate = y;
		}
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public void setEnergy(double energy) {
		if (energy >= 0 && energy <= 1) {
			this.energy = energy;
		}
		if (this.getEnergy() == 0) {
			this.die();
		}
	}
	public void setNutrients(int nutrients) {
		if (nutrients >= 0) {
			this.nutrients = nutrients;
		}
	}
	public void setSpeed(int tiles) {
		if (tiles >= 0) {
			this.speed = tiles;
		}
	}
	public void setCamouflage(int tiles) {
		if (tiles >= 0) {
			this.camouflage = tiles;
		}
	}
	public void setPerception(int tiles) {
		if (tiles >= 0) {
			this.perception = tiles;
		}
	}
	public void setStrength(int strength) {
		if (strength >= 0) {
			this.strength = strength;
		}
	}
	public void setNumberOfOffspring(int offspring) {
		if (offspring > 0) {
			this.numberOfOffspring = offspring;
		}
	}
	public void setEnergyToReproduce(double minimum) {
		if (minimum > 0 && minimum < 100) {
			this.energyToReproduce = minimum;
		}
	}
	
	public void drawOrganism() {
		//Setting up the label for row
				label = new JLabel("");
				label.setSize(40, 40);
				label.setLocation(this.getY()*50, this.getX()*50);
				this.getSpeciesName().getMap().getFrame().add(label);
				
				//Drawing the picture
				label.setIcon(this.getImage());
				this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getLabel().add(label);
				this.getSpeciesName().getMap().getFrame().revalidate();
				this.getSpeciesName().getMap().getFrame().repaint();
	}
	
	//Random movement:
	public void move() {
		if (this.getIsAlive() == true) {
			Random random = new Random();
			int direction;
			for (int moves = 0; moves < this.speed; moves++) {
				this.getSpeciesName().getMap().remove(this);
				direction = random.nextInt(4)+1;
				if (direction == 1) {
					if (this.getX() < this.getSpeciesName().getMap().getYBound()-1) {
						this.setX(this.xCoordinate + 1);
						//System.out.println("You moved right");
					} else {
						//System.out.println("You can't move right");
						this.setX(this.xCoordinate - 1);
						//System.out.println("You moved left");
					}
				} else if (direction == 2) {
					if (this.getX() > 0) {
						this.setX(this.xCoordinate - 1);
						//System.out.println("You moved left");
					} else {
						//System.out.println("You can't move left");
						this.setX(this.xCoordinate + 1);
						//System.out.println("You moved right");
					}
				} else if (direction == 3) {
					if (this.getY() < this.getSpeciesName().getMap().getXBound()-1) {
						this.setY(this.yCoordinate + 1);
						//System.out.println("You moved down");
					} else {
						//System.out.println("You can't move down");
						this.setY(this.yCoordinate - 1);
						//System.out.println("You moved up");
					}
				} else {
					if (this.getY() > 0) {
						this.setY(this.yCoordinate - 1);
						//System.out.println("You moved up");
					} else {
						//System.out.println("You can't move up");
						this.setY(this.yCoordinate + 1);
						//System.out.println("You moved down");
					}
				}
				this.getSpeciesName().getMap().locate(this);
			}
		}
	}
	
	public void eat(int food) {
		if (this.getIsAlive() == true) {
			this.setNutrients(this.getNutrients()+food);
		}
	}
	public void eat() {
		if (this.getIsAlive() == true) {
			this.setNutrients(this.getNutrients());
		}
	}
	
	public void metabolize() {		
		if (this.getIsAlive() == true) {
			if (this.getEnergy() < (1 - (this.getNutrients()*this.getMetabolismRate())/100)) {
				this.setEnergy(this.getEnergy()+(this.getNutrients()*this.getMetabolismRate())/100);
				this.setNutrients((int)(this.getNutrients()*(1-this.getMetabolismRate())));
			} else {
				double oldEnergy = this.getEnergy();
				this.setEnergy(1);
				this.setNutrients((int)(this.getNutrients()*oldEnergy));
			}
		}
	}
	
	public void reproduce() {
		if (this.getIsAlive() == true) {
			if (this.getEnergy() > this.getEnergyToReproduce()) {
				for (int i = 0; i < this.getNumOffspring(); i++) {
					Random random = new Random();
					int mutation;
					
					Organism child = new Plant(this.getSpeciesName());
					//child.getSpeciesName().getMap().remove(child);
					mutation = random.nextInt(3) - 1;
					if (this.getX() + mutation >= 0 && this.getX() + mutation < this.getSpeciesName().getMap().getXBound()) {
						child.setX(this.getX() + mutation);
					} else {
						child.setX(this.getX());
					}
					mutation = random.nextInt(3) - 1;
					if (this.getY() + mutation >= 0 && this.getX() + mutation < this.getSpeciesName().getMap().getYBound()) {
						child.setY(this.getY() + mutation);
					} else {
						child.setY(this.getY());
					}
					child.setImage(this.getImage());
					child.getSpeciesName().getMap().locate(child);
					
					child.setEnergy(this.getEnergy()/(this.getNumOffspring() + 1));
					
					double speedMutation = random.nextDouble();
					if (speedMutation > this.getMutationRate()) {
						mutation = random.nextInt(3) - 1;
						if (this.getSpeed() + mutation >= 0) {
							child.setSpeed(this.getSpeed() + mutation);
						}
					} else {
						child.setSpeed(this.getSpeed());
					}
					double perceptionMutation = random.nextDouble();
					if (perceptionMutation > this.getMutationRate()) {
						mutation = random.nextInt(3) - 1;
						if (this.getPerception() + mutation >= 0) {
							child.setPerception(this.getPerception() + mutation);
						}
					} else {
						child.setPerception(this.getPerception());
					}
					double camouflageMutation = random.nextDouble();
					if (camouflageMutation > this.getMutationRate()) {
						mutation = random.nextInt(3) - 1;
						if (this.getCamouflage() + mutation >= 0) {
							child.setCamouflage(this.getCamouflage() + mutation);
						}
					} else {
						child.setCamouflage(this.getCamouflage());
					}
					double strengthMutation = random.nextDouble();
					if (strengthMutation > this.getMutationRate()) {
						mutation = random.nextInt(3) - 1;
						if (this.getStrength() + mutation >= 0) {
							child.setStrength(this.getStrength() + mutation);
						}
					} else {
						child.setStrength(this.getStrength());
					}
					this.getSpeciesName().setAvgSpe();
					this.getSpeciesName().setAvgPer();
					this.getSpeciesName().setAvgCam();
					this.getSpeciesName().setAvgStr();
					
					child.setNumberOfOffspring(this.getNumOffspring());
					child.setEnergyToReproduce(this.getEnergyToReproduce());
				}
				this.setEnergy(this.getEnergy()/(this.getNumOffspring() + 1));
			}
		}
	}
	
	public void die() {
		this.setIsAlive(false);
		this.getSpeciesName().getMap().getFrame().remove(this.getLabel());
		this.getLabel().setIcon(null);
		this.setLabel(null);
		this.getSpeciesName().setSize(this.getSpeciesName().getSize() - 1);
	}
	
	public String toString() {
		String s = "";
		s += "\n"+this.speciesName.getSpeciesName()+this.organismID+" ("+this.xCoordinate+","+this.yCoordinate+") Energy: "+this.energy+" Nutrients: "+this.nutrients+" Strength: "+this.strength+" Speed: "+this.speed+" Perception: "+this.perception+" camouflage: "+this.camouflage;
		return s;
	}
}
