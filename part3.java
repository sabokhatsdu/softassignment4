import java.util.HashMap;
import java.util.Map;

interface MazePrototypeFactory {
    Maze makeMaze();
    Room makeRoom(int roomNo);
    Wall makeWall();
    DoorWall makeDoorWall(Room r1, Room r2);
}

class OriginalMazePrototypeFactory implements MazePrototypeFactory {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Room makeRoom(int roomNo) {
        return new Room(roomNo);
    }

    @Override
    public Wall makeWall() {
        return new Wall();
    }

    @Override
    public DoorWall makeDoorWall(Room r1, Room r2) {
        return new DoorWall(r1, r2);
    }
}

class NewMazePrototypeFactory implements MazePrototypeFactory {
    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Room makeRoom(int roomNo) {
        return new Room(roomNo);
    }

    @Override
    public Wall makeWall() {
        // New implementation of wall for the new game
        return new ColorfulWall(); // for example

    }

    class ColorfulWall extends Wall {
    }


    class SecretDoorWall extends DoorWall {
        public SecretDoorWall(Room r1, Room r2) {
            super(r1, r2);

        }
    }


    @Override
    public DoorWall makeDoorWall(Room r1, Room r2) {

        return new SecretDoorWall(r1, r2); // for example
    }
}

public class part3 {
    public static void main(String[] argv) {
        createOriginalMaze();
        createNewMaze();
    }

    private static void createOriginalMaze() {
        MazePrototypeFactory factory = new OriginalMazePrototypeFactory();
        Maze aMaze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        DoorWall d = factory.makeDoorWall(r1, r2);

        aMaze.addRoom(r1);
        aMaze.addRoom(r2);

        r1.setSide(Direction.NORTH, d);
        r1.setSide(Direction.EAST, factory.makeWall());
        r1.setSide(Direction.SOUTH, factory.makeWall());
        r1.setSide(Direction.WEST, factory.makeWall());
        r2.setSide(Direction.NORTH, factory.makeWall());
        r2.setSide(Direction.EAST, factory.makeWall());
        r2.setSide(Direction.SOUTH, d);
        r2.setSide(Direction.WEST, factory.makeWall());

        // Output information about the created maze
        System.out.println("Original Maze Created:");
        System.out.println(aMaze);
    }

    private static void createNewMaze() {
        MazePrototypeFactory factory = new NewMazePrototypeFactory();
        Maze aMaze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        DoorWall d = factory.makeDoorWall(r1, r2);

        aMaze.addRoom(r1);
        aMaze.addRoom(r2);

        r1.setSide(Direction.NORTH, d);
        r1.setSide(Direction.EAST, factory.makeWall());
        r1.setSide(Direction.SOUTH, factory.makeWall());
        r1.setSide(Direction.WEST, factory.makeWall());
        r2.setSide(Direction.NORTH, factory.makeWall());
        r2.setSide(Direction.EAST, factory.makeWall());
        r2.setSide(Direction.SOUTH, d);
        r2.setSide(Direction.WEST, factory.makeWall());

        // Output information about the created maze
        System.out.println("New Maze Created:");
        System.out.println(aMaze);
    }
}
