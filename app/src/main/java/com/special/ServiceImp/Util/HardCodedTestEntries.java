package com.special.ServiceImp.Util;

import com.special.DataStorage.Instances.EntryStamp;

/**
 * Simple tester
 */
public class HardCodedTestEntries {

    public EntryStamp[] getEntries(boolean ImAMall) {

        EntryStamp[] stamps = new EntryStamp[22];
        int stampCount = 0;
        int FSID = 1;
        int FSMGID = 1000;


        ///
        // Taeknigardur ShittNigg
        ///
        EntryStamp TGMall = new EntryStamp(
                FSMGID++,
                64.13953557237824,
                -21.955277295783162,
                "TaekniGardur",
                true,
                1
        );

        EntryStamp TGENTRY1 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "TGStofa1",
                "StudentaKort",
                "TaekniGardur",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                1
        );

        EntryStamp TGENTRY2 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "TGStofa2",
                "StudentaKort",
                "TaekniGardur",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                100
        );
        EntryStamp TGENTRY3 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "TGStofa3",
                "StudentaKort",
                "TaekniGardur",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                1
        );
        EntryStamp TGENTRY4 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "TGStofa4",
                "StudentaKort",
                "TaekniGardur",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                1
        );
        EntryStamp TGENTRY5 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "TGStofa5",
                "StudentaKort",
                "TaekniGardur",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                1
        );

        EntryStamp TGENTRYReal = new EntryStamp(
                FSID++,
                64.13953557237824,
                -21.955277295783162,
                "MalmquistSkrifstofa",
                "StudentaKort",
                "General",
                false,
                "StofaLongDesc",
                "StofaShortDesc",
                true,
                1
        );


        ///
        // Debug
        ///

        EntryStamp ArnarMall = new EntryStamp(
                FSMGID++,
                64.1348028083071,
                -21.949451537802815,
                "ArnarMall",
                true,
                1
        );

        EntryStamp ArnarNonMall = new EntryStamp(
                FSID++,
                64.1348028083071,
                -21.949451537802815,
                "ArnarNonMall",
                "Kort",
                "General",
                false,
                "ArnarNonMallLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp ArnarPlace1 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "ArnarHerbergi",
                "Kort",
                "ArnarMall",
                false,
                "ArnarHerbergiLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp ArnarPlace2 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "KariHerbergi",
                "Kort",
                "ArnarMall",
                false,
                "KariHerbergiLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp ArnarPlace3 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "Eldhus",
                "Kort",
                "ArnarMall",
                false,
                "EldhusLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp ArnarPlace4 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "Klosett",
                "Kort",
                "ArnarMall",
                false,
                "KlosettLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp ArnarPlace5 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "Hol",
                "Kort",
                "ArnarMall",
                false,
                "HolLongDesc",
                "Desc",
                true,
                1
        );

        stamps[stampCount++] = TGMall;
        stamps[stampCount++] = TGENTRY1;
        stamps[stampCount++] = TGENTRY2;
        stamps[stampCount++] = TGENTRY3;
        stamps[stampCount++] = TGENTRY4;
        stamps[stampCount++] = TGENTRY5;
        stamps[stampCount++] = TGENTRYReal;

        stamps[stampCount++] = ArnarMall;
        stamps[stampCount++] = ArnarPlace1;
        stamps[stampCount++] = ArnarPlace2;
        stamps[stampCount++] = ArnarPlace3;
        stamps[stampCount++] = ArnarPlace4;
        stamps[stampCount++] = ArnarPlace5;
        stamps[stampCount++] = ArnarNonMall;


        EntryStamp tenEleven1 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "10-11",
                "StudentaKortid",
                "General",
                false,
                "10-11LongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp tenEleven2 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "10-11",
                "StudentaKortid",
                "General",
                false,
                "10-11LongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp tenEleven3 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "10-11",
                "StudentaKortid",
                "General",
                false,
                "10-11LongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp tenEleven4 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "10-11",
                "StudentaKortid",
                "General",
                false,
                "10-11LongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp tenEleven5 = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "10-11",
                "StudentaKortid",
                "General",
                false,
                "10-11LongDesc",
                "Desc",
                true,
                1
        );


        stamps[stampCount++] = tenEleven1;
        stamps[stampCount++] = tenEleven2;
        stamps[stampCount++] = tenEleven3;
        stamps[stampCount++] = tenEleven4;
        stamps[stampCount++] = tenEleven5;

        EntryStamp hama = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "Hama",
                "StudentaKortid",
                "General",
                false,
                "HamaLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp haskoli = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "HaskoliIslands",
                "StudentaKortid",
                "General",
                false,
                "HaskoliLongDesc",
                "Desc",
                true,
                1
        );

        EntryStamp arnastofnun = new EntryStamp(
                FSID++,
                0.0,
                0.0,
                "ArnaStofnun",
                "StudentaKortid",
                "General",
                false,
                "ArnaStofnunLongDesc",
                "Desc",
                true,
                1
        );

        stamps[stampCount++] = hama;
        stamps[stampCount++] = haskoli;
        stamps[stampCount++] = arnastofnun;

        return stamps;
    }
}
