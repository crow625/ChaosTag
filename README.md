# ChaosTag

Chaos tag is a group tag game where all players are simultaneously 'it'. When a player is tagged, they must sit down and cannot tag anyone. However, when a player is tagged, anyone they tagged is now back in the game and can tag others again. The game ends when a single person has tagged all the other players, or alternatively when a certain amount of time has passed.

# The code

The main method is stored in Tag/Driver.java . It simulates interactions between different players, who each have a different skill value associated. This skill value determines how likely a player is to tag or be tagged in a confrontation with another player. Player interactions are chosen randomly, and the victor of an interaction is also chosen randomly, but biased in favor of the more skilled player.
The simulation finishes when a single player has tagged all other players, or when 100 interactions have occurred.
