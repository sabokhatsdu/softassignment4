import java.util.HashMap;
import java.util.Map;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

class Wall {

}

class DoorWall extends Wall {
    private Room r1;
    private Room r2;
    private boolean isOpen;

    public DoorWall(Room r1, Room r2) {
        this.r1 = r1;
        this.r2 = r2;
        this.isOpen = false;
    }

    public Room getOtherRoom(Room currentRoom) {
        if (currentRoom == r1) {
            return r2;
        } else {
            return r1;
        }
    }
}

class Room {
    private Map<Direction, Wall> sides = new HashMap<>();
    private int roomNo;

    public Room(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public Wall getSide(Direction direction) {
        return sides.get(direction);
    }

    public void setSide(Direction direction, Wall wall) {
        sides.put(direction, wall);
    }
}

class Maze {
    private Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(Room r) {
        rooms.put(r.getRoomNo(), r);
    }

    public Room roomNo(int r) {
        return rooms.get(r);
    }

    public Iterable<Integer> roomNumbers() {
        return rooms.keySet();
    }
}

interface MazeBuilder {
    void buildRooms();

    Maze getMaze();
}

class OldMazeBuilder implements MazeBuilder {
    private Maze maze;

    public OldMazeBuilder() {
        this.maze = new Maze();
    }

    @Override
    public void buildRooms() {
        Room r1 = new Room(1);
        Room r2 = new Room(2);
        DoorWall d = new DoorWall(r1, r2);

        maze.addRoom(r1);
        maze.addRoom(r2);

        r1.setSide(Direction.NORTH, d);
        r1.setSide(Direction.EAST, new Wall());
        r1.setSide(Direction.SOUTH, new Wall());
        r1.setSide(Direction.WEST, new Wall());
        r2.setSide(Direction.NORTH, new Wall());
        r2.setSide(Direction.EAST, new Wall());
        r2.setSide(Direction.SOUTH, d);
        r2.setSide(Direction.WEST, new Wall());
    }

    @Override
    public Maze getMaze() {
        return maze;
    }
}

class MazeGame {
    public static void main(String[] argv) {
        Maze maze = createMaze(new OldMazeBuilder()); // Creating the old game
        displayMaze(maze);
    }

    private static Maze createMaze(MazeBuilder builder) {
        builder.buildRooms();
        return builder.getMaze();
    }

    private static void displayMaze(Maze maze) {
        for (int roomNo : maze.roomNumbers()) {
            Room room = maze.roomNo(roomNo);
            System.out.println("Room " + room.getRoomNo() + ":");
            for (Direction direction : Direction.values()) {
                Wall wall = room.getSide(direction);
                if (wall instanceof DoorWall) {
                    System.out.println("\t" + direction + " -> Door to Room " + ((DoorWall) wall).getOtherRoom(room).getRoomNo());
                } else {
                    System.out.println("\t" + direction + " -> Wall");
                }
            }
        }
    }
}
