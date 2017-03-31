package com.optimissa;

/**
 *
 * @author NeMuX <garaujo@kea.mx>
 */
public enum WinType {
    HORIZONTAL(1),
    VERTICAL(2),
    DIAGONAL(3),
    SECUNDARY_DIAGONAL(4);
    
    Integer type;
    
    WinType(int type){
        this.type = type;
    }
    
    public Integer getType(){
        return this.type;
    }
}
