package de.zockersk.hastebinclient.options;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Created by steven on 21.05.16.
 */
public class OptionsManager {

    private Options options;

    public OptionsManager() {
        options = new Options();
        options.addOption(Option.builder("f").longOpt("file").desc("paste given file to server").hasArg().argName("file").build());
        options.addOption(Option.builder("h").longOpt("help").desc("show this help page").build());
        options.addOption(Option.builder("o").longOpt("open").desc("open returned page in webbrowser").build());
        options.addOption(Option.builder("s").longOpt("server").desc("configure haste-server").build());
    }

    public Options getOptions() {
        return options;
    }
}
