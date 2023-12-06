package com.bitc.board1.service;

import com.bitc.board1.common.FileUtils;
import com.bitc.board1.dto.BoardDto;
import com.bitc.board1.dto.BoardFileDto;
import com.bitc.board1.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

// @Service : 스프링 프레임워크에 해당 파일이 서비스 파일임을 알려주는 어노테이션
@Service
// 구현 클래스 이므로 부모인 서비스 인터페이스를 상속받아 구현해야 함
public class BoardServiceImpl implements BoardService {

  @Autowired
//  데이터 베이스를 사용하기 위한 Mapper 인터페이스
  private BoardMapper boardMapper;

  @Autowired
  private FileUtils fileUtils;

//  상속받아 구현한 메소드
  @Override
  public List<BoardDto> selectBoardList() throws Exception {
    return boardMapper.selectBoardList();
  }

//  글 등록
//  글 정보와 파일 정보를 함께 매개변수로 받아옴
  @Override
  public void insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
//  mapper을 사용하여 데이터 베이스에 글 등록
    boardMapper.insertBoard(board);

////    업로드된 파일 정보에서 데이터 베이스에 저장하기 위한 BoardFileDto 타입으로 변환
////    첫번째 매개변수로 해당 글의 글번호
////    세번째 매개변수로 업로드된 파일 정보(MultipartHttpServletRequest)
    List<BoardFileDto> fileList = fileUtils.parseFileInfo(board.getBoardIdx(), board.getCreateId(), multipart);
//
////    CollectionUtils : 스프링 프레임워크에서 제공하는 컬렉션 타입의 객체를 활용할 수 있는 클래스
    if (CollectionUtils.isEmpty(fileList) == false) {
//      만들어진 파일 정보를 데이터베이스 저장
      boardMapper.insertBoardFileList(fileList);
    }



////    ObjectUtils.isEmpty : 스프링프레임워크에서 제공하는 클래스로 해당 객체가 비었는지 확인함
//    if (ObjectUtils.isEmpty(multipart) == false) {
////    getFileNames() : 가져온 파일 정보에서 모든 파일 이름을 출력
//      Iterator<String> iterator = multipart.getFileNames();
//      String name;
//
//      while (iterator.hasNext()) {
//        name = iterator.next();
////        getFiles() : 가져온 파일 정보에서 지정한 파일 이름을 가지고 있는 파일 객체 정보를 출력
//        List<MultipartFile> fileInfoList = multipart.getFiles(name);
//
//        for (MultipartFile fileInfo : fileInfoList) {
//          System.out.println("\n***** 파일 정보 *****\n");
//          System.out.println("file name : " + fileInfo.getOriginalFilename());
//          System.out.println("file size : " + fileInfo.getSize());
//          System.out.println("file content type : " + fileInfo.getContentType());
//          System.out.println("\n");
//        }
//      }
//    }


  }

//  상세 글 보기
  @Override
  public BoardDto selectBoardDetail(int boardIdx) throws Exception {
//    mapper을 사용하여 해당 글번호의 조회수 증가
    boardMapper.updateHitCount(boardIdx);
//    mapper을 사용하여 데이터 베이스에서 지정한 글의 상세 내용 가져오기
    BoardDto board = boardMapper.selectBoardDetail(boardIdx);
//    현재 글번호를 기준으로 첨부파일 목록을 가져옴
    List<BoardFileDto> boardFileList = boardMapper.selectBoardFileList(boardIdx);
//    가져온 첨부파일 목록을 기존 상세 글 정보에 추가함
    board.setFileList(boardFileList);

    return board;
  }

//  게시글 수정
  @Override
  public void updateBoard(BoardDto board) throws Exception {
//    mapper를 사용하여 데이터 베이스의 내용을 사용자가 입력한 데이터로 수정
    boardMapper.updateBoard(board);
  }

//  게시글 삭제
  @Override
  public void deleteBoard(int boardIdx) throws Exception {
//    mapper를 사용하여 데이터 베이스의 내용을 삭제
    boardMapper.deleteBoard(boardIdx);
  }

  @Override
  public BoardFileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception {
    return boardMapper.selectBoardFileInfo(idx, boardIdx);
  }
}







