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

// @Bean, @Component은 스프링 프레임워크에서 객체를 생성하여 사용하고 관리하는 라이브러리를 뜻하는 어노테이션
// @Bean : 스프링 프레임워크 혹은 각종 서드파티 회사에서 제공하는 라이브러리를 사용 시 사용하는 어노테이션
// @Component : 사용자가 직접 생성하고 사용하기 위해서 스프링 프레임워크에 관리를 맡기기 위해서 사용하는 어노테이션
@Component
public class FileUtils {

  public List<BoardFileDto> parseFileInfo(int boardIdx, String createId, MultipartHttpServletRequest multipart) throws Exception {
    if (ObjectUtils.isEmpty(multipart)) {
      return null;
    }

//    파일 정보 리스트 객체 생성
    List<BoardFileDto> fileList = new ArrayList<>();

//    사용자가 지정한 형식으로 날짜 데이터 출력 형식을 설정
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    현재 위치를 기준으로 현재 시간을 가져옴
    ZonedDateTime current = ZonedDateTime.now();

//    저장할 파일 경로 설정, 기본 위치 + 현재 시간을 기준으로 지정된 형식 으로 전체 경로 설정
    String path = "C:/fullstack501/images/" + current.format(format);

//    File 클래스를 통해서 파일 객체 생성
    File file = new File(path);

//    지정한 경로가 존재하는지 확인
    if (file.exists() == false) {
//      지정한 경로가 없을 경우 해당 경로 생성
      file.mkdirs();
    }

//    getFileNames() : 업로드된 파일 정보에서 전체 파일 이름 목록 가져오기
    Iterator<String> iterator = multipart.getFileNames();

    String newFileName; // 파일 이름을 저장할 변수
    String originalFileExtension; // 확장자를 저장할 변수(원본)
    String contentType; // 확장자를 저장할 변수(타겟)

    while (iterator.hasNext()) {
//      파일 이름을 기준으로 파일 정보를 가져옴
      String name = iterator.next();
      List<MultipartFile> fileLists = multipart.getFiles(name);

      for (MultipartFile uploadFile : fileLists) {

        if (uploadFile.isEmpty() == false) {
          contentType = uploadFile.getContentType(); // 파일 타입 가져오기

          if (ObjectUtils.isEmpty(contentType)) {
            break;
          }
          else {
//            파일의 타입에 따라 확장자명 입력하기
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

//          현재 시간을 기준으로 새로운 파일 이름 생성하기
          newFileName = System.nanoTime() + originalFileExtension;

          BoardFileDto boardFile = new BoardFileDto();
          boardFile.setBoardIdx(boardIdx); // 매개변수로 받아온 게시물 번호 저장
          boardFile.setFileSize(uploadFile.getSize());
          boardFile.setOriginalFileName(uploadFile.getOriginalFilename()); // 원본 파일명
//          위에서 생성한 파일 저장 경로 + 현재 시간을 기준으로 새로 만든 파일명 으로 서버에 저장한 파일명 설정
          boardFile.setStoredFileName(path + "/" + newFileName);
          boardFile.setCreateId(createId);

//          만들어진 파일 정보를 위에서 생성한 List<BoardFileDto> 타입의 객체에 데이터 추가
          fileList.add(boardFile);

//          서버에 저장하기 위해서 파일 객체 생성
          file = new File(path + "/" + newFileName);
//          업로드된 파일 정보(메모리상에 존재)를 실제 서버의 지정한 폴더로 복사(물리적 서버의 폴더에 저장)
          uploadFile.transferTo(file);
        }
      }
    }
//    파일 정보 목록을 반환
    return fileList;
  }
}







