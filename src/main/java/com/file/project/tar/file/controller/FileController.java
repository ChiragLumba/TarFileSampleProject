package com.file.project.tar.file.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.file.project.tar.file.utility.ServiceConstants;

@RestController
@RequestMapping(path = "/file", method = RequestMethod.GET)
public class FileController {

	@Autowired
	ImportFile importFileController;

	@Autowired
	PullFile pullFileController;

	@Autowired
	ListOfFiles listOfFiles;

	@RequestMapping(path = "/import", method = RequestMethod.GET)
	public ResponseEntity<String> returnFile() {

		try {
			importFileController.compressedFile();
		} catch (Exception e) {
			return ResponseEntity.status(ServiceConstants.HTTP_Status_Code).body(e.getMessage());
		}
		return ResponseEntity.ok().body(ServiceConstants.Import_Response_Message);

	}

	@RequestMapping(path = "/pull", method = RequestMethod.GET)

	public ResponseEntity<String> decompressedFile() {
		try {
			pullFileController.decompressTarFile();
		} catch (Exception e) {
			return ResponseEntity.status(ServiceConstants.HTTP_Status_Code).body(e.getMessage());
		}
		return ResponseEntity.ok().body(ServiceConstants.Pull_Response_Message);

	}

	@RequestMapping(path = "/listOfFiles", method = RequestMethod.GET)
	public void listOfFiles() {
		listOfFiles.listFiles(ServiceConstants.Resource_Path);

	}

}
