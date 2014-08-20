/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob;

import by_software.engine.GameObjectType;

/**
 *
 * @author Nigel
 */
public enum Team
{
    PLAYERS(new Faction[] {Faction.PLAYERS}),
    MONSTERS(new Faction[]{Faction.TROLIC});
    
    
    private Faction[] teamMembers;
    
    private Team(Faction[] teamMembers)
    {
        this.teamMembers = teamMembers;
    }
    
    public static void initTeams()
    {
        for(Team t: Team.values())
        {
            for(Faction f :t.getTeamMembers())
            {
                f.initFaction(t);
            }
        }
    }
    public Faction[] getTeamMembers()
    {
        return this.teamMembers;
    }
    public boolean isEnemy(Mob mob)
    {
        for(Faction f: teamMembers)
        {
            if(mob.getFaction() == f)
            {
                return false;
            }
        }
        return true;
    }
}
