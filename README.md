# 17 623 Quality Assurance: Recitation 2 Fall 2023

*Work in Progress*

---

## Installation

**GitHub Codespaces**

1. In the current repository, go to `<> Code > Codespaces > Create codespace on main`. 
2. Wait until the container finishes building.
3. After the Codespaces open, wait until receiving the message that the setup is Done and the terminal closes.

![Building the final setup](.devcontainer/build-finish.png)

**Local Setup**

Follow the instructions presented in [Homework 4 - Static Analysis](https://canvas.cmu.edu/courses/36250/assignments/614274). This setup is not recommended for the purpose of the recitation (it may take sometime to setup).


## Content
```
.
├── README.md
├── projects/
    ├── infer/
    ├── pdm/
    └── spotbugs/
└── resources/              Presentation from the Recitation
```

# Exercises

### Exercise 1 (Infer): 

1. Open the classes `Student` and `App` and analyze what each one of them does.
2. In the terminal, change to the infer project directory (`projects/infer`).
3. Execute the following command in the terminal: `infer --bufferoverrun -- mvn clean package`
4. Analyze the output generated in the `infer-out` folder.


### Exercise 2 (PDM): 

1. Open .
2. Execute the command `pmd check -R ruleset.xml src/`.



### Exercise 3 (SpotBugs): 

