package algorithms.interviews.bp;

//A brave Knight "A" has an array of monsters to face, and will use a combination of might and magic to defeat as many as possible.
// In this challenge we'd like to know if the knight is successful at defeating them all, and if not, how many monsters are defeated.
//
//A can see the monsters and their order ahead of time.  Despite being evil monsters they will politely queue and challenge A in the current order.
// Knowing this, A can plan what to do so that it is optimal.
//* The first monster will always be defeated by A's squires while A prepares for battle
//* For each other monster there are two possibilities :
//
//1) If the current monster is weaker than the previous one (i.e. enemies[curr] < enemies[curr-1]),
// The enemy surrenders - what goblin would face someone who has just defeated a dragon?
//2) If the current monster is stronger than the previous one (i.e. enemies[curr] > enemies[curr-1]), then A has two options :
//2.1) Might!  A fights the monster taking damage (reducing hitpoints by the difference between the current and the previous monster).
//2.2) Magic!  A can drink an invulnerability potion, defeating the monster without taking damage.
//
//Write a function that takes as initial parameters
//
//* A list of monsters in order of how A will face them, with their strength as values;
//* A’s initial hit points;
//* A’s amount of invulnerability potions.
//
//And returns
//
//* The 0-based index of the last monster A defeated
//
//Constraints:
//
//* 0 <= enemies.length <= 10e6
//* 1 <= enemies[i] <= 10e4
//* 1 <= hp <= 10e5
//* 0 <= potions <= 10e4
public class BraveKnight {

    private int monsterDefeated(int[] enemies, int hp, int potions) {
        return monsterDefeated(enemies, 1, hp, potions);
    }

    private int monsterDefeated(int[] enemies, int curr, int hp, int potions) {
        if(curr >=enemies.length) {
            return curr - 1;
        }
        if(enemies[curr] < enemies[curr - 1]) {
            // if current enemy strength is less than previous enemy, the current enemy surrenders
            // we move on to next enemy. hp and potions aren't used, so they remain unchanged
           return monsterDefeated(enemies, curr+1, hp, potions);
        }
        // current enemy is stronger than previous enemy
        int hitCountRequiredToDefeatEnemy = enemies[curr] - enemies[curr-1];
        if(hp < hitCountRequiredToDefeatEnemy && potions==0) {
            // if the current hit points of knight are less than the hit points required to defeat enemy
            // and 0 potions available. The Knight can't defeat this monster.
            // In this case, the defeated monster is the previous one
            return curr -1;
        }
        // 3 cases
        //Case 1. Knight has enough hit counts. Knight also has potions >0
        if(hp >= hitCountRequiredToDefeatEnemy && potions >0) {
                return Math.max(monsterDefeated(enemies, curr+1, hp-hitCountRequiredToDefeatEnemy, potions),
                        monsterDefeated(enemies, curr +1, hp, potions-1));
        } else if (hp >= hitCountRequiredToDefeatEnemy) {
            return monsterDefeated(enemies, curr+1, hp-hitCountRequiredToDefeatEnemy, potions);
        } else {
            return monsterDefeated(enemies, curr +1, hp, potions-1);
        }
        //Time complexity: exponential, specifically O(2^N), where N is the number of monsters.
        // This is because the function explores all possible combinations of using hit points and potions for each monster,
        // leading to a branching factor of 2 (two options for each monster: use hit points or use potions).

        // The space complexity of your recursive solution is O(N), where N is the number of monsters. This space complexity is primarily due to the call stack used for the recursive function calls.
        //
        //Space complexity: Each recursive call consumes space on the call stack, and because the function can be recursively called for each monster,
        // the maximum depth of the call stack will be proportional to the number of monsters.
        // Therefore, the space complexity is O(N), as it grows linearly with the number of monsters.
    }

    public static int maxMonstersDefeated(int[] monsters, int initialHP, int potions) {
        // 2, 1, 5, 6
        //hp =7
        // potions =1
        int n = monsters.length;
        int[][][] dp = new int[n + 1][initialHP + 1][potions + 1];
        // int[5][8][2]
        // Initialize the dp array
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= initialHP; j++) {
                for (int k = 0; k <= potions; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        dp[0][initialHP][potions] = 0;
        //{
        // {{,}, {,}, {,}, {,}, {,}, {,}, {,}, {,}},
        // {{,}, {,}, {,}, {,}, {,}, {,}, {,}, {,}},
        // {{,}, {,}, {,}, {,}, {,}, {,}, {,}, {,}},
        // {{,}, {,}, {,}, {,}, {,}, {,}, {,}, {,}},
        // {{,}, {,}, {,}, {,}, {,}, {,}, {,}, {,}},
        // }
        // Dynamic programming loop
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= initialHP; j++) {
                for (int k = 0; k <= potions; k++) {
                    // Option 1: Surrender (no change in HP or potions)
                    dp[i][j][k] = dp[i - 1][j][k];

                    // Option 2: Use a potion (increase defeated monsters count, decrease potions)
                    if (k > 0 && j + monsters[i - 1] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j + monsters[i - 1]][k - 1] + 1);
                    }

                    // Option 3: Use might (decrease HP, no change in potions)
                    if (j >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - monsters[i - 1]][k]);
                    }
                }
            }
        }

        // Find the maximum number of defeated monsters
        int maxDefeated = 0;
        for (int j = 0; j <= initialHP; j++) {
            for (int k = 0; k <= potions; k++) {
                maxDefeated = Math.max(maxDefeated, dp[n][j][k]);
            }
        }

        return maxDefeated;
    }

}
