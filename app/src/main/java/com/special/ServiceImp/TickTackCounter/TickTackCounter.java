package com.special.ServiceImp.TickTackCounter;

import com.special.ServiceImp.Interfaces.AppInterface;

/**
 * @author Arnar Jonsson
 * @version 0.5
 * @since ..
 * Originally created .. Arnar Jonsson
 *
 * TickTackCounter.class
 * Stores timestamps corresponding to created notification id.
 * When called it iterates through these timestamps to check if any have expired, otherwise
 * it does not care about anything?
 */
public class TickTackCounter {

    ///
    // TickTackCounter Variables
    //
    ///
    private AppInterface callBack;

    private long clockCycle;
    private long startingTimeStamp;

    private TickTackItem first;
    private TickTackItem last;
    private int itemCount;

    private boolean debug;

    ///
    // TickTackCounter Constructors
    //
    ///

    /**
     * TickTackCounter
     *
     * Async? integer storage
     * @param callBack AppInterface.callback
     * @param clockCycle storage timer
     * @param startingTimeStamp TickTackCounter initialization timer (logging purposes)
     * @param debug debug
     */
    public TickTackCounter(AppInterface callBack, long clockCycle, long startingTimeStamp, boolean debug) {

        this.debug = debug;

        this.callBack = callBack;

        this.clockCycle = clockCycle;

        this.startingTimeStamp = startingTimeStamp;

        first = last = null;
        itemCount = 0;

        if(this.debug) log("TickTackCounter - constructor call");
    }


    ///
    // TickTackCounter Public Methods
    //
    ///

    /**
     * @return initialization time
     */
    public long getStartingTimeStamp() {
        return this.startingTimeStamp;
    }

    /**
     * @return lifetime of counter
     */
    public long getClockCycle() {
        return this.clockCycle;
    }

    /**
     * @return number of id's in the counter
     */
    public int getItemCount() {
        shuffle();
        return this.itemCount;
    }

    /**
     * tick(id)
     *
     * Iterates trough stored id's and check if asked id is already stored
     *
     * @param id id
     * @return true if the id does not exist in the counter, false otherwise
     */
    public boolean tick(int id) {
        shuffle();
        return !exists(id);
    }

    /**
     * tack(id)
     *
     * Adds a new id to the counter
     * @param id id
     */
    public void tack(int id) {
        manageTack(id);
    }

    ///
    // TickTackCounter Private Methods
    //
    ///

    private void shuffle() {

        if(last == null) {
            return;
        }

        TickTackItem item = first;
        while (item != null) {

            if(System.currentTimeMillis() - item.timeStamp > clockCycle) {

                if(first == last) {
                    first = last = null;
                    itemCount--;
                    break;
                }

                first = first.next;
                item = first;
                itemCount--;

                continue;
            }

            item = item.next;

        }
    }

    private void manageTack(int id) {
        addToQueue(id);
    }

    private void addToQueue(int id) {

        TickTackItem item = new TickTackItem();
        item.id = id;
        item.timeStamp = System.currentTimeMillis();

        if(last == null) {
            first = last = item;
        }
        else {
            last.next = item;
            last = item;
        }

        itemCount++;
        if(this.debug) log("TickTackCounter - id added to counter : " + id);
    }

    ///
    // TickTackCounter Utilz
    //
    ///

    private boolean exists(int id) {
        if(last == null) {
            return false;
        }

        boolean exists = false;

        TickTackItem item = first;
        while (item != null) {

            if(item.id == id) {
                exists = true;
                break;
            }

            item = item.next;
        }

        return exists;
    }

    ///
    // TickTackCounter Queue Object
    //
    ///

    private static class TickTackItem {
        public int id;
        public long timeStamp;
        public TickTackItem next;
    }

    ///
    // Logging
    //
    ///

    private void log(String desc) {
        callBack.onStatusUpdate(desc);
    }
}
