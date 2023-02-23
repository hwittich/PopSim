package PopSim;

import java.awt.event.*;
import javax.swing.*;

public class Setup {
	private JFrame characterDesign;
	private JLabel message;
	private Species modelSpecies = new Species("model");
	private Organism modelOrganism;
	private JTextField inputName;
	private String attribute = "";
	private JButton increaseSpeed;
	private JButton decreaseSpeed;
	private JLabel spe;
	private JButton increasePerception;
	private JButton decreasePerception;
	private JLabel per;
	private JButton increaseCamouflage;
	private JButton decreaseCamouflage;
	private JLabel cam;
	private JButton increaseStrength;
	private JButton decreaseStrength;
	private JLabel str;
	private JButton startButton;
	private boolean start;
	
	public Setup(String organismType) {
		switch (organismType) {
		case "1":
			modelOrganism = new Plant(modelSpecies);
			//modelOrganism.setSpeed(0);
			//modelOrganism.setPerception(0);
			//modelOrganism.setCamouflage(3);
			//modelOrganism.setStrength(3);
			break;
		case "2":
			modelOrganism = new Herbivore(modelSpecies);
			//modelOrganism.setSpeed(3);
			//modelOrganism.setPerception(3);
			//modelOrganism.setCamouflage(3);
			//modelOrganism.setStrength(2);
			break;
		case "3":
			modelOrganism = new Predator(modelSpecies);
			//modelOrganism.setSpeed(3);
			//modelOrganism.setPerception(3);
			//modelOrganism.setCamouflage(3);
			//modelOrganism.setStrength(3);
			break;
		case "4":
			modelOrganism = new Scavenger(modelSpecies);
			//modelOrganism.setSpeed(3);
			//modelOrganism.setPerception(3);
			//modelOrganism.setCamouflage(3);
			//modelOrganism.setStrength(2);
			break;
		}
		
		start = false;
		//Designing the window
		characterDesign = new JFrame("Design your species!");
		characterDesign.setLocation(0, 0);
		characterDesign.setSize(520,250);
		characterDesign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		characterDesign.setResizable(false);
		characterDesign.setLayout(null);
		
		message = new JLabel();
		message.setLocation(5,5);
		message.setSize(450,20);
		message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
		characterDesign.add(message);
		
		inputName = new JTextField("Name your species.", 20);
		inputName.setLocation(5,30);
		inputName.setSize(480,20);
		characterDesign.add(inputName);
		
		spe = new JLabel();
		spe.setLocation(5,60);
		spe.setSize(285,20);
		attribute = "Speed:           [";
		for (int sp = 0; sp < modelOrganism.getSpeed(); sp++) {
			attribute += "   ";
		}
		attribute += "]"+modelOrganism.getSpeed();
		spe.setText(attribute);
		characterDesign.add(spe);
		
		per = new JLabel();
		per.setLocation(5,90);
		per.setSize(285,20);
		attribute = "Perception:   [";
		for (int p = 0; p < modelOrganism.getPerception(); p++) {
			attribute += "   ";
		}
		attribute += "]"+modelOrganism.getPerception();
		per.setText(attribute);
		characterDesign.add(per);
		
		cam = new JLabel();
		cam.setLocation(5,120);
		cam.setSize(285,20);
		attribute = "Camouflage: [";
		for (int c = 0; c < modelOrganism.getCamouflage(); c++) {
			attribute += "   ";
		}
		attribute += "]"+modelOrganism.getCamouflage();
		cam.setText(attribute);
		characterDesign.add(cam);
		
		str = new JLabel();
		str.setLocation(5,150);
		str.setSize(285,20);
		attribute = "Strength:       [";
		for (int st = 0; st < modelOrganism.getStrength(); st++) {
			attribute += "   ";
		}
		attribute += "]"+modelOrganism.getStrength();
		str.setText(attribute);
		characterDesign.add(str);
		
		if (organismType.equals("2") || organismType.equals("3") || organismType.equals("4")) {
			increaseSpeed = new JButton("Increase");
			increaseSpeed.setLocation(295,60);
			increaseSpeed.setSize(100,20);
			increaseSpeed.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					if (modelOrganism.getSpeciesName().getSkillPoints() > 0) {
						modelOrganism.setSpeed(modelOrganism.getSpeed() + 1);
						modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() - 1);
					}
					message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
					String attribute = "";
					attribute = "Speed:           [";
					for (int sp = 0; sp < modelOrganism.getSpeed(); sp++) {
						attribute += "   ";
					}
					attribute += "]"+modelOrganism.getSpeed();
					spe.setText(attribute);
					characterDesign.add(spe);
					characterDesign.revalidate();
					characterDesign.repaint();
				} 
			} );
			characterDesign.add(increaseSpeed);
			
			decreaseSpeed = new JButton("Decrease");
			decreaseSpeed.setLocation(405,60);
			decreaseSpeed.setSize(100,20);
			decreaseSpeed.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					if (modelOrganism.getSpeed() > 0) {
						modelOrganism.setSpeed(modelOrganism.getSpeed() - 1);
						modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() + 1);
					}
					message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
					String attribute = "";
					attribute = "Speed:           [";
					for (int sp = 0; sp < modelOrganism.getSpeed(); sp++) {
						attribute += "   ";
					}
					attribute += "]"+modelOrganism.getSpeed();
					spe.setText(attribute);
					characterDesign.add(spe);
					characterDesign.revalidate();
					characterDesign.repaint();
				} 
			} );
			characterDesign.add(decreaseSpeed);
			
			increasePerception = new JButton("Increase");
			increasePerception.setLocation(295,90);
			increasePerception.setSize(100,20);
			increasePerception.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					if (modelOrganism.getSpeciesName().getSkillPoints() > 0) {
						modelOrganism.setPerception(modelOrganism.getPerception() + 1);
						modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() - 1);
					}
					message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
					String attribute = "";
					attribute = "Perception:   [";
					for (int p = 0; p < modelOrganism.getPerception(); p++) {
						attribute += "   ";
					}
					attribute += "]"+modelOrganism.getPerception();
					per.setText(attribute);
					characterDesign.add(per);
					characterDesign.revalidate();
					characterDesign.repaint();
				} 
			} );
			characterDesign.add(increasePerception);
			
			decreasePerception = new JButton("Decrease");
			decreasePerception.setLocation(405,90);
			decreasePerception.setSize(100,20);
			decreasePerception.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					if (modelOrganism.getPerception() > 0) {
						modelOrganism.setPerception(modelOrganism.getPerception() - 1);
						modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() + 1);
					}
					message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
					String attribute = "";
					attribute = "Perception:   [";
					for (int p = 0; p < modelOrganism.getPerception(); p++) {
						attribute += "   ";
					}
					attribute += "]"+modelOrganism.getPerception();
					per.setText(attribute);
					characterDesign.add(per);
					characterDesign.revalidate();
					characterDesign.repaint();
				} 
			} );
			characterDesign.add(decreasePerception);
		}			
		increaseCamouflage = new JButton("Increase");
		increaseCamouflage.setLocation(295,120);
		increaseCamouflage.setSize(100,20);
		increaseCamouflage.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (modelOrganism.getSpeciesName().getSkillPoints() > 0) {
					modelOrganism.setCamouflage(modelOrganism.getCamouflage() + 1);
					modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() - 1);
				}
				message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
				String attribute = "";
				attribute = "Camouflage: [";
				for (int c = 0; c < modelOrganism.getCamouflage(); c++) {
					attribute += "   ";
				}
				attribute += "]"+modelOrganism.getCamouflage();
				cam.setText(attribute);
				characterDesign.add(cam);
				characterDesign.revalidate();
				characterDesign.repaint();
			} 
		} );
		characterDesign.add(increaseCamouflage);
		
		decreaseCamouflage = new JButton("Decrease");
		decreaseCamouflage.setLocation(405,120);
		decreaseCamouflage.setSize(100,20);
		decreaseCamouflage.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (modelOrganism.getCamouflage() > 0) {
					modelOrganism.setCamouflage(modelOrganism.getCamouflage() - 1);
					modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() + 1);
				}
				message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
				String attribute = "";
				attribute = "Camouflage: [";
				for (int c = 0; c < modelOrganism.getCamouflage(); c++) {
					attribute += "   ";
				}
				attribute += "]"+modelOrganism.getCamouflage();
				cam.setText(attribute);
				characterDesign.add(cam);
				characterDesign.revalidate();
				characterDesign.repaint();
			} 
		} );
		characterDesign.add(decreaseCamouflage);
		
		increaseStrength = new JButton("Increase");
		increaseStrength.setLocation(295,150);
		increaseStrength.setSize(100,20);
		increaseStrength.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (modelOrganism.getSpeciesName().getSkillPoints() > 0) {
					modelOrganism.setStrength(modelOrganism.getStrength() + 1);
					modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() - 1);
				}
				message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
				String attribute = "";
				attribute = "Strength:       [";
				for (int st = 0; st < modelOrganism.getStrength(); st++) {
					attribute += "   ";
				}
				attribute += "]"+modelOrganism.getStrength();
				str.setText(attribute);
				characterDesign.add(str);
				characterDesign.revalidate();
				characterDesign.repaint();
			} 
		} );
		characterDesign.add(increaseStrength);
		
		decreaseStrength = new JButton("Decrease");
		decreaseStrength.setLocation(405,150);
		decreaseStrength.setSize(100,20);
		decreaseStrength.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (modelOrganism.getStrength() > 0) {
					modelOrganism.setStrength(modelOrganism.getStrength() - 1);
					modelOrganism.getSpeciesName().setSkillPoints(modelOrganism.getSpeciesName().getSkillPoints() + 1);
				}
				message.setText("Design the attributes of your progenitor organism. You can invest "+modelOrganism.getSpeciesName().getSkillPoints()+" skill points:");
				String attribute = "";
				attribute = "Strength:       [";
				for (int st = 0; st < modelOrganism.getStrength(); st++) {
					attribute += "   ";
				}
				attribute += "]"+modelOrganism.getStrength();
				str.setText(attribute);
				characterDesign.add(str);
				characterDesign.revalidate();
				characterDesign.repaint();
			} 
		} );
		characterDesign.add(decreaseStrength);

		startButton = new JButton("Start!");
		startButton.setLocation(5,180);
		startButton.setSize(100,20);
		startButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				modelSpecies.setName(inputName.getText());
				characterDesign.dispose();
				start = true;
				//System.out.println("Start program");
			} 
		} );
		characterDesign.add(startButton);
		
		characterDesign.setVisible(true);
	}
	
	public boolean getStart() {
		return this.start;
	}
	public Species getModelSpecies() {
		return this.modelSpecies;
	}
	public Organism getModelOrganism() {
		return this.modelOrganism;
	}
}
