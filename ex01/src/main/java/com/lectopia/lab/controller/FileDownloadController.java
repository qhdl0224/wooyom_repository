package com.lectopia.lab.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/down")
public class FileDownloadController {

	
   private static final String FILE_PATH = "C:/test_file/upload/";

   //안쓰임
   @GetMapping("/fileDown")
   public String fileUploadForm(Model model) {
      return "fileDownloadView";
   }

   // Using ResponseEntity<InputStreamResource>
   @GetMapping("/download1")
   public ResponseEntity<InputStreamResource> downloadFile1(HttpServletRequest req) throws IOException {
	   String userid = req.getParameter("writer");
	   String filename = req.getParameter("filename");
	   System.out.println(filename);
      File file = new File(FILE_PATH+userid+"/"+filename);
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

      return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                  "attachment;filename=" + file.getName())
            .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
            .body(resource);
   }

   // Using ResponseEntity<ByteArrayResource> 아직 안씀
   @GetMapping("/download2")
   public ResponseEntity<ByteArrayResource> downloadFile2() throws IOException {

      Path path = Paths.get(FILE_PATH);
      byte[] data = Files.readAllBytes(path);
      ByteArrayResource resource = new ByteArrayResource(data);

      return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                  "attachment;filename=" + path.getFileName().toString())
            .contentType(MediaType.APPLICATION_PDF).contentLength(data.length)
            .body(resource);
   }

   // Using HttpServletResponse 아직 안씀
   @GetMapping("/download3")
   public void downloadFile3(HttpServletResponse resonse) throws IOException {
      File file = new File(FILE_PATH);

      resonse.setContentType("image/jpeg");
      resonse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
      BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
      BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
      
      byte[] buffer = new byte[1024];
      int bytesRead = 0;
      while ((bytesRead = inStrem.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
      }
      outStream.flush();
      inStrem.close();
   }
}
