### GameMap

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `GameMap` | new GameMap(boolean misterXPlayedByHuman) | Constructor for creating a GameMap|
| `boolean` | isFieldWithoutDetectives() | returns true, if a field is not occupied by a Detective |
| `List<Move>` | getLegalMoves(player Player, boolean isDetective) | returns all legal moves for the player |
| `HashMap <Integer,List<List<Integer>>>` | getGraph() | returns the graph (all possible Moves on the Board) |
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

### MisterX/Detective - Player

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `Player` | new Player(int id, Field Currentfield, boolean playedByHuman, HashMap<VehicleType,Integer> tickets) | Constructor for creating a Player|
| `int` | getId() | returns id (0 to 4 - 4 is MisterX) |
| `Field` | getCurrentField() | returns Field Player is standing on |
| `void` | setCurrentField(Field newField) | (automaticly done) |
| `Move` | getMove(GameMap gameMap) | returns the best Move |
| `void` | reduceTickets(VehicleType vt) | (automaticly done) |

### Move

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `Move` | new Move(Field startField,Field targetField,VehicleType vehicleType) | Constructor for creating a Move|
| `Field` | getStartField() | returns starting Field |
| `Field` | getTargetField() | returns landing Field |
| `VehicleTyp` | getVehicleTyp() | returns VehicleType for this Move |

### Field

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `Field` | new Field(int id) | Constructor for creating a Field|
| `int` | getId() | returns Field id |
| `boolean` | isOccupied() | returns wether the Field is occupied (true) or not (false) |
| `void` | setOccupiedTrue() | (automaticly done) |
| `void` | setOccupiedFalse() | (automaticly done) |
| `int` | getHeatMapCount() | returns the number of players on this field in this round  |
| `void` | raiseHeatMapCount() | (automaticly done) |

### VehicleType (ENUM)

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
|  |  | TAXI, BUS, TRAIN, SHIP, BLACK_TICKET

### GameState (ENUM)

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
|  |  | ONGOING, DETECTIVES_WIN, MISTERX_WIN

### Graph

| Type   | Definition                              | Notes                                                     |
|--------|-----------------------------------------|-----------------------------------------------------------|
| `HashMap <Integer,List<List<Integer>>>` | loadGraphFromCSV() | returns the Graph with all legal Moves. Mapping FieldId to all VehicleTypes (0-3) mapping to the next FieldId|



