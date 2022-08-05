import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    
    boolean tagged;
    double skill;
    int name;
    public ArrayList<Player> playersTagged;

    /* Constructor
     * 
     * skill is a double that will determine how this player matches up against others in the simulator.
     */
    public Player(double skill, int name) {
        this.skill = skill;
        this.tagged = false;
        this.name = name;
        this.playersTagged = new ArrayList<Player>();
    }

    /* Tag
     * 
     * This marks the current player tagging the Player 'target'.
     */
    public void tag(Player target, boolean debug) {
        playersTagged.add(target);
        target.isTagged(this);
        if (debug) {
            System.out.printf("Player %d tagged player %d %n", name, target.name);
        }
    }

    /* isTagged
     * 
     * Called when a player gets tagged.
     */
    public void isTagged(Player tagger) {
        this.tagged = true;
    }

    /* showTagged
     * 
     * Prints out all players tagged by this object.
     */
    public void showTagged() {
        Iterator<Player> itr = playersTagged.iterator();

        System.out.printf("Player %d has tagged players: ", this.name);
        while (itr.hasNext()) {
            System.out.printf("%d, ", itr.next().name);
        }
        System.out.println();
    }

}
