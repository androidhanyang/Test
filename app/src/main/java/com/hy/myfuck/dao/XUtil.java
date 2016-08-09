package com.hy.myfuck.dao;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by user on 2016/8/8.
 */
public class XUtil {
    static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig() {
        File file = new File("/sdcard/Zaza/db/");
        if(file.exists()){
            file.mkdirs();
        }
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig().setDbName("zaker.db").setDbDir(file).setDbVersion(1)
                    .setAllowTransaction(true).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });

        }
        return daoConfig;
    }
}
