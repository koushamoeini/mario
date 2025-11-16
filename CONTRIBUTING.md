# Contributing to Super Mario JavaFX Game

First off, thank you for considering contributing to this project! 🎉

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates.

When you create a bug report, include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples** (code snippets, screenshots)
- **Describe the behavior you observed** and what you expected
- **Include details about your environment**:
  - OS and version
  - Java version
  - Game version/commit

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion:

- **Use a clear and descriptive title**
- **Provide a detailed description** of the suggested enhancement
- **Explain why this enhancement would be useful**
- **List any similar features** in other games if applicable

### Pull Requests

1. Fork the repository
2. Create a new branch from `phase-2`:
   ```bash
   git checkout -b feature/my-new-feature
   ```
3. Make your changes
4. Write or update tests if applicable
5. Ensure the code compiles:
   ```bash
   .\gradlew clean build
   ```
6. Commit your changes using conventional commits:
   ```bash
   git commit -m "feat: add amazing feature"
   ```
7. Push to your fork:
   ```bash
   git push origin feature/my-new-feature
   ```
8. Open a Pull Request

## Coding Guidelines

### Java Style Guide

- Use **Java 21** features where appropriate
- Follow standard Java naming conventions:
  - Classes: `PascalCase`
  - Methods/variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
- Keep methods focused and under 50 lines when possible
- Add JavaDoc comments for public methods and classes

### Commit Message Format

We follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `perf`: Performance improvements
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Examples:**
```
feat(player): add double jump ability
fix(collision): resolve enemy overlap bug
docs(readme): update installation steps
refactor(levels): simplify level loading logic
```

### Code Review Process

1. At least one maintainer must review and approve the PR
2. All CI checks must pass
3. Code should follow the style guidelines
4. New features should include tests
5. Documentation should be updated if needed

## Project Structure

```
src/main/java/com/example/mario/
├── SuperMario.java          # Main application entry point
├── Mario/                   # Player character logic
├── blocks/                  # Block and terrain classes
├── enemies/                 # Enemy AI and behaviors
├── Items/                   # Power-ups and collectibles
├── levels/                  # Level definitions
├── controllers/             # FXML UI controllers
├── manager/                 # Game managers (JSON, Audio)
├── user/                    # User profile system
├── GameHandle/              # Game state handlers
└── Gun/                     # Projectile system
```

## Development Setup

1. Install JDK 21
2. Clone your fork
3. Open in your IDE (IntelliJ IDEA recommended)
4. Run `.\gradlew build` to download dependencies
5. Run the game with `.\gradlew run`

## Testing

Currently, the project has minimal test coverage. Adding tests for your contributions is highly appreciated!

```bash
# Run tests
.\gradlew test

# Run with coverage (if configured)
.\gradlew test jacocoTestReport
```

## Questions?

Feel free to open an issue with the `question` label or reach out to the maintainers.

---

Thank you for contributing! 🚀
