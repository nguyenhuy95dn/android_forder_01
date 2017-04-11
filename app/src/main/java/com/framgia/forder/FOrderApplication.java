package com.framgia.forder;

import android.app.Application;
import com.framgia.forder.data.source.local.realm.DataLocalMigration;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class FOrderApplication extends Application {

    private static final String REALM_SCHEMA_NAME = "forder.realm";
    private static final int REALM_SCHEMA_VERSION = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        FOrderServiceClient.initialize(this);
        initAndMigrateRealmIfNeeded();
    }

    private void initAndMigrateRealmIfNeeded() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(REALM_SCHEMA_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION)
                .migration(new DataLocalMigration())
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance(); // Automatically run migration if needed
        realm.close();
    }
}
