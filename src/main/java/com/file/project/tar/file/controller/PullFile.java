package com.file.project.tar.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.file.project.tar.file.utility.ServiceConstants;

@RestController
@Service
public class PullFile {

	public static void decompress(String in, File out) {
		try (TarArchiveInputStream fin = new TarArchiveInputStream(new FileInputStream(in))) {
			TarArchiveEntry entry;
			while ((entry = fin.getNextTarEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File curfile = new File(out, entry.getName());
				File parent = curfile.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				IOUtils.copy(fin, new FileOutputStream(curfile));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void decompressTarFile() {
		decompress(ServiceConstants.Resource_Path, new File(ServiceConstants.Decompress_Directory));
	}

}
