package gr.aueb.dmst.gameName;

/**
 * Our game's program main class.
 *
 * @depend - - - gr.aueb.dmst.gameName.Graph
 * @depend - - - gr.aueb.dmst.gameName.Battle
 * @depend - - - gr.aueb.dmst.gameName.Stages
 * @depend - - - gr.aueb.dmst.gameName.Move
 * @depend - - - gr.aueb.dmst.gameName.BuffMove
 * @depend - - - gr.aueb.dmst.gameName.DamageMove
 * @depend - - - gr.aueb.dmst.gameName.ProtectiveMove
 * @depend - - - gr.aueb.dmst.gameName.Character
 * @depend - - - gr.aueb.dmst.gameName.God
 * @depend - - - gr.aueb.dmst.gameName.Hero
 */
public class Game {
    /** A Graph instance accessible to other classes */
    static Graph graph;

    public static void main(String [] args){
        graph = new Graph("CODERUNNERS");
    }
}
