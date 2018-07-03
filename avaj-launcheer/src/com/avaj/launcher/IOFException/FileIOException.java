package com.avaj.launcher.IOFException;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import java.io.IOException;

public class FileIOException extends IOException {
    public FileIOException() {}
    public FileIOException(String message) {
        super(message);
    }
}
