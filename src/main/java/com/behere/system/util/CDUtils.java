package com.behere.system.util;  
  
import java.io.File;  
import java.io.FileWriter;  
import java.util.ArrayList;  
import javax.swing.JOptionPane;  
import javax.swing.filechooser.FileSystemView;  
  
/** 
 * A program to Open CD drive using VB script with the help of JAVA 
 * @author mark.wang 
 *  
 */  
  
public class CDUtils {  
    private CDUtils() {  
    }  
  
    // 利用VB script 打开光驱  
    public static void open(String drive) {  
        try {  
            File file = File.createTempFile("realhowto", ".vbs");  
            file.deleteOnExit();  
            FileWriter fw = new java.io.FileWriter(file);  
  
            String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"  
                    + "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""  
                    + drive + "\") \n cd.Eject";  
  
            fw.write(vbs);  
            fw.close();  
  
            Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 利用VB script 关闭光驱  
    public static void close(String drive) {  
        try {  
            File file = File.createTempFile("realhowto", ".vbs");  
            file.deleteOnExit();  
            FileWriter fw = new FileWriter(file);  
  
            // to close a CD, we need eject two times!  
            String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"  
                    + "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""  
                    + drive + "\") \n cd.Eject \n cd.Eject ";  
  
            fw.write(vbs);  
            fw.close();  
  
            Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 获取所有光驱的盘符列表  
    public static ArrayList<File> findCDWin32() {  
        FileSystemView fsv = FileSystemView.getFileSystemView();  
  
        File[] roots = fsv.getRoots();  
        if (roots.length == 1) {  
            roots = roots[0].listFiles()[0].listFiles();  
        } else {  
            System.out.println("I guess you're not on Windows");  
            return null;  
        }  
  
        ArrayList<File> foundDrives = new ArrayList<File>();  
        for (int i = 0; i < roots.length; i++) {  
            if (fsv.isDrive(roots[i])) {  
                if (fsv.getSystemTypeDescription(roots[i]).indexOf("CD") != -1) {  
                    foundDrives.add(roots[i]);  
                }  
            }  
        }  
  
        return foundDrives;  
    }  
      
    public static void main(String[] args) {  
  
        String cdDrive = "";  
          
        if(findCDWin32().size() > 0) {  
            File file = findCDWin32().toArray(new File[0])[0];  
            cdDrive = file.getPath();  
            System.out.println(cdDrive);
        } else {  
            return;  
        }  
          
        // open the cd-rom  
        JOptionPane.showConfirmDialog((java.awt.Component) null, "Press OK to open CD", "CDUtils",   
                javax.swing.JOptionPane.DEFAULT_OPTION);  
  
        CDUtils.open(cdDrive);  
    }  
  
}  