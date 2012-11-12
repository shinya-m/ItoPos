package itopos;

/**
 *
 * @author kengo
 */

import java.io.IOException;
import java.util.TimerTask;

class Backup extends TimerTask{

    @Override
    public void run() {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("CMD.EXE /C mysqldump -u root -pmindstorm -x --all-databases > C:\\Users\\itopos\\Dropbox\\dump.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}