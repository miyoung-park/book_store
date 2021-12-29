package com.mi.bookvillage.common.util.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileVO {

    @Getter @Setter
    private int fileSeq;

    @Getter @Setter
    private int bookSeq;

    @Getter @Setter
    private String originFileName;

    @JsonIgnore
    @Getter @Setter
    private String renameFileName;

    @JsonIgnore
    @Getter @Setter
    private String savePath;

    private String path = "/Users/miyoung/Desktop/wdfall_study/study/personal_project/book_village/resources/upload/";

    public String getFullPath() {
        return path + savePath;
    }
}
