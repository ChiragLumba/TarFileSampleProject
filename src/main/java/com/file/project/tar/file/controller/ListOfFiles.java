package com.file.project.tar.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class ListOfFiles {

	/**
	 * List all files from a directory and its subdirectories
	 * 
	 * @param directoryName to be listed
	 */
	public List<String> listFiles(String directoryName) {
		List<String> fileList = new ArrayList<String>();
		try {
			TarArchiveInputStream fin = new TarArchiveInputStream(new FileInputStream(directoryName));
			TarArchiveEntry entry;

			while ((entry = fin.getNextTarEntry()) != null) {
				if (entry.isFile()) {
					fileList.add(entry.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}

}
