package PopSim;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Acre {
	private ArrayList<Organism> organisms;
	private Map map;
	private String acreID;
	private int xCoordinate;
	private int yCoordinate;
	private double sunlight;
	private double microbes;
	private int nutrients;
	private JLabel label;
	private ImageIcon image;
	private static String[] images = new String[] {"jungle.jpg","savannah.jpg","desert.jpg","forest.jpg","prairie.jpg","tundra.jpg","taiga.jpg","marsh.jpg","ice.jpg"};
	
	public Acre(int x, int y) {
		Random random = new Random();
		
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.acreID = "("+x+","+y+")";
		this.organisms = new ArrayList<Organism>();
		this.sunlight = random.nextDouble()*(1-0.5)+0.5;
		this.microbes = random.nextDouble()*(1-0.5)+0.5;
		this.nutrients = random.nextInt(101)+500;
		/*http://spiralgraphics.biz/packs/terrain_lush/index.htm
		If high sunlight, high nutrients - jungle
		If high sunlight, medium nutrients - savannah
		If high sunlight, low nutrients - desert
		If medium sunlight, high nutrients - (temperate) forest
		If medium sunlight, medium nutrients - prairie (grassland)
		If medium sunlight, low nutrints - tundra
		If low sunlight, high nutrients - boreal forest
		If low sunlight, medium nutrients - marsh
		If low sunlight, low nutrients - artic/icy */
		String terrain = "";
		if (this.sunlight >= 0.83) {
			if (this.microbes >= 0.83) {
				terrain += images[0];
			} else if (this.microbes <= 0.67) {
				terrain += images[2];
			} else {
				terrain += images[1];
			}
		} else if (this.sunlight <= 0.67) {
			if (this.microbes >= 0.83) {
				terrain += images[6];
			} else if (this.microbes <= 0.67) {
				terrain += images[8];
			} else {
				terrain += images[7];
			}
		} else {
			if (this.microbes >= 0.83) {
				terrain += images[3];
			} else if (this.microbes <= 0.67) {
				terrain += images[5];
			} else {
				terrain += images[4];
			}
		}
		image = new ImageIcon(new ImageIcon(terrain).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		//System.out.println(this.getAcreID()+" "+terrain+" "+this.getSunlight()+"% sunlight "+this.getMicrobes()+"% microbes");
	}
	public void drawAcre(int row, int column) {
		//Setting up the label for row
		label = new JLabel(this.getAcreID());
		label.setSize(50, 50);
		label.setLocation(row*50, column*50);
		this.getMap().getFrame().add(label);
		
		//Drawing the picture
		label.setIcon(this.getImage());
		label.setLayout(new BorderLayout());
		//this.getMap().getFrame().revalidate();
		//this.getMap().getFrame().repaint();
	}
	
	public String getAcreID() {
		return this.acreID;
	}
	public ImageIcon getImage() {
		return this.image;
	}
	public JLabel getLabel() {
		return this.label;
	}
	public ArrayList<Organism> getOrganisms() {
		return this.organisms;
	}
	public double getSunlight() {
		return this.sunlight;
	}
	public double getMicrobes() {
		return this.microbes;
	}
	public int getNutrients() {
		return this.nutrients;
	}
	public Map getMap() {
		return this.map;
	}
	
	public void setSunlight(double sunlight) {
		this.sunlight = sunlight;
	}
	public void setMicrobes(double microbes) {
		this.microbes = microbes;
	}
	public void setNutrients(int nutrients) {
		this.nutrients = nutrients;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void decompose() {
		for (int organism = 0; organism < this.getOrganisms().size(); organism++) {
			if (this.getOrganisms().get(organism).getIsAlive() == false) {
				this.setNutrients((int)(this.getNutrients() + this.getOrganisms().get(organism).getNutrients()*this.getMicrobes()));
				this.getOrganisms().get(organism).setNutrients((int)(this.getOrganisms().get(organism).getNutrients()*(1-this.getMicrobes())));
				if (this.getOrganisms().get(organism).getNutrients() == 0) {
					this.getMap().remove(this.getOrganisms().get(organism));
				}
			}
		}
	}
	
	public String toString() {
		String s = "";
		s += "Acre"+acreID;
		//s += this.getNutrients()+" nutrients, "+this.getSunlight()*100+"% sunlight, "+this.getMicrobes()*100+"% microbes. ";
		for (int i = 0; i < organisms.size(); i++) {
			s += organisms.get(i).getSpeciesName().getSpeciesName();
			s += organisms.get(i).getOrganismID() +", ";
		}
		return s;
	}
}
