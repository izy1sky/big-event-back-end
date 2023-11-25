package org.sunshiyi.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordDTO {
    @NotEmpty
    @JsonProperty("old_pwd")
    private String oldPwd;
    @NotEmpty
    @JsonProperty("new_pwd")
    private String newPwd;
    @NotEmpty
    @JsonProperty("re_pwd")
    private String rePwd;
}
