/**
 * Start.java
 * Created on 19.08.2003, 20:40:08 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import java.io.IOException;
import java.sql.SQLException;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.LoginBox;


/**
 * The type Start.
 */
public class Start {

    static App app = null;
    static LoginBox login;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        if ((args.length == 0) || (!args[0].equals("-m"))) {
            login = new LoginBox();

        }
        else{
            app = new App(false, login.conn);
        }
    }
}

