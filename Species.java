package PopSim;

import java.util.ArrayList;
import javax.swing.*;

public class Species {
	private String speciesName;
	private ArrayList<Organism> population;
	private int skillPoints;
	private int size;
	private Map map;
	private JLabel overview;
	private JLabel stats;
	private double averageSpeed;
	private double averagePerception;
	private double averageCamouflage;
	private double averageStrength;
	
	public Species(Map map, String name) {
		this.map = map;
		map.AddSpecies(this);
		this.speciesName = name;
		this.population = new ArrayList<Organism>();
		this.skillPoints = 5;
		averageSpeed = 0;
		averagePerception = 0;
		averageCamouflage = 0;
		averageStrength = 0;
		overview = new JLabel();overview.setLocation(5,(this.getMap().getSpecies().size()-1)*60+20);
		overview.setSize(500,20);
		this.getMap().getInfoPanel().add(overview);
		
		stats = new JLabel();
		stats.setLocation(5,(this.getMap().getSpecies().size()-1)*60+35);
		stats.setSize(500,20);
		this.getMap().getInfoPanel().add(stats);
	}
	public Species(String name) {
		this.speciesName = name;
		this.population = new ArrayList<Organism>();
		this.skillPoints = 5;
	}
	
	public void drawSpecies() {
		overview.setText(this.getSpeciesName()+": "+this.getSize()+" individuals");
		stats.setText("Speed: "+(double)Math.round(averageSpeed*100)/100+", Perception: "+(double)Math.round(averagePerception*100)/100+", Camouflage: "+(double)Math.round(averageCamouflage*100)/100+", Strength: "+(double)Math.round(averageStrength*100)/100);
		this.getMap().getInfoPanel().revalidate();
		this.getMap().getInfoPanel().repaint();
	}
	
	//Accessor Functions
	public String getSpeciesName() {
		return this.speciesName;
	}
	public int getSize() {
		return this.size;
	}
	public int getSkillPoints() {
		return this.skillPoints;
	}
	public Map getMap() {
		return this.map;
	}
	public ArrayList<Organism> getPopulation() {
		return this.population;
	}
	
	//Mutator Functions
	public void setName(String name) {
		this.speciesName = name;
	}
	public void setSkillPoints(int skillPoints) {
		if (skillPoints>=0) {
			this.skillPoints = skillPoints;
		}
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setAvgSpe() {
		int average = 0;
		for (int organism = 0; organism < this.getSize(); organism++) {
			average += this.getPopulation().get(organism).getSpeed();
		}
		this.averageSpeed = average/(double)this.getSize();
	}
	public void setAvgPer() {
		int average = 0;
		for (int organism = 0; organism < this.getSize(); organism++) {
			average += this.getPopulation().get(organism).getPerception();
		}
		this.averagePerception = average/(double)this.getSize();
	}
	public void setAvgCam() {
		int average = 0;
		for (int organism = 0; organism < this.getSize(); organism++) {
			average += this.getPopulation().get(organism).getCamouflage();
		}
		this.averageCamouflage = average/(double)this.getSize();
	}
	public void setAvgStr() {
		int average = 0;
		for (int organism = 0; organism < this.getSize(); organism++) {
			average += this.getPopulation().get(organism).getStrength();
		}
		this.averageStrength = average/(double)this.getSize();
	}
	public void grow(Organism organism) {
		this.getPopulation().add(organism);
		this.size++;
	}
	
	public String toString() {
		String s = "";
		s += this.getSpeciesName()+" - "+" Speed: "+Math.round(averageSpeed*100)/100+", Perception: "+Math.round(averagePerception*100)/100+", Camouflage: "+Math.round(averageCamouflage*100)/100+", Strength: "+Math.round(averageStrength*100)/100+", "+this.getPopulation().size()+" individuals: \n"+this.getPopulation();
		return s;
	}

}
