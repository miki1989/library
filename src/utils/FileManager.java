package utils;

import data.Library;

import java.io.*;

public interface FileManager {

    Library importData();
    void exportData(Library library);
}
