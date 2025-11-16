# 🎮 Super Mario JavaFX Game

A classic Super Mario-style 2D platformer game built with JavaFX, featuring smooth character animations, enemy AI, multiple levels, and engaging gameplay mechanics.

## 📋 Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Game Controls](#game-controls)
- [Gameplay](#gameplay)
- [Project Structure](#project-structure)
- [Building from Source](#building-from-source)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### Core Gameplay
- **Smooth Character Movement**: Fluid player controls with running and jumping mechanics
- **Multiple Playable Characters**: Choose from different character skins (Mario, Cuphead, Mugman, Chalice, Plane)
- **Dynamic Animations**: Character animations for running, jumping, falling, and dying states
- **Collision Detection**: Precise collision detection system for blocks, enemies, and items

### Game Elements
- **Various Enemy Types**: Multiple enemy types including Goombas, Koopas, Spiny, and boss fights (Bowser)
- **Interactive Blocks**: Mystery blocks, brick blocks, coin blocks, and pipes
- **Power-ups & Items**: Mushrooms, magic flowers, stars, and collectible coins
- **Multiple Game Levels**: Progress through different worlds and stages (Level 1-1 through Level 3-1)
- **Boss Battles**: Epic boss fights with unique attack patterns

### Audio & Visual
- **Background Music**: Immersive background music for menus and gameplay
- **Sound Effects**: Sound effects for jumps, damage, item collection, and more
- **Custom Graphics**: Hand-picked sprite sheets and background images
- **Smooth Animations**: Frame-by-frame sprite animations

### User Features
- **User Profiles**: Create and manage multiple user profiles
- **Save System**: Multiple save slots to track progress
- **Character Shop**: Unlock and purchase new character skins
- **Score Tracking**: Track scores across different game sessions
- **Pause Menu**: Pause functionality with settings options

## 📸 Screenshots

### Main Menu
![Main Menu](https://via.placeholder.com/800x450.png?text=Main+Menu+-+Coming+Soon)

### Gameplay
![Gameplay](https://via.placeholder.com/800x450.png?text=Gameplay+-+Coming+Soon)

### Character Selection
![Character Shop](https://via.placeholder.com/800x450.png?text=Character+Shop+-+Coming+Soon)

### Boss Fight
![Boss Fight](https://via.placeholder.com/800x450.png?text=Boss+Fight+-+Coming+Soon)

> **Note**: Screenshots coming soon! To add screenshots manually:
> 1. Take screenshots of the game
> 2. Save them in the `docs/screenshots/` folder with these names:
>    - `main-menu.png`, `gameplay.png`, `character-shop.png`, `boss-fight.png`
> 3. Update the image links above to use local paths

## 🛠 Technologies Used

- **Java 21**: Modern Java features and performance
- **JavaFX 21.0.5**: Rich UI framework for graphics and animations
- **Gradle 8.5**: Build automation and dependency management
- **Jackson 2.13.4**: JSON serialization for game data and user profiles
- **Java Modules**: Modular application structure (module-info.java)

## 📦 Prerequisites

Before running the game, ensure you have:

- **Java Development Kit (JDK) 21** or higher
  - Download from: https://adoptium.net/ or https://www.oracle.com/java/technologies/downloads/
- **Gradle 8.5** (optional - included via Gradle Wrapper)
- **Minimum System Requirements**:
  - OS: Windows 10/11, macOS 10.14+, or Linux
  - RAM: 4GB minimum, 8GB recommended
  - Graphics: OpenGL 2.0 compatible graphics card
  - Display: 1024x768 minimum resolution

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/koushamoeini/mario.git
cd mario
```

### 2. Verify Java Installation

```bash
java -version
```

You should see Java 21 or higher.

### 3. Make Gradle Wrapper Executable (Linux/Mac only)

```bash
chmod +x gradlew
```

## ▶️ How to Run

### Using Gradle Wrapper (Recommended)

**Windows:**
```bash
.\gradlew run
```

**Linux/Mac:**
```bash
./gradlew run
```

### Using Gradle (if installed globally)

```bash
gradle run
```

The game window will open automatically!

## 🎮 Game Controls

### Keyboard Controls
- **Arrow Keys** / **WASD**: Move character
  - `←` / `A`: Move left
  - `→` / `D`: Move right
  - `↑` / `W`: Jump
  - `↓` / `S`: Crouch/Sit
- **SPACE**: Shoot/Attack (when powered up)
- **ESC**: Pause game
- **ENTER**: Confirm selection in menus

### Menu Navigation
- Use mouse clicks to navigate menus
- Click buttons to select options
- Create profile or select existing save slots

## 🎯 Gameplay

### Objective
Navigate through various levels, defeat enemies, collect coins, and reach the end goal while avoiding obstacles and hazards.

### Game Mechanics
1. **Movement**: Use arrow keys to run and jump through levels
2. **Combat**: Jump on enemies to defeat them or use power-ups to shoot
3. **Power-ups**: 
   - **Mushroom**: Grow bigger and gain extra hit point
   - **Fire Flower**: Gain ability to shoot fireballs
   - **Star**: Temporary invincibility
4. **Coins**: Collect coins for points and extra lives
5. **Lives System**: Start with limited lives; lose one when taking damage
6. **Checkpoints**: Progress is saved at checkpoint flags

### Tips
- Jump on enemies from above to defeat them safely
- Collect power-ups to increase survivability
- Break brick blocks by hitting them from below
- Mystery blocks may contain coins or power-ups
- Watch out for gaps and falling hazards

## 📁 Project Structure

```
mario-pashe2/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java
│   │   │   └── com/example/mario/
│   │   │       ├── SuperMario.java          # Main application entry
│   │   │       ├── Mario/                   # Player character classes
│   │   │       ├── blocks/                  # Block and terrain classes
│   │   │       ├── enemies/                 # Enemy AI and behaviors
│   │   │       ├── Items/                   # Power-ups and collectibles
│   │   │       ├── levels/                  # Level definitions
│   │   │       ├── controllers/             # FXML controllers
│   │   │       ├── manager/                 # Game managers (JSON, Audio)
│   │   │       ├── user/                    # User profile system
│   │   │       ├── GameHandle/              # Game state handlers
│   │   │       └── Gun/                     # Projectile system
│   │   └── resources/
│   │       ├── com/example/mario/          # FXML layouts
│   │       ├── Images/                      # Sprite sheets and graphics
│   │       ├── Media/                       # Sound effects and music
│   │       ├── Font/                        # Custom fonts
│   │       └── GamaData/                    # Save data and configs
├── build.gradle                             # Gradle build configuration
├── settings.gradle                          # Gradle settings
├── gradlew                                  # Gradle wrapper (Unix)
├── gradlew.bat                              # Gradle wrapper (Windows)
└── README.md                                # This file
```

## 🔨 Building from Source

### Clean Build
```bash
.\gradlew clean build
```

### Compile Only
```bash
.\gradlew compileJava
```

### Run Tests
```bash
.\gradlew test
```

### Create JAR
```bash
.\gradlew jar
```

The JAR file will be created in `build/libs/`

### Create Distribution
```bash
.\gradlew jlink
```

This creates a standalone distribution in `build/distributions/`

### Common Build Issues & Solutions

#### Issue: "Java version mismatch"
**Solution**: Ensure you're using JDK 21+
```bash
# Check Java version
java -version
javac -version

# Set JAVA_HOME if needed (Windows)
setx JAVA_HOME "C:\Path\To\JDK21"

# Set JAVA_HOME if needed (Linux/Mac)
export JAVA_HOME=/path/to/jdk21
```

#### Issue: "Module not found" errors
**Solution**: Clean and rebuild the project
```bash
.\gradlew clean build --refresh-dependencies
```

#### Issue: "JavaFX runtime components are missing"
**Solution**: The project uses JavaFX plugin which handles dependencies automatically. Ensure you're running via Gradle:
```bash
.\gradlew run
```

### IDE Setup

#### IntelliJ IDEA
1. Open IntelliJ IDEA
2. Select `File` → `Open`
3. Navigate to the project directory
4. IntelliJ will automatically detect Gradle and import the project
5. Wait for dependency resolution
6. Run `SuperMario.java` main class

#### Eclipse
1. Import as Gradle project
2. Right-click project → `Gradle` → `Refresh Gradle Project`
3. Run as Java Application

#### VS Code
1. Install "Extension Pack for Java"
2. Open project folder
3. VS Code will auto-detect Gradle
4. Press F5 or use Run menu

## 🔮 Future Improvements

### Planned Features
- [ ] More levels and worlds
- [ ] Multiplayer mode
- [ ] Level editor
- [ ] More enemy types and boss fights
- [ ] Enhanced power-up system
- [ ] Leaderboard system
- [ ] Game settings (difficulty levels)
- [ ] Mobile/touch controls support
- [ ] Additional character abilities

### Technical Improvements
- [ ] Optimize resource loading
- [ ] Improve collision detection precision
- [ ] Add unit tests
- [ ] Refactor code for better maintainability
- [ ] Add documentation (JavaDoc)
- [ ] Performance profiling and optimization

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please follow the [Conventional Commits](https://www.conventionalcommits.org/) specification for commit messages.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- Original Super Mario Bros. by Nintendo for inspiration
- JavaFX community for excellent documentation
- OpenJFX for the graphics framework
- All contributors and testers

## 📧 Contact

**Author**: Kousha Moeini  
**Repository**: [github.com/koushamoeini/mario](https://github.com/koushamoeini/mario)

---

**Enjoy the game! 🎮✨**
