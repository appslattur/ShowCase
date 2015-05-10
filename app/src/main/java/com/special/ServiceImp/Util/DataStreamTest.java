package com.special.ServiceImp.Util;

import android.content.Context;

import com.special.DataBaseHandler.AsyncTasks.EntryTask;
import com.special.DataBaseHandler.AsyncTasks.IterableTask;
import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Instances.IterableStamp;
import com.special.DataStorage.Instances.ValueStamp;
import com.special.DataStorage.Messages.EntryMessage;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

/**
 * Created by arnarjons on 9.5.2015.
 */
public class DataStreamTest {

    private Context context;

    public DataStreamTest(Context context) {
        this.context = context;
    }

    public String beginTesting() {
        String report = "";

        ///// EntryMessage Test

        try {
            EntryMessage message = new EntryTask(context)
                    .execute(new EntryMessage(
                            new HardCodedTestEntries().getEntries(false)
                    )).get();
            report += "BEGIN ENTRYSTAMP INSERTION TEST :::: \n" +
                    message.getMessage();
        }
        catch (Exception e) {
            report += "BEGIN ENTRYSTAMP INSERTION TEST :::: \n" +
                    "EntryTask.async failed to execute";
        }

        report += "\n" + "\n" + "\n" + "\n" + "\n";

        try {
            ValueMessage message = new ValueTask(context)
                    .execute(new ValueMessage(ValueMessage.VALUEMESSAGE_LOCATION_FS, 1))
                    .get();

            ValueMessage message1 = new ValueTask(context)
                    .execute(new ValueMessage(ValueMessage.VALUEMESSAGE_LOCATION_FSMG, 1))
                    .get();

            ValueMessage message2 = new ValueTask(context)
                    .execute(new ValueMessage())
                    .get();

            report += "BEGIN VALUESTAMP EXTRACTION TEST :::: \n" +
                    createValueTestString(message, message1, message2);
        }
        catch (Exception e) {
            report += "BEGIN VALUESTAMP EXTRACTION TEST :::: \n" +
                    "ValueTask.async failed to execute";
        }

        report += "\n" + "\n" + "\n" + "\n" + "\n";

        try {
            IterableMessage message = new IterableTask(context)
                    .execute(new IterableMessage(
                            IterableMessage.ITERABLEMESSAGE_TYPE_RADAR
                    )).get();

            IterableMessage message1 = new IterableTask(context)
                    .execute(new IterableMessage(
                            message.getStamps()[0]
                    )).get();

            IterableMessage message2 = new IterableTask(context)
                    .execute(new IterableMessage(
                            new IterableStamp(
                                    2,
                                    64.12990539144691+"",
                                    -21.89643122255802+"",
                                    "Kringlan",
                                    1000,
                                    true
                            )
                    )).get();

            report += "BEGIN ITERABLE EXTRACTION TEST :::: \n" +
                    createIterableTestString(message,message1,message2);
        }
        catch (Exception e) {
            report += "BEGIN ITERABLE EXTRACTION TEST :::: \n" +
                    "IterableTask.async failed to execute";
        }

        report += "\n" + "\n" + "\n" + "\n" + "\n";
        /*
        try {
            UpdateMessage message = new UpdateTask(context)
                    .execute(new UpdateMessage(
                            UpdateMessage.UPDATEMESSAGE_LOCATION_FS,
                            1,
                            0)).get();

            UpdateMessage message1 = new UpdateTask(context)
                    .execute(new UpdateMessage(
                            UpdateMessage.UPDATEMESSAGE_LOCATION_FS,
                            "General",
                            0)).get();

            UpdateMessage message2 = new UpdateTask(context)
                    .execute(new UpdateMessage(
                            UpdateMessage.UPDATEMESSAGE_LOCATION_FSMG,
                            "Kringlan",
                            0)).get();

            report += "BEGIN UPDATE UPDATE TESTS :::: \n" +
                    "In-app Database should be updated";
        }
        catch (Exception e) {
            report += "BEGIN UPDATE UPDATE TESTS :::: \n" +
                    "UpdateTask.async failed to execute";
        }

        report += "\n" + "\n" + "\n" + "\n" + "\n";

        try {
            IterableMessage message = new IterableTask(context)
                    .execute(new IterableMessage(
                            IterableMessage.ITERABLEMESSAGE_TYPE_RADAR
                    )).get();

            report += "BEGIN ITERABLE UPDATE TEST :::: \n" +
                    createIterableTestString(message);
        }
        catch (Exception e) {
            report += "BEGIN ITERABLE UPDATE TEST :::: \n" +
                    "IterableTask.async failed to execute";
        }
        */
        return report;
    }

    private String createIterableTestString(IterableMessage message) {
        String report = "";

        for(IterableStamp stamp : message.getStamps()) {

            if (stamp.isMall()) {

                report += "Iteration stamp MALL :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | pR : " + "\n" +
                        stamp.getName() + "|" + stamp.getPingRadius() + "\n";

                continue;
            }

            if (stamp.hasTimeStamp()) {

                report += "Iteration stamp ENTRYTS :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | cG : | mG : " + "\n" +
                        stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                        " tS : | lD : | sD : " + "\n" +
                        stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                        " eN : | tS : | tS : " + "\n" +
                        stamp.isEnable() + "|" + stamp.getTimeStart() + "|" + stamp.getTimeStop() + "\n";

                continue;
            }

            report += "Iteration stamp ENTRY :" + "\n" +
                    " id : | lat : | lon : " + "\n" +
                    stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                    " nA : | cG : | mG : " + "\n" +
                    stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                    " tS : | lD : | sD : " + "\n" +
                    stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                    " eN : " + "\n" +
                    stamp.isEnable() + "\n";

        }

        return  report;
    }

    private String createValueTestString(ValueMessage message, ValueMessage message1, ValueMessage message2) {
        String firstMessage = "First Value Message Test FS/FSTS single Value : " + "\n";
        String seconMessage = "Second Value Message Test FSMG single Value : " + "\n";
        String thirdMessage = "Third Value TestMessage Test DUMP : " + "\n";

        ValueStamp stamp;

        stamp = (ValueStamp) message.getStamps()[0];

        firstMessage += "Iteration stamp ENTRY :" + "\n" +
                " id : | lat : | lon : " + "\n" +
                stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                " nA : | cG : | mG : " + "\n" +
                stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                " tS : | lD : | sD : " + "\n" +
                stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                " eN : " + "\n" +
                stamp.isEnable() + "\n";

        stamp = (ValueStamp) message1.getStamps()[0];

        seconMessage += "Iteration stamp MALL :" + "\n" +
                " id : | lat : | lon : " + "\n" +
                stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                " nA : | eN : | pR : " + "\n" +
                stamp.getName() + "|" + stamp.isEnable() + "|" + stamp.getPingRadius() + "\n";

        ValueStamp[] stamps = (ValueStamp[]) message2.getStamps();

        for(ValueStamp stampf : stamps) {

            if(stampf.getSubType().equals(DataStamp.VALUESTAMP_TYPE_FSMG)) {
                thirdMessage += "Iteration stamp MALL :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stampf.getId() + "|" + stampf.getLatitude() + "|" + stampf.getLongitude() + "\n" +
                        " nA : | pR : " + "\n" +
                        stampf.getName() + "|" + stampf.getPingRadius() + "\n";
            }
            else if(stampf.getSubType().equals(DataStamp.VALUESTAMP_TYPE_FS)) {
                if(stampf.hasTimeStamp()) {

                    thirdMessage += "Iteration stamp ENTRYTS :" + "\n" +
                            " id : | lat : | lon : " + "\n" +
                            stampf.getId() + "|" + stampf.getLatitude() + "|" + stampf.getLongitude() + "\n" +
                            " nA : | cG : | mG : " + "\n" +
                            stampf.getName() + "|" + stampf.getCardGroup() + "|" + stampf.getMallGroup() + "\n" +
                            " tS : | lD : | sD : " + "\n" +
                            stampf.hasTimeStamp() + "|" + stampf.getLongDescription() + "|" + stampf.getShortDescription() + "\n" +
                            " eN : | tS : | tS : " + "\n" +
                            stampf.isEnable() + "|" + stampf.getTimeStart() + "|" + stampf.getTimeStop() + "\n";


                }

                thirdMessage += "Iteration stamp ENTRY :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stampf.getId() + "|" + stampf.getLatitude() + "|" + stampf.getLongitude() + "\n" +
                        " nA : | cG : | mG : " + "\n" +
                        stampf.getName() + "|" + stampf.getCardGroup() + "|" + stampf.getMallGroup() + "\n" +
                        " tS : | lD : | sD : " + "\n" +
                        stampf.hasTimeStamp() + "|" + stampf.getLongDescription() + "|" + stampf.getShortDescription() + "\n" +
                        " eN : " + "\n" +
                        stampf.isEnable() + "\n";
            }
            else {
                thirdMessage += "\n" + "stamp subtype not recognized" + "\n" + "\n";
            }

        }

        return firstMessage + "\n" + "\n" + seconMessage + "\n" + "\n" + thirdMessage + "\n";
    }

    private String createIterableTestString(IterableMessage message, IterableMessage message1, IterableMessage message2) {
        String report = "Iterable Test stamp return RADAR : " + "\n";
        String report1 = "Iterable Test stamp return DISPLAY ENTRY : " + "\n";
        String report2 = "Iterable Test stamp return DISPLAY MALL : " + "\n";

        for(IterableStamp stamp : message.getStamps()) {

            if (stamp.isMall()) {

                report += "Iteration stamp MALL :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | pR : " + "\n" +
                        stamp.getName() + "|" + stamp.getPingRadius() + "\n";

                continue;
            }

            if (stamp.hasTimeStamp()) {

                report += "Iteration stamp ENTRYTS :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | cG : | mG : " + "\n" +
                        stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                        " tS : | lD : | sD : " + "\n" +
                        stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                        " eN : | tS : | tS : " + "\n" +
                        stamp.isEnable() + "|" + stamp.getTimeStart() + "|" + stamp.getTimeStop() + "\n";

                continue;
            }

            report += "Iteration stamp ENTRY :" + "\n" +
                    " id : | lat : | lon : " + "\n" +
                    stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                    " nA : | cG : | mG : " + "\n" +
                    stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                    " tS : | lD : | sD : " + "\n" +
                    stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                    " eN : " + "\n" +
                    stamp.isEnable() + "\n";

        }

        for(IterableStamp stamp : message1.getStamps()) {

            if (stamp.isMall()) {

                report1 += "Iteration stamp MALL :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | pR : " + "\n" +
                        stamp.getName() + "|" + stamp.getPingRadius() + "\n";

                continue;
            }

            if (stamp.hasTimeStamp()) {

                report1 += "Iteration stamp ENTRYTS :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | cG : | mG : " + "\n" +
                        stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                        " tS : | lD : | sD : " + "\n" +
                        stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                        " eN : | tS : | tS : " + "\n" +
                        stamp.isEnable() + "|" + stamp.getTimeStart() + "|" + stamp.getTimeStop() + "\n";

                continue;
            }

            report1 += "Iteration stamp ENTRY :" + "\n" +
                    " id : | lat : | lon : " + "\n" +
                    stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                    " nA : | cG : | mG : " + "\n" +
                    stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                    " tS : | lD : | sD : " + "\n" +
                    stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                    " eN : " + "\n" +
                    stamp.isEnable() + "\n";

        }

        for(IterableStamp stamp : message2.getStamps()) {

            if (stamp.isMall()) {

                report2 += "Iteration stamp MALL :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | pR : " + "\n" +
                        stamp.getName() + "|" + stamp.getPingRadius() + "\n";

                continue;
            }

            if (stamp.hasTimeStamp()) {

                report2 += "Iteration stamp ENTRYTS :" + "\n" +
                        " id : | lat : | lon : " + "\n" +
                        stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                        " nA : | cG : | mG : " + "\n" +
                        stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                        " tS : | lD : | sD : " + "\n" +
                        stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                        " eN : | tS : | tS : " + "\n" +
                        stamp.isEnable() + "|" + stamp.getTimeStart() + "|" + stamp.getTimeStop() + "\n";

                continue;
            }

            report2 += "Iteration stamp ENTRY :" + "\n" +
                    " id : | lat : | lon : " + "\n" +
                    stamp.getId() + "|" + stamp.getLatitude() + "|" + stamp.getLongitude() + "\n" +
                    " nA : | cG : | mG : " + "\n" +
                    stamp.getName() + "|" + stamp.getCardGroup() + "|" + stamp.getMallGroup() + "\n" +
                    " tS : | lD : | sD : " + "\n" +
                    stamp.hasTimeStamp() + "|" + stamp.getLongDescription() + "|" + stamp.getShortDescription() + "\n" +
                    " eN : " + "\n" +
                    stamp.isEnable() + "\n";

        }

        return report + "\n" + report1 + "\n" + report2;
    }
}
