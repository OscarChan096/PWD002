package com.studio.chan.pwd.Datos;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class Paths {

   public static File pathFilex = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
   public static File pathInf = new File(getExternalStorageDirectory(),"Android/data/com.studio.chan.pwd/filesx/inf");

}
