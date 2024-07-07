package com.prajna.mentor_extension.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileFormat {
    private String fileName;
    private String content;
    private ProjectName projectName;
}