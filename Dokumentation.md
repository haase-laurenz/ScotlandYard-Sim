### GameBoard

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `GameMap` | `new GameMap(boolean misterXPlayedByHuman)` | Constructor for creating a GameMap|
| `boolean` | isFieldWithoutDetectives() | returns true, if a field is not occupied by a Detective |
| `List<Move>` | getLegalMoves(player Player, boolean isDetective) | returns all legal moves for the player |
| `Graph` | getGraph() | returns the graph (all possible Moves on the Board) |
| `List<Detective>` | getDetectives() | returns all Detectives |
| `int` | getRounds() | return current round |
| `Set<Field>` | getMisterXCloud() | returns all Fields MisterX could stand on |
| `GameState` | getGameState() | returns current GameState |
| `Player` | getCurrentplayer() | returns current Player |
| `List<Field>` | getLastMisterXFields() | returns all shown MisterX Fields |
| `List<VehicleType>` | getLastMisterXVehicleTypes() | returns all VehicleTypes MisterX has used |
| `void` | makeMove(Move move) | makes move on the Board |
| `void` | undoMove(Move move) | makes move backwards on the Board |
| `int` | distanceBetween(Field f1, Field f2, boolean isDetective) | returns the shortest amout of moves from f1 to f2 possible |

