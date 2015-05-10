package com.special.ServiceImp.Util;

/**
 * @author Ari Freyr Gudmundsson
 * @version 0.1
 * @since 10.5.2015.
 *
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
