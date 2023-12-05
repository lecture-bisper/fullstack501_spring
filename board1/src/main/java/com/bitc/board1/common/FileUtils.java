package com.bitc.board1.common;

import com.bitc.board1.dto.BoardFileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

  public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipart) throws Exception {
    if (ObjectUtils.isEmpty(multipart)) {
      return null;
    }

    List<BoardFileDto> fileList = new ArrayList<>();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ZonedDateTime current = ZonedDateTime.now();

    String path = "C:/fullstack501/images/" + current.format(format);

    File file = new File(path);

    if (file.exists() == false) {
      file.mkdirs();
    }

    Iterator<String> iterator = multipart.getFileNames();

    String newFileName;
    String originalFileExtension;
    String contentType;

    while (iterator.hasNext()) {
      List<MultipartFile> fileLists = multipart.getFiles(iterator.next());

      for (MultipartFile multiFile : fileLists) {
        if (multiFile.isEmpty() == false) {
          contentType = multipart.getContentType();

          if (ObjectUtils.isEmpty(contentType)) {
            break;
          }
          else {
            if (contentType.contains("image/jpeg")) {
              originalFileExtension = ".jpg";
            }
            else if (contentType.contains("image/png")) {
              originalFileExtension = ".png";
            }
            else if (contentType.contains("image/gif")) {
              originalFileExtension = ".gif";
            }
            else {
              break;
            }
          }

          newFileName = System.nanoTime() + originalFileExtension;

          BoardFileDto boardFile = new BoardFileDto();
          boardFile.setBoardIdx(boardIdx);
          boardFile.setFileSize(multiFile.getSize());
          boardFile.setOriginalFileName(multiFile.getOriginalFilename());
          boardFile.setStoredFileName(path + "/" + newFileName);

          file = new File(path + "/" + newFileName);
          multiFile.transferTo(file);
        }
      }
    }
    return fileList;
  }
}







