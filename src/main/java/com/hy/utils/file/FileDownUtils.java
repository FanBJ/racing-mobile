package com.hy.utils.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FileDownUtils {

	public ResponseEntity<byte[]> downloadAndroidApp() throws IOException {
		String path = "D:\\Jw_v1.0.1_2016-06-02_full.apk";
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("Jw_v1.0.1_2016-06-02_full.apk".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
