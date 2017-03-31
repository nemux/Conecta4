package com.optimissa;

/**
 *
 * @author NeMuX <garaujo@kea.mx>
 */
public enum Player {
    ONE(1,21,'X'),
    TWO(2,21,'O');
    
    private Integer playerToken;
    private Integer totalTokens;
    private char symbol;
    
    Player(int playerToken, int totalTokens, char symbol){
        this.playerToken = playerToken;
        this.totalTokens = totalTokens;
        this.symbol = symbol;
    }
    
    public Integer putToken(){
        if (totalTokens > 0)
            totalTokens--;
        return this.playerToken;
    }
    
    public Integer getTokenValue(){
        return this.playerToken;
    }
    
    public Integer getTotalToken(){
        return this.totalTokens;
    }
    
    public char getSymbol(){
        return this.symbol;
    }
}
