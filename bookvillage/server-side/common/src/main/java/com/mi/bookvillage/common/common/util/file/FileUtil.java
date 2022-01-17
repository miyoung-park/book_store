package com.mi.bookvillage.common.common.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * FileUtil
 * : MultipartFile 객체를 FileVO 로 변환하는 역할
 */
@Slf4j
@Component
public class FileUtil {


    /* @Value("${resources.location}")
    private String location;

    @Value("${resources.server_port}")
    private String server_port; */

    // SAVE_PATH 설정
    String savePath = getSavePath();
    // IMAGE_WEB_PATH 설정
    String Base_Path= "/Users/miyoung/Desktop/ebrainSoft_study/study/personal_project/book_village/bookvillage/front-side/admin-front/public";


    public List<FileVO> uploadFiles( List<MultipartFile> files , int bookSeq ) throws IOException {

        List<FileVO> fileList = new ArrayList<>();
        // LOCATION 설정
        //String locationPath = location;

        // 파일 DB에 저장
        if( files != null){
            for( MultipartFile multipartFiles : files ) {
                FileVO fileVO = new FileVO();
                fileVO.setOriginFileName(multipartFiles.getOriginalFilename());
                fileVO.setRenameFileName(UUID.randomUUID().toString()); // unique name
                fileVO.setBookSeq(bookSeq);
                fileVO.setSavePath(savePath);
                fileList.add(fileVO);

                saveFile(fileVO, multipartFiles);
            }
        }
        return fileList;
    }

    private static String getSavePath(){
        Calendar cal = Calendar.getInstance();
        // 저장경로 설정
        String savePath =  "/images/upload/"
                          + cal.get(Calendar.YEAR)+"/"
                          + (cal.get(Calendar.MONTH) + 1) +"/"
                          +  cal.get(Calendar.DAY_OF_MONTH) +"/";
        return savePath;
    }

    /**
     * 파일 저장
     * @param fileVO
     * @param multipartFiles
     * @throws IOException
     */
    private void saveFile(FileVO fileVO , MultipartFile multipartFiles) throws IOException {
        // 파일로 저장
        File file_admin = new File(Base_Path + getSavePath() + fileVO.getRenameFileName());
        if(!file_admin.exists()) { // 예외처리 -- 폴더가 존재하지 않을 경우엔 새로 만들어주기
            new File( Base_Path + getSavePath() ).mkdirs();
        }

        // 파일로 저장
        File file_user = new File(Base_Path + getSavePath() + fileVO.getRenameFileName());

        if(!file_user.exists()) { // 예외처리 -- 폴더가 존재하지 않을 경우엔 새로 만들어주기
            new File( Base_Path + getSavePath() ).mkdirs();
        }

        //파일객체 한 번에 옮겨주기
        multipartFiles.transferTo(file_admin);

    }

}
