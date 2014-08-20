/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob;

/**
 *
 * @author Nigel
 */
public enum Faction
{
    PLAYERS(),
    TEAM_RED(),
    TEAM_BLUE(),
    TEAM_YELLOW(),
    TEAM_GREEN(),
    TROLIC();
    
    private Team team;
    
    public void setTeam(Team team)
    {
        this.team = team;
    }
    
    public void initFaction(Team team)
    {
        this.team = team;
    }
    
    public boolean isEnemy(Mob mob)
    {
        return team.isEnemy(mob);
    }
            
}
