package PopSim;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Plant extends Organism {
	public Plant(Species species) {
		setSpeciesName(species);
		this.getSpeciesName().grow(this);
		setIsAlive(true);
		setOrganismID();
		setImage(new ImageIcon(new ImageIcon("plants.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setEnergy(0.5);
		setNutrients(100);
		setSpeed(0);
		setPerception(0);
		setCamouflage(3);
		setStrength(3);
		setNumberOfOffspring(5);
		setEnergyToReproduce(0.8);
	}
	public Plant(Species species, int speed, int perception, int camouflage, int strength) {
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
		setImage(new ImageIcon(new ImageIcon("plants.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		this.drawOrganism();
		setEnergy(0.5);
		setNutrients(50);
		setSpeed(speed);
		setPerception(perception);
		setCamouflage(camouflage);
		setStrength(strength);
		setNumberOfOffspring(2);
		setEnergyToReproduce(0.8);
	}
	
	public void eat() {
		if (this.getIsAlive() == true) {
			this.setNutrients(this.getNutrients() + (int)(this.getSpeciesName().getMap().getAcre(this.getX(),this.getY()).getNutrients()*this.getSpeciesName().getMap().getAcre(this.getX(),this.getY()).getSunlight()));
			this.getSpeciesName().getMap().getAcre(this.getX(),this.getY()).setNutrients((int)(this.getSpeciesName().getMap().getAcre(this.getX(),this.getY()).getNutrients()*(1-this.getSpeciesName().getMap().getAcre(this.getX(),this.getY()).getSunlight())));
		}
	}
	
	public void reproduce() {
		if (this.getIsAlive() == true) {
			if (this.getEnergy() > this.getEnergyToReproduce()) {
				for (int i = 0; i < this.getNumOffspring(); i++) {
					Random random = new Random();
					int mutation;
					
					Organism child = new Plant(this.getSpeciesName());
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
					
					child.setSpeed(this.getSpeed());
					child.setPerception(this.getPerception());
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
