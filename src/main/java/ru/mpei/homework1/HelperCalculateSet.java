package ru.mpei.homework1;

import lombok.Data;

@Data
public class HelperCalculateSet {

    private int count = 0;

    private int takeSetMax = 0;

    private String nameAgent = "";

    public void calculateSet(int newSet, String nameAgent){

        if (newSet > takeSetMax){
            takeSetMax = newSet;
            this.nameAgent = nameAgent;
        }

    }

}
