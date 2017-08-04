package com.shellcore.android.irn.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Cesar on 02/08/2017.
 */

@Database(name = IRNDatabase.NAME, version = IRNDatabase.VERSION)
public class IRNDatabase {

    public static final int VERSION = 1;
    public static final String NAME = "irns";
}
