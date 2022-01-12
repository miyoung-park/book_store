package com.mi.bookvillage.common.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${resources.location}")
    private String location;

    @Value("${resources.server_port}")
    private String server_port;


    public List<FileVO> uploadFiles( List<MultipartFile> files , int bookSeq ) throws IOException {

        List<FileVO> fileList = new ArrayList<>();
        // LOCATION 설정
        String locationPath = location;
        // SAVE_PATH 설정
        String savePath = "/upload" + getSavePath();
        // IMAGE_WEB_PATH 설정
        String imageWebPath = server_port + savePath;

        // 파일 DB에 저장
        if( files != null){
            for( MultipartFile multipartFiles : files ) {
                FileVO fileVO = new FileVO();
                fileVO.setOriginFileName(multipartFiles.getOriginalFilename());
                fileVO.setRenameFileName(UUID.randomUUID().toString()); // unique name
                fileVO.setBookSeq(bookSeq);
                fileVO.setSavePath(savePath);
                fileVO.setImageWebPath(imageWebPath);
                fileList.add(fileVO);

                saveFile(fileVO, multipartFiles);
            }
        }
        return fileList;
    }

    private static String getSavePath(){
        Calendar cal = Calendar.getInstance();
        // 저장경로 설정
        String savePath =  "/"
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
        File file = new File(location + getSavePath() + fileVO.getRenameFileName());
        System.out.println(file);

        if(!file.exists()) { // 예외처리 -- 폴더가 존재하지 않을 경우엔 새로 만들어주기
            new File( location + getSavePath() ).mkdirs();
        }

        //파일객체 한 번에 옮겨주기
        multipartFiles.transferTo(file);

    }

}
