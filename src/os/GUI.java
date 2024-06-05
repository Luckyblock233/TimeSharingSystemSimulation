package os;

import os.File.FileGUI;
import os.Process.ProcessGUI;

public class GUI {
    GUI() {
        new ProcessGUI();
        new FileGUI();
    }
}