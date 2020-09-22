package com.file.project.tar.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.file.project.tar.file.utility.ServiceConstants;

@RestController
@Service
public class ImportFile {
	

	
	public static void compress(String name, File... files)  {
        try (TarArchiveOutputStream out = getTarArchiveOutputStream(name)){
            for (File file : files){
                addToArchiveCompression(out, file, ".");
            }
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
	
	private static TarArchiveOutputStream getTarArchiveOutputStream(String name) throws IOException {
        TarArchiveOutputStream taos = new TarArchiveOutputStream(new FileOutputStream(name));
        // TAR has an 8 gig file limit by default, this gets around that
        taos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_STAR);
        // TAR originally didn't support long file names, so enable the support for it
        taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        taos.setAddPaxHeadersForNonAsciiNames(true);
        return taos;
    }
	
	private static void addToArchiveCompression(TarArchiveOutputStream out, File file, String dir) throws IOException {
        String entry = dir + File.separator + file.getName();
        if (file.isFile()){
            out.putArchiveEntry(new TarArchiveEntry(file, entry));
            try (FileInputStream in = new FileInputStream(file)){
                IOUtils.copy(in, out);
            }
            out.closeArchiveEntry();
        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null){
                for (File child : children){
                    addToArchiveCompression(out, child, entry);
                }
            }
        } else {
            System.out.println(file.getName() + " is not supported");
        }
    }
	
	
	public static void compressedFile(){
		Class clazz = ImportFile.class;
		File resource1 = new File(clazz.getResource("/Resource1.txt").getFile());
		File resource2 = new File(clazz.getResource("/Resource2.txt").getFile());
		compress(ServiceConstants.Resource_Path,resource1,resource2);
	}
	}
	
