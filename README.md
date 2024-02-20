# Maze Escaping

- [ ] Maze Escaping is a project developed using Java language where players navigate through a labyrinth, aiming to find the exit.

## Goal

- [ ] The objective is to escape from the maze, starting from an entry point defined by "__*E*__" and reaching an exit point marked as "__*S*__". Walls are represented by "__*#*__". During the journey, if the character encounters multiple paths, they randomly choose which one to follow. If the selected option does not lead to "__*S*__" and there are walls all around, the character returns to the point where he initiated his choice decision and goes on by choosing another one.

## Quick Tips

- [ ] Download VSCode according to your OS by clicking [here.](https://code.visualstudio.com/download)


- [ ] Inside this [folder](https://github.com/Matheus-Oliveira-Marino/Maze-escaping/tree/main/project/src/labyrynths%20for%20testing) you can find ```.txt``` files the program reads, validating first and then, if is it correct, then building the maze and starting the movement. To select the ```.txt``` you want, 
go to ```src/Main.java``` then you will find this: 

![Quick guide](https://github.com/Matheus-Oliveira-Marino/Maze-escaping/assets/139178883/65dea788-9351-4b5f-a07b-b41374dc150a)


<p align = "center"> <code> Change the ```@path``` where the project is located until you get to ```labyrynths for testing```
right location in your machine.
  </code>
</p>

* Windows: "`your drive letter`:\\Users\\`your user`\\```where you saved this project```\\Maze escaping\\project\\src\\labyrynths for testing\\"

* Linux: "/home/your user/```where you saved this project```/Maze escaping/project/src/labyrinths for testing/"

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

eanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
