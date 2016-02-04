import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String...args) throws FileNotFoundException {
        Foresee foresee = new Foresee("res/Test_12_input.txt");
        foresee.start();
    }
}

class Foresee {
    static InputStream ins = System.in;
    public List<String> steps = new ArrayList<>();
    static boolean debug = true;

    char[][] map;
    List<int[]> teleports = new ArrayList<>(2);

    Foresee.State state = new Foresee.State();
    final int X = 1;
    final int Y = 0;

    public void start() {
        for (int i = 0; i < 200; i++) {
            step();
            showMap();
            if (map[state.y][state.x] == Envirnt.finish)
                break;
            if (i == 200 - 1) {
                steps.clear();
                steps.add("LOOP");
            }
        }
    }
    void step() {
        pick();

        if (isDeadlock(whatNext(state.d))) {
            for (WorldSide s : Envirnt.WrldSidePriority) {
                if (isFree(whatNext(s))) {
                    state.rotateTo(s);
                    break;
                }
            }
        }

        state.forward();
    }

    void pick() {
        char picked = map[state.y][state.x];

        if (picked == Envirnt.space)
            return;

        if (WorldSide.get(picked) != null) {
            state.rotateTo(WorldSide.get(picked));
            return;
        }

        if (picked == Envirnt.beer) {
            state.breaker = !state.breaker;
            return;
        }

        if (Envirnt.crushableObstcles.contains(picked)) {
            map[state.y][state.x] = Envirnt.space;
            return;
        }

        if (picked == Envirnt.reverse) {
            Collections.reverse(Envirnt.WrldSidePriority);
            return;
        }

        if (picked == Envirnt.teleport) {
            int[] to = new int[2];

            for (int i = 0; i < teleports.size(); i++)
                if (teleports.get(i)[X] != state.x || teleports.get(i)[Y] != state.y)
                    to = teleports.get(i);

            state.y = to[Y];
            state.x = to[X];
        }
    }

    char whatNext(WorldSide direction) {
        int[] c = nextCoords(direction);
        return map[state.y + c[Y]][state.x + c[X]];
    }
    char whatForward() {
        return whatNext(state.d);
    }
    int[] nextCoords(WorldSide direction) {
        int[] res = {0, 0};
        switch (direction) {
            case SOUTH:
                res[Y] = 1;
                break;
            case EAST:
                res[X] = 1;
                break;
            case NORTH:
                res[Y] = -1;
                break;
            case WEST:
                res[X] = -1;
                break;
        }
        return res;
    }
    void showMap() {
//        System.out.printf("x=%d, y=%d, %s\n", Solution.state.x, Solution.state.y, Solution.state.d);
        System.out.printf("%s\n", state.d);
        steps.add(state.d.name());

        if(debug) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    char sh = i == state.y && j == state.x ? Envirnt.bender : map[i][j];
                    System.out.print(sh);
                }
                System.out.println();
            }
        }
    }
    public Foresee(String format) throws FileNotFoundException {
        ins = new FileInputStream(format);

        Scanner in = new Scanner(ins);
        int L = in.nextInt();
        int C = in.nextInt();
        map = new char[L][C];
        in.nextLine();
        for (int i = 0; i < L; i++)
            initLine(i, in.nextLine());
    }
    void initLine(int l, String row) {
        for (int i = 0; i < row.length(); i++) {
            map[l][i] = row.charAt(i);
            if (row.charAt(i) == Envirnt.bender) {
                state.x = i;
                state.y = l;
                map[l][i] = Envirnt.space;
            }

            if (row.charAt(i) == Envirnt.teleport)
                teleports.add(new int[]{l, i});
        }
    }
    boolean isFree(char sym) {
        return !isDeadlock(sym);
    }
    boolean isDeadlock(char sym) {
        List<Character> locks = new ArrayList<>();
        locks.addAll(Envirnt.crushableObstcles);
        locks.addAll(Envirnt.solidObstcles);

        if (state.breaker)
            locks.removeAll(Envirnt.crushableObstcles);

        return locks.contains(sym);
    }

    class State {
        WorldSide d = WorldSide.SOUTH;
        int x;
        int y;
        boolean breaker = false;

        void rotateTo(WorldSide direction) {
            d = direction;
        }
        void forward() {
            x += nextCoords(d)[X];
            y += nextCoords(d)[Y];
        }
    }
}

class Envirnt {
    static List<Character> solidObstcles = Arrays.asList('#');
    static List<Character> crushableObstcles = Arrays.asList('X');
    static List<WorldSide> WrldSidePriority = Arrays.asList(WorldSide.SOUTH, WorldSide.EAST, WorldSide.NORTH, WorldSide.WEST);
    static char beer = 'B';
    static char bender = '@';
    static char finish = '$';
    static char space = ' ';
    static char reverse = 'I';
    static char teleport = 'T';

}
enum WorldSide {
    SOUTH('S'),
    EAST('E'),
    NORTH('N'),
    WEST('W');

    private final char shortName;

    WorldSide(char shortName) {
        this.shortName = shortName;
    }

    static WorldSide get(char shortName) {
        for (WorldSide worldSide : values())
            if (worldSide.shortName == shortName)
                return worldSide;
        return null;
    }
}