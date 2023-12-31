package org.izy1sky.springboot.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.izy1sky.springboot.annotation.validation.ArticleStateWithEmpty;

@Data
@NoArgsConstructor
public class ArticlePageDTO {
    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;
    private Integer categoryId;
    @ArticleStateWithEmpty
    private String state;
}
