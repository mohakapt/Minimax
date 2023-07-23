# Advanced Tic-Tac-Toe AI: A Journey from Charmander to Charizard 🐲

Welcome to this unique exploration of the Minimax algorithm, presented through the lens of a Tic-Tac-Toe playing program. This project was crafted as a supplementary resource for an upcoming YouTube video on my channel, where we dive deep into the intricacies of game theory and AI algorithms, using the world of Pokémon as a fun metaphor.

## Table of Contents

- [Features](#features)
- [Versions](#versions)
- [Tech Stack](#tech-stack)
- [Contributions](#contributions)
- [Acknowledgements](#acknowledgements)
- [License](#license)

## Features

- **[Minimax Algorithm](https://en.wikipedia.org/wiki/Minimax):** The backbone of our AI, enabling it to make optimal decisions at every turn.

- **[Alpha Beta Pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning):** A refinement to the Minimax algorithm that helps the program make decisions faster.

- **Bit Boards:** A space-efficient method to represent the Tic-Tac-Toe board using binary representation. (Note: While bitboards are commonly used in board games like chess, a direct link for tic-tac-toe might not exist, so I'm leaving it without a link.)

- **Winning Combinations:** Utilizes binary operations to efficiently check for winning conditions. This rapid evaluation ensures a faster gameplay experience.

- **Prolong When Losing Strategy:** Delays the opponent's victory even when losing seems inevitable.

- **Transposition Table:** Optimizes searches by remembering past states.

- **Basic Strategy:** When the search tree becomes too large to be explored by Minimax in a reasonable time, this strategy helps the AI play good moves quickly. While these moves might not always be the best, they're generally very competitive.

- **Scalability:** Our AI can play Tic-Tac-Toe on any board size.

---

## Versions

**1. Charmander: 🔥**
- The starting point of our AI journey.
- Uses a basic Minimax algorithm.
- Can competently play on a 3x3 board and potentially handle a 4x4 board if given enough time.

**2. Charmeleon: 🔥🔥**
- An evolved form of our AI.
- Incorporates the Alpha Beta Pruning and Bit Boards for efficiency.
- Efficiently plays on a 4x4 board and can manage a 5x5 board with some patience.

**3. Charizard: 🔥🔥🔥**
- The ultimate version.
- Combines all the techniques mentioned above for the best performance.
- Capable of playing on boards up to 9x9 in size.

## Tech Stack

- **Language:** Kotlin
- **Algorithm:** Minimax
- **Optimizations:** Alpha Beta Pruning, Bit Boards, Transposition Table

## Contributions

Feel free to fork this project, submit PRs, and suggest any improvements or features you think might enhance the project.

## Acknowledgements

A big shoutout to my subscribers and the community for motivating me to create this project. I hope you find it useful and learn something new from it. If you have any questions, feel free to reach out to me on [Twitter](https://twitter.com/heysem_k).

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details.
