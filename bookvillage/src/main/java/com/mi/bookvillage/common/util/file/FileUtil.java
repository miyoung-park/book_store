package com.mi.bookvillage.common.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    private static final String path = "/Users/miyoung/Desktop/wdfall_study/study/personal_project/book_village/bookvillage/front/public";

    public static List<FileVO> uploadFiles( List<MultipartFile> files , int bookSeq ) throws IOException {

        List<FileVO> fileList = new ArrayList<>();
        // 저장경로 설정
        String savePath = getSavePath();

        if( files.size() > 0 && !files.get(0).getOriginalFilename().equals("")){
            for( MultipartFile multipartFiles : files ) {
                FileVO fileVO = new FileVO();
                fileVO.setOriginFileName(multipartFiles.getOriginalFilename());
                fileVO.setRenameFileName(UUID.randomUUID().toString()); // unique name
                fileVO.setBookSeq(bookSeq);
                fileVO.setSavePath(savePath);
                fileList.add(fileVO);

                // 파일 저장
                saveFile(fileVO, multipartFiles);
            }
        }
        return fileList;
    }

    private static String getSavePath(){
        Calendar cal = Calendar.getInstance();
        // 저장경로 설정
        String savePath = "/images/upload/"
                          +  cal.get(Calendar.YEAR)+"/"
                          + (cal.get(Calendar.MONTH) + 1) +"/"
                          +  cal.get(Calendar.DAY_OF_MONTH) +"/";
        return savePath;
    }

    private static void saveFile(FileVO fileVO , MultipartFile multipartFiles) throws IOException {
        // 파일로 저장
        File file = new File(path + getSavePath() + fileVO.getRenameFileName());

        if(!file.exists()) { // 예외처리 -- 폴더가 존재하지 않을 경우엔 새로 만들어주기
            new File(path + getSavePath()).mkdirs();
        }
        //파일객체 한 번에 옮겨주기
        multipartFiles.transferTo(file);
    }



}
