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

    @Getter @Setter
    private String renameFileName;

    @Getter @Setter
    private String savePath;


}
