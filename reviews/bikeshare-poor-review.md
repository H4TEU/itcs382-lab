Files read: bikeshare-poor/src/main/java/bikeshare/Main.java

| No. | Check | Code location (file:line) | Finding | Severity (Must fix, Should fix or Note) | Suggested fix |
|---:|---|---|---|---|---|
| 1 | Code quality | bikeshare-poor/src/main/java/bikeshare/Main.java:28-100 | The main method is a long command loop that mixes input parsing, command dispatch, business logic, and persistence; it is hard to read and hard to test. | Must fix | Split the command loop into smaller methods or a command handler class. |
| 2 | Code quality | bikeshare-poor/src/main/java/bikeshare/Main.java:94-97 | The catch-all `Exception` handler hides the real fault and turns all failures into a generic message, making debugging and recovery difficult. | Should fix | Catch only the specific exceptions you expect and let unexpected errors surface. |
| 3 | Code quality | bikeshare-poor/src/main/java/bikeshare/Main.java:18-19 | The static collections are public and mutable, so any caller can add, remove, or replace bikes and rides without going through validation. | Must fix | Make the collections private and expose controlled accessors or update methods. |
| 4 | Code quality | bikeshare-poor/src/main/java/bikeshare/Main.java:160-165 | The `Bike` state is exposed through public fields (`id`, `station`, `rented`, `memberId`, `memberType`), so callers can violate the rental invariant from anywhere. | Must fix | Make the fields private and provide explicit rent/return methods that maintain the state. |
| 5 | Cohesion | bikeshare-poor/src/main/java/bikeshare/Main.java:13-175 | `Main` is a god class: it contains the CLI, bike state management, fee logic, and file persistence, so it represents more than one responsibility. | Must fix | Move the bike-rental logic and persistence into separate domain and service classes. |
| 6 | Cohesion | bikeshare-poor/src/main/java/bikeshare/Main.java:28-100 | `main` has procedural cohesion because it performs a sequence of unrelated actions in one method: read input, parse commands, update bike state, log rides, and print output. | Should fix | Extract each command action into a dedicated method or separate class. |
| 7 | Connascence | bikeshare-poor/src/main/java/bikeshare/Main.java:45-56, bikeshare-poor/src/main/java/bikeshare/Main.java:73-83 | Several `Bike` fields (`rented`, `memberId`, `memberType`, `station`) must be updated together to maintain a valid state, but they are public and manipulated in multiple places; this is connascence of convention/position. | Should fix | Encapsulate the bike state in a method that updates all related fields together. |

Number of findings for each check:
- Code quality: 4
- Cohesion: 2
- Coupling: 0
- Connascence: 1

Not found: `==` compared with `equals()`, unused imports, unused fields/parameters, empty blocks, and custom interactions tighter than data coupling.