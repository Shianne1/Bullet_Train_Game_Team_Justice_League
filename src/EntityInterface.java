/*
     @Interface: EntityInterface()
     @Function: basic interface used as a template for any similarities between actions that affect entities
     @implementedBy: Player, Monster
     @author(s) Carlton Napier
     @added 10/16/2022
  */
public interface EntityInterface {

    void healHealth(int healthModifier);

    void takeDamage(int healthModifier);


}