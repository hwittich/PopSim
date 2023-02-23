package PopSim;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Predator extends Organism {
	public Predator(Species species) {
		setSpeciesName(species);
		this.getSpeciesName().grow(this);
		setIsAlive(true);
		setOrganismID();
		setImage(new ImageIcon(new ImageIcon("predators.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setEnergy(0.5);
		setNutrients(100);
		setSpeed(3);
		setPerception(3);
		setCamouflage(3);
		setStrength(3);
		setNumberOfOffspring(2);
		setEnergyToReproduce(0.8);
	}
	public Predator(Species species, int speed, int perception, int camouflage, int strength) {
		Random random = new Random();
		
		setSpeciesName(species);
		this.getSpeciesName().grow(this);
		this.getSpeciesName().setAvgSpe();
		this.getSpeciesName().setAvgPer();
		this.getSpeciesName().setAvgCam();
		this.getSpeciesName().setAvgStr();
		setIsAlive(true);
		setOrganismID();
		setX(random.nextInt(this.getSpeciesName().getMap().getXBound()));
		setY(random.nextInt(this.getSpeciesName().getMap().getYBound()));
		this.getSpeciesName().getMap().locate(this);
		setImage(new ImageIcon(new ImageIcon("predators.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		this.drawOrganism();
		setEnergy(0.5);
		setNutrients(50);
		setSpeed(speed);
		setPerception(perception);
		setCamouflage(camouflage);
		setStrength(strength);
		setNumberOfOffspring(1);
		setEnergyToReproduce(0.5);
	}
	
	public void move() {
		if (this.getIsAlive() == true) {
			this.getSpeciesName().getMap().remove(this);
			//setting bounds for search area
			int xLowSearchDistance = this.getX() - this.getPerception();
			//System.out.println("xLowSearchDistance: "+xLowSearchDistance);
			if (xLowSearchDistance < 0) {
				xLowSearchDistance = 0;
			}
			//System.out.println("xLowSearchDistance: "+xLowSearchDistance);
			int xHighSearchDistance = this.getX() + this.getPerception();
			//System.out.println("xHighSearchDistance: "+xHighSearchDistance);
			if (xHighSearchDistance > this.getSpeciesName().getMap().getXBound() - 1) {
				xHighSearchDistance = this.getSpeciesName().getMap().getXBound() - 1;
			}
			//System.out.println("xHighSearchDistance: "+xHighSearchDistance);
			int yLowSearchDistance = this.getY() - this.getPerception();
			//System.out.println("yLowSearchDistance: "+yLowSearchDistance);
			if (yLowSearchDistance < 0) {
				yLowSearchDistance = 0;
			}
			//System.out.println("yLowSearchDistance: "+yLowSearchDistance);
			int yHighSearchDistance = this.getY() + this.getPerception();
			//System.out.println("yHighSearchDistance: "+yHighSearchDistance);
			if (yHighSearchDistance > this.getSpeciesName().getMap().getYBound() - 1) {
				yHighSearchDistance = this.getSpeciesName().getMap().getYBound() - 1;
			}
			//System.out.println("yHighSearchDistance: "+yHighSearchDistance);
			//Searching nearby area for organisms
			int distance;
			int preyDistance = 2147483647;
			Organism closestPrey = null;
			int predatorDistance = 2147483647;
			Organism closestPredator = null;
			for (int column = yLowSearchDistance; column <= yHighSearchDistance; column++) {
				for (int row = xLowSearchDistance; row <= xHighSearchDistance; row++) {
					if (this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().size() > 0) {
						for (int organism = 0; organism < this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().size(); organism++) {
							if (this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getIsAlive() == true && (this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Herbivore || this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Predator || this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Scavenger) && !(this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getSpeciesName() == this.getSpeciesName())) {
								distance = 0;
								distance += Math.abs(this.getX()-this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getX());
								distance += Math.abs(this.getY()-this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getY());
								//System.out.println("Organism:"+this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getSpeciesName().getSpeciesName()+this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getOrganismID()+" Distance: "+distance);
								if ((this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Herbivore || this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Scavenger) && distance <= this.getPerception() - this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getCamouflage()) {
									if (distance < preyDistance) {
										preyDistance = distance;
										closestPrey = this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism);
										//System.out.println(closestPrey.getSpeciesName().getSpeciesName()+closestPrey.getOrganismID()+" is the new closest prey!");
									}
								}
								if (this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism) instanceof Predator && distance <= this.getPerception() - this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism).getCamouflage()) {
									if (distance < predatorDistance) {
										predatorDistance = distance;
										closestPredator = this.getSpeciesName().getMap().getAcre(row, column).getOrganisms().get(organism);
										//System.out.println(closestPredator.getSpeciesName().getSpeciesName()+closestPredator.getOrganismID()+" is the new closest predator!");
									}
								}
							}
						}
					}
				}
			}
			//System.out.println("Closest Prey: "+closestPrey);
			//System.out.println("Distance: "+preyDistance);
			//System.out.println("Closest Predator: "+closestPredator);
			//System.out.println("Distance: "+predatorDistance);
			
			//Deciding to run towards prey or from a predator
			Random random = new Random();
			String priority = "";
			boolean preyOrPredator = random.nextBoolean();
			if ((!(closestPrey == null) && closestPredator == null) || 
					((!(closestPrey == null) && !(closestPredator == null)) && preyDistance < predatorDistance) ||
					((!(closestPrey == null) && !(closestPredator == null)) && preyDistance == predatorDistance && preyOrPredator == true)) {
				priority = "prey";
				//System.out.println("Moving towards prey");
				//move towards prey
			} else if ((closestPrey == null && !(closestPredator == null)) || 
					((!(closestPrey == null) && !(closestPredator == null)) && predatorDistance < preyDistance) ||
							((!(closestPrey == null) && !(closestPredator == null)) && preyDistance == predatorDistance && preyOrPredator == false)) {
				priority = "predator";	
				//System.out.println("Moving away from Predator");
			}
			
			int direction = 0;
			boolean xOrY = random.nextBoolean();
			boolean randomMovement = false;
			//Rows are y values and columns are x values
			//up is -y, direction = 1
			//down is +y, direction = 2
			//left is -x, direction = 3
			//right is +x, direction = 4
			if (priority.equals("prey")) {
				//Move towards prey
				if (this.getX() == closestPrey.getX() && !(this.getY() == closestPrey.getY())) {
					//Move through column to prey
					if (this.getY() > closestPrey.getY()) {
						//move up to prey
						direction = 1;
					} else {
						//move down to prey
						direction = 2;
					}
				} else if (this.getY() == closestPrey.getY() && !(this.getX() == closestPrey.getX())) {
					//Move through row to prey
					if (this.getX() > closestPrey.getX()) {
						//move left to prey
						direction = 3;
					} else {
						//move right to prey
						direction = 4;
					}
				} else if (!(this.getX() == closestPrey.getX()) && !(this.getY() == closestPrey.getY())) {
					//Move through column or row towards prey
					xOrY = random.nextBoolean();
					if (xOrY == true) {
						//Move through row to prey
						if (this.getX() > closestPrey.getX()) {
							//Move left to prey
							direction = 3;
						} else {
							//Move right to prey
							direction = 4;
						}
					} else {
						//Move through column to prey
						if (this.getY() > closestPrey.getY()) {
							//Move up to prey
							direction = 1;
						} else {
							//Move down to prey
							direction = 2;
						}
					}
				}
			} else if (priority.equals("predator")) {
				//Move away from predator
				if ((this.getX() == closestPredator.getX() && !(this.getY() == closestPredator.getY())) || 
						((!(this.getX() == closestPredator.getX()) && !(this.getY() == closestPredator.getY())) && xOrY == false)) {
					//Move through column away from predator
					if (this.getY() > closestPredator.getY()) {
						//Try to move down away from predator
						if (this.getY() < this.getSpeciesName().getMap().getYBound() - 1) {
							//Move down away from predator
							direction = 2;
						} else if (this.getY() == this.getSpeciesName().getMap().getYBound() - 1) {
							//Move left or right away from predator
							if ((this.getX() > (this.getSpeciesName().getMap().getXBound()-1)/2) && this.getX() + this.getPerception() > this.getSpeciesName().getMap().getXBound()-1) {
								//Move left away from corner of map
								direction = 3;
							} else if ((this.getX() < (this.getSpeciesName().getMap().getXBound()-1)/2) && this.getX() - this.getPerception() < 0) {
								//Move right away from corner of map
								direction = 4;
							} else {
								//Randomly move right or left
								direction = random.nextInt(2)+3;
							}
						}
					} else {
						//Try to move up away from predator
						if (this.getY() > 0) {
							//Move up away from predator
							direction = 1;
						} else if (this.getY() == 0) {
							//Move left or right away from predator
							if ((this.getX() > (this.getSpeciesName().getMap().getXBound()-1)/2) && this.getX() + this.getPerception() > this.getSpeciesName().getMap().getXBound()-1) {
								//Move left away from corner of map
								direction = 3;
							} else if ((this.getX() < (this.getSpeciesName().getMap().getXBound()-1)/2) && this.getX() - this.getPerception() < 0) {
								//Move right away from corner of map
								direction = 4;
							} else {
								//Randomly move right or left
								direction = random.nextInt(2)+3;
							}
						}
					}
				} else if ((this.getY() == closestPredator.getY() && !(this.getX() == closestPredator.getX())) || 
						((!(this.getX() == closestPredator.getX()) && !(this.getY() == closestPredator.getY())) && xOrY == true)) {
					//Move through row away from predator
					if (this.getX() > closestPredator.getX()) {
						//Try to move right away from predator
						if (this.getX() < this.getSpeciesName().getMap().getXBound()-1) {
							//Move right away from predator
							direction = 4;
						} else if (this.getX() == this.getSpeciesName().getMap().getXBound()-1) {
							//Move up or down away from predator
							if ((this.getY() > (this.getSpeciesName().getMap().getYBound()-1)/2) && (this.getY() + this.getPerception() > this.getSpeciesName().getMap().getYBound())) {
								//Move up away from corner
								direction = 1;
							} else if ((this.getY() < (this.getSpeciesName().getMap().getYBound()-1)/2) && (this.getY() - this.getPerception() < 0)) {
								//Move down away from corner
								direction = 2;
							} else {
								//Randomly move up or down
								direction = random.nextInt(2)+1;
							}
						}
					} else {
						//Try to move left away from predator
						if (this.getX() > 0) {
							direction = 3;
						} else if (this.getX() == 0) {
							//Move up or down away from predator
							if ((this.getY() > (this.getSpeciesName().getMap().getYBound()-1)/2) && (this.getY() + this.getPerception() > this.getSpeciesName().getMap().getYBound())) {
								//Move up away from corner
								direction = 1;
							} else if ((this.getY() < (this.getSpeciesName().getMap().getYBound()-1)/2) && (this.getY() - this.getPerception() < 0)) {
								//Move down away from corner
								direction = 2;
							} else {
								//Randomly move up or down
								direction = random.nextInt(2)+1;
							}
						}
					}
				} else if (this.getX() == closestPredator.getX() && this.getY() == closestPredator.getY()) {
					//Randomly move away from square
					randomMovement = true;
				}
			} else {
				//Random movement
				randomMovement = true;
			}
			if (randomMovement == true) {
				direction = random.nextInt(4) + 1;
				if ((this.getX() == 0 && direction == 3) || (this.getX() == this.getSpeciesName().getMap().getXBound()-1 && direction == 4)) {
					//Move up or down away from square
					direction = random.nextInt(2)+1;
					if (this.getY() == 0 && direction == 1) {
						direction = 2;
					}
					if (this.getY() == this.getSpeciesName().getMap().getYBound()-1 && direction == 2) {
						direction = 1;
					}
				} 
				if ((this.getY() == 0 && direction == 1) || (this.getY() == this.getSpeciesName().getMap().getYBound()-1) && direction == 2) {
					//Move left or right away from square
					direction = random.nextInt(2)+3;
					if (this.getX() == 0 && direction == 3) {
						direction = 4;
					}
					if  (this.getX() == this.getSpeciesName().getMap().getXBound()-1 && direction == 4) {
						direction = 3;
					}
				}
			}
			
			switch (direction) {
			case 1:
				//Move up
				this.setY(this.getY() - 1);
				break;
			case 2:
				//Move down
				this.setY(this.getY() + 1);
				break;
			case 3:
				//Move left
				this.setX(this.getX() - 1);
				break;
			case 4:
				//Move right
				this.setX(this.getX() + 1);
				break;
			}
			this.getSpeciesName().getMap().locate(this);
		}
	}
	
	public void eat() {
		if (this.getIsAlive() == true) {
			if (this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().size() > 1) {
				int organismStrength = 2147483647;
				Organism weakestOrganism = null;
				for (int organism = 0; organism < this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().size(); organism++) {
					if ((this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism) instanceof Herbivore || this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism) instanceof Predator || this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism) instanceof Scavenger) && this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism).getIsAlive() == true && !(this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism) == this)) {
						if (this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism).getStrength() < organismStrength) {
							organismStrength = this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism).getStrength();
							weakestOrganism = this.getSpeciesName().getMap().getAcre(this.getX(), this.getY()).getOrganisms().get(organism);
						}
					}
				}
				
				if (!(weakestOrganism == null)) {
					if (this.getStrength() > weakestOrganism.getStrength()) {
						if (weakestOrganism.getEnergy() > (0.1 * (this.getStrength() - weakestOrganism.getStrength()))) {
							weakestOrganism.setEnergy(weakestOrganism.getEnergy() - (0.1 * (this.getStrength() - weakestOrganism.getStrength())));
							this.setNutrients(this.getNutrients() + (int)(weakestOrganism.getNutrients() * (0.1 * (this.getStrength() - weakestOrganism.getStrength()))));
							weakestOrganism.setNutrients((int)(weakestOrganism.getNutrients() * (1 - (0.1 * (this.getStrength() - weakestOrganism.getStrength())))));
						} else {
							weakestOrganism.setEnergy(0);
							this.setNutrients(this.getNutrients() + (int)(weakestOrganism.getNutrients() * (0.1 * this.getStrength())));
							weakestOrganism.setNutrients((int)(weakestOrganism.getNutrients() * (1 - (0.1 * this.getStrength()))));
						}
					} else if (this.getStrength() < weakestOrganism.getStrength()) {
						if (this.getEnergy() > (0.1 * (weakestOrganism.getStrength() - this.getStrength()))) {
							this.setEnergy(this.getEnergy() - (0.1 * (weakestOrganism.getStrength() - this.getStrength())));
							this.setNutrients(this.getNutrients() + (int)(weakestOrganism.getNutrients() * (0.1 / (weakestOrganism.getStrength() - this.getStrength()))));
							weakestOrganism.setNutrients((int)(weakestOrganism.getNutrients() * (1 - (0.1 / (weakestOrganism.getStrength() - this.getStrength())))));
						} else {
							this.setEnergy(0);
						}
					} else {
						this.setNutrients(this.getNutrients() + (int)(weakestOrganism.getNutrients() * 0.1));
						weakestOrganism.setNutrients((int)(weakestOrganism.getNutrients() * 0.9));
					}
				}
			}
		}
	}
	
	public void reproduce() {
		if (this.getIsAlive() == true) {
			if (this.getEnergy() > this.getEnergyToReproduce()) {
				for (int i = 0; i < this.getNumOffspring(); i++) {
					Random random = new Random();
					int mutation;
					
					Organism child = new Predator(this.getSpeciesName());
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
}