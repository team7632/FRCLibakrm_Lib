![](https://github.com/team7632/FRC7632-2026-season/blob/main/254167164.png)
[![Discord](https://img.shields.io/discord/SERVER_ID?style=for-the-badge&logo=discord)](https://discord.gg/QYT3Y2aJ)
[![Instagram](https://img.shields.io/badge/Instagram-FRC%207632-E4405F?logo=instagram&logoColor=purple)](https://www.instagram.com/frc_team_7632/)
[![Facebook](https://img.shields.io/badge/Facebook-FRC%207632-1877F2?logo=facebook&logoColor=blue)](https://www.facebook.com/FRCTeam7632)



# FRC7632 – 2026 Season Robot Code

This repository contains the official **FRC Team 7632** robot code for the **2026 competition season**.

## Project Overview

- **Team**: FRC 7632  
- **Season**: 2026  
- **Language**: Java  
- **Framework**: WPILib (Command-Based)  
- **Version Control**: Git / GitHub  

This codebase is structured using WPILib’s command-based framework to ensure modularity, readability, and ease of testing.

---

## Code Rules

To maintain code quality, readability, and long-term maintainability, all contributors should follow these rules:

### General Rules
- Use **clear and descriptive names** for classes, variables, and methods
- Keep code **readable over clever**
- Follow standard **Java naming conventions**
- Remove unused code and commented-out blocks before committing

### Command-Based Architecture
- **Subsystems**
  - Only contain hardware definitions and low-level methods
  - No button bindings or driver logic
- **Commands**
  - Contain robot behavior and decision-making
  - Should do **one thing well**
- **RobotContainer**
  - All controller bindings must be defined here
  - No hardware initialization logic

### Naming & Code Style Rules

To keep the codebase consistent and easy to read, follow the naming conventions below.

#### Subsystems
- Subsystem class names use **camelCase**
- Subsystem variables and methods use **camelCase**

---

#### Commands
- Command class names use **PascalCase**
- Command variables and methods use **camelCase**
- Commands should clearly describe an action

---

#### Constants
- Use **UPPER_SNAKE_CASE**
- Constants must be placed in the `constants` package

---

#### Package Names
- Use **lowercase**
- No underscores or capital letters

---



# FRCLibakrm_Lib
