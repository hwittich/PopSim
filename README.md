# PopSim
A population simulator created as a final project for COMP170 - Object Oriented Programming With Dr. Eric Chan-Tin at Loyola University Chicago. This project was written with Java using the IDE, Eclipse. I have included all of the .java files I wrote for this project in this repo.

## Introduction

In 1859, Charles Darwin proposed his theory of natural selection in his work, On the Origin of Species. Natural selection, according to Darwin is the evolutionary process by which the pressure of survival put on a population of organisms selects for traits that improve an organism’s ability to reproduce and pass on that trait. 

Darwin’s theory frames organismal interactions as a constant struggle for resources, where only the most fit animals survive. This program attempts to simulate Darwin’s theory of natural selection. Users are tasked with designing an organism, according to set parameters, that will successfully survive in a randomly generated ecosystem, with limited resource availability and competition from other organisms. In this way, PopSim serves as both a fun game and a useful educational tool for conceptualizing natural selection and other principals of population biology.

## Game Overview

At the start of the game, the player will choose a class of organism (plant, primary consumer, predator, scavenger), which will determine the base stats for their organism. From there, they can invest a set amount of skill points into various attributes that affect their organism’s ability to survive (say, defense, health, stealth, perception). Once they have finished customizing their organism, the program will generate a grid-based map and place your organisms at randomized locations, along with resources and other randomly generated organisms. 

During gameplay, the program will loop through days, with each organism making it’s moves at the beginning of each day. After moving, an organism might attack another nearby organism or consume a nearby resource. Finally, at the end of the day, organisms will reproduce if possible. After many iterations, the population structure of the species within the ecosystem should shift until, eventually, certain organisms come out on top.

## Stretch Goals
- Game modes (timed mode where goal is to stay alive in certain time vs. infinite mode where goal is to come out on top of food chain)    
- Climate Change! Or just natural disasters (random occurrences that improve replayability like droughts, rising sea levels, forest fires, etc.)   
- Biomes (user-selected ecosystems with different parameters for randomly generating resources/organisms)    
- Data and graphs! (part of the UI that will help the user conceptualize the current state of the ecosystem and their organism’s success like population levels, resources levels, etc.)

## Demo
![PopSim Screenshot](https://github.com/hwittich/PopSim/blob/main/PopSim%20Demo.png?raw=true)
