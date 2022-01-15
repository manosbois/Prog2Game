package gr.aueb.dmst.GodsNemesis;

/**
 * Our game's program main class.
 *
 * @depend - - - gr.aueb.dmst.GodsNemesis.Graph
 * @depend - - - gr.aueb.dmst.GodsNemesis.Battle
 * @depend - - - gr.aueb.dmst.GodsNemesis.Stages
 * @depend - - - gr.aueb.dmst.GodsNemesis.Move
 * @depend - - - gr.aueb.dmst.GodsNemesis.BuffMove
 * @depend - - - gr.aueb.dmst.GodsNemesis.DamageMove
 * @depend - - - gr.aueb.dmst.GodsNemesis.ProtectiveMove
 * @depend - - - gr.aueb.dmst.GodsNemesis.Character
 * @depend - - - gr.aueb.dmst.GodsNemesis.God
 * @depend - - - gr.aueb.dmst.GodsNemesis.Hero
 */
public class Game {
    /** A Graph instance accessible to other classes */
    static Graph graph;

    public static void main(String [] args){
        graph = new Graph("Gods' Nemesis");
    }
}
