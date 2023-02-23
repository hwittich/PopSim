package PopSim;

import java.util.Scanner;
import java.util.Random;

public class PopSimMain {
	//Print welcome message and explain the game
	//generate map (10x10 for now)
	//Ask the user if they would like to play in a loop
	//Explain the four different organism types
	//Ask the user what type of animal they would like to be
	//Open Jframe for species design
	//Jframe includes textField for them to name their species
	//increase and decrease buttons for all four traits. If plant, don't include speed and perception buttons
	//allow them to invest a certain amount of skill points into their organism's traits
	//click start button once they are done investing point
	//randomly generate other species
	//open up map window next to info tab.
	//Info tab lists the day
	//info tab lists each species average attributes of the species and (living) population size
	
	public static String isValid(String input, int choices, Scanner keyboard) {
		String response = input;
		try {
			if (!(Integer.parseInt(response) > 0 && Integer.parseInt(response) <= choices)) {
				System.out.println("Input is incorrect. Please enter a response from 1 to "+choices);
				response = keyboard.nextLine();
				isValid(response, choices, keyboard);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("You did not enter a number option. Please enter a response from 1 to "+choices);
			response = keyboard.nextLine();
			isValid(response, choices, keyboard);
		}
		return response;
	}
	public static void generatePlants(int amount, Map map) {
		Random random = new Random();
		Species computerSpecies;
		Organism computerOrganism;
		int plants = 1;
		while (plants <= amount) {
			computerSpecies = new Species(map,"pl"+plants+"-");
			computerOrganism = new Plant(computerSpecies,0,0,3,3);
			while (computerSpecies.getSkillPoints() > 0) {
				int attribute = random.nextInt(2)+3;
				switch (attribute) {
				case 1:
					computerOrganism.setSpeed(computerOrganism.getSpeed() + 1);
					break;
				case 2:
					computerOrganism.setPerception(computerOrganism.getPerception() + 1);
					break;
				case 3:
					computerOrganism.setCamouflage(computerOrganism.getCamouflage() + 1);
					break;
				case 4:
					computerOrganism.setStrength(computerOrganism.getStrength() + 1);
					break;
				}
				computerSpecies.setSkillPoints(computerSpecies.getSkillPoints() - 1);
			}
			plants++;
		}
	}
	public static void generateHerbivores(int amount, Map map) {
		Random random = new Random();
		Species computerSpecies;
		Organism computerOrganism;
		int herbivores = 1;
		while (herbivores <= amount) {
			computerSpecies = new Species(map,"h"+herbivores+"-");
			computerOrganism = new Herbivore(computerSpecies,3,3,3,2);
			while (computerSpecies.getSkillPoints() > 0) {
				int attribute = random.nextInt(4)+1;
				switch (attribute) {
				case 1:
					computerOrganism.setSpeed(computerOrganism.getSpeed() + 1);
					break;
				case 2:
					computerOrganism.setPerception(computerOrganism.getPerception() + 1);
					break;
				case 3:
					computerOrganism.setCamouflage(computerOrganism.getCamouflage() + 1);
					break;
				case 4:
					computerOrganism.setStrength(computerOrganism.getStrength() + 1);
					break;
				}
				computerSpecies.setSkillPoints(computerSpecies.getSkillPoints() - 1);
			}
			herbivores++;
		}
	}
	public static void generatePredators(int amount, Map map) {
		Random random = new Random();
		Species computerSpecies;
		Organism computerOrganism;
		int predators = 1;
		while (predators <= amount) {
			computerSpecies = new Species(map,"pr"+predators+"-");
			computerOrganism = new Predator(computerSpecies,3,3,3,3);
			while (computerSpecies.getSkillPoints() > 0) {
				int attribute = random.nextInt(4)+1;
				switch (attribute) {
				case 1:
					computerOrganism.setSpeed(computerOrganism.getSpeed() + 1);
					break;
				case 2:
					computerOrganism.setPerception(computerOrganism.getPerception() + 1);
					break;
				case 3:
					computerOrganism.setCamouflage(computerOrganism.getCamouflage() + 1);
					break;
				case 4:
					computerOrganism.setStrength(computerOrganism.getStrength() + 1);
					break;
				}
				computerSpecies.setSkillPoints(computerSpecies.getSkillPoints() - 1);
			}
			predators++;
		}
	}
	public static void generateScavengers(int amount, Map map) {
		Random random = new Random();
		Species computerSpecies;
		Organism computerOrganism;
		int scavengers = 1;
		while (scavengers <= 3) {
			computerSpecies = new Species(map,"s"+scavengers+"-");
			computerOrganism = new Scavenger(computerSpecies,3,3,3,2);
			while (computerSpecies.getSkillPoints() > 0) {
				int attribute = random.nextInt(4)+1;
				switch (attribute) {
				case 1:
					computerOrganism.setSpeed(computerOrganism.getSpeed() + 1);
					break;
				case 2:
					computerOrganism.setPerception(computerOrganism.getPerception() + 1);
					break;
				case 3:
					computerOrganism.setCamouflage(computerOrganism.getCamouflage() + 1);
					break;
				case 4:
					computerOrganism.setStrength(computerOrganism.getStrength() + 1);
					break;
				}
				computerSpecies.setSkillPoints(computerSpecies.getSkillPoints() - 1);
			}
			scavengers++;
		}
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String response;	
		System.out.println("Welcome to PopSim!");
		System.out.println("This program is a population simulator.\nDesign your own species and try to survive in "
				+ "this computationally generated ecosystem!");
		System.out.println("Basic explanation of stats and mechanisms.");
		System.out.println("Would you like to play?\n1: Yes\n2: No");
		response  = keyboard.nextLine();
		response = isValid(response, 2, keyboard);
		
		if (response.equals("1")) {
			System.out.println("Now you have to choose what type of organism you want to play as.");
			System.out.println("Plant description.");
			System.out.println("Herbivore description.");
			System.out.println("Predator description.");
			System.out.println("Scavenger description.");
			System.out.println("Which organism type would you like to play as?\n1: Plant\n2: Herbivore\n3: Predator\n4: Scavenger");
			response = keyboard.nextLine();
			response = isValid(response, 4, keyboard);
			
			Setup setupFrame = new Setup(response);
			while (!(setupFrame.getStart()==true)) {
				System.out.print("");
			}
			
			Map map = new Map(10,10);
			Species playerSpecies = new Species(map, setupFrame.getModelSpecies().getSpeciesName());
			Organism playerOrganism = null;
			switch (response) {
			case "1":
				playerOrganism = new Plant(playerSpecies,setupFrame.getModelOrganism().getSpeed(),setupFrame.getModelOrganism().getPerception(),setupFrame.getModelOrganism().getCamouflage(),setupFrame.getModelOrganism().getStrength());
				//generating computer species
				generatePlants(2,map);
				generateHerbivores(3,map);
				generatePredators(3,map);
				generateScavengers(3,map);
				break;
			case "2":
				playerOrganism = new Herbivore(playerSpecies,setupFrame.getModelOrganism().getSpeed(),setupFrame.getModelOrganism().getPerception(),setupFrame.getModelOrganism().getCamouflage(),setupFrame.getModelOrganism().getStrength());
				//generating computer species
				generatePlants(3,map);
				generateHerbivores(2,map);
				generatePredators(3,map);
				generateScavengers(3,map);
				break;
			case "3":
				playerOrganism = new Predator(playerSpecies,setupFrame.getModelOrganism().getSpeed(),setupFrame.getModelOrganism().getPerception(),setupFrame.getModelOrganism().getCamouflage(),setupFrame.getModelOrganism().getStrength());
				//generating computer species
				generatePlants(3,map);
				generateHerbivores(3,map);
				generatePredators(2,map);
				generateScavengers(3,map);
				break;
			case "4":
				playerOrganism = new Scavenger(playerSpecies,setupFrame.getModelOrganism().getSpeed(),setupFrame.getModelOrganism().getPerception(),setupFrame.getModelOrganism().getCamouflage(),setupFrame.getModelOrganism().getStrength());
				//generating computer species
				generatePlants(3,map);
				generateHerbivores(3,map);
				generatePredators(3,map);
				generateScavengers(2,map);
				break;
			}
			//running the game
			boolean nextDay = true;
			for (int species = 0; species < map.getSpecies().size(); species++) {
				map.getSpecies().get(species).setAvgSpe();
				map.getSpecies().get(species).setAvgPer();
				map.getSpecies().get(species).setAvgCam();
				map.getSpecies().get(species).setAvgStr();
				map.getSpecies().get(species).drawSpecies();
			}
			while (nextDay == true) {
				System.out.println("Press enter to advance to the next day.");
				keyboard.nextLine();
				map.nextDay();
				if (playerSpecies.getSize() == 0) {
					nextDay = false;
					System.out.println("Your species went extinct!");
				}
			}
		}
		keyboard.close();
	}
}
