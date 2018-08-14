/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.GV;
import fn.Zipper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesZipFiles {
    File zip;
    private static ZipOutputStream output;

    public static void zipperBackup(){
        try {
            Zipper z = new Zipper(new File(GV.filesPath()+"rsp.zip"));
            z.zip(new File(GV.filesPath()+"RSP"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
