import java.util.Random;
import java.util.Iterator;
import java.util.HashMap;

/* Chaos tag simulator.
 * In chaos tag, every player is simultaneously 'it'.
 * When a player is tagged, they are no longer allowed to tag anyone.
 * Also, when a player is tagged, anyone they had previously tagged 
 * is now allowed to tag again.
 * The game ends when a single player has tagged everyone in the game.
 */

public class Driver {

    public static HashMap<Integer, Player> active = new HashMap<Integer, Player>();
    public static HashMap<Integer, Player> tagged = new HashMap<Integer, Player>();
    public static boolean debug = true;
    public static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        
        // rand.setSeed(12345);
        int numPlayers = 10;
        int maxRounds = 100;

        for (int i = 0; i < numPlayers; i++) {
            active.put(i, new Player((i * 2.0) + 1.0, i));
        }

        Player p, t;
        int rounds = 0;
        while (active.size() > 1 && rounds < maxRounds) {
            p = null;
            while (p == null) {
                p = active.get(rand.nextInt(numPlayers));
            }

            t = active.get(rand.nextInt(numPlayers));

            while (t == null || t.name == p.name) {
                t = active.get(rand.nextInt(numPlayers));
            }

            standoff(p, t);
            rounds++;
        }

        for (Player player : active.values()) {
            player.showTagged();
        }

    }

    /* standoff
     * 
     * Players p1 and p2 go head-to-head.
     * One player tags the other, determined by their skill values.
     * Returns the player that did not get tagged.
     */
    public static Player standoff(Player p1, Player p2) {

        if (rand.nextDouble() < (p1.skill / (p1.skill + p2.skill))) {
            p1.tag(p2, debug);
            freeAll(p2);
            return p1;
        }

        p2.tag(p1, debug);
        freeAll(p1);
        return p2;
    }

    /* freeAll
     * 
     * Releases all players that were tagged by player p
     * and adds/removes them from active/tagged accordingly.
     * Also adds/removes player p from tagged/active.
     */
    public static void freeAll(Player p) {
        Iterator<Player> itr = p.playersTagged.iterator();
        Player t;

        while (itr.hasNext()) {
            t = itr.next();
            t.tagged = false;
            tagged.remove(t.name);
            active.put(t.name, t);
            itr.remove();
        }

        active.remove(p.name);
        tagged.put(p.name, p);
    }
}
