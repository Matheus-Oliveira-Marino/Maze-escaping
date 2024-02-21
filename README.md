# Maze Escaping :runner:

- [ ] Maze Escaping is a project developed using Java language where a character navigate through a labyrinth, aiming to find the exit.

## Feature

- [ ] The objective is to escape from the maze, starting from an entry point defined by "__*E*__" and reaching an exit point marked as "__*S*__". Walls are represented by "__*#*__". During the journey, if the character encounters multiple paths, they randomly choose which one to follow. If the selected option does not lead to "__*S*__" and there are walls all around, the character returns to the point where he initiated his choice decision and goes on by choosing another one.

 - [ ] Inside the `labyrinths for testing` folder, there are multiple files with the `.txt` extension representing various mazes to be constructed. However, not all of them are valid; some have, for instancce, more than one entrance or more than one exit, while others do not have any or only one of both, and so on. The files "__*Test1.txt*__" to "*__Test6.txt*__" are valid mazes for building. The user selects the file name using an object of the `Scanner` class. Through a `Buffered Reader`, the file choice in the pre-selected path is captured, and then the construction begins, followed by movement, represented by the `#` character.

## Quick Tips

- [ ] Download VSCode according to your OS by clicking [here.](https://code.visualstudio.com/download)


- [ ] Inside this [folder](https://github.com/Matheus-Oliveira-Marino/Maze-escaping/tree/main/project/src/labyrynths%20for%20testing) you can find ```.txt``` files the program reads, validating first and then, if is it correct, then building the maze and starting the movement. To select the ```.txt``` you want, 
go to ```src/Main.java``` then you will find this:

<br>

![Quick guide](https://github.com/Matheus-Oliveira-Marino/Maze-escaping/assets/139178883/65dea788-9351-4b5f-a07b-b41374dc150a)

<br>

> Change the `@path` to the location where the project is stored, up until you reach the `labyrinths for testing` folder, in the correct directory on your machine.
<br></br>


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
