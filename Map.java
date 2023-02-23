package PopSim;

import java.util.ArrayList;
import javax.swing.*;

public class Map {
	private Acre[][] grid;
	private int xSize;
	private int ySize;
	private JFrame frame;
	private JFrame infoPanel;
	private JLabel mapState;
	private ArrayList<Species> species;
	private int day;
	
	public Map(int x, int y) {
		this.xSize = x;
		this.ySize = y;
		grid = new Acre[ySize][xSize];
		for (int column = 0; column < ySize; column++) {
			for (int row = 0; row < xSize; row++) {
				Acre acre = new Acre(row, column);
				grid[column][row] = acre;
				acre.setMap(this);
			}
		}
		species = new ArrayList<Species>();
		this.day = 0;
		this.drawMap();
	}
	public void drawMap() {
		infoPanel = new JFrame("Info Panel");
		infoPanel.setLocation(10, 10);
		infoPanel.setSize(500,800);
		infoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		infoPanel.setResizable(true);
		infoPanel.setVisible(true);
		infoPanel.setLayout(null);
		
		mapState = new JLabel();
		mapState.setLocation(5,5);
		mapState.setSize(500,20);
		mapState.setText("Day: "+this.getDay());
		infoPanel.add(mapState);
		
		frame = new JFrame("PopSim Map");
		frame.setLocation(500,10);
		frame.setSize((xSize*50)+6,(ySize*50)+28);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLayout(null);
		
		for (int column = 0; column < ySize; column++) {
			for (int row = 0; row < xSize; row++) {
				this.getAcre(row, column).drawAcre(row, column);
			}
		}
	}
	
	public int getXBound() {
		return this.xSize;
	}
	public int getYBound() {
		return this.ySize;
	}
	public JFrame getFrame() {
		return this.frame;
	}
	public JFrame getInfoPanel() {
		return this.infoPanel;
	}
	public ArrayList<Species> getSpecies() {
		return this.species;
	}
	public Acre[][] getGrid() {
		return grid;
	}
	public Acre getAcre(int x, int y) {
		return this.grid[y][x];
	}
	public int getDay() {
		return this.day;
	}
	
	public void AddSpecies(Species species) {
		this.getSpecies().add(species);
	}
	
	public void locate(Organism organism) {
		organism.drawOrganism();
		this.grid[organism.getY()][organism.getX()].getOrganisms().add(organism);
	}
	public void remove(Organism organism) {
		if (organism.getIsAlive()) {
			this.getFrame().remove(organism.getLabel());
			organism.getLabel().setIcon(null);
			organism.setLabel(null);
		}
		this.grid[organism.getY()][organism.getX()].getOrganisms().remove(organism);
	}
	
	public void nextDay() {
		//organisms reproduce if they can
		//each organism loses a portion of their energy
		//all organisms moves
		//all organisms eat
		//all organism metabolize
		//each acre decomposes
		for (int species = 0; species < this.getSpecies().size(); species++) {
			for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
				this.getSpecies().get(species).getPopulation().get(organism).reproduce();
			}
		}
		for (int moves = 0; moves < 20; moves++) {
			for (int species = 0; species < this.getSpecies().size(); species++) {
				for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
					if (this.getSpecies().get(species).getPopulation().get(organism).getSpeed() > moves) {
						this.getSpecies().get(species).getPopulation().get(organism).move();
					}
				}
			}
		}
		for (int species = 0; species < this.getSpecies().size(); species++) {
			for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
				this.getSpecies().get(species).getPopulation().get(organism).eat();
			}
		}
		for (int species = 0; species < this.getSpecies().size(); species++) {
			for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
				this.getSpecies().get(species).getPopulation().get(organism).metabolize();
			}
		}
		for (int species = 0; species < this.getSpecies().size(); species++) {
			for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
				if (this.getSpecies().get(species).getPopulation().get(organism).getEnergy() > 0.1) {
					this.getSpecies().get(species).getPopulation().get(organism).setEnergy(this.getSpecies().get(species).getPopulation().get(organism).getEnergy() - 0.1);
				} else if (this.getSpecies().get(species).getPopulation().get(organism).getEnergy() > 0 && this.getSpecies().get(species).getPopulation().get(organism).getEnergy() <= 0.1) {
					this.getSpecies().get(species).getPopulation().get(organism).setEnergy(0);
				}
			}
		}
		for (int i = 0; i < this.getGrid().length; i++) {
			for (int j = 0; j < this.getGrid()[i].length; j++) {
				this.getGrid()[i][j].decompose();
			}
		}
		//Clean acres and species lists
		for (int species = 0; species < this.getSpecies().size(); species++) {
			for (int organism = 0; organism < this.getSpecies().get(species).getPopulation().size(); organism++) {
				if (this.getSpecies().get(species).getPopulation().get(organism).getEnergy() == 0 && this.getSpecies().get(species).getPopulation().get(organism).getNutrients() == 0) {
					this.remove(this.getSpecies().get(species).getPopulation().get(organism));
					this.getSpecies().get(species).getPopulation().remove(organism);
				}
			}
		}
		day++;
		mapState.setText("Day: "+this.getDay());
		infoPanel.revalidate();
		infoPanel.repaint();
		for (int species = 0; species < this.getSpecies().size(); species++) {
			this.getSpecies().get(species).setAvgSpe();
			this.getSpecies().get(species).setAvgPer();
			this.getSpecies().get(species).setAvgCam();
			this.getSpecies().get(species).setAvgStr();
			this.getSpecies().get(species).drawSpecies();
		}
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				s += grid[i][j]+"\t\t";
			}
			s += "\n";
		}
		return s;
	}
}
