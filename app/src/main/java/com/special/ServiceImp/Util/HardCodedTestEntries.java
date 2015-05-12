package com.special.ServiceImp.Util;

import com.special.DataStorage.Instances.EntryStamp;

/**
 * Created by arnarjons on 9.5.2015.
 */
public class HardCodedTestEntries {

    public EntryStamp[] getEntries(boolean ImAMall) {

        EntryStamp[] stamps = new EntryStamp[30];

        int fsCounter = 1;
        int fsmgCounter = 1;
        int stampCount = 0;

        EntryStamp ArnarStamp;
        EntryStamp ArnarMallStamp;

        if(ImAMall) {
            ArnarStamp = new EntryStamp(
                    fsCounter++,
                    64.13475249276243,
                    -21.949356738477945,
                    "Home",
                    "Arnar",
                    "General",
                    false,
                    "arnarLongDesc",
                    "arnarShortDesc",
                    true,
                    100
            );
            ArnarMallStamp = new EntryStamp(
                    fsmgCounter++,
                    10.0,
                    10.0,
                    "Home",
                    true,
                    100
            );
        }
        else {
            ArnarStamp = new EntryStamp(
                    fsCounter++,
                    64.13474547198153,
                    -21.949346931651235,
                    "Home",
                    "Arnar",
                    "General",
                    false,
                    "arnarLongDesc",
                    "arnarShortDesc",
                    true,
                    100
            );
            ArnarMallStamp = new EntryStamp(
                    fsmgCounter++,
                    64.13475249276243,
                    -21.949356738477945,
                    "Home",
                    true,
                    100
            );
        }

        stamps[stampCount++] = ArnarStamp;
        stamps[stampCount++] = ArnarMallStamp;

        stamps[stampCount++] = new EntryStamp(
                fsmgCounter++,
                64.12990539144691,
                -21.89643122255802,
                "Kringlan",
                true,
                1000
        );

        stamps[stampCount++] = new EntryStamp(
                fsmgCounter++,
                64.10118460175556,
                -21.883835569024086,
                "Smaralind",
                true,
                1000
        );
        // 4

        int malldudcount = 0;
        while( malldudcount < 5 ) {

            stamps[stampCount++] = new EntryStamp(
                    fsCounter++,
                    0.0,
                    0.0,
                    "MallDud",
                    "NoCard",
                    "Kringlan",
                    false,
                    "LongDesc",
                    "ShortDesc",
                    true,
                    100
            );

            stamps[stampCount++] = new EntryStamp(
                    fsCounter++,
                    0.0,
                    0.0,
                    "MallDud",
                    "NoCard",
                    "Smaralind",
                    false,
                    "LongDesc",
                    "ShortDesc",
                    true,
                    100
            );

            malldudcount++;
        }

        // 14

        int fsDudCount = 0;
        while (fsDudCount < 5) {

            stamps[stampCount++] = new EntryStamp(
                    fsCounter++,
                    0.0,
                    0.0,
                    "Dud",
                    "DudNoCard",
                    "General",
                    false,
                    "LongDesc",
                    "ShortDesc",
                    true,
                    100
            );

            stamps[stampCount++] = new EntryStamp(
                    fsCounter++,
                    0.0,
                    0.0,
                    "Dud",
                    "NoCard",
                    "General",
                    true,
                    "LongDesc",
                    "ShortDesc",
                    true,
                    100,
                    "00:00",
                    "00:00"
            );

            fsDudCount++;
        }

        // 24

        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                64.13549668547687,
                -21.950928512960672,
                "10-11",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "07:00",
                "00:00"
        );

        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                64.1394911150589,
                -21.95498401299119,
                "TaekniGardur",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "07:30",
                "18:00"
        );

        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                64.13963852589715,
                -21.95570284500718,
                "Nord",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "08:00",
                "22:00"
        );

        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                64.1399356850498,
                -21.950392071157694,
                "Haskolatorg",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "07:00",
                "22:00"
        );


        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                0.0,
                0.0,
                "Shit",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "00:00",
                "00:00"
        );

        stamps[stampCount++] = new EntryStamp(
                fsCounter++,
                0.0,
                0.0,
                "Shit2",
                "NoCard",
                "General",
                true,
                "LongDesc",
                "ShortDesc",
                true,
                100,
                "00:00",
                "00:00"
        );

        // 30

        return stamps;
    }
}
