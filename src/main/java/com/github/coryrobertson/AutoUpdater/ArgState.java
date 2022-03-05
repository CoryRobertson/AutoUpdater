package com.github.coryrobertson.AutoUpdater;

//import com.sun.org.apache.xpath.internal.Arg;

/**
 * Fancy arg state enum for checking the arguments of Updater, probably could have put this inside the Updater class but this was more fun.
 */
public enum ArgState
{
        errorArgs(-1),
        noArgs(0),
        validArgs(1),
        validArgsDelete(2);

        //I gave them numbers just in case I wanted to check them that way for some reason.
        public final int stateNumber;

        //Decided to make this because it was kinda fun to do more than just make boring enums
        public static ArgState findArgState(int stateNumber)
        {
                for(ArgState as : ArgState.values())
                {
                    if(as.stateNumber == stateNumber)
                    {
                            return as;
                    }
                }
                return null;
        }

        //Constructor for an ArgState enum
        ArgState(int argStateNumber)
        {
            this.stateNumber = argStateNumber;
        }

        //To string statement just in case we use it
        @Override
        public String toString()
        {
                return stateNumber + ", " + name();
        }
}
