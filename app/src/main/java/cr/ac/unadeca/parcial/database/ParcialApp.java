package cr.ac.unadeca.parcial.database;
import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by pc on 28/2/2018.
 */

public class ParcialApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
