package com.special.ServiceImp.Util;

/**
 * Available info in the DataStamps that you (Ari) cares about :
 *
 * id
 * name
 * cardgroup,
 * mallgroup,
 * enabled
 * longdesc
 * shortdesc
 *
 *
 */
public interface Stamp {

    public int getID();

    public String getName();

    public String getLongDescription();

    public String getShortDescription();

}
