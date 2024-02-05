## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Running the project

```
/usr/bin/env /Library/Java/JavaVirtualMachines/jdk-17.0.2.jdk/Contents/Home/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp $HOME/Library/Application\ Support/Code/User/workspaceStorage/2f09ce04a4ec3139360fad4effb1b743/redhat.java/jdt_ws/crafting-interpreters-turf_2c293026/bin com.craftinginterpretersturf.lox.Lox
```

## Generating a new AST

```
/usr/bin/env /Library/Java/JavaVirtualMachines/jdk-17.0.2.jdk/Contents/Home/bin/java -XX:+ShowCodeDetailsInExceptionMessages -cp $HOME/Library/Application\ Support/Code/User/workspaceStorage/2f09ce04a4ec3139360fad4effb1b743/redhat.java/jdt_ws/crafting-interpreters-turf_2c293026/bin com.craftinginterpretersturf.tool.GenerateAst turf-java/src/com/craftinginterpretersturf/lox
```
